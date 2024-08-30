package io.miragon.zeebe.tm.exporter

data class JobRecordDto(
    val key: Long,
    val intent: String,
    val elementId: String,
    val processInstanceKey: Long,
    val bpmnProcessId: String,
    val processDefinitionKey: Long,
    val variables: Map<String, Any> = emptyMap(),
)