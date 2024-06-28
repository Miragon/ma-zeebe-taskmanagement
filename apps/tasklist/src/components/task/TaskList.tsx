import Task from "./Task.tsx";
import { Fragment, useEffect, useState } from "react";
import JsonFormRenderer from "../form/JsonFormRenderer.tsx";
import HtmlFormRenderer from "../form/HtmlFormRenderer.tsx";
import { Configuration, LoadUserTaskControllerApi, UserTaskDto } from "../../client/generated/taskmanager";
import { Form, HtmlForm, JsonForm } from "../../model/form.ts";
import { makeStyles } from "@mui/styles";
import { BASE_URL } from "../../config.ts";
import { completeTask, FormType, HtmlFormDto, JsonFormDto, loadForm } from "../../client/process/api.ts";

const useStyles = makeStyles({
    taskList: {
        margin: "0",
        padding: "0",
        listStyle: "none",
    },
    taskContainer: {
        display: "grid",
        gridTemplateColumns: "1fr 1fr",
        gap: "1rem",
    },
});

function TaskList() {
    const [isLoading, setIsLoading] = useState<boolean>(true);
    const [task, setTask] = useState<UserTaskDto | null>(null);
    const [tasks, setTasks] = useState<UserTaskDto[]>([]);
    const [formType, setFormType] = useState<FormType | null>(null);
    const [form, setForm] = useState<Form>({});
    const [completedTask, setCompletedTask] = useState<UserTaskDto | null>(null);

    const classes = useStyles();

    useEffect(() => {
        async function fetchTasks() {
            const config = new Configuration({ basePath: `${BASE_URL}/taskmanager` });
            const taskApi = new LoadUserTaskControllerApi(config);
            const response = await taskApi.loadTasks();
            setTasks(response.data.filter((task) => task.key !== completedTask?.key));
        }

        console.debug("fetchTasks");
        fetchTasks().catch((error) => console.error("Failed to load tasks:", error));
        setIsLoading(false);
    }, [completedTask]);

    function TaskList() {
        if (isLoading) {
            return <p>Loading...</p>;
        } else {
            return (
                <ul className={classes.taskList}>
                    {tasks.map((task) => (
                        <Task key={task.key} task={task} event={getForm} />
                    ))}
                </ul>
            );
        }
    }

    const getForm = async (userTask: UserTaskDto) => {
        setTask(userTask);

        try {
            const { type, form } = await loadForm(
                userTask["bpmnProcessId"],
                userTask["elementId"],
                userTask,
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
        if (!task) {
            console.error("No user task found");
            return;
        }

        try {
            await completeTask(task, data);
        } catch (error) {
            console.error("Failed to complete task:", error);
        }

        setTask(null);
        setFormType(null);
        setForm({});

        setIsLoading(true);
        setCompletedTask(task);
    };

    return (
        <Fragment>
            <div className={classes.taskContainer}>
                <TaskList />
                {formType === "jsonForm" && (
                    <JsonFormRenderer
                        form={form as JsonForm}
                        // userTask={task}
                        submitEvent={submit}
                    />
                )}
                {formType === "htmlForm" && <HtmlFormRenderer form={form as HtmlForm} />}
            </div>
        </Fragment>
    );
}

export default TaskList;
