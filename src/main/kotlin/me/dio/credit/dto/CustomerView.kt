package me.dio.credit.dto

import me.dio.credit.entity.Customer

data class CustomerView(
    val firstName: String,
    val lastName: String,
    val cpf: String,
    val email: String,
    val income: Double,
    val addressZipCode: String,
    val addressStreet: String
) {
    constructor(customer: Customer) : this(
        firstName = customer.firstName,
        lastName = customer.lastName,
        cpf = customer.cpf,
        email = customer.email,
        income = customer.income,
        addressZipCode = customer.address.zipCode,
        addressStreet = customer.address.street
    )
}