package io.miragon.zeebe.taskmanager.application.service

import io.miragon.zeebe.taskmanager.domain.ProcessApplicationMetadata
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest(classes = [MetadataService::class])
@EnableConfigurationProperties(value = [ProcessApplicationMetadata::class])
@TestPropertySource("classpath:application.yml")
class ProcessApplicationMetadataServiceTest
{
    @Autowired
    private lateinit var metadataService: MetadataService

    @Test
    fun getMetadata()
    {
        val metadata = metadataService.getMetadata()
        assert(metadata.processApplications.isNotEmpty())
    }
}