package io.miragon.order.application.port.`in`

import io.miragon.order.domain.FormDeployment

interface DeployFormUseCase
{
    fun deployCheckOrderForm(form: FormDeployment)

    fun deployPrepareOrderForm(form: FormDeployment)
}