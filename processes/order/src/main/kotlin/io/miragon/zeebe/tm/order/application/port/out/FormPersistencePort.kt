package io.miragon.zeebe.tm.order.application.port.out

import io.miragon.zeebe.tm.order.domain.Form
import io.miragon.zeebe.tm.order.domain.FormDeployment

interface FormPersistencePort
{
    fun loadProcessStartForm(): Form

    fun loadCheckOrderForm(): Form

    fun loadPrepareOrderForm(): Form

    fun saveProcessStartForm(form: FormDeployment)

    fun saveCheckOrderForm(form: FormDeployment)

    fun savePrepareOrderForm(form: FormDeployment)
}