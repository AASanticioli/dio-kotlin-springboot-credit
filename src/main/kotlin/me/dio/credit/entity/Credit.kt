package me.dio.credit.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "credits")
data class Credit(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(nullable = false, unique = true)
    val creditCode: UUID = UUID.randomUUID(),

    @Enumerated
    val status: CreditStatus,

    @Column(nullable = false)
    val creditValue: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    val dayFirstOfInstallment: LocalDate,

    @Column(nullable = false)
    val numberOfInstallments: Int = 0,

    @ManyToOne(optional = false)
    val customer: Customer
)
enum class CreditStatus {
    IN_PROGRESS, APPROVED, REJECTED
}