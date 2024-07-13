package io.miragon.zeebe.tm.order.adapter.`in`.rest.model

data class CompleteTaskDto(
    val userTask: UserTaskDto,
    val formData: Any
)