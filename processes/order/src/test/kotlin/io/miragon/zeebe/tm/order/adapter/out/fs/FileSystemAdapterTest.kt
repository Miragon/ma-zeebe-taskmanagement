package io.miragon.zeebe.tm.order.adapter.out.fs

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

class FileSystemAdapterTest
{
    private val formPersistenceAdapter = FileSystemAdapter()

    private val directory = File("src/main/resources/forms/generated")

    @BeforeEach
    fun setUp()
    {
        assert(directory.exists())
    }

    @Test
    fun readProcessStartForm()
    {
        // Create file
        val processStartSchema = File(directory, "StartProcessSchema.form.json")
        assert(processStartSchema.exists())

        // Read content
        val read = formPersistenceAdapter.readProcessStartForm()

        assert(read.schema.isNotEmpty())
        assert(read.uiSchema.isNotEmpty())
    }

    @Test
    fun readCheckOrderForm()
    {
        // Create file
        val checkOrderSchema = File(directory, "CheckOrderSchema.form.json")
        assert(checkOrderSchema.exists())

        // Read content
        val read = formPersistenceAdapter.readCheckOrderForm()

        assert(read.schema.isNotEmpty())
        assert(read.uiSchema.isNotEmpty())
    }

    @Test
    fun readPrepareOrderForm()
    {
        // Create file
        val prepareOrderSchema = File(directory, "PrepareOrderSchema.form.json")
        assert(prepareOrderSchema.exists())

        // Read content
        val read = formPersistenceAdapter.readPrepareOrderForm()

        assert(read.schema.isNotEmpty())
        assert(read.uiSchema.isNotEmpty())
    }
}