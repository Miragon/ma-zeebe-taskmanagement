/**
 * This is the interface for the data that is sent from the iframe
 * to the parent window and vice versa.
 */
export interface IFrameEvent<T> {
    type: TasklistEventType;
    data?: T;
}

export enum TasklistEventType {
    FORM_DATA_EVENT = "FormDataEvent",
    UPDATE_EVENT = "UpdateEvent",
    SUBMIT_EVENT = "SubmitEvent",
}

export function postMessage(message: IFrameEvent<any>) {
    window.parent.postMessage(
        message,
        "*",
    );
}