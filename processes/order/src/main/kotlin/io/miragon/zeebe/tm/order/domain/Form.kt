package io.miragon.zeebe.tm.order.domain

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

sealed class Form
{
    companion object Factory
    {
        private val mapper = jacksonObjectMapper()

        // Factory method to create a JsonForm from a JSON string
        fun createJsonForm(json: String): JsonForm
        {
            return mapper.readValue(json, object : TypeReference<JsonForm>()
            {})
        }

        // Factory method to create an HtmlForm
        fun createHtmlForm(html: String): HtmlForm
        {
            return HtmlForm(html)
        }
    }

    class JsonForm(
        val schema: String,
        val uiSchema: String,
    ) : Form()

    class HtmlForm(
        val html: String,
    ) : Form()

}