package io.miragon.zeebe.tm.order.adapter.out.fs

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class FileSystemAdapterTest
{
    private val formPersistenceAdapter = FileSystemAdapter()

    private val directory = "/forms"

    @Test
    fun readProcessStartForm()
    {
        // Arrange
        val fileName = "StartProcessSchema.form.json"

        // Read content
        val read = formPersistenceAdapter.readProcessStartForm("$directory/$fileName")

        assert(read.schema.isNotEmpty())
        assert(read.uiSchema.isNotEmpty())
    }

    @Test
    fun readCheckOrderForm()
    {
        // Arrange
        val fileName = "CheckOrderSchema.form.json"

        // Read content
        val read = formPersistenceAdapter.readCheckOrderForm("$directory/$fileName")

        assert(read.schema.isNotEmpty())
        assert(read.uiSchema.isNotEmpty())
    }

    @Test
    fun readPrepareOrderForm()
    {
        // Arrange
        val fileName = "PrepareOrderSchema.form.json"

        // Read content
        val read = formPersistenceAdapter.readPrepareOrderForm("$directory/$fileName")

        assert(read.schema.isNotEmpty())
        assert(read.uiSchema.isNotEmpty())
    }

    @Test
    fun fileDoesNotExist()
    {
        // Arrange
        val fileName = "DoesNotExist.form.json"

        // Act & Assert
        assertThrows<IllegalStateException> {
            formPersistenceAdapter.readProcessStartForm("$directory/$fileName")
        }
    }

    @Test
    fun defaultUiSchema()
    {
        val mapper = jacksonObjectMapper()

        // Arrange
        val fileName = "DefaultUiSchema.json"
        val defaultUiSchema = """
            {
                "type": "VerticalLayout",
                "elements": []
            }
        """.trimIndent().replace("\\s+".toRegex(), "")

        // Read content
        val read = formPersistenceAdapter.readProcessStartForm("$directory/$fileName")

        val uiSchema = mapper.writeValueAsString(read.uiSchema)

        assert(read.schema.isNotEmpty())
        assert(read.uiSchema.isNotEmpty())
        assertEquals(uiSchema, defaultUiSchema)
    }
}