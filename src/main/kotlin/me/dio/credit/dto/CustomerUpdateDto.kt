package me.dio.credit.dto

import me.dio.credit.entity.Address
import me.dio.credit.entity.Customer

data class CustomerUpdateDto(
    val firstName: String?,
    val lastName: String?,
    val income: Double?,
    val addressZipCode: String?,
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