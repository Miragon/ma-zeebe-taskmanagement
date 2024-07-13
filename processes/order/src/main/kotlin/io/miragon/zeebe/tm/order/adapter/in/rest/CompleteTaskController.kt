package io.miragon.zeebe.tm.order.adapter.`in`.rest

import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.CheckOrderSchema
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.PrepareOrderSchema
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.TaskIdDto
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.UserTaskDto
import io.miragon.zeebe.tm.order.application.port.`in`.CompleteCheckOrderTaskUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.CompletePrepareOrderTaskUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/task")
class CompleteTaskController(
    private val completeCheckOrderTaskUseCase: CompleteCheckOrderTaskUseCase,
    private val completePrepareOrderTaskUseCase: CompletePrepareOrderTaskUseCase,
)
{
    @PostMapping("/complete")
    fun completeTask(userTask: UserTaskDto, formData: Any): ResponseEntity<TaskIdDto>
    {
        return when (userTask.elementId)
        {
            UserTaskId.CHECK_ORDER.id ->
            {
                val data = formData as CheckOrderSchema
                val command = data.toCommand(userTask.key, userTask.variables["orderId"].toString())
                val response = TaskIdDto(completeCheckOrderTaskUseCase.complete(command))
                ResponseEntity.ok(response)
            }

            UserTaskId.PREPARE_ORDER.id ->
            {
                val data = formData as PrepareOrderSchema
                val command = data.toCommand(userTask.key, userTask.variables["orderId"].toString())
                val response = TaskIdDto(completePrepareOrderTaskUseCase.complete(command))
                ResponseEntity.ok(response)
            }

            else -> ResponseEntity.badRequest().build()
        }
    }

    @PostMapping("/update")
    fun updateTask(userTask: UserTaskDto, formData: Any): ResponseEntity<TaskIdDto>
    {
        return when (userTask.elementId)
        {
            UserTaskId.PREPARE_ORDER.id ->
            {
                val data = formData as PrepareOrderSchema
                val command = data.toCommand(userTask.key, userTask.variables["orderId"].toString())
                val response = TaskIdDto(completePrepareOrderTaskUseCase.update(command))
                ResponseEntity.ok(response)
            }

            else -> ResponseEntity.badRequest().build()
        }
    }
}
