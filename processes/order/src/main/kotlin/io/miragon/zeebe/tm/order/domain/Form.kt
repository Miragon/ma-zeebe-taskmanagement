package io.miragon.zeebe.tm.order.domain

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DatabindException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

sealed class Form
{
    companion object Factory
    {
        private val mapper = jacksonObjectMapper()

        // Factory method to create a JsonForm from a JSON string
        fun createJsonForm(jsonString: String): JsonForm
        {
            return try
            {
                mapper.readValue(jsonString, object : TypeReference<JsonForm>()
                {})
            } catch (e: DatabindException)
            {
                val json = mapper.readValue(jsonString, object : TypeReference<Map<String, Any>>()
                {})
                if (json["type"] == "object" && json.containsKey("properties"))
                {
                    return JsonForm(json, mapOf("type" to "VerticalLayout", "elements" to emptyArray<Any>()))
                } else
                {
                    throw e
                }
            }
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

    class HtmlForm(
        val html: String,
    ) : Form()

}