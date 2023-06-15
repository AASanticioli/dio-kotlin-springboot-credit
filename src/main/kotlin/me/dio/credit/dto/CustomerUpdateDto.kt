package me.dio.credit.dto

import me.dio.credit.entity.Address
import me.dio.credit.entity.Customer

data class CustomerUpdateDto(
    val firstName: String,
    val lastName: String,
    val income: Double,
    val addressZipCode: String,
    val addressStreet: String
) {
    fun toEntity(customer: Customer): Customer {
        val addressNew = Address(addressZipCode, addressStreet)
        return customer.copy(
            firstName = this.firstName,
            lastName = this.lastName,
            income = this.income,
            address = addressNew
        )
    }
}