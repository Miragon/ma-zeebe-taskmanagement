interface UserTaskParameters {
    key: number;
    elementId: string;
    processInstanceKey: number;
    bpmnProcessId: string;
    processDefinitionKey: number;
    variables: { [key: string]: object };
    taskState: string;
    assignee?: string;
}

export class UserTask {

    readonly key: number;

    readonly elementId: string;

    readonly processInstanceKey: number;

    readonly bpmnProcessId: string;

    readonly processDefinitionKey: number;

    readonly variables: { [key: string]: object };

    readonly taskState: string;

    readonly assignee?: string;

    constructor({
                    key,
                    elementId,
                    processInstanceKey,
                    bpmnProcessId,
                    processDefinitionKey,
                    variables,
                    taskState,
                    assignee,
                }: UserTaskParameters) {
        this.key = key;
        this.elementId = elementId;
        this.processInstanceKey = processInstanceKey;
        this.bpmnProcessId = bpmnProcessId;
        this.processDefinitionKey = processDefinitionKey;
        this.variables = variables;
        this.taskState = taskState;
        this.assignee = assignee;
    }
}