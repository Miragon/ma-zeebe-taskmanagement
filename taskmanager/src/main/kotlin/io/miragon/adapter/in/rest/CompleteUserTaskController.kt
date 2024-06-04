package io.miragon.adapter.`in`.rest

import io.miragon.application.port.`in`.CompleteUserTaskUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/complete")
class CompleteUserTaskController(
    private val completeUserTaskUseCase: CompleteUserTaskUseCase
)
{
    @PostMapping("/{id}")
    fun completeTask(@PathVariable id: Long, @RequestBody variables: Map<String, Any>)
    {
        completeUserTaskUseCase.complete(CompleteUserTaskUseCase.CompleteUserTaskCommand(id, variables))
    }
}