package io.miragon.zeebe.tm.order.adapter.`in`.rest.model

import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema.FormDataDto

sealed class FormDto
{
    data class JsonForm<T : FormDataDto>(
        val schema: String,
        val uiSchema: String,
        val updatable: Boolean,
        val formData: T?
    ) : FormDto()

    data class HtmlForm<T : FormDataDto>(
        val html: String,
        val updatable: Boolean,
        val formData: T?
    ) : FormDto()
}

