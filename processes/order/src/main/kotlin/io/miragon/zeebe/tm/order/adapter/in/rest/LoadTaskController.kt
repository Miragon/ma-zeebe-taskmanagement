package io.miragon.zeebe.tm.order.adapter.`in`.rest

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.FormDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.UserTaskDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema.*
import io.miragon.zeebe.tm.order.application.port.`in`.LoadCheckOrderTaskUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.LoadPrepareOrderTaskUseCase
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/task")
class LoadTaskController(
    private val loadCheckOrderTask: LoadCheckOrderTaskUseCase,
    private val loadPrepareOrderTask: LoadPrepareOrderTaskUseCase
)
{
    private val logger = KotlinLogging.logger {}

    private val mapper = jacksonObjectMapper()

    private val checkOrderFormPath = "/forms/CheckOrderSchema.form.json"

    private val prepareOrderFormPath = "/forms/PrepareOrderSchema.form.json"

    @PostMapping("/load")
    fun loadData(@RequestBody userTask: UserTaskDto): ResponseEntity<FormDto<*>>
    {
        val userTaskId = userTask.elementId

        logger.info { "Loading form for user task with id: $userTaskId" }

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
        val command = LoadCheckOrderTaskUseCase.Command(
            orderId = userTask.variables["orderId"].toString(),
            filePath = checkOrderFormPath
        )
        val response = loadCheckOrderTask.load(command)

        val schema = mapper.writeValueAsString(response.form.schema)
        val uiSchema = mapper.writeValueAsString(response.form.uiSchema)
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

        return FormDto.JsonFormDto(
            schema = schema, uiSchema = uiSchema, formData = formData
        )
    }

    private fun loadPrepareOrder(userTask: UserTaskDto): FormDto<PrepareOrderSchema>
    {
        val command = LoadPrepareOrderTaskUseCase.Command(
            orderId = userTask.variables["orderId"].toString(),
            filePath = prepareOrderFormPath
        )
        val response = loadPrepareOrderTask.load(command)

        val schema = mapper.writeValueAsString(response.form.schema)
        val uiSchema = mapper.writeValueAsString(response.form.uiSchema)
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
            schema = schema,
            uiSchema = uiSchema,
            updatable = true,
            formData = formData
        )
    }
}
