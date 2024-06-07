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
    fun completeTask(@RequestBody checkOrderDto: CheckOrderDto): ResponseEntity<Long>
    {
        val taskId = checkOrderDto.userTask.key
        val orderId = checkOrderDto.userTask.variables["orderId"].toString()
        return ResponseEntity.ok(useCase.completeCheckOrderTask(taskId, orderId, checkOrderDto.approved))
    }

    @PostMapping("/prepareOrder")
    fun completePrepareTask(@RequestBody prepareOrderDto: PrepareOrderDto): ResponseEntity<Long>
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

        return ResponseEntity.ok(useCase.completePrepareOrderTask(taskId, orderId, items))
    }
}