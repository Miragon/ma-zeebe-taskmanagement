import { Fragment } from "react";
import { Button } from "@mui/material";
import SendIcon from "@mui/icons-material/Send";
import { makeStyles } from "@mui/styles";
import { startProcess } from "../../client/process/api.ts";
import { ORDER } from "../../mocks.ts";

interface Props {
    id: string;
    label: string;
}

const useStyles = makeStyles({
    listItem: {
        width: "100%",
        margin: "auto",
        marginBottom: "1rem",
    },
    button: {
        width: "100%",
    },
});

function ProcessStartButton(props: Props) {
    const classes = useStyles();

    const handleButtonClick = async () => {
        try {
            // TODO: Replace ORDER with the actual data
            const variables = new Map([["order", ORDER]]);
            await startProcess(props.id, variables);
        } catch (error) {
            console.error("Failed to start process:", error);
        }
    };

    return (
        <Fragment>
            <li className={classes.listItem}>
                <Button
                    className={classes.button}
                    variant="outlined"
                    color="primary"
                    size="large"
                    endIcon={<SendIcon />}
                    onClick={handleButtonClick}
                >
                    {props.label}
                </Button>
            </li>
        </Fragment>
    );
}

export default ProcessStartButton;
