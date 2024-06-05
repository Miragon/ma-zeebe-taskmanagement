package io.miragon.order.adapter.`in`.rest

import io.miragon.order.application.port.`in`.CompleteTaskUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/order/task")
class CompleteTaskController(private val useCase: CompleteTaskUseCase)
{
    @PostMapping("/complete/check/{id}")
    fun completeTask(@PathVariable id: Long): ResponseEntity<Long>
    {
        return ResponseEntity.ok(useCase.completeCheckOrderTask(id))
    }

    @PostMapping("/complete/prepare/{id}")
    fun completePrepareTask(@PathVariable id: Long): ResponseEntity<Long>
    {
        return ResponseEntity.ok(useCase.completePrepareOrderTask(id))
    }
}