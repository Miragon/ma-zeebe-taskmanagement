import { Fragment, useEffect, useRef, useState } from "react";
import { makeStyles } from "@mui/styles";
import ProcessStartButton from "./ProcessStartButton.tsx";
import { getGlobalProcessDefinitions, Process } from "../../model/process.ts";

const useStyles = makeStyles({
    processDefinitionList: {
        margin: "0",
        padding: "0",
        listStyle: "none",
    },
});

function ProcessList() {
    const [isLoading, setIsLoading] = useState<boolean>(true);
    const [processDefinitions, setProcessDefinitions] = useState<Process[]>([]);
    const initialized = useRef(false);

    const classes = useStyles();

    useEffect(() => {
        if (!initialized.current) {
            initialized.current = true;
            setProcessDefinitions(getGlobalProcessDefinitions);
            setIsLoading(false);
        }
    }, [initialized]);

    function ProcessDefinitionList() {
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
            <ProcessDefinitionList />
        </Fragment>
    );
}

export default ProcessList;
