package io.miragon.zeebe.tm.order.adapter.`in`.rest

import io.miragon.order.jsonForm.CheckOrderSchema
import io.miragon.order.jsonForm.PrepareOrderSchema
import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.UserTaskDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/task")
class CompleteTaskController
{
    @PostMapping("/complete-task")
    fun completeTask(userTask: UserTaskDto, data: Any): ResponseEntity<Unit>
    {
        when (userTask.elementId)
        {
            UserTaskId.CHECK_ORDER.id ->
            {
                val orderId = userTask.variables["orderId"].toString()
                val checkOrderSchema = data as CheckOrderSchema
                return ResponseEntity.ok().build()
            }

            UserTaskId.PREPARE_ORDER.id ->
            {
                val orderId = userTask.variables["orderId"].toString()
                val prepareOrderSchema = data as PrepareOrderSchema
                return ResponseEntity.ok().build()
            }

            else -> return ResponseEntity.badRequest().build()
        }
    }
}
