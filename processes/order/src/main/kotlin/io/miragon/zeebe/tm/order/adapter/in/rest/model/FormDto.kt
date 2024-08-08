package io.miragon.zeebe.tm.order.adapter.`in`.rest.model

import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema.TaskDto

sealed class FormDto
{
    data class JsonForm<T : TaskDto>(
        val schema: String,
        val uiSchema: String,
        val updatable: Boolean,
        val formData: T?
    ) : FormDto()

    data class HtmlForm<T : TaskDto>(
        val html: String,
        val updatable: Boolean,
        val formData: T?
    ) : FormDto()
}

