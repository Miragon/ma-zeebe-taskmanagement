package io.miragon.zeebe.taskmanager.application.service

import io.miragon.zeebe.taskmanager.application.port.`in`.MetadataUseCase
import io.miragon.zeebe.taskmanager.application.port.`in`.MetadataUseCase.ProcessApplication
import io.miragon.zeebe.taskmanager.application.port.`in`.MetadataUseCase.Response
import io.miragon.zeebe.taskmanager.domain.ProcessApplicationMetadata
import org.springframework.stereotype.Service

@Service
class MetadataService(
    private val metadata: ProcessApplicationMetadata
) : MetadataUseCase
{
    override fun getMetadata(): Response
    {
        val processApplications = metadata.processApplication.map { (key, value) ->
            ProcessApplication(
                id = key,
                baseUrl = value.baseUrl,
                startable = value.startable,
                startProcessUrl = value.processUrl?.let {
                    ProcessApplicationMetadata.buildUrl(
                        value.processUrl.baseUrl,
                        value.processUrl.startProcessUrl
                    )
                },
                startProcessFormUrl = value.processUrl?.let {
                    ProcessApplicationMetadata.buildUrl(
                        value.processUrl.baseUrl,
                        value.processUrl.startProcessFormUrl
                    )
                },
                loadDataUrl = value.userTaskUrl?.let {
                    ProcessApplicationMetadata.buildUrl(value.userTaskUrl.baseUrl, value.userTaskUrl.loadDataUrl)
                },
                completeTaskUrl = value.userTaskUrl?.let {
                    ProcessApplicationMetadata.buildUrl(
                        value.userTaskUrl.baseUrl,
                        value.userTaskUrl.completeTaskUrl
                    )
                },
                updateTaskUrl = value.userTaskUrl?.let {
                    ProcessApplicationMetadata.buildUrl(
                        value.userTaskUrl.baseUrl,
                        value.userTaskUrl.updateTaskUrl
                    )
                }
            )
        }

        return Response(processApplications)
    }
}
