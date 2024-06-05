package io.miragon.order.adapter.out.database

import io.miragon.order.application.port.out.FormPersistencePort
import io.miragon.order.domain.Form
import io.miragon.order.domain.FormType

class FormPersistenceAdapter(
    private val formRepository: FormRepository,
) : FormPersistencePort
{
    override fun load(id: String): Form
    {
        val formEntity = formRepository.findById(id).orElseThrow { throw RuntimeException("Order not found") }
        return Form.create(FormType.valueOf(formEntity.type), formEntity.form)
    }

    override fun save(form: Form)
    {
        TODO("Not yet implemented")
    }
}