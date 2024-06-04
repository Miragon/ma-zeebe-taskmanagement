package io.miragon.application.port.out

interface CompleteUserTaskPort
{
    fun complete(key: Long, variables: Map<String, Any>)
}