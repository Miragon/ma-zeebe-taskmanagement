package io.miragon.zeebe.tm.payment.adapter.`in`

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest")
class MetadataController
{
    @Value("\${payment.id}")
    private lateinit var id: String

    @Value("\${payment.name}")
    private lateinit var name: String

    @Value("\${payment.startable}")
    private var startable: Boolean? = null

    @GetMapping("/metadata")
    fun getMetadata(): ResponseEntity<Metadata>
    {
        return ResponseEntity.ok(Metadata(id, name, startable ?: true))
    }

    data class Metadata(
        val id: String,
        val name: String,
        val startable: Boolean
    )
}