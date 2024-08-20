package io.miragon.zeebe.tm.payment.adapter.out.fs

import io.miragon.zeebe.tm.payment.application.port.out.FormPersistencePort
import io.miragon.zeebe.tm.payment.domain.Form
import org.springframework.stereotype.Component

@Component
class FileSystemAdapter : FormPersistencePort
{
    override fun readCheckPaymentForm(path: String): Form.JsonForm
    {
        val content = read(path) ?: throw IllegalStateException("Form not found: $path")

        return Form.createJsonForm(content)
    }

    private fun read(path: String) = this::class.java.getResource(path)?.readText()
}