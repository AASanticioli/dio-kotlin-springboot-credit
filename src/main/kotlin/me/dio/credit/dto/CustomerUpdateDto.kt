package me.dio.credit.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PositiveOrZero
import me.dio.credit.entity.Address
import me.dio.credit.entity.Customer

data class CustomerUpdateDto(
    @field:NotBlank(message = "First name cannot be empty")
    val firstName: String?,

    @field:NotBlank(message = "Last name cannot be empty")
    val lastName: String?,

    @field:PositiveOrZero(message = "Income must be positive")
    val income: Double?,

    @field:NotBlank(message = "Zip code cannot be empty")
    val addressZipCode: String?,

    @field:NotBlank(message = "Street cannot be empty")
    val addressStreet: String?
) {
    fun toEntity(customer: Customer): Customer {
        val addressNew =
            if (addressZipCode != null && addressStreet != null) Address(addressZipCode, addressStreet) else null
        return customer.copy(
            firstName = this.firstName ?: customer.firstName,
            lastName = this.lastName ?: customer.lastName,
            income = this.income ?: customer.income,
            address = addressNew ?: customer.address
        )
    }
}