import { ProcessApplication } from "./client/generated/taskmanager";

export const BASE_URL: string = import.meta.env.VITE_BASE_URL;

export function setProcessApplications(applications: ProcessApplication[]) {
    processApplications = applications;
}

export function getAllProcessApplications(): ProcessApplication[] {
    return processApplications;
}

export function getMetadataByProcessId(processId: string): ProcessApplication {
    const processApplication = processApplications.find((application) => application.processId === processId);

    if (!processApplication) {
        throw new Error(`Process application with ID ${processId} not found`);
    }

    return processApplication;
}

export function getUrlByType(type: UrlType, processId: string): string {
    const processApplication = getMetadataByProcessId(processId);

    switch (type) {
        case UrlType.PROCESS_START:
            return processApplication.startProcessUrl;
        case UrlType.PROCESS_START_FORM:
            return processApplication.startProcessFormUrl;
        case UrlType.LOAD_TASK:
            return processApplication.loadTaskUrl;
        case UrlType.UPDATE_TASK:
            return processApplication.updateTaskUrl;
        case UrlType.COMPLETE_TASK:
            return processApplication.completeTaskUrl;
    }
}

export function isProcessStartable(processId: string): boolean {
    return getMetadataByProcessId(processId).startable;
}

export enum UrlType {
    PROCESS_START,
    PROCESS_START_FORM,
    LOAD_TASK,
    UPDATE_TASK,
    COMPLETE_TASK,
}

let processApplications: ProcessApplication[] = [];

