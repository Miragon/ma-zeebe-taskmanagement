package io.miragon.zeebe.tm.order.adapter.`in`.rest.model

import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema.TaskDto

data class CompleteTaskDto<T : TaskDto>(
    val userTask: UserTaskDto,
    val formData: T
)