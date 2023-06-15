package me.dio.credit.dto

import me.dio.credit.entity.Address
import me.dio.credit.entity.Credit
import me.dio.credit.entity.CreditStatus
import me.dio.credit.entity.Customer
import java.time.LocalDate

data class CreditDto(
    val creditValue: Double,
    val dayFirstOfInstallment: LocalDate,
    val numberOfInstallments: Int,
    val customerId: Long
){
    fun toEntity(): Credit {
        val customer = Customer(
            id = customerId,
            firstName = "",
            lastName = "",
            cpf = "",
            email = "",
            password = "",
            address = Address(zipCode = "", street = ""),
        )

        return Credit(
            id = null,
            status = CreditStatus.IN_PROGRESS,
            creditValue = creditValue,
            dayFirstOfInstallment = dayFirstOfInstallment,
            numberOfInstallments = numberOfInstallments,
            customer = customer
        )
    }
}