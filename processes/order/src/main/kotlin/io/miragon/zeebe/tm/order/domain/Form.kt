package io.miragon.zeebe.tm.order.domain

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

sealed class Form
{
    var updatable: Boolean = false

    companion object Factory
    {
        private val mapper = jacksonObjectMapper()

        // Factory method to create a JsonForm from a JSON string
        fun createJsonForm(jsonString: String): JsonForm
        {
            return mapper.readValue(jsonString, object : TypeReference<JsonForm>()
            {})
        }

        // Factory method to create an HtmlForm
        fun createHtmlForm(html: String): HtmlForm
        {
            return HtmlForm(html)
        }
    }

    class JsonForm(
        val schema: Map<String, Any>,
        val uiSchema: Map<String, Any>,
    ) : Form()

    class HtmlForm(content: String) : Form()
    {
        val html: String = trimHtml(content)

        private fun trimHtml(content: String): String
        {
            val removeWhitespace = content.replace("\\s".toRegex(), "")
            val removeNewLines = removeWhitespace.replace("\n", "")
            return removeNewLines
        }
    }

}