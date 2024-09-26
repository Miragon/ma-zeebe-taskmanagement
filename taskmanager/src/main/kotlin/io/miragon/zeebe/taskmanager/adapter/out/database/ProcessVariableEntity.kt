package io.miragon.zeebe.taskmanager.adapter.out.database

import io.miragon.zeebe.taskmanager.domain.ProcessVariable
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "process_variable")
class ProcessVariableEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val processInstanceKey: Long,

    val bpmnProcessId: String,

    val processDefinitionKey: Long,

    val name: String,

    var value: Any,
)
{
    fun toDomain(): ProcessVariable
    {
        return ProcessVariable(
            processInstanceKey = processInstanceKey,
            name = name,
            value = value,
            bpmnProcessId = null,
            processDefinitionKey = null,
        )
    }
}