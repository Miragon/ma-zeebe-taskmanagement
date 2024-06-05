package io.miragon.order.adapter.`in`.rest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/load")
class LoadDataController
{
    @GetMapping("/checkOrder/{id}")
    fun loadCheckOrderForm(@PathVariable id: Long): ResponseEntity<Form>
    {
        TODO("Not yet implemented")
    }

    @GetMapping("/prepareOrder/{id}")
    fun loadPrepareOrderForm(@PathVariable id: Long): ResponseEntity<Form>
    {
        TODO("Not yet implemented")
    }
}