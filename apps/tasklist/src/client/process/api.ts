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
    uiSchema: string;
    updatable: boolean;
    formData: any;
}

export interface HtmlFormDto {
    html: string;
    updatable: boolean;
    formData: any;
}

export interface CompleteTaskDto {
    taskId: number;
}

export interface StartProcessDto {
    message: string;
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
 * @param url
 * @param userTask
 * @returns The form type and the form object
 */
export async function loadTask(
    url: string,
    userTask: UserTaskDto,
): Promise<FormDto> {
    const response = await client.post(url, userTask, config);
    const form = response.data;

    console.debug("loadForm", form);

    if (response.status !== 200) {
        throw new Error(`Failed to start process: ${response.statusText}`);
    }

    return getFormType(form);
}

export async function updateTask(
    url: string,
    userTask: UserTaskDto,
    formData: any,
): Promise<CompleteTaskDto> {
    const data = {
        userTask,
        formData,
    };
    const response = await client.post(url, data, config);
    const taskId = response.data;

    console.debug("completeTask", data, taskId);

    if (response.status !== 200) {
        throw new Error(`Failed to start process: ${response.statusText}`);
    }

    return taskId;
}

/**
 * Complete the given user task.
 * (/rest/task/complete/{elementId})
 * @param url
 * @param userTask
 * @param formData
 * @returns The response data
 */
export async function completeTask(
    url: string,
    userTask: UserTaskDto,
    formData: any,
): Promise<CompleteTaskDto> {
    const data = {
        userTask,
        formData,
    };
    const response = await client.post(url, data, config);
    const taskId = response.data;

    console.debug("completeTask", data, taskId);

    if (response.status !== 200) {
        throw new Error(`Failed to start process: ${response.statusText}`);
    }

    return taskId;
}

export async function loadStartForm(url: string): Promise<FormDto> {
    const response = await client.get(url, config);
    const form = response.data;

    console.debug("loadStartForm", form);

    if (response.status !== 200) {
        throw new Error(`Failed to load start form: ${response.statusText}`);
    }

    return getFormType(form);
}

/**
 * Start a new process instance.
 * (/rest/process/start)
 * @param url
 * @param variables
 * @returns The response data
 */
export async function startProcess(
    url: string,
    variables: any,
): Promise<StartProcessDto> {
    const response = await client.post(url, variables, config);
    const processInstanceId = response.data;

    console.debug("startProcess", processInstanceId);

    if (response.status !== 200) {
        throw new Error(`Failed to start process: ${response.statusText}`);
    }

    return processInstanceId;
}

function getFormType(form: any): FormDto {
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