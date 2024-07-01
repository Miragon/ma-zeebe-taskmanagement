package io.miragon.zeebe.tm.order.adapter.`in`.rest.model

data class FormDto<T>(


    val data: T
)
{
    data class JsonFormDto(

        val schema: String,

        val uiSchema: String
    )

    data class HtmlFormDto(
        val html: String,
    )
}
