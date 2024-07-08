package io.miragon.zeebe.tm.order.adapter.`in`.rest

import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.CheckOrderSchema
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.PrepareOrderSchema
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.UserTaskDto
import io.miragon.zeebe.tm.order.application.port.`in`.CompleteCheckOrderTaskUseCase
import io.miragon.zeebe.tm.order.application.port.`in`.CompletePrepareOrderTaskUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/task")
class CompleteTaskController(
    private val completeCheckOrderTaskUseCase: CompleteCheckOrderTaskUseCase,
    private val completePrepareOrderTaskUseCase: CompletePrepareOrderTaskUseCase,
)
{
    @PostMapping("/complete-task")
    fun completeTask(userTask: UserTaskDto, data: Any): ResponseEntity<Long>
    {
        return when (userTask.elementId)
        {
            UserTaskId.CHECK_ORDER.id ->
            {
                ResponseEntity.ok(this.completeCheckOrderTask(userTask, data as CheckOrderSchema))
            }

            UserTaskId.PREPARE_ORDER.id ->
            {
                ResponseEntity.ok(this.completePrepareOrderTask(userTask, data as PrepareOrderSchema))
            }

            else -> ResponseEntity.badRequest().build()
        }
    }

    private fun completeCheckOrderTask(userTask: UserTaskDto, data: CheckOrderSchema): Long
    {
        val orderId = userTask.variables["orderId"].toString()
        val command = CompleteCheckOrderTaskUseCase.Command(
            userTask.key,
            orderId,
            data.isOrderValid
        )
        return completeCheckOrderTaskUseCase.complete(command)
    }

    private fun completePrepareOrderTask(userTask: UserTaskDto, data: PrepareOrderSchema): Long
    {
        val orderId = userTask.variables["orderId"].toString()
        val items = data.itemCheckList.map { it.toItem() }
        val command = CompletePrepareOrderTaskUseCase.Command(
            userTask.key,
            orderId,
            items
        )
        return completePrepareOrderTaskUseCase.complete(command)
    }
}
