package io.miragon.zeebe.tm.order.adapter.`in`.rest

import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.ItemDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.LoadItemsDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.PlaceOrderDto
import io.miragon.zeebe.tm.order.application.port.`in`.LoadStartEventUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.StartProcessUseCase
import io.miragon.zeebe.tm.tasklist.FormDto
import io.miragon.zeebe.tm.tasklist.MessageDto
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/process")
class StartProcessController(
    private val loadFormUseCase: LoadStartEventUseCase,
    private val startProcessUseCase: StartProcessUseCase
)
{
    private val logger = KotlinLogging.logger {}

    private val processStartFormPath = "/forms/index.html"

    /* TODO: What happens if a process has multiple start events? */

    @PostMapping("/start/form")
    fun loadItems(): ResponseEntity<FormDto.HtmlForm<LoadItemsDto>>
    {
        val command = LoadStartEventUseCase.Command(
            filePath = processStartFormPath
        )
        val response = loadFormUseCase.load(command)

        val form = response.form

        val formData = LoadItemsDto(
            items = response.formData.map { item ->
                ItemDto(
                    id = item.id,
                    name = item.name ?: throw IllegalArgumentException("Item name must not be null"),
                    price = item.price ?: throw IllegalArgumentException("Item price must not be null"),
                    image = item.image ?: throw IllegalArgumentException("Item image must not be null"),
                )
            }
        )

        return ResponseEntity.ok(
            FormDto.HtmlForm(
                html = form.html,
                updatable = form.updatable,
                formData = formData
            )
        )
    }

    @PostMapping("/start")
    fun placeOrder(@RequestBody formData: PlaceOrderDto): ResponseEntity<MessageDto>
    {
        val command = formData.toCommand()
        val orderId = startProcessUseCase.startProcess(command)
        val response = MessageDto("Order with id $orderId created!")

        logger.info { "Order with id $orderId created!" }

        return ResponseEntity.ok(response)
    }
}