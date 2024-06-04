package io.miragon.taskmanager.adapter.`in`.rest

import io.miragon.taskmanager.application.port.`in`.LoadUserTaskUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/load")
class LoadUserTaskController(
    private val loadUserTaskUseCase: LoadUserTaskUseCase
)
{
    @GetMapping("/tasks")
    fun loadTasks(): ResponseEntity<List<UserTaskDto>>
    {
        val tasks = loadUserTaskUseCase.load()

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

data class UserTaskDto(
    val key: Long,
    val elementId: String,
    val processInstanceKey: Long,
    val bpmnProcessId: String,
    val processDefinitionKey: Long,
    val variables: Map<String, Any> = emptyMap(),
    var taskState: String,
    var assignee: String?,
)