package io.miragon.zeebe.taskmanager.adapter.`in`.rest

import io.miragon.zeebe.taskmanager.adapter.`in`.rest.model.MetadataDto
import io.miragon.zeebe.taskmanager.application.port.`in`.MetadataUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest")
class LoadMetadataController(
    private val metadataUseCase: MetadataUseCase
)
{
    @GetMapping("/metadata")
    fun loadMetadata(): ResponseEntity<MetadataDto>
    {
        val processApplications = metadataUseCase.getMetadata().processApplications
        return ResponseEntity.ok(MetadataDto(processApplications))
    }
}
