package io.miragon.zeebe.tm.order.adapter.`in`.rest.model

sealed class FormDto<Schema>
{
    abstract val formData: Schema?

    data class JsonFormDto<Schema>(
        val schema: String,
        val uiSchema: String,
        val updateable: Boolean = false,
        override val formData: Schema?
    ) : FormDto<Schema>()

    data class HtmlFormDto<Schema>(
        val html: String,
        val updateable: Boolean = false,
        override val formData: Schema?
    ) : FormDto<Schema>()
}

