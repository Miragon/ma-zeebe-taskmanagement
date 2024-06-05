package io.miragon.order.adapter.`in`.rest

data class OrderDto(
    val customerName: String,
    val deliveryAddress: AddressDto,
    val items: List<ItemDto>,
)

data class AddressDto(
    val street: String,
    val city: String,
    val zipCode: String,
)

data class ItemDto(
    val id: Long,
    val quantity: Int,
)

interface Form

data class JsonForm<T>(
    val schema: Map<String, String>,
    val uischema: Map<String, String>,
    val data: T,
) : Form

data class HtmlForm(
    val html: String,
    val data: Map<String, Any>,
) : Form