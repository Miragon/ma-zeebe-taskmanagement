import {useEffect, useState} from 'react'
import {IFrameEvent, postMessage, TasklistEventType} from "./tasklist.ts";

import './App.css'
import OrderStepper from "./components/OrderStepper.tsx";
import OrderOverview from "./components/OrderOverview.tsx";

interface FormProps {
    elementId: string;
    data: any;
    variables?: Map<string, any>;
}

function App() {
    const [formProps, setFormProps] = useState<FormProps>();

    useEffect(() => {
        const handleMessageEvent = (event: MessageEvent<IFrameEvent<any>>) => {
            const {type, userTask, data} = event.data;
            const {elementId, variables} = userTask;

            console.debug("Received message", type, data);

            switch (type) {
                case "FormDataEvent": {
                    setFormProps({
                        elementId,
                        variables,
                        data
                    });
                    break;
                }
            }
        }

        window.addEventListener("message", handleMessageEvent);
        postMessage({type: TasklistEventType.FORM_DATA_EVENT});

        return () => {
            window.removeEventListener("message", handleMessageEvent);
        }
    }, []);

    const Form = (props: FormProps) => {
        const {elementId, variables, data} = props;
        switch (elementId) {
            case "OrderReceived": {
                return <OrderStepper items={data}/>
            }
            case "CheckOrder": {
                const orderId = variables?.get("orderId") as string;
                return <OrderOverview order={data} orderId={orderId} checkable={true}/>
            }
            default: {
                return <div>Form not found</div>
            }
        }
    }

    return (
        <>
            {formProps
                ? <Form elementId={formProps.elementId} variables={formProps.variables} data={formProps.data}/>
                : <div>Loading...</div>
            }
        </>
    )
}

export default App
