package io.miragon.zeebe.tm.order.adapter.out.database

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "form_data")
@IdClass(FormKey::class)
class FormEntity(
    @Id
    val id: String,

    @Id
    val version: Double,

    val type: String,

    @Column(columnDefinition = "TEXT")
    val form: String,
)

@Embeddable
data class FormKey(
    val id: String,
    val version: Double,
) : Serializable