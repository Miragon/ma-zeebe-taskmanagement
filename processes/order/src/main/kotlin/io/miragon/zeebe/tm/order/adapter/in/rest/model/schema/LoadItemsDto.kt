package io.miragon.zeebe.tm.order.adapter.`in`.rest.model.schema

data class LoadItemsDto(
    val items: List<ItemDto>,
) : TaskDto()