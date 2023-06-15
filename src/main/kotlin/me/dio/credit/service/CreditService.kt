package me.dio.credit.service

import me.dio.credit.entity.Credit
import me.dio.credit.entity.Customer
import java.util.UUID

interface CreditService {
    fun save(credit: Credit): Credit;
    fun findAllByCustomer(customer: Customer): List<Credit>;

    fun findByCreditCode(customer: Customer, creditCode: UUID): Credit?;

}