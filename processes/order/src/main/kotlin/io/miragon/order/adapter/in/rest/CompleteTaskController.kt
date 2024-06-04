package io.miragon.order.adapter.`in`.rest

import io.miragon.order.application.port.`in`.CompleteTaskUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/task")
class CompleteTaskController(private val useCase: CompleteTaskUseCase)
{
    @PostMapping("/complete/{id}")
    fun completeTask(@PathVariable id: String): ResponseEntity<Boolean>
    {
        return ResponseEntity.ok(useCase.completeTask(id))
    }
}