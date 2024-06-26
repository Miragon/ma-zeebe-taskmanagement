import { makeStyles } from "@mui/styles";
import { Grid } from "@mui/material";
import { Fragment } from "react";
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
}

function HtmlFormRenderer(props: Props) {
    const classes = useStyles();

    return (
        <Fragment>
            <Grid
                container
                justifyContent={"center"}
                spacing={1}
                className={classes.container}
            >
                <iframe className={classes.iframe} srcDoc={props.form.getHtml()} />
            </Grid>
        </Fragment>
    );
}

export default HtmlFormRenderer;
