package io.miragon.zeebe.taskmanager.adapter.`in`.zeebe

data class JobRecordDto(
    val key: Long,
    val intent: String,
    val elementId: String,
    val processInstanceKey: Long,
    val bpmnProcessId: String,
    val processDefinitionKey: Long,
    val variables: Map<String, Any> = emptyMap(),
)