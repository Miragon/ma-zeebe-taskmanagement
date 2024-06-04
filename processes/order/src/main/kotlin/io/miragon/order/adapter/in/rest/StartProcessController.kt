package io.miragon.order.adapter.`in`.rest

import io.miragon.order.application.port.`in`.StartProcessUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/process")
class StartProcessController(private val useCase: StartProcessUseCase)
{
    @PutMapping("/start")
    fun startProcess(): ResponseEntity<String>
    {
        return ResponseEntity.ok(useCase.startProcess())
    }
}
