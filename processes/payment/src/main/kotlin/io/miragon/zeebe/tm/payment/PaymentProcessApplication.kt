package io.miragon.zeebe.tm.payment

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PaymentProcessApplication

fun main(args: Array<String>)
{
    runApplication<PaymentProcessApplication>(*args)
}