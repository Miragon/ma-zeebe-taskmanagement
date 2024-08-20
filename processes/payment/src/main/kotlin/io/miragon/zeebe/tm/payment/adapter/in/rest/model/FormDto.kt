package io.miragon.zeebe.tm.payment.adapter.`in`.rest.model

/**
 * Base class for all classes that hold data that is shown in the UI.
 */
sealed class FormDataDto

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

