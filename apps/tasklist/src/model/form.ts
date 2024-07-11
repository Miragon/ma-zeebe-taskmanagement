import { JsonSchema, UISchemaElement } from "@jsonforms/core";

interface JsonFormParameters {
    schema: string;
    uiSchema: string;
    updatable: boolean;
    formData: any;
}

interface HtmlFormParameters {
    html: string;
    updatable: boolean;
    formData: any;
}

export interface Form {
}

export class JsonForm implements Form {
    private readonly schema: JsonSchema;

    private readonly uiSchema: UISchemaElement;

    private readonly updatable: boolean;

    private readonly formData: any;

    constructor({ schema, uiSchema, updatable, formData }: JsonFormParameters) {
        this.schema = JSON.parse(JSON.stringify(schema));
        this.uiSchema = JSON.parse(JSON.stringify(uiSchema));
        this.updatable = updatable;
        this.formData = formData ?? {};
    }

    getSchema() {
        return this.schema;
    }

    getUiSchema() {
        return this.uiSchema;
    }

    getUpdateable() {
        return this.updatable;
    }

    getFormData() {
        return this.formData;
    }
}

export class HtmlForm implements Form {
    private readonly html: string;

    private readonly updatable: boolean;

    private readonly formData: any;

    constructor({ html, updatable, formData }: HtmlFormParameters) {
        this.html = html;
        this.updatable = updatable;
        this.formData = formData;
    }

    getHtml() {
        return this.html;
    }

    getUpdateable() {
        return this.updatable;
    }

    getFormData() {
        return this.formData;
    }
}
