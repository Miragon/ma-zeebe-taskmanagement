package io.miragon.zeebe.taskmanager.domain

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "miranum.tm")
data class ProcessApplicationMetadata(
    val processApplication: Map<String, ProcessApplication>,
)
{
    companion object
    {
        fun buildUrl(baseUrl: String, path: String): String
        {
            return "$baseUrl/$path"
        }
    }

    data class ProcessApplication(
        val label: String,
        val baseUrl: String,
        val startable: Boolean? = false,
        val processUrl: Process?,
        val userTaskUrl: UserTask?,
    )

    data class Process(
        val baseUrl: String,
        val startProcessUrl: String,
        val startProcessFormUrl: String,
    )

    data class UserTask(
        val baseUrl: String,
        val loadDataUrl: String,
        val completeTaskUrl: String,
        val updateTaskUrl: String,
    )
}
