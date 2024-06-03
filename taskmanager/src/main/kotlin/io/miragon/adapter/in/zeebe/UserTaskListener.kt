package io.miragon.adapter.`in`.zeebe

import io.camunda.zeebe.client.api.response.ActivatedJob
import io.camunda.zeebe.spring.client.annotation.JobWorker
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class UserTaskListener
{
    val log = KotlinLogging.logger { }

    @JobWorker(type = "io.camunda.zeebe:userTask", timeout = 10000, autoComplete = false)
    fun receiveTask(job: ActivatedJob)
    {
        log.info { "Received task: ${job.key}" }
    }
}