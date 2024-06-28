package io.miragon.zeebe.tm.order.adapter.`in`.form.model

data class PrepareOrderSchema(
    var itemCheckList: List<CheckItemDto> = ArrayList()
)
