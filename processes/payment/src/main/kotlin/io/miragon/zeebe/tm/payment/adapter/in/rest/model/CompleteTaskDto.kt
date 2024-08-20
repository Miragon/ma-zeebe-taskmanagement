package io.miragon.zeebe.tm.payment.adapter.`in`.rest.model

data class CompleteTaskDto<T : FormDataDto>(
    val userTask: UserTaskDto,
    val formData: T
)