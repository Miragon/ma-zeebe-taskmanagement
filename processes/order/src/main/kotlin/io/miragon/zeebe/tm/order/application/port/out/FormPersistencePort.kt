package io.miragon.zeebe.tm.order.application.port.out

import io.miragon.zeebe.tm.order.domain.Form
import io.miragon.zeebe.tm.order.domain.FormDeployment

interface FormPersistencePort
{
    fun loadCheckOrderForm(): Form

    fun loadPrepareOrderForm(): Form

    fun saveCheckOrderForm(form: FormDeployment)

    fun savePrepareOrderForm(form: FormDeployment)
}