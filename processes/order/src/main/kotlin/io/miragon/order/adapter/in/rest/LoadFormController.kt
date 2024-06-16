package io.miragon.order.adapter.`in`.rest

import io.miragon.order.application.port.`in`.LoadFormUseCase
import io.miragon.order.domain.Form
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/task")
class LoadFormController(
    private val loadCheckOrderFormUseCase: LoadFormUseCase.LoadCheckOrderFormUseCase,
    private val loadPrepareOrderFormUseCase: LoadFormUseCase.LoadPrepareOrderFormUseCase,
)
{
    @PostMapping("/checkOrder")
    fun loadCheckOrderForm(@RequestBody userTask: UserTaskDto): ResponseEntity<Form>
    {
        val orderId = userTask.variables["orderId"].toString()
        return ResponseEntity.ok(loadCheckOrderFormUseCase.load(orderId))
    }

    @PostMapping("/prepareOrder")
    fun loadPrepareOrderForm(@RequestBody userTask: UserTaskDto): ResponseEntity<Form>
    {
        val orderId = userTask.variables["orderId"].toString()
        return ResponseEntity.ok(loadPrepareOrderFormUseCase.load(orderId))
    }
}