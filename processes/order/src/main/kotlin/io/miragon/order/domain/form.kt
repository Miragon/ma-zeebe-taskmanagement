package io.miragon.order.domain

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

abstract class Form
{
    companion object Factory
    {
        private val mapper = jacksonObjectMapper()

        operator fun invoke(type: FormType, form: String): Form
        {
            return when (type)
            {
                FormType.JSON_FORM -> buildJsonFormFromString(form)
                FormType.HTML -> HtmlForm(html = form)
            }
        }

        private fun buildJsonFormFromString(form: String): JsonForm<Any>
        {
            return mapper.readValue(form, object : TypeReference<JsonForm<Any>>()
            {})
        }
    }

    class JsonForm<T>(
        val schema: Map<String, Any>,
        val uischema: Map<String, Any>,
        var data: T? = null,
    ) : Form()

    class HtmlForm(
        val html: String,
        var data: Map<String, Any>? = null,
    ) : Form()

}

data class FormDeployment(
    val version: Double,
    var type: FormType = FormType.JSON_FORM,
    val form: String,
)

enum class FormType
{
    JSON_FORM,
    HTML
}
