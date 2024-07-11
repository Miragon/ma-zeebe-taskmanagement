package io.miragon.zeebe.taskmanager.application.port.`in`

interface MetadataUseCase
{
    fun getMetadata(): Response

    data class Response(
        val processApplications: List<ProcessApplication>
    )

    data class ProcessApplication(
        val processId: String,
        val label: String,
        val startable: Boolean,
        val startProcessUrl: String,
        val startProcessFormUrl: String,
        val loadTaskUrl: String,
        val completeTaskUrl: String,
        val updateTaskUrl: String,
    )
}