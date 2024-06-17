package io.miragon.order.adapter.`in`.rest

import io.miragon.order.application.port.`in`.CompleteTaskUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/task/complete")
class CompleteTaskController(private val useCase: CompleteTaskUseCase)
{
    @PostMapping("/checkOrder")
    fun completeTask(@RequestBody checkOrderDto: CheckOrderDto): ResponseEntity<CompleteTaskResult>
    {
        val taskId = checkOrderDto.userTask.key
        val orderId = checkOrderDto.userTask.variables["orderId"].toString()

        val completedTaskResult =
            CompleteTaskResult(useCase.completeCheckOrderTask(taskId, orderId, checkOrderDto.approved))

        return ResponseEntity.ok(completedTaskResult)
    }

    @PostMapping("/prepareOrder")
    fun completePrepareTask(@RequestBody prepareOrderDto: PrepareOrderDto): ResponseEntity<CompleteTaskResult>
    {
        val taskId = prepareOrderDto.userTask.key
        val orderId = prepareOrderDto.userTask.variables["orderId"].toString()
        val items = prepareOrderDto.items.map {
            mapOf(
                "id" to it.id,
                "quantity" to it.quantity,
                "isAvailable" to it.isAvailable,
                "deliveryDate" to it.deliveryDate,
            )
        }

        val completedTaskResult =
            CompleteTaskResult(useCase.completePrepareOrderTask(taskId, orderId, items))

        return ResponseEntity.ok(completedTaskResult)
    }

    data class CompleteTaskResult(
        private val taskId: Long,
    )
}