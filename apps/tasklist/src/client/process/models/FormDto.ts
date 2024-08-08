export type FormDto = HtmlFormDto | JsonFormDto;

export interface HtmlFormDto {
    html: string;
    updatable: boolean;
    formData?: any;
}

export interface JsonFormDto {
    schema: string;
    uiSchema: string;
    updatable: boolean;
    formData?: any;
}
