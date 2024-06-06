package io.miragon.order.application.service

import io.miragon.order.application.port.`in`.LoadFormUseCase
import io.miragon.order.application.port.out.FormPersistencePort
import io.miragon.order.application.port.out.OrderPersistencePort
import io.miragon.order.domain.Form
import io.miragon.order.jsonForm.ItemCheck
import io.miragon.order.jsonForm.PrepareOrderSchema
import org.springframework.stereotype.Service

@Service
class LoadPrepareOrderForm(
    private val formPersistencePort: FormPersistencePort,
    private val orderPersistencePort: OrderPersistencePort,
) : LoadFormUseCase.LoadPrepareOrderFormUseCase
{
    override fun load(orderId: Long): Form
    {
        val form = formPersistencePort.loadPrepareOrderForm()
        val order = orderPersistencePort.findById(orderId)

        if (form is Form.JsonForm<*>)
        {
            val data: PrepareOrderSchema = PrepareOrderSchema()
                .withItemCheckList(
                    order.items.map {
                        ItemCheck()
                            .withId(it["id"].toString().toLong())
                            .withQuantity(it["quantity"].toString().toLong())
                    }
                )

            return Form.JsonForm(
                schema = form.schema,
                uischema = form.uischema,
                data = data,
            )
        } else if (form is Form.HtmlForm)
        {
            return Form.HtmlForm(
                html = form.html,
                data = mapOf(
                    "items" to order.items,
                ),
            )
        }

        throw RuntimeException("Form type not supported")
    }
}
