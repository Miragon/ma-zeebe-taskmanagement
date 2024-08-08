import "./App.css";
import { useEffect, useState } from "react";
import { tss } from "tss-react/mui";
import { CheckOrderDto, FormData, LoadItemsDto } from "./api";
import { MessageReceiveEvent, postMessage, TasklistEventType, validateReceivedMessage } from "./tasklist.ts";
import { Item, Order, PersonalInformation } from "./domain";

import OrderStepper from "./components/OrderStepper.tsx";
import OrderOverview from "./components/OrderOverview.tsx";
import { Alert } from "@mui/material";

interface FormProps {
    formData: FormData;
    elementId: string;
    variables?: Map<string, any>;
    updatable?: boolean;
}

const useStyles = tss.create({
    root: {
        height: "100vh",
        display: "flex",
        flexDirection: "column",
        alignItems: "flex-start",
    },
});

function App() {
    const [formProps, setFormProps] = useState<FormProps>();
    const [error, setError] = useState<string | undefined>(undefined);

    const { classes } = useStyles();

    useEffect(() => {
        const handleMessageEvent = (event: MessageEvent<MessageReceiveEvent>) => {
            try {
                validateReceivedMessage(event.data);
            } catch (error) {
                setError((error as Error).message);
                return;
            }

            const { type, bpmnElement, formData, updatable } = event.data;
            const { elementId, variables } = bpmnElement;

            switch (type) {
                case TasklistEventType.FORM_DATA_EVENT: {
                    setFormProps({
                        formData,
                        elementId,
                        variables: variables ? new Map(Object.entries(variables)) : undefined,
                        updatable,
                    });
                    break;
                }
            }
        };

        window.addEventListener("message", handleMessageEvent);
        postMessage({
            type: TasklistEventType.FORM_DATA_EVENT,
        });

        return () => {
            window.removeEventListener("message", handleMessageEvent);
        };
    }, []);

    const Form = (props: FormProps) => {
        const { elementId, variables, formData, updatable } = props;
        switch (elementId) {
            case "StartEvent": {
                const data = formData as LoadItemsDto;
                // TODO: use types generated from openapi generator
                const items = data.items.map((item) => {
                    return new Item({
                        id: item.id,
                        name: item.name,
                        price: item.price,
                        image: item.image,
                    });
                });
                return (
                    <div className={classes.root}>
                        <OrderStepper items={items} />
                    </div>
                );
            }
            case "CheckOrder": {
                const data = formData as CheckOrderDto;
                // TODO: use types generated from openapi generator
                const orderId = variables?.get("orderId") as string;
                const order = new Order({
                    personalInformation: new PersonalInformation({
                        firstname: data.firstname,
                        lastname: data.lastname,
                        email: data.email,
                        street: data.street,
                        city: data.city,
                        zip: data.zip,
                    }),
                    items: data.items.map((item) => {
                        return new Item({
                            id: item.id,
                            name: item.name,
                            price: item.price,
                            image: item.image,
                        });
                    }),
                });
                return (
                    <div className={classes.root}>
                        <OrderOverview
                            orderId={orderId}
                            formData={order}
                            updatable={updatable ?? false}
                            checkable={true}
                        />
                    </div>
                );
            }
            default: {
                return <div>Form not found</div>;
            }
        }
    };

    return (
        <>
            {formProps
                ? <Form elementId={formProps.elementId} variables={formProps.variables} formData={formProps.formData} />
                : { error } ? <Alert severity="error">{error}</Alert> : <div>Loading...</div>
            }
        </>
    );
}

export default App;
