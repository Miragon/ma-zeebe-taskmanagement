package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.adapter.`in`.form.model.AddressDto
import io.miragon.zeebe.tm.order.adapter.`in`.form.model.CheckOrderSchema
import io.miragon.zeebe.tm.order.adapter.`in`.form.model.ItemDto
import io.miragon.zeebe.tm.order.application.port.`in`.LoadFormUseCase
import io.miragon.zeebe.tm.order.application.port.out.FormPersistencePort
import io.miragon.zeebe.tm.order.application.port.out.OrderPersistencePort
import io.miragon.zeebe.tm.order.domain.Form
import org.springframework.stereotype.Service

@Service
class LoadCheckOrderFormService(
    private val formPersistencePort: FormPersistencePort,
    private val orderPersistencePort: OrderPersistencePort,
) : LoadFormUseCase.LoadCheckOrderFormUseCase
{
    override fun load(orderId: String): Form
    {
        val form = formPersistencePort.loadCheckOrderForm()
        val order = orderPersistencePort.findById(orderId)

        if (form is Form.JsonForm<*>)
        {
            // Use the POJOs from the JSON schema to populate the form
            val data = CheckOrderSchema(
                customerName = order.customerName,
                deliveryAddress = AddressDto(
                    street = order.deliveryAddress["street"].toString(),
                    zipCode = order.deliveryAddress["zipCode"].toString(),
                    city = order.deliveryAddress["city"].toString(),
                ),
                items = order.items.map {
                    ItemDto(
                        id = it["id"].toString().toLong(),
                        quantity = it["quantity"].toString().toLong(),
                    )
                },
                isOrderValid = false,
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
                    "customerName" to order.customerName,
                    "deliveryAddress" to order.deliveryAddress,
                    "items" to order.items,
                ),
            )
        }

        throw RuntimeException("Form type not supported")
    }
}