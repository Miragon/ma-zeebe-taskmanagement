package io.miragon.zeebe.tm.order.adapter.out.fs

import io.miragon.zeebe.tm.order.application.port.out.FormPersistencePort
import io.miragon.zeebe.tm.order.domain.Form
import org.springframework.stereotype.Component

@Component
class FileSystemAdapter : FormPersistencePort
{
    override fun readProcessStartForm(): Form.JsonForm
    {
        val path = "/forms/generated/OrderSchema-schema.json"
        val content = read(path) ?: throw IllegalStateException("Form not found: $path")

        return Form.createJsonForm(content)
    }

    override fun readCheckOrderForm(): Form.JsonForm
    {
        val path = "/forms/generated/CheckOrderSchema-schema.json"
        val content = read(path) ?: throw IllegalStateException("Form not found: $path")

        return Form.createJsonForm(content)
    }

    override fun readPrepareOrderForm(): Form.JsonForm
    {
        val path = "/forms/generated/PrepareOrderSchema-schema.json"
        val content = read(path) ?: throw IllegalStateException("Form not found: $path")

        return Form.createJsonForm(content)
    }

    private fun read(path: String) = this::class.java.getResource(path)?.readText()
}

