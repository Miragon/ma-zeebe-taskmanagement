package io.miragon.zeebe.tm.order.adapter.out.fs

import io.miragon.zeebe.tm.order.application.port.out.FormPersistencePort
import io.miragon.zeebe.tm.order.domain.Form
import org.springframework.stereotype.Component

@Component
class FileSystemAdapter : FormPersistencePort
{
    override fun readProcessStartForm(path: String): Form.HtmlForm
    {
        val content = read(path) ?: throw IllegalStateException("Form not found: $path")

        return Form.createHtmlForm(content)
    }

    override fun readCheckOrderForm(path: String): Form.HtmlForm
    {
        val content = read(path) ?: throw IllegalStateException("Form not found: $path")

        return Form.createHtmlForm(content)
    }

    override fun readPrepareOrderForm(path: String): Form.JsonForm
    {
        val content = read(path) ?: throw IllegalStateException("Form not found: $path")

        return Form.createJsonForm(content)
    }

    private fun read(path: String) = this::class.java.getResource(path)?.readText()

}

