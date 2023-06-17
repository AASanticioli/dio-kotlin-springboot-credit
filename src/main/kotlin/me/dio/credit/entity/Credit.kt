package me.dio.credit.entity

import jakarta.persistence.*
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "credits")
data class Credit(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(nullable = false, unique = true)
    val code: UUID = UUID.randomUUID(),

    @Enumerated
    val status: CreditStatus,

    @Column(nullable = false)
    val creditValue: Double = 0.0,

    @Column(nullable = false)
    val dayFirstOfInstallment: LocalDate,

    @Column(nullable = false)
    val numberOfInstallments: Int = 0,

    @ManyToOne(optional = false)
    val customer: Customer
)
