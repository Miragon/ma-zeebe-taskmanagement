package io.miragon.zeebe.tm.payment.application.port.out

import io.miragon.zeebe.tm.payment.domain.Form

interface FormPersistencePort
{
    fun readCheckPaymentForm(path: String): Form.JsonForm
}