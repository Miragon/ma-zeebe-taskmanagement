package io.miragon.zeebe.tm.order.domain

data class Item(
    val id: Long,
    val quantity: Int,
    val deliveryDate: String? = null
)
{
    fun updateDeliveryDate(deliveryDate: String): Item
    {
        return this.copy(deliveryDate = deliveryDate)
    }
}