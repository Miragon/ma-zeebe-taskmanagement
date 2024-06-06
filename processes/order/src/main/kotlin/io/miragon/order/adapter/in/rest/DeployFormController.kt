package io.miragon.order.adapter.`in`.rest

import io.miragon.order.application.port.`in`.DeployFormUseCase
import io.miragon.order.domain.FormDeployment
import io.miragon.order.domain.FormType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/deploy")
class DeployFormController(
    private val deployFormUseCase: DeployFormUseCase
)
{
    @PostMapping("/checkOrder")
    fun deployCheckOrderForm(@RequestBody form: FormDeploymentDto)
    {
        deployFormUseCase.deployCheckOrderForm(buildFormDeployment(form))
    }

    @PostMapping("/prepareOrder")
    fun deployPrepareOrderForm(@RequestBody form: FormDeploymentDto)
    {
        deployFormUseCase.deployPrepareOrderForm(buildFormDeployment(form))
    }

    private fun buildFormDeployment(form: FormDeploymentDto): FormDeployment
    {
        return FormDeployment(
            version = form.version,
            form = form.form,
            type = FormType.valueOf(form.type)
        )
    }
}