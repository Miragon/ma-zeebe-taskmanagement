import { Fragment, useEffect, useRef } from "react";
import { makeStyles } from "@mui/styles";
import { Grid } from "@mui/material";
import { HtmlForm } from "../../model";

const useStyles = makeStyles({
    container: {
        padding: "1em",
        width: "100%",
    },
    demoform: {
        margin: "auto",
        padding: "1rem",
    },
    iframe: {
        width: "100%",
        height: "100vh",
        border: "none",
    },
});

interface Props {
    form: HtmlForm;
    bpmnElement: { elementId?: string; variables?: { [key: string]: any } };
    submitEvent: (formData: any) => void;
    updateEvent?: (formData: any) => void;
}

interface IFrameEvent {
    type: IFrameEventType;
    formData: any;
}

enum IFrameEventType {
    FORM_DATA_EVENT = "FormDataEvent",
    UPDATE_EVENT = "UpdateEvent",
    SUBMIT_EVENT = "SubmitEvent",
}

function HtmlFormRenderer(props: Props) {
    const { form, bpmnElement, submitEvent, updateEvent } = props;

    const iframeRef = useRef<HTMLIFrameElement>(null);

    const classes = useStyles();

    useEffect(() => {
        const handleMessageEvent = (event: MessageEvent<IFrameEvent>) => {
            const { type, formData } = event.data;

            switch (type) {
                case IFrameEventType.FORM_DATA_EVENT: {
                    iframeRef.current?.contentWindow?.postMessage(
                        {
                            type: IFrameEventType.FORM_DATA_EVENT,
                            bpmnElement,
                            formData: form.getFormData(),
                            updatable: form.getUpdatable(),
                        },
                        "*",
                    );
                    break;
                }
                case IFrameEventType.UPDATE_EVENT: {
                    updateEvent?.(formData);
                    break;
                }
                case IFrameEventType.SUBMIT_EVENT: {
                    submitEvent(formData);
                    break;
                }
            }
        };

        window.addEventListener("message", handleMessageEvent);

        if (!bpmnElement.elementId) {
            console.error("No elementId found is undefined. This may cause problems with your form.");
        }

        return () => {
            window.removeEventListener("message", handleMessageEvent);
        };
    }, []);

    return (
        <Fragment>
            <Grid
                container
                justifyContent={"center"}
                spacing={1}
                className={classes.container}
            >
                <iframe className={classes.iframe} srcDoc={form.getHtml()} ref={iframeRef} />
            </Grid>
        </Fragment>
    );
}

export default HtmlFormRenderer;
