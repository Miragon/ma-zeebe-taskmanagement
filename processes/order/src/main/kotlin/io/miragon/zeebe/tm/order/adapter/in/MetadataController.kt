package io.miragon.zeebe.tm.order.adapter.`in`

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest")
class MetadataController
{
    @Value("\${order.id}")
    private lateinit var id: String

    @Value("\${order.name}")
    private lateinit var name: String

    @Value("\${order.startable}")
    private var startable: Boolean? = null

    @GetMapping("/metadata")
    fun getMetadata(): ResponseEntity<Metadata>
    {
        return ResponseEntity.ok(Metadata(id, name, startable ?: false))
    }

    data class Metadata(
        val id: String,
        val name: String,
        val startable: Boolean
    )
}
