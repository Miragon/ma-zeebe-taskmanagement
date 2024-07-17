package io.miragon.zeebe.tm.order.adapter.`in`.rest.model

data class CompleteTaskDto<T>(
    val userTask: UserTaskDto,
    val formData: T
)