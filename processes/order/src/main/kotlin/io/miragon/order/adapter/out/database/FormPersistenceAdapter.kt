package io.miragon.order.adapter.out.database

import io.miragon.order.application.port.out.FormPersistencePort
import io.miragon.order.domain.Form
import io.miragon.order.domain.FormDeployment
import io.miragon.order.domain.FormType
import org.springframework.stereotype.Component

@Component
class FormPersistenceAdapter(
    private val formRepository: FormRepository,
) : FormPersistencePort
{
    private val checkOrderId = "checkOrderForm"

    private val prepareOrderId = "prepareOrderForm"

    override fun loadCheckOrderForm(): Form
    {
        val key = FormKey(checkOrderId, 1.0)
        val formEntity = formRepository.findById(key).orElseThrow { throw RuntimeException("Form not found") }
        return Form(FormType.valueOf(formEntity.type), formEntity.form)
    }

    override fun loadPrepareOrderForm(): Form
    {
        val key = FormKey(prepareOrderId, 1.0)
        val formEntity = formRepository.findById(key).orElseThrow { throw RuntimeException("Form not found") }
        return Form(FormType.valueOf(formEntity.type), formEntity.form)
    }

    override fun saveCheckOrderForm(form: FormDeployment)
    {
        save(checkOrderId, form)
    }

    override fun savePrepareOrderForm(form: FormDeployment)
    {
        save(prepareOrderId, form)
    }

    fun save(id: String, form: FormDeployment)
    {
        formRepository.save(
            FormEntity(
                id = id,
                version = form.version,
                type = form.type.toString(),
                form = form.form,
            )
        )
    }
}