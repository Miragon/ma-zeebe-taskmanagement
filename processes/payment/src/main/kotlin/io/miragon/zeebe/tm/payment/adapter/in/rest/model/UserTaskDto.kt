package io.miragon.zeebe.tm.payment.adapter.`in`.rest.model

data class UserTaskDto(
    val key: Long,
    val elementId: String,
    val processInstanceKey: Long,
    val bpmnProcessId: String,
    val processDefinitionKey: Long,
    val variables: Map<String, Any>,
    var taskState: String,
    var assignee: String?,
)


