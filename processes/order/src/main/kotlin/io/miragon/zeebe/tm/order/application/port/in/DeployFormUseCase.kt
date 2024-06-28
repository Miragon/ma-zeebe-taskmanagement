package io.miragon.zeebe.tm.order.application.port.`in`

import io.miragon.zeebe.tm.order.domain.FormDeployment

interface DeployFormUseCase
{
    fun deployProcessStartForm(form: FormDeployment)

    fun deployCheckOrderForm(form: FormDeployment)

    fun deployPrepareOrderForm(form: FormDeployment)
}