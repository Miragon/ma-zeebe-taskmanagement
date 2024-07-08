package io.miragon.zeebe.tm.order.adapter.`in`.rest.model

import io.miragon.zeebe.tm.order.application.port.`in`.CompleteCheckOrderTaskUseCase
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CheckOrderSchema(
    @get:NotBlank(message = "Name must not be blank")
    val customerName: String,

    val deliveryAddress: AddressDto,

    val items: List<ItemDto> = ArrayList(),

    @get:NotNull
    val isOrderValid: Boolean
)
{
    fun toCommand(taskId: Long, orderId: String): CompleteCheckOrderTaskUseCase.Command
    {
        return CompleteCheckOrderTaskUseCase.Command(
            taskId = taskId,
            orderId = orderId,
            isAccepted = this.isOrderValid
        )
    }
}