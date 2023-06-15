package me.dio.credit.service

import me.dio.credit.entity.Customer

interface CustomerService {

    fun save(customer: Customer): Customer

    fun findById(id: Long): Customer

    fun delete(customer: Customer)
}