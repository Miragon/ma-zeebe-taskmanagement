package io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema

data class PrepareOrderSchema(
    val itemCheckList: List<CheckItemDto> = ArrayList(),
    val deliveryDate: String,
) : TaskDto()
