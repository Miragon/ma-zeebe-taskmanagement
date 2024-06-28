package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.adapter.`in`.form.model.CheckItemDto
import io.miragon.zeebe.tm.order.adapter.`in`.form.model.ItemDto
import io.miragon.zeebe.tm.order.adapter.`in`.form.model.PrepareOrderSchema
import io.miragon.zeebe.tm.order.application.port.`in`.LoadFormUseCase
import io.miragon.zeebe.tm.order.application.port.out.FormPersistencePort
import io.miragon.zeebe.tm.order.application.port.out.OrderPersistencePort
import io.miragon.zeebe.tm.order.domain.Form
import org.springframework.stereotype.Service

@Service
class LoadPrepareOrderForm(
    private val formPersistencePort: FormPersistencePort,
    private val orderPersistencePort: OrderPersistencePort,
) : LoadFormUseCase.LoadPrepareOrderFormUseCase
{
    override fun load(orderId: String): Form
    {
        val form = formPersistencePort.loadPrepareOrderForm()
        val order = orderPersistencePort.findById(orderId)

        if (form is Form.JsonForm<*>)
        {
            val data = PrepareOrderSchema(
                itemCheckList = order.items.map {
                    CheckItemDto(
                        ItemDto(
                            id = it["id"].toString().toLong(),
                            quantity = it["quantity"].toString().toLong(),
                        ),
                        isAvailable = false,
                        deliveryDate = "",
                    )
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
