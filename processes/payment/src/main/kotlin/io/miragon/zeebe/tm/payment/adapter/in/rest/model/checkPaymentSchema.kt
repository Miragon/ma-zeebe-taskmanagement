package io.miragon.zeebe.tm.payment.adapter.`in`.rest.model

import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull
import java.math.BigDecimal

data class CheckPaymentSchema(
    @get:NotNull
    @get:NotBlank(message = "Please provide the id of the invoice.")
    val invoiceId: String,

    @get:NotNull
    @get:NotBlank(message = "Please provide the id of the corresponding order.")
    val orderId: String,

    @get:NotNull
    @get:NotBlank(message = "Please provide the amount of money that was received.")
    val amount: BigDecimal,

    val isAccepted: Boolean = false,
) : FormDataDto()
