package io.miragon.zeebe.tm.order.adapter.`in`.rest.model

sealed class FormDto<Schema>
{
    abstract val updatable: Boolean

    abstract val formData: Schema?

    data class JsonFormDto<Schema>(
        val schema: String,
        val uiSchema: String,
        override val updatable: Boolean,
        override val formData: Schema?
    ) : FormDto<Schema>()

    data class HtmlFormDto<Schema>(
        val html: String,
        override val updatable: Boolean,
        override val formData: Schema?
    ) : FormDto<Schema>()
}

