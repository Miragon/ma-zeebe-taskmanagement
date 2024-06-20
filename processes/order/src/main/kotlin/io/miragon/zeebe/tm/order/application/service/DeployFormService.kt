package io.miragon.zeebe.tm.order.application.service

import io.miragon.zeebe.tm.order.application.port.`in`.DeployFormUseCase
import io.miragon.zeebe.tm.order.application.port.out.FormPersistencePort
import io.miragon.zeebe.tm.order.domain.FormDeployment
import org.springframework.stereotype.Service

@Service
class DeployFormService(
    private val formPersistencePort: FormPersistencePort
) : DeployFormUseCase
{
    override fun deployCheckOrderForm(form: FormDeployment)
    {
        formPersistencePort.saveCheckOrderForm(form)
    }

    override fun deployPrepareOrderForm(form: FormDeployment)
    {
        formPersistencePort.savePrepareOrderForm(form)
    }
}