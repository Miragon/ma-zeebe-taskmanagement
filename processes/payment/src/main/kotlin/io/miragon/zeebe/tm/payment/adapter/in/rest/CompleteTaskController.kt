package io.miragon.zeebe.tm.payment.adapter.`in`.rest

import io.miragon.zeebe.tm.payment.adapter.`in`.rest.model.CheckPaymentSchema
import io.miragon.zeebe.tm.payment.adapter.`in`.rest.model.CompleteTaskDto
import io.miragon.zeebe.tm.payment.adapter.`in`.rest.model.FormDataDto
import io.miragon.zeebe.tm.payment.adapter.`in`.rest.model.MessageDto
import io.miragon.zeebe.tm.payment.application.port.`in`.CompleteCheckPaymentUseCase
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/task")
class CompleteTaskController(
    private val useCase: CompleteCheckPaymentUseCase
)
{
    private val logger = KotlinLogging.logger {}

    @PostMapping("/complete")
    fun completeTask(@RequestBody completeTaskDto: CompleteTaskDto<FormDataDto>): ResponseEntity<MessageDto>
    {
        val userTask = completeTaskDto.userTask

        logger.info { "Completing task with id: ${userTask.elementId}" }

        when (val data = completeTaskDto.formData)
        {
            is CheckPaymentSchema ->
            {
                val command = data.toCommand(userTask.key, userTask.variables["invoiceId"].toString())
                val taskId = useCase.complete(command)
                val message = MessageDto("""Task $taskId completed""")
                return ResponseEntity.ok(message)
            }

            else -> return ResponseEntity.badRequest().build()
        }
    }
}
