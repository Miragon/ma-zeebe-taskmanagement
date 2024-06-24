package io.miragon.zeebe.tm.order.adapter.`in`.form

import io.miragon.order.jsonForm.CheckOrderSchema
import io.miragon.order.jsonForm.PrepareOrderSchema

// TODO: Add dependency on io.miragon.taskmanager
data class UserTaskDto(
    val key: Long,
    val elementId: String,
    val processInstanceKey: Long,
    val bpmnProcessId: String,
    val processDefinitionKey: Long,
    val variables: Map<String, Any>,
    var taskState: String,
    var assignee: String = "",
)

data class CheckOrderDto(
    val userTask: UserTaskDto,
    val formData: CheckOrderSchema,
)

data class PrepareOrderDto(
    val userTask: UserTaskDto,
    val formData: PrepareOrderSchema,
)

data class FormDeploymentDto(
    val version: Double,
    val type: String,
    val form: String,
)
