import { Card, CardContent, Typography } from "@mui/material";
import { tss } from "tss-react/mui";
import { UserTaskDto } from "../../client/generated/taskmanager";

const useStyles = tss.create({
    task: {
        margin: "1rem",
        cursor: "pointer",
    },
});

interface Props {
    event: (task: UserTaskDto) => void;
    task: UserTaskDto;
}

function Task(props: Props) {
    const { classes } = useStyles();

    return (
        <li className={classes.task}>
            <Card>
                <CardContent>
                    <div onClick={() => props.event(props.task)}>
                        <Typography>
                            {props.task.elementId} ({props.task.key})
                        </Typography>
                        <Typography>
                            {props.task.bpmnProcessId} ({props.task.processInstanceKey})
                        </Typography>
                    </div>
                </CardContent>
            </Card>
        </li>
    );
}

export default Task;
