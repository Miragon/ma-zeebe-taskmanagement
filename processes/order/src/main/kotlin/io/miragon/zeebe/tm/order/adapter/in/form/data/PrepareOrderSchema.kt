package io.miragon.zeebe.tm.order.adapter.`in`.form.data

data class PrepareOrderSchema(
    var itemCheckList: List<CheckItemDto> = ArrayList()
)
