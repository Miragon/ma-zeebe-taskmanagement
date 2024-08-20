package io.miragon.zeebe.tm.payment.adapter.`in`.rest

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.miragon.zeebe.tm.payment.adapter.`in`.rest.model.CheckPaymentSchema
import io.miragon.zeebe.tm.payment.adapter.`in`.rest.model.FormDto
import io.miragon.zeebe.tm.payment.adapter.`in`.rest.model.UserTaskDto
import io.miragon.zeebe.tm.payment.application.port.`in`.LoadCheckPaymentTaskUseCase
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/task")
class LoadTaskController(
    private val useCase: LoadCheckPaymentTaskUseCase,
)
{
    private val logger = KotlinLogging.logger {}

    private val mapper = jacksonObjectMapper()

    private val checkPaymentFormPath = "/forms/CheckPaymentSchema.form.json"

    @PostMapping("/load")
    fun loadData(@RequestBody userTask: UserTaskDto): ResponseEntity<FormDto>
    {
        val userTaskId = userTask.elementId

        logger.info { "Loading form for user task with id: $userTaskId" }

        return when (userTaskId)
        {
            UserTaskId.CHECK_PAYMENT.id ->
            {
                return ResponseEntity.ok(loadCheckPayment(userTask))
            }

            else -> ResponseEntity.badRequest().build()
        }
    }

    private fun loadCheckPayment(userTask: UserTaskDto): FormDto.JsonForm<CheckPaymentSchema>
    {
        val invoiceId = userTask.variables["invoiceId"].toString()
        val command = LoadCheckPaymentTaskUseCase.Command(
            invoiceId,
            filePath = checkPaymentFormPath
        )
        val response = useCase.load(command)

        val (form, invoice) = response
        val schema = mapper.writeValueAsString(form.schema)
        val uiSchema = mapper.writeValueAsString(form.uischema)

        val formData = CheckPaymentSchema(
            invoiceId = invoiceId,
            orderId = invoice.orderId,
            amount = invoice.amount,
        )

        return FormDto.JsonForm(
            schema = schema,
            uiSchema = uiSchema,
            updatable = form.updatable,
            formData = formData,
        )
    }
}
