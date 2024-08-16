package io.miragon.zeebe.tm.order.adapter.`in`.rest

import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.CompleteTaskDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.TaskIdDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema.CheckOrderDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema.FormDataDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema.PrepareDeliverySchema
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.toCommand
import io.miragon.zeebe.tm.order.application.port.`in`.CompleteCheckOrderTaskUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.CompletePrepareDeliveryTaskUseCase
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/task")
class CompleteTaskController(
    private val checkOrderUseCase: CompleteCheckOrderTaskUseCase,
    private val prepareDeliveryUseCase: CompletePrepareDeliveryTaskUseCase,
)
{
    private val logger = KotlinLogging.logger {}

    @PostMapping("/complete")
    fun completeTask(@RequestBody completeTaskDto: CompleteTaskDto<FormDataDto>): ResponseEntity<TaskIdDto>
    {
        val userTask = completeTaskDto.userTask

        logger.info { "Completing task with id: ${userTask.elementId}" }

        when (val data = completeTaskDto.formData)
        {
            is CheckOrderDto ->
            {
                val command = data.toCommand(userTask.key, userTask.variables["orderId"].toString())
                val response = TaskIdDto(checkOrderUseCase.complete(command))
                return ResponseEntity.ok(response)
            }

            is PrepareDeliverySchema ->
            {
                val command = data.toCommand(userTask.key, userTask.variables["orderId"].toString())
                val response = TaskIdDto(prepareDeliveryUseCase.complete(command))
                return ResponseEntity.ok(response)
            }

            else -> return ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/update")
    fun updateTask(@RequestBody completeTaskDto: CompleteTaskDto<FormDataDto>): ResponseEntity<TaskIdDto>
    {
        val userTask = completeTaskDto.userTask
        val formData = completeTaskDto.formData

        return when (userTask.elementId)
        {
            UserTaskId.PREPARE_DELIVERY.id ->
            {
                val data = formData as PrepareDeliverySchema
                val command = data.toCommand(userTask.key, userTask.variables["orderId"].toString())
                val response = TaskIdDto(prepareDeliveryUseCase.update(command))
                ResponseEntity.ok(response)
            }

            else -> ResponseEntity.badRequest().build()
        }
    }
}
