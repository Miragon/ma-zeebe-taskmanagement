package io.miragon.zeebe.tm.order.adapter.`in`.rest.model

sealed class FormDto<Schema>
{
    abstract val data: Schema?

    data class JsonFormDto<Schema>(
        val schema: String,
        val uiSchema: String,
        override val data: Schema?
    ) : FormDto<Schema>()

    data class HtmlFormDto<Schema>(
        val html: String,
        override val data: Schema?
    ) : FormDto<Schema>()
}

