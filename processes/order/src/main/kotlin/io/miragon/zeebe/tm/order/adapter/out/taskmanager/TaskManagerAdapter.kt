package io.miragon.zeebe.tm.order.adapter.out.taskmanager

import io.miragon.libs.taskmanager.client.apis.CompleteTaskControllerApi
import io.miragon.zeebe.tm.order.application.port.out.TaskManagerPort
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestClientResponseException

@Component
class TaskManagerAdapter(
    private val restClient: RestClient
) : TaskManagerPort
{
    private val log = KotlinLogging.logger {}

    override fun markTaskAsCompleted(taskId: Long): Boolean
    {
        val apiClient = CompleteTaskControllerApi(restClient)

        try
        {
            return apiClient.completeTask(taskId)
        } catch (e: RestClientResponseException)
        {
            log.error { "Failed to complete task with id $taskId" }
            throw e
        }
    }
}