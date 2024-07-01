package io.miragon.zeebe.tm.order.adapter.`in`.rest.model

data class PrepareOrderSchema(
    var itemCheckList: List<CheckItemDto> = ArrayList()
)
