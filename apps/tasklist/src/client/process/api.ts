import { UserTaskDto } from "../generated/taskmanager";
import axios, { AxiosRequestConfig, RawAxiosRequestHeaders } from "axios";
import { BASE_URL } from "../../config.ts";

export enum FormType {
    JSON_FROM = "jsonForm",
    HTML = "htmlForm",
}

export interface FormDto {
    type: FormType;
    form: JsonFormDto | HtmlFormDto;
}

export interface JsonFormDto {
    schema: string;
    uischema: string;
    data: any;
}

export interface HtmlFormDto {
    html: string;
    data: any;
}

export interface CompleteTaskDto {
    taskId: number;
}

export interface StartProcessDto {
    returnMessage: string;
}

export interface ProcessDefinitionDto {
    id: string;
    name: string;
    startable: boolean;
    schema?: string;
}

const client = axios.create({
    baseURL: BASE_URL,
});

const config: AxiosRequestConfig = {
    headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
    } as RawAxiosRequestHeaders,
};

/**
 * Load the form for the given user task.
 * (/rest/task/{elementId})
 * @param bpmnProcessId
 * @param elementId
 * @param userTask
 * @returns The form type and the form object
 */
export async function loadForm(
    bpmnProcessId: string,
    elementId: string,
    userTask: UserTaskDto,
): Promise<FormDto> {
    const query = `/${bpmnProcessId}/rest/task/${elementId}`;
    const response = await client.post(query, userTask, config);
    const form = response.data;

    console.debug("loadForm", form);

    if (response.status !== 200) {
        throw new Error(`Failed to start process: ${response.statusText}`);
    }

    switch ("schema" in form) {
        case true:
            return {
                type: FormType.JSON_FROM,
                form,
            };
        case false:
            return {
                type: FormType.HTML,
                form,
            };
        default:
            throw new Error("Invalid form type");
    }
}

/**
 * Complete the given user task.
 * (/rest/task/complete/{elementId})
 * @param userTask
 * @param formData
 * @returns The response data
 */
export async function completeTask(
    userTask: UserTaskDto,
    formData: any,
): Promise<CompleteTaskDto> {
    const data = {
        userTask,
        formData,
    };
    const query = `/${userTask.bpmnProcessId}/rest/task/complete/${userTask.elementId}`;
    const response = await client.post(query, data, config);
    const taskId = response.data;

    console.debug("completeTask", data, taskId);

    if (response.status !== 200) {
        throw new Error(`Failed to start process: ${response.statusText}`);
    }

    return taskId;
}

/**
 * Start a new process instance.
 * (/rest/process/start)
 * @param bpmnProcessId
 * @param variables
 * @returns The response data
 */
export async function startProcess(
    bpmnProcessId: string,
    variables: Map<string, any>,
): Promise<StartProcessDto> {
    const query = `/${bpmnProcessId}/rest/process/start`;
    const response = await client.post(query, variables, config);
    const processInstanceId = response.data;

    console.debug("startProcess", bpmnProcessId, processInstanceId);

    if (response.status !== 200) {
        throw new Error(`Failed to start process: ${response.statusText}`);
    }

    return processInstanceId;
}

/**
 * Get the metadata of all registered microservices.
 * @returns The response data
 */
export async function loadProcessDefinitions(): Promise<ProcessDefinitionDto[]> {
    const query = `/getMetadata`;
    const response = await client.get(query, config);
    const processDefinitions = response.data;

    console.debug("loadProcessDefinitions", processDefinitions);

    if (response.status !== 200) {
        throw new Error(`Failed to start process: ${response.statusText}`);
    }

    return processDefinitions;
}
