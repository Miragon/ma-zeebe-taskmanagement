import { Fragment, useEffect, useRef, useState } from "react";
import { makeStyles } from "@mui/styles";
import ProcessStartButton from "./ProcessStartButton.tsx";
import { FormType, HtmlFormDto, JsonFormDto, loadStartForm, startProcess } from "../../client/process/api.ts";
import { Form, HtmlForm, JsonForm } from "../../model/form.ts";
import JsonFormRenderer from "../form/JsonFormRenderer.tsx";
import HtmlFormRenderer from "../form/HtmlFormRenderer.tsx";
import { getAllProcessApplications, getUrlByType, UrlType } from "../../config.ts";
import { ProcessApplication } from "../../client/generated/taskmanager";

const useStyles = makeStyles({
    processContainer: {
        display: "grid",
        gridTemplateColumns: "1fr 1fr",
        gap: "1rem",
    },
    processDefinitionList: {
        margin: "0",
        padding: "0",
        listStyle: "none",
    },
});


function ProcessList() {
    const [isLoading, setIsLoading] = useState<boolean>(true);
    const [processId, setProcessId] = useState<string>("");
    const [processApplications, setProcessApplications] = useState<ProcessApplication[]>([]);
    const [formType, setFormType] = useState<FormType | null>(null);
    const [form, setForm] = useState<Form>({});
    const initialized = useRef(false);

    const classes = useStyles();

    useEffect(() => {
        if (!initialized.current) {
            initialized.current = true;
            setProcessApplications(getAllProcessApplications);
            setIsLoading(false);
        }
    }, [initialized]);

    const getForm = async (id: string) => {
        setProcessId(id);

        const url = getUrlByType(UrlType.PROCESS_START_FORM, id);

        try {
            const { type, form } = await loadStartForm(
                url,
            );

            switch (type) {
                case FormType.JSON_FROM: {
                    const jsonForm = form as JsonFormDto;
                    setFormType(FormType.JSON_FROM);
                    setForm(new JsonForm({ ...jsonForm }));
                    return;
                }
                case FormType.HTML: {
                    const htmlForm = form as HtmlFormDto;
                    setFormType(FormType.HTML);
                    setForm(new HtmlForm({ ...htmlForm }));
                    return;
                }
            }
        } catch (error) {
            console.error("Failed to load form:", error);
        }
    };

    const submit = async (data: any) => {
        const url = getUrlByType(UrlType.PROCESS_START, processId);

        try {
            await startProcess(url, data);
        } catch (error) {
            console.error("Failed to complete task:", error);
        }
    };

    function ProcessList() {
        if (isLoading) {
            return <p>Loading...</p>;
        } else {
            const buttons = processApplications.map((process) => {
                if (process.startable) {
                    return (
                        <ProcessStartButton
                            key={process.processId}
                            id={process.processId}
                            label={process.label}
                            onClick={getForm}
                        />
                    );
                } else {
                    return null;
                }
            });

            return <ul className={classes.processDefinitionList}>{buttons}</ul>;
        }
    }

    return (
        <Fragment>
            <div className={classes.processContainer}>
                <ProcessList />
                {formType === "jsonForm" && (
                    <JsonFormRenderer
                        form={form as JsonForm}
                        submitEvent={submit}
                    />
                )}
                {formType === "htmlForm" && <HtmlFormRenderer form={form as HtmlForm} />}
            </div>
        </Fragment>
    );
}

export default ProcessList;
