package io.miragon.zeebe.tm.order.adapter.`in`.rest.model

import io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema.FormDataDto

data class CompleteTaskDto<T : FormDataDto>(
    val userTask: UserTaskDto,
    val formData: T
)