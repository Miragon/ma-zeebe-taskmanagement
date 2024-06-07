package io.miragon.order.application.service

import io.miragon.order.application.port.`in`.LoadFormUseCase
import io.miragon.order.application.port.out.FormPersistencePort
import io.miragon.order.application.port.out.OrderPersistencePort
import io.miragon.order.domain.Form
import io.miragon.order.jsonForm.CheckOrderSchema
import io.miragon.order.jsonForm.DeliveryAddress
import io.miragon.order.jsonForm.Item
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
            val data: CheckOrderSchema = CheckOrderSchema()
                .withCustomerName(order.customerName)
                .withDeliveryAddress(
                    DeliveryAddress()
                        .withStreet(order.deliveryAddress["street"].toString())
                        .withZipCode(order.deliveryAddress["zipCode"].toString())
                        .withCity(order.deliveryAddress["city"].toString())
                )
                .withItems(
                    order.items.map {
                        Item()
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
                    "customerName" to order.customerName,
                    "deliveryAddress" to order.deliveryAddress,
                    "items" to order.items,
                ),
            )
        }

        throw RuntimeException("Form type not supported")
    }
}