package io.miragon.zeebe.taskmanager.adapter.`in`.rest

import io.miragon.zeebe.taskmanager.application.port.`in`.CompleteTaskUseCase
import io.miragon.zeebe.taskmanager.application.port.`in`.CompleteTaskUseCase.Command
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest")
class CompleteTaskController(
    private val useCase: CompleteTaskUseCase
)
{
    @PostMapping("/task/complete")
    fun completeTask(@RequestBody taskId: Long): Boolean
    {
        val command = Command(taskId)
        return useCase.complete(command)
    }
}