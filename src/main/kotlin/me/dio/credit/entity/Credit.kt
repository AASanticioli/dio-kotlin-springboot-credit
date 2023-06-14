package me.dio.credit.entity

import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

data class Credit(
    val id: Long,
    val creditCode: UUID = UUID.randomUUID(),
    val status: CreditStatus,
    val creditValue: BigDecimal = BigDecimal.ZERO,
    val dayFirstOfInstallment: LocalDate,
    val numberOfInstallments: Int = 0,
    val customer: Customer
)
enum class CreditStatus {
    IN_PROGRESS, APPROVED, REJECTED
}