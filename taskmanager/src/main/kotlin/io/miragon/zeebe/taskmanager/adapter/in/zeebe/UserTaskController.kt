package io.miragon.zeebe.taskmanager.adapter.`in`.zeebe

import io.miragon.zeebe.taskmanager.application.port.`in`.UserTaskUseCase
import io.miragon.zeebe.taskmanager.application.port.`in`.UserTaskUseCase.SaveCommand
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/task")
class UserTaskController(
    private val useCase: UserTaskUseCase,
)
{
    private val log = KotlinLogging.logger {}

    @PostMapping("/save")
    fun saveTask(@RequestBody job: JobRecordDto): ResponseEntity<String>
    {
        log.info { "Saving task with key: ${job.key}" }

        try
        {
            useCase.save(
                SaveCommand(
                    key = job.key,
                    elementId = job.elementId,
                    processInstanceKey = job.processInstanceKey,
                    bpmnProcessId = job.bpmnProcessId,
                    processDefinitionKey = job.processDefinitionKey,
                    variables = job.variables,
                )
            )
            return ResponseEntity.ok("Task saved")
        } catch (e: Exception)
        {
            return ResponseEntity.badRequest().body(e.message)
        }
    }

    @PostMapping("/update")
    fun updateTask(@RequestBody job: JobRecordDto): ResponseEntity<String>
    {
        log.info { "Updating task with key: ${job.key} ${job.intent}" }
        try
        {
            useCase.updateState(job.key, job.intent)
            return ResponseEntity.ok("Task updated")
        } catch (e: Exception)
        {
            return ResponseEntity.badRequest().body(e.message)
        }
    }
}