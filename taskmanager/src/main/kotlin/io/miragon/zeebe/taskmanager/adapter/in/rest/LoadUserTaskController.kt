package io.miragon.zeebe.taskmanager.adapter.`in`.rest

import io.miragon.zeebe.taskmanager.application.port.`in`.LoadUserTaskUseCase
import io.miragon.zeebe.tm.tasklist.UserTaskDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest")
class LoadUserTaskController(
    private val loadUserTaskUseCase: LoadUserTaskUseCase
)
{
    @GetMapping("/tasks")
    fun loadTasks(): ResponseEntity<List<UserTaskDto>>
    {
        val tasks = loadUserTaskUseCase.loadByTaskState()

        return ResponseEntity.ok(tasks.map {
            UserTaskDto(
                key = it.key,
                elementId = it.elementId,
                processInstanceKey = it.processInstanceKey,
                bpmnProcessId = it.bpmnProcessId,
                processDefinitionKey = it.processDefinitionKey,
                variables = it.variables,
                taskState = it.taskState.toString(),
                assignee = it.assignee,
            )
        })
    }
}