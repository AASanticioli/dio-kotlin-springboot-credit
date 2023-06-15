package me.dio.credit.dto

import me.dio.credit.entity.Address
import me.dio.credit.entity.Customer

data class CustomerDto(
    val firstName: String,
    val lastName: String,
    val cpf: String,
    val income: Double,
    val email: String,
    val password: String,
    val zipCode: String,
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