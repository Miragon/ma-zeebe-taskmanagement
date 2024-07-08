package io.miragon.zeebe.taskmanager.application.port.`in`

interface MetadataUseCase
{
    fun getMetadata(): Response

    data class Response(
        val processApplications: List<ProcessApplication>
    )

    data class ProcessApplication(
        private val id: String,
        private val baseUrl: String,
        private val startable: Boolean? = false,
        private val startProcessUrl: String?,
        private val startProcessFormUrl: String?,
        private val loadDataUrl: String?,
        private val completeTaskUrl: String?,
        private val updateTaskUrl: String?,
    )
}