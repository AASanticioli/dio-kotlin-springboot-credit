package me.dio.credit.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PositiveOrZero
import me.dio.credit.entity.Address
import me.dio.credit.entity.Customer
import org.hibernate.validator.constraints.br.CPF

data class CustomerDto(
    @field:NotEmpty(message = "First name cannot be empty")
    val firstName: String,

    @field:NotEmpty(message = "Last name cannot be empty")
    val lastName: String,

    @field:NotEmpty(message = "CPF cannot be empty")
    @field:CPF(message = "Invalid CPS")
    val cpf: String,

    @field:NotNull(message = "Income must be informed")
    @field:PositiveOrZero(message = "Income must be positive")
    val income: Double,

    @field:NotEmpty(message = "E-mail cannot be empty")
    @field:Email(message = "Invalid e-mail")
    val email: String,

    @field:NotEmpty(message = "Password cannot be empty")
    val password: String,

    @field:NotEmpty(message = "Zip code cannot be empty")
    val zipCode: String,

    @field:NotEmpty(message = "Street cannot be empty")
    val street: String,
) {
    fun toEntity(): Customer {
        val customerAddress = Address(
            zipCode = this.zipCode,
            street = this.street
        )
        return Customer(
            firstName = this.firstName,
            lastName = this.lastName,
            cpf = this.cpf,
            income = this.income,
            email = this.email,
            password = this.password,
            address = customerAddress
        )
    }
}