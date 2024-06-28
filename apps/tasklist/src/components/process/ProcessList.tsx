import { Fragment, useEffect, useRef, useState } from "react";
import { makeStyles } from "@mui/styles";
import ProcessStartButton from "./ProcessStartButton.tsx";
import { getGlobalProcessDefinitions, Process } from "../../model/process.ts";
import { FormType, HtmlFormDto, JsonFormDto, loadForm, startProcess } from "../../client/process/api.ts";
import { Form, HtmlForm, JsonForm } from "../../model/form.ts";
import JsonFormRenderer from "../form/JsonFormRenderer.tsx";
import HtmlFormRenderer from "../form/HtmlFormRenderer.tsx";

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
    const [process, setProcess] = useState<string>("");
    const [processDefinitions, setProcessDefinitions] = useState<Process[]>([]);
    const [formType, setFormType] = useState<FormType | null>(null);
    const [form, setForm] = useState<Form>({});
    const initialized = useRef(false);

    const classes = useStyles();

    useEffect(() => {
        if (!initialized.current) {
            initialized.current = true;
            setProcessDefinitions(getGlobalProcessDefinitions);
            setIsLoading(false);
        }
    }, [initialized]);

    const getForm = async (id: string) => {
        setProcess(id);

        try {
            const { type, form } = await loadForm(
                id,
                "processStart",
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
        try {
            await startProcess(process, data);
        } catch (error) {
            console.error("Failed to complete task:", error);
        }
    };

    function ProcessList() {
        if (isLoading) {
            return <p>Loading...</p>;
        } else {
            const buttons = processDefinitions.map((process) => {
                if (process.isStartable()) {
                    return (
                        <ProcessStartButton
                            key={process.getId()}
                            id={process.getId()}
                            label={process.getName()}
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
