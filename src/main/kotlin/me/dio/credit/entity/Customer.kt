package me.dio.credit.entity

data class Customer(
    val id: Long?,
    val firstName: String,
    val lastName: String,
    val cpf: String,
    val income: Double,
    val email: String,
    val password: String,
    val address: Address,
    val credits: List<Credit> = mutableListOf()
)
