package io.miragon.zeebe.tm.order.adapter.out.fs

import io.miragon.zeebe.tm.order.application.port.out.FormPersistencePort
import io.miragon.zeebe.tm.order.domain.Form
import org.springframework.stereotype.Component

@Component
class FileSystemAdapter : FormPersistencePort
{
    private val processStartFormPath = "/forms/generated/StartProcessSchema.form.json"

    private val checkOrderFormPath = "/forms/generated/CheckOrderSchema.form.json"

    private val prepareOrderFormPath = "/forms/generated/PrepareOrderSchema.form.json"

    override fun readProcessStartForm(): Form.JsonForm
    {
        val path = processStartFormPath
        val content = read(path) ?: throw IllegalStateException("Form not found: $path")

        return Form.createJsonForm(content)
    }

    override fun readCheckOrderForm(): Form.JsonForm
    {
        val path = checkOrderFormPath
        val content = read(path) ?: throw IllegalStateException("Form not found: $path")

        return Form.createJsonForm(content)
    }

    override fun readPrepareOrderForm(): Form.JsonForm
    {
        val path = prepareOrderFormPath
        val content = read(path) ?: throw IllegalStateException("Form not found: $path")

        return Form.createJsonForm(content)
    }

    private fun read(path: String) = this::class.java.getResource(path)?.readText()
}

