package io.miragon.zeebe.taskmanager.adapter.out.database

import io.miragon.zeebe.taskmanager.domain.ProcessVariable
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

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
            processInstanceKey = id,
            name = name,
            value = value,
        )
    }
}