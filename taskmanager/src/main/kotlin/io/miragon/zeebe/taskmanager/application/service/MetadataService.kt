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
                processId = key,
                label = value.label,
                startable = value.startable,
                startProcessUrl = ProcessApplicationMetadata.buildUrl(value.baseUrl, value.processUrl.start),
                startProcessFormUrl = ProcessApplicationMetadata.buildUrl(
                    value.baseUrl,
                    value.processUrl.form
                ),
                loadTaskUrl = ProcessApplicationMetadata.buildUrl(value.baseUrl, value.userTaskUrl.load),
                completeTaskUrl = ProcessApplicationMetadata.buildUrl(
                    value.baseUrl,
                    value.userTaskUrl.complete
                ),
                updateTaskUrl = ProcessApplicationMetadata.buildUrl(
                    value.baseUrl,
                    value.userTaskUrl.update
                )
            )
        }

        return Response(processApplications)
    }
}
