import { JsonSchema } from "@jsonforms/core";

interface ProcessParams {
    id: string;
    name: string;
    startable: boolean;
    schema?: any;
}

export class Process {
    private readonly id: string;

    private readonly name: string;

    private readonly startable: boolean;

    private readonly schema?: JsonSchema;

    private readonly variables?: Map<string, any>;

    constructor(params: ProcessParams) {
        this.id = params.id;
        this.name = params.name;
        this.startable = params.startable;
        this.schema = params.schema;
        this.variables = new Map();
    }

    public getId(): string {
        return this.id;
    }

    public getName(): string {
        return this.name;
    }

    public isStartable(): boolean {
        return this.startable;
    }

    public getSchema(): JsonSchema | undefined {
        return this.schema;
    }

    public getVariables(): Map<string, any> | undefined {
        return this.variables;
    }

    public addVariable(key: string, value: any): Process {
        this.variables?.set(key, value);
        return this;
    }
}

const processDefinitions: Map<string, Process> = new Map();

export function getGlobalProcessDefinitions(): Process[] {
    return Array.from(processDefinitions.values());
}

export function setGlobalProcessDefinitions(definitions: ProcessParams[]) {
    for (const definition of definitions) {
        processDefinitions.set(definition.id, new Process(definition));
    }
}
