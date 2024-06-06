package io.miragon.order.adapter.`in`.rest

import io.miragon.order.application.port.`in`.CompleteTaskUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest/task/complete")
class CompleteTaskController(private val useCase: CompleteTaskUseCase)
{
    @PostMapping("/checkOrder/{id}")
    fun completeTask(@PathVariable id: Long, @RequestBody checkOrderDto: CheckOrderDto): ResponseEntity<Long>
    {
        return ResponseEntity.ok(useCase.completeCheckOrderTask(id, checkOrderDto.approved))
    }

    @PostMapping("/prepareOrder/{id}")
    fun completePrepareTask(@PathVariable id: Long, @RequestBody prepareOrderDto: PrepareOrderDto): ResponseEntity<Long>
    {
        val items = prepareOrderDto.items.map {
            mapOf(
                "id" to it.id,
                "quantity" to it.quantity,
                "isAvailable" to it.isAvailable,
                "deliveryDate" to it.deliveryDate,
            )
        }

        return ResponseEntity.ok(useCase.completePrepareOrderTask(id, items))
    }
}