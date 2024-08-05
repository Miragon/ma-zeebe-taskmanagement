import React, { Fragment, useEffect, useState } from "react";
import { Snackbar, SnackbarProps } from "@mui/material";
import { tss } from "tss-react/mui";
import { AxiosRequestConfig } from "axios";
import { JsonFormDto } from "../../client/generated/processModels/models/JsonFormDto.ts";
import { HtmlFormDto } from "../../client/generated/processModels/models/HtmlFormDto.ts";
import { LoadUserTaskControllerApi } from "../../client/generated/taskmanager";
import { CompleteTaskControllerApi, LoadTaskControllerApi } from "../../client/process";
import { FormProps, FormType, getFormType, HtmlForm, JsonForm, UserTask } from "../../model";
import { getUrlByType, taskManagerConfig, UrlType } from "../../config.ts";

import Task from "./Task.tsx";
import JsonFormRenderer from "../form/JsonFormRenderer.tsx";
import HtmlFormRenderer from "../form/HtmlFormRenderer.tsx";

const useStyles = tss.create({
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

    const { classes } = useStyles();

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

    function Tasks() {
        if (isLoading) {
            return <p>Loading...</p>;
        } else {
            return (
                <ul className={classes.taskList}>
                    {tasks.map((task) => (
                        <Task key={task.key} task={task} event={selectTask} />
                    ))}
                </ul>
            );
        }
    }

    const selectTask = async (userTask: UserTask) => {
        setTask(userTask);

        const api = new LoadTaskControllerApi();
        const config: AxiosRequestConfig = {
            url: getUrlByType(UrlType.LOAD_TASK, userTask.bpmnProcessId),
        };

        try {
            const response = await api.loadData(userTask, config);
            const form = response.data;

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

    const update = async (data: any) => {
        if (!task) {
            console.error("No user task found");
            return;
        }

        const api = new CompleteTaskControllerApi();
        const config: AxiosRequestConfig = {
            url: getUrlByType(UrlType.UPDATE_TASK, task.bpmnProcessId),
        };

        try {
            const response = await api.updateTask({
                userTask: task,
                formData: data,
            }, config);

            const taskId = response.data.taskId;
            setSnackbarProps({
                open: true,
                message: `Task ${taskId} updated.`,
            });
        } catch (error) {
            console.error("Failed to update task:", error);
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
                <Tasks />
                {form?.type === "jsonForm" && (
                    <JsonFormRenderer
                        form={form.content as JsonForm}
                        updateEvent={update}
                        submitEvent={submit}
                    />
                )}
                {form?.type === "htmlForm" && (
                    <HtmlFormRenderer
                        form={form.content as HtmlForm}
                        bpmnElement={{ elementId: task?.elementId, variables: task?.variables }}
                        updateEvent={update}
                        submitEvent={submit}
                    />
                )}
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
