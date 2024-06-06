package io.miragon.order.adapter.`in`.rest

import io.miragon.order.application.port.`in`.LoadFormUseCase
import io.miragon.order.domain.Form
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/task/load")
class LoadFormController(
    private val loadCheckOrderFormUseCase: LoadFormUseCase.LoadCheckOrderFormUseCase,
    private val loadPrepareOrderFormUseCase: LoadFormUseCase.LoadPrepareOrderFormUseCase,
)
{
    @GetMapping("/checkOrder/{id}")
    fun loadCheckOrderForm(@PathVariable id: Long): ResponseEntity<Form>
    {
        return ResponseEntity.ok(loadCheckOrderFormUseCase.load(id))
    }

    @GetMapping("/prepareOrder/{id}")
    fun loadPrepareOrderForm(@PathVariable id: Long): ResponseEntity<Form>
    {
        return ResponseEntity.ok(loadPrepareOrderFormUseCase.load(id))
    }
}