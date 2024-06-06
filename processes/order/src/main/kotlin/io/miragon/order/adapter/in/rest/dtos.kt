package io.miragon.order.adapter.`in`.rest

data class OrderDto(
    val name: String,
    val address: AddressDto,
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

data class CheckOrderDto(
    val approved: Boolean,
)

data class PrepareOrderDto(
    val items: List<ItemCheckDto>,
)
{
    data class ItemCheckDto(
        val id: Long,
        val quantity: Int,
        val isAvailable: Boolean,
        val deliveryDate: String,
    )
}

data class FormDeploymentDto(
    val version: Double,
    val type: String,
    val form: String,
)
