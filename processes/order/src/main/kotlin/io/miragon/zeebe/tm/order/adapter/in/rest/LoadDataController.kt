package io.miragon.zeebe.tm.order.adapter.`in`.rest

import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.*
import io.miragon.zeebe.tm.order.application.port.`in`.LoadCheckOrderTaskUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.LoadPrepareOrderTaskUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/task")
class LoadDataController(
    private val loadCheckOrderTask: LoadCheckOrderTaskUseCase,
    private val loadPrepareOrderTask: LoadPrepareOrderTaskUseCase
)
{
    @PostMapping("/load-data")
    fun loadData(@RequestBody userTask: UserTaskDto): ResponseEntity<FormDto<*>>
    {
        val userTaskId = userTask.elementId

        return when (userTaskId)
        {
            UserTaskId.CHECK_ORDER.id ->
            {
                return ResponseEntity.ok(loadCheckOrder(userTask))
            }

            UserTaskId.PREPARE_ORDER.id ->
            {
                return ResponseEntity.ok(loadPrepareOrder(userTask))
            }

            else -> ResponseEntity.badRequest().build()
        }
    }

    private fun loadCheckOrder(userTask: UserTaskDto): FormDto<CheckOrderSchema>
    {
        val command = LoadCheckOrderTaskUseCase.Command(userTask.variables["orderId"].toString())
        val response = loadCheckOrderTask.load(command)
        val form = response.form
        val order = response.order

        val formData = CheckOrderSchema(
            customerName = order.customerName,
            deliveryAddress = order.deliveryAddress.let {
                AddressDto(
                    street = it.street,
                    zipCode = it.zip,
                    city = it.city
                )
            },
            items = order.items.map {
                ItemDto(
                    id = it.id,
                    quantity = it.quantity
                )
            },
            isOrderValid = false
        )

        return FormDto.JsonFormDto(schema = form.schema, uiSchema = form.uiSchema, formData = formData)
    }

    private fun loadPrepareOrder(userTask: UserTaskDto): FormDto<PrepareOrderSchema>
    {
        val command = LoadPrepareOrderTaskUseCase.Command(userTask.variables["orderId"].toString())
        val response = loadPrepareOrderTask.load(command)
        val form = response.form
        val items = response.items

        val formData = PrepareOrderSchema(
            itemCheckList = items.map {
                CheckItemDto(
                    item = ItemDto(
                        id = it.id,
                        quantity = it.quantity
                    ),
                    isAvailable = false,
                    deliveryDate = ""
                )
            }
        )

        return FormDto.JsonFormDto(
            schema = form.schema,
            uiSchema = form.uiSchema,
            updateable = true,
            formData = formData
        )
    }
}
