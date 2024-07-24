import {useEffect, useState} from 'react'
import { postMessage, TasklistEventType } from "./tasklist.ts";
import { IFrameEvent } from "./tasklist.ts";
import InvoiceForm from "./components/InvoiceForm.tsx";

import "./components/InvoiceForm.tsx"
import './App.css'

function App() {
    const [formData, setFormData] = useState<any>();

    useEffect(() => {
        const handleMessageEvent = (event: MessageEvent<IFrameEvent<any>>) => {
            const { type, data } = event.data;

            switch (type) {
                case "FormDataEvent": {
                    setFormData(data);
                    break;
                }
            }
        }

        window.addEventListener("message", handleMessageEvent);
        postMessage({ type: TasklistEventType.FORM_DATA_EVENT });

        return () => {
            window.removeEventListener("message", handleMessageEvent);
        }
    }, []);


    return (
        <>
            { formData && (
                <InvoiceForm
                    id={formData.id}
                    date={formData.date}
                    amount={formData.amount}
                />
            )}
        </>
    )
}

export default App
