package io.miragon.zeebe.tm.order

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
// @Deployment(resources = ["classpath:bpmn/*.bpmn"])
class OrderProcessApplication

fun main(args: Array<String>)
{
    runApplication<OrderProcessApplication>(*args)
}