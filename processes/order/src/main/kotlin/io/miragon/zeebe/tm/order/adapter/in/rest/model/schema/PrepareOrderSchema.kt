package io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema

data class PrepareOrderSchema(
    var itemCheckList: List<CheckItemDto> = ArrayList()
)
