import Task from "./Task.tsx";
import { Fragment, useEffect, useState } from "react";
import JsonFormRenderer from "../form/JsonFormRenderer.tsx";
import HtmlFormRenderer from "../form/HtmlFormRenderer.tsx";
import { LoadUserTaskControllerApi } from "../../client/generated/taskmanager";
import { FormProps, FormType, getFormType, HtmlForm, JsonForm } from "../../model/form.ts";
import { makeStyles } from "@mui/styles";
import { getUrlByType, taskManagerConfig, UrlType } from "../../config.ts";
import { CompleteTaskControllerApi, LoadTaskControllerApi } from "../../client/process";
import { UserTask } from "../../model/UserTask.ts";
import { AxiosRequestConfig } from "axios";
import { JsonFormDto } from "../../client/generated/processModels/models/JsonFormDto.ts";
import { HtmlFormDto } from "../../client/generated/processModels/models/HtmlFormDto.ts";
import { Snackbar, SnackbarProps } from "@mui/material";

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
    const [tasks, setTasks] = useState<UserTask[]>([]);
    const [task, setTask] = useState<UserTask | null>(null);
    const [completedTask, setCompletedTask] = useState<UserTask | null>(null);
    const [form, setForm] = useState<FormProps | null>(null);
    const [snackbarProps, setSnackbarProps] = useState<SnackbarProps>({
        open: false,
        message: "",
    });

    const classes = useStyles();

    useEffect(() => {
        async function fetchTasks(): Promise<UserTask[]> {
            const api = new LoadUserTaskControllerApi(taskManagerConfig);
            const response = await api.loadTasks();

            return response.data.map((task) => new UserTask({ ...task }));
        }

        fetchTasks()
            .then((tasks) => {
                setTasks(tasks.filter((task) => task.key !== completedTask?.key));
            })
            .catch((error) => console.error("Failed to load tasks:", error));

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

    const getForm = async (userTask: UserTask) => {
        setTask(userTask);

        const api = new LoadTaskControllerApi();
        const config: AxiosRequestConfig = {
            url: getUrlByType(UrlType.LOAD_TASK, userTask.bpmnProcessId),
        };

        try {
            const response = await api.loadData(userTask, config);
            const form = response.data;

            console.debug("Form loaded:", form);

            switch (getFormType(response.data)) {
                case FormType.JSON_FROM: {
                    const jsonForm = form as JsonFormDto;
                    setForm({
                        type: FormType.JSON_FROM,
                        content: new JsonForm({ ...jsonForm }),
                    });
                    return;
                }
                case FormType.HTML: {
                    const htmlForm = form as HtmlFormDto;
                    setForm({
                        type: FormType.HTML,
                        content: new HtmlForm({ ...htmlForm }),
                    });
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

        const api = new CompleteTaskControllerApi();
        const config: AxiosRequestConfig = {
            url: getUrlByType(UrlType.COMPLETE_TASK, task.bpmnProcessId),
        };

        try {
            const response = await api.completeTask({
                userTask: task,
                formData: data,
            }, config);

            const taskId = response.data.taskId;
            setSnackbarProps({
                open: true,
                message: `Task ${taskId} completed.`,
            });

        } catch (error) {
            console.error("Failed to complete task:", error);
        }

        setTask(null);
        setForm(null);

        setIsLoading(true);
        setCompletedTask(task);
    };

    return (
        <Fragment>
            <div className={classes.taskContainer}>
                <TaskList />
                {form?.type === "jsonForm" && (
                    <JsonFormRenderer
                        form={form.content as JsonForm}
                        // userTask={task}
                        submitEvent={submit}
                    />
                )}
                {form?.type === "htmlForm" && <HtmlFormRenderer form={form.content as HtmlForm} />}
            </div>
            <Snackbar
                anchorOrigin={{ vertical: "bottom", horizontal: "right" }}
                message={snackbarProps.message}
                open={snackbarProps.open}
            />
        </Fragment>
    );
}

export default TaskList;
