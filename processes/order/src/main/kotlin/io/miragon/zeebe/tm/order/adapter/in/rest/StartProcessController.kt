package io.miragon.zeebe.tm.order.adapter.`in`.rest

import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.FormDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.MessageDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema.ItemDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema.StartProcessResponseDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema.StartProcessResultDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.toCommand
import io.miragon.zeebe.tm.order.application.port.`in`.LoadStartEventUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.StartProcessUseCase
import io.miragon.zeebe.tm.order.domain.Order
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest/process")
class StartProcessController(
    private val loadFormUseCase: LoadStartEventUseCase,
    private val startProcessUseCase: StartProcessUseCase
)
{
    private val logger = KotlinLogging.logger {}

    private val processStartFormPath = "/forms/StartProcessSchema.form.json"

    @GetMapping("/start/form")
    fun loadForm(): ResponseEntity<FormDto<StartProcessResponseDto>>
    {
        val command = LoadStartEventUseCase.Command(
            filePath = processStartFormPath
        )
        val response = loadFormUseCase.load(command)

        val form = response.form

        val formData = StartProcessResponseDto(
            items = response.formData.map {
                ItemDto(
                    id = it.id,
                    name = it.name,
                    price = it.price,
                    image = it.image,
                )
            }
        )

        return ResponseEntity.ok(
            FormDto.HtmlFormDto(
                html = form.html,
                updatable = form.updatable,
                formData = formData
            )
        )
    }

    @PostMapping("/start")
    fun startProcess(@RequestBody formData: StartProcessResultDto): ResponseEntity<MessageDto>
    {
        val command = formData.toCommand(Order.OrderState.CHECKED)
        val orderId = startProcessUseCase.startProcess(command)
        val response = MessageDto("Order with id $orderId created!")

        logger.info { "Order with id $orderId created!" }

        return ResponseEntity.ok(response)
    }
}