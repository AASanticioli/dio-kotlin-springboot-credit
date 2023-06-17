package me.dio.credit.dto

import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.PositiveOrZero
import me.dio.credit.entity.Address
import me.dio.credit.entity.Credit
import me.dio.credit.entity.CreditStatus
import me.dio.credit.entity.Customer
import java.time.LocalDate

data class CreditDto(

    @field:NotNull(message = "Credit value must be informed")
    @field:PositiveOrZero(message = "Credit value must be positive")
    val creditValue: Double,

    @field:NotNull(message = "First day must be informed")
    @field:Future(message = "First day must be in future")
    val dayFirstOfInstallment: LocalDate,

    @field:NotNull(message = "Number of installments must be informed")
    @field:Positive(message = "Number of installments must be positive")
    @field:Min(value = 1, message = "Number of installments must be between 1 and 48")
    @field:Max(value = 48, message = "Number of installments must be between 1 and 48")
    val numberOfInstallments: Int,

    @field:NotNull(message = "Customer identification must be informed")
    @field:Positive(message = "Invalid customer identification")
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