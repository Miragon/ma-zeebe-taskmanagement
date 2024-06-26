import { JsonSchema, UISchemaElement } from "@jsonforms/core";

interface JsonFormParameters {
    schema: JsonSchema;
    uischema: UISchemaElement;
    data: any;
}

interface HtmlFormParameters {
    html: string;
    data: any;
}

export interface Form {}

export class JsonForm implements Form {
    private readonly schema: JsonSchema;

    private readonly uischema: UISchemaElement;

    private readonly data: any;

    constructor({ schema, uischema, data }: JsonFormParameters) {
        this.schema = schema;
        this.uischema = uischema;
        this.data = data;
    }

    getSchema() {
        return this.schema;
    }

    getUiSchema() {
        return this.uischema;
    }

    getData() {
        return this.data;
    }
}

export class HtmlForm implements Form {
    private readonly html: string;

    private readonly data: any;

    constructor({ html, data }: HtmlFormParameters) {
        this.html = html;
        this.data = data;
    }

    getHtml() {
        return this.html;
    }

    getData() {
        return this.data;
    }
}
