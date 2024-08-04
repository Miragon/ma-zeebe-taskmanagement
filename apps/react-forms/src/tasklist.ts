/**
 * This is the interface for the data that is sent from the iframe
 * to the parent window and vice versa.
 */
export interface IFrameEvent<T> {
    type: TasklistEventType;
    userTask?: any;
    data?: T;
}

export enum TasklistEventType {
    FORM_DATA_EVENT = "FormDataEvent",
    UPDATE_EVENT = "UpdateEvent",
    SUBMIT_EVENT = "SubmitEvent",
}

export function postMessage(message: IFrameEvent<any>) {
    if (import.meta.env.DEV) {
        mockPostMessage(message);
        return;
    }

    window.parent.postMessage(
        message,
        "*",
    );
}

function mockPostMessage(message: IFrameEvent<any>) {
    switch (message.type) {
        case TasklistEventType.FORM_DATA_EVENT:
            console.debug("Ask for data");
            window.dispatchEvent(new MessageEvent("message", {
                data: {
                    type: TasklistEventType.FORM_DATA_EVENT,
                    userTask: {
                        elementId: "OrderReceived",
                    },
                    data: [
                        {
                            id: 1,
                            name: "Item 1",
                            price: 100,
                            image: "https://via.placeholder.com/300"
                        },
                        {
                            id: 2,
                            name: "Item 2",
                            price: 200,
                            image: "https://via.placeholder.com/150"
                        },
                    ]
                }
            }));
            break;
        case TasklistEventType.UPDATE_EVENT:
            console.debug("Received update event", message.data);
            break;
        case TasklistEventType.SUBMIT_EVENT:
            console.debug("Received submit event", message.data);
            break;
    }
}