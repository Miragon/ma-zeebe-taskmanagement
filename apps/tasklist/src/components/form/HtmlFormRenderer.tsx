import { makeStyles } from "@mui/styles";
import { Grid } from "@mui/material";
import { Fragment, useEffect, useRef } from "react";
import { HtmlForm } from "../../model/form.ts";

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
        height: "100%",
        border: "none",
    },
});

interface Props {
    form: HtmlForm;
    submitEvent: (formData: any) => void;
    updateEvent?: (formData: any) => void;
}

function HtmlFormRenderer(props: Props) {
    const iframeRef = useRef<HTMLIFrameElement>(null);

    const classes = useStyles();

    useEffect(() => {
        const handleMessageEvent = (event: MessageEvent) => {
            const { type, data } = event.data;

            switch (type) {
                case "FormDataEvent": {
                    iframeRef.current?.contentWindow?.postMessage(
                        {
                            type: "FormDataEvent",
                            data: props.form.getFormData(),
                        },
                        "*",
                    );
                    break;
                }
                case "UpdateEvent": {
                    props.updateEvent?.(data);
                    break;
                }
                case "SubmitEvent": {
                    props.submitEvent(data);
                    break;
                }
            }
        };

        window.addEventListener("message", handleMessageEvent);

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
                <iframe className={classes.iframe} srcDoc={props.form.getHtml()} ref={iframeRef} />
            </Grid>
        </Fragment>
    );
}

export default HtmlFormRenderer;
