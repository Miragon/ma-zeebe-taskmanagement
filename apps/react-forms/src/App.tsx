import {useEffect, useState} from 'react'
import {MessageReceiveEvent, postMessage, TasklistEventType} from "./tasklist.ts";

import './App.css'
import OrderStepper from "./components/OrderStepper.tsx";
import OrderOverview from "./components/OrderOverview.tsx";
import {Item, Order, PersonalInformation} from "./domain";
import {tss} from "tss-react/mui";

interface FormProps {
    elementId: string;
    formData: any;
    variables?: Map<string, any>;
    updatable?: boolean;
}

const useStyles = tss.create({
    root: {
        height: "100vh",
        display: "flex",
        flexDirection: "column",
        alignItems: "flex-start"
    },
})

function App() {
    const [formProps, setFormProps] = useState<FormProps>();

    const {classes} = useStyles();

    useEffect(() => {
        const handleMessageEvent = (event: MessageEvent<MessageReceiveEvent>) => {
            const {type, bpmnElement, formData, updatable} = event.data;
            const {elementId, variables} = bpmnElement;

            console.debug("Received message", type, formData);

            switch (type) {
                case "FormDataEvent": {
                    setFormProps({
                        elementId,
                        variables: variables ? new Map(Object.entries(variables)) : undefined,
                        formData,
                        updatable
                    });
                    break;
                }
            }
        }

        window.addEventListener("message", handleMessageEvent);
        postMessage({
            type: TasklistEventType.FORM_DATA_EVENT,
        });

        return () => {
            window.removeEventListener("message", handleMessageEvent);
        }
    }, []);

    const Form = (props: FormProps) => {
        const {elementId, variables, formData, updatable} = props;
        switch (elementId) {
            case "StartEvent": {
                // TODO: use types generated from openapi generator
                const items = formData.items.map((item: any) => {
                    return new Item({
                        id: item.id,
                        name: item.name,
                        price: item.price,
                        image: item.image
                    })
                })
                return (
                    <div className={classes.root}>
                        <OrderStepper items={items}/>
                    </div>
                )
            }
            case "CheckOrder": {
                // TODO: use types generated from openapi generator
                const orderId = variables?.get("orderId") as string;
                const order = new Order({
                    personalInformation: new PersonalInformation({
                        firstname: formData.firstname,
                        lastname: formData.lastname,
                        email: formData.email,
                        street: formData.address.street,
                        city: formData.address.city,
                        zip: formData.address.zip
                    }),
                    items: formData.items.map((item: any) => {
                        return new Item({
                            id: item.id,
                            name: item.name,
                            price: item.price,
                            image: item.image
                        })
                    }),
                })
                return (
                    <div className={classes.root}>
                        <OrderOverview
                            orderId={orderId}
                            formData={order}
                            updatable={updatable ?? false}
                            checkable={true}
                        />
                    </div>
                )
            }
            default: {
                return <div>Form not found</div>
            }
        }
    }

    return (
        <>
            {formProps
                ? <Form elementId={formProps.elementId} variables={formProps.variables} formData={formProps.formData}/>
                : <div>Loading...</div>
            }
        </>
    )
}

export default App
