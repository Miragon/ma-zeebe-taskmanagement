import { Fragment, useEffect, useState } from "react";
import { JsonSchema, UISchemaElement } from "@jsonforms/core";
import { JsonForms } from "@jsonforms/react";
import { materialCells, materialRenderers } from "@jsonforms/material-renderers";
import { Button, Grid } from "@mui/material";
import { makeStyles } from "@mui/styles";
import { JsonForm } from "../../model/form.ts";
import SendIcon from "@mui/icons-material/Send";

interface Props {
    form: JsonForm;
    // userTask: UserTaskDto | null;
    submitEvent: (formData: any) => void;
}

const useStyles = makeStyles({
    container: {
        width: "100%",
    },
    title: {
        textAlign: "center",
        padding: "0.25em",
    },
    demoform: {
        margin: "auto",
        padding: "1rem",
    },
    submitButton: {
        width: "100%",
        margin: "auto",
        marginTop: "1rem",
    },
});

function JsonFormRenderer(props: Props) {
    const [schema, setSchema] = useState<JsonSchema | undefined>();
    const [uiSchema, setUiSchema] = useState<UISchemaElement | undefined>();
    const [data, setData] = useState<any>({});
    const [formError, setFormError] = useState<boolean>(true);

    const classes = useStyles();
    const renderers = [...materialRenderers];

    useEffect(() => {
        const schema = props.form.getSchema();
        const uiSchema = props.form.getUiSchema();
        const data = props.form.getFormData();
        setSchema(schema);
        setUiSchema(uiSchema);
        setData(data);
    }, [props.form]);

    function handleFormChange({ errors, data }: any) {
        setFormError(errors.length > 0);
        setData(data);
    }

    async function submit() {
        props.submitEvent(data);
    }

    return (
        <Fragment>
            <Grid
                container
                justifyContent={"center"}
                spacing={1}
                className={classes.container}
            >
                <Grid item>
                    <div className={classes.demoform}>
                        <JsonForms
                            schema={schema}
                            uischema={uiSchema}
                            data={data}
                            renderers={renderers}
                            cells={materialCells}
                            onChange={({ errors, data }) =>
                                handleFormChange({ errors, data })
                            }
                        />
                    </div>
                    <Button
                        className={classes.submitButton}
                        variant="contained"
                        color="primary"
                        size="large"
                        endIcon={<SendIcon />}
                        disabled={formError}
                        onClick={submit}
                    >
                        Submit
                    </Button>
                </Grid>
            </Grid>
        </Fragment>
    );
}

export default JsonFormRenderer;
