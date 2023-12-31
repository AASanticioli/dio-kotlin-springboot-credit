package me.dio.credit.service

import me.dio.credit.entity.Credit
import me.dio.credit.entity.Customer
import me.dio.credit.repository.CreditRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*
import kotlin.jvm.optionals.getOrElse

@Service
class CreditServiceImpl(val repository: CreditRepository, val customerService: CustomerService) : CreditService {
    override fun save(credit: Credit): Credit {
        val customer =
            credit.customer.id?.let { customerService.findById(it) } ?: throw RuntimeException("Customer not found")
        return repository.save(credit.copy(customer = customer))
    }

    override fun findAllByCustomer(customer: Customer): List<Credit> {
        return customer.id?.let { repository.findAllByCustomerId(it) } ?: listOf()
    }

    override fun findByCreditCode(customer: Customer, code: UUID): Credit {
        val credit = repository.findByCode(code).getOrElse { throw RuntimeException("Credit not found") }
        return if (credit.customer == customer) credit else throw RuntimeException("This credit belongs to another customer")
    }
}