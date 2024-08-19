package io.miragon.zeebe.tm.order.adapter.`in`.rest

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.*
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

    private val checkOrderFormPath = "/forms/index.html"

    private val prepareOrderFormPath = "/forms/PrepareOrderSchema.form.json"

    @PostMapping("/load")
    fun loadData(@RequestBody userTask: UserTaskDto): ResponseEntity<FormDto>
    {
        val userTaskId = userTask.elementId

        logger.info { "Loading form for user task with id: $userTaskId" }

        return when (userTaskId)
        {
            UserTaskId.CHECK_ORDER.id ->
            {
                return ResponseEntity.ok(loadCheckOrder(userTask))
            }

            UserTaskId.PREPARE_DELIVERY.id ->
            {
                return ResponseEntity.ok(loadPrepareOrder(userTask))
            }

            else -> ResponseEntity.badRequest().build()
        }
    }

    private fun loadCheckOrder(userTask: UserTaskDto): FormDto.HtmlForm<LoadOrderDto>
    {
        val command = LoadCheckOrderTaskUseCase.Command(
            orderId = userTask.variables["orderId"].toString(),
            filePath = checkOrderFormPath
        )
        val response = loadCheckOrderTask.load(command)

        val form = response.form
        val order = response.order

        val formData = LoadOrderDto(
            firstname = order.firstname,
            lastname = order.lastname,
            email = order.email,
            street = order.street,
            city = order.city,
            zip = order.zip,
            items = order.items.map {
                CheckOrderItemDto(
                    id = it.id,
                    name = it.name ?: throw IllegalArgumentException("Item name must not be null"),
                    price = it.price ?: throw IllegalArgumentException("Item price must not be null"),
                    image = it.image ?: throw IllegalArgumentException("Item image must not be null"),
                    quantity = it.quantity ?: throw IllegalArgumentException("Item quantity must not be null"),
                )
            }
        )

        return FormDto.HtmlForm(
            html = form.html,
            updatable = form.updatable,
            formData = formData
        )
    }

    private fun loadPrepareOrder(userTask: UserTaskDto): FormDto.JsonForm<PrepareDeliverySchema>
    {
        val command = LoadPrepareOrderTaskUseCase.Command(
            orderId = userTask.variables["orderId"].toString(),
            filePath = prepareOrderFormPath
        )
        val response = loadPrepareOrderTask.load(command)

        val schema = mapper.writeValueAsString(response.form.schema)
        val uiSchema = mapper.writeValueAsString(response.form.uischema)
        val items = response.items

        val formData = PrepareDeliverySchema(
            itemCheckList = items.map {
                CheckItemDto(
                    id = it.id,
                    name = it.name ?: throw IllegalArgumentException("Item name must not be null"),
                    quantity = it.quantity ?: throw IllegalArgumentException("Item quantity must not be null"),
                    ready = false,
                )
            },
            deliveryDate = "",
            modeOfDispatch = ModeOfDispatch.STANDARD
        )

        return FormDto.JsonForm(
            schema = schema,
            uiSchema = uiSchema,
            updatable = response.form.updatable,
            formData = formData
        )
    }
}
