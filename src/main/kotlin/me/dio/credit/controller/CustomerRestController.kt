package me.dio.credit.controller

import jakarta.validation.Valid
import me.dio.credit.dto.CustomerDto
import me.dio.credit.dto.CustomerUpdateDto
import me.dio.credit.dto.CustomerView
import me.dio.credit.service.CustomerService
import org.springframework.http.HttpRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customers")
class CustomerRestController(
    private val service: CustomerService
) {

    @PostMapping
    fun save(@RequestBody @Valid dto: CustomerDto): ResponseEntity<String> {
        val customer = service.save(dto.toEntity())
        val responseBody = "Customer ${customer.email} saved."
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CustomerView> {
        val customer = service.findById(id)
        val responseBody = CustomerView(customer)
        return ResponseEntity.status(HttpStatus.OK).body(responseBody)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        val customer = service.findById(id)
        service.delete(customer)
    }

    @PatchMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid customerUpdateDto: CustomerUpdateDto): ResponseEntity<CustomerView> {
        val customerOld = service.findById(id)
        val customerNew =  customerUpdateDto.toEntity(customerOld)
        val customerUpdated = service.save(customerNew)
        val responseBody = CustomerView(customerUpdated)
        return ResponseEntity.status(HttpStatus.OK).body(responseBody)
    }
}