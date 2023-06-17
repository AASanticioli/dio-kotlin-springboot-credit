package me.dio.credit.dto

import me.dio.credit.entity.Credit
import me.dio.credit.entity.CreditStatus
import java.util.*

/**
 * DTO for {@link me.dio.credit.entity.Credit}
 */
data class CreditView(
    val creditCode: UUID,
    val status: CreditStatus,
    val creditValue: Double,
    val numberOfInstallments: Int,
    val customerEmail: String,
    val customerIncome: Double
) {
    constructor(credit: Credit) : this(
        creditCode = credit.code,
        status = credit.status,
        creditValue = credit.creditValue,
        numberOfInstallments = credit.numberOfInstallments,
        customerEmail = credit.customer.email,
        customerIncome = credit.customer.income
    )
}