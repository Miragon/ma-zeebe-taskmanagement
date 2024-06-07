package io.miragon.order.adapter.`in`.rest

// TODO: Add dependency on io.miragon.taskmanager
data class UserTaskDto(
    val key: Long,
    val elementId: String,
    val processInstanceKey: Long,
    val bpmnProcessId: String,
    val processDefinitionKey: Long,
    val variables: Map<String, Any>,
    var taskState: String,
    var assignee: String?,
)

data class OrderDto(
    val name: String,
    val address: AddressDto,
    val items: List<ItemDto>,
)
{
    data class AddressDto(
        val street: String,
        val city: String,
        val zipCode: String,
    )

    data class ItemDto(
        val id: Long,
        val quantity: Int,
    )
}

data class CheckOrderDto(
    val userTask: UserTaskDto,
    val approved: Boolean,
)

data class PrepareOrderDto(
    val userTask: UserTaskDto,
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
