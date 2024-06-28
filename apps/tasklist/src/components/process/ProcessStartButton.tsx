import { Fragment } from "react";
import { Button } from "@mui/material";
import SendIcon from "@mui/icons-material/Send";
import { makeStyles } from "@mui/styles";

interface Props {
    id: string;
    label: string;
    onClick: (id: string) => void;
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
        props.onClick(props.id);
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
