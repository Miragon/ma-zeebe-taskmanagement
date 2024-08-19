package io.miragon.zeebe.tm.order.adapter.out.kafka

import io.miragon.zeebe.tm.order.application.port.out.RequestPaymentPort
import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class RequestPaymentAdapter : RequestPaymentPort
{
    private val log = KotlinLogging.logger {}

    override fun publish(orderId: String, amount: BigDecimal): Boolean
    {
        // TODO: Implement Kafka
        log.info { "Requesting payment for order $orderId with amount $amount" }
        //val request = PaymentRequest(orderId, amount)
        // kafkaTemplate.send("payment-request", request)
        return true
    }
}