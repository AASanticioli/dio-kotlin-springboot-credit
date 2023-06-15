package me.dio.credit.service

import me.dio.credit.entity.Customer
import me.dio.credit.repository.CustomerRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class CustomerServiceImpl(val repository: CustomerRepository) : CustomerService {
    override fun save(customer: Customer): Customer {
        return repository.save(customer)
    }

    override fun findById(id: Long): Customer {
        return repository.findById(id).orElseThrow {
            throw RuntimeException("Id $id not found")
        }
    }

    override fun delete(customer: Customer) {
        customer.id?.let { repository.deleteById(it) }
    }
}