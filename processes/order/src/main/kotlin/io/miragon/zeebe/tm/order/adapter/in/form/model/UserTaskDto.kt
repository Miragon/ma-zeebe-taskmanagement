package io.miragon.zeebe.tm.order.adapter.`in`.form.model

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


