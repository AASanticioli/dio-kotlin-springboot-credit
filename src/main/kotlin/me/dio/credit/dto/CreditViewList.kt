package me.dio.credit.dto

import me.dio.credit.entity.Credit
import java.util.*

/**
 * DTO for {@link me.dio.credit.entity.Credit}
 */
data class CreditViewList(
    val creditCode: UUID,
    val creditValue: Double,
    val numberOfInstallments: Int
) {
    constructor(credit: Credit) : this(
        creditCode = credit.code,
        creditValue = credit.creditValue,
        numberOfInstallments = credit.numberOfInstallments
    )
}