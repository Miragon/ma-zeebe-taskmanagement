package io.miragon.order.application.port.out

import io.miragon.order.domain.Form
import io.miragon.order.domain.FormDeployment

interface FormPersistencePort
{
    fun loadCheckOrderForm(): Form

    fun loadPrepareOrderForm(): Form

    fun saveCheckOrderForm(form: FormDeployment)

    fun savePrepareOrderForm(form: FormDeployment)
}