package me.dio.credit.controller

import jakarta.validation.Valid
import me.dio.credit.dto.CreditDto
import me.dio.credit.dto.CreditView
import me.dio.credit.dto.CreditViewList
import me.dio.credit.entity.Customer
import me.dio.credit.service.CreditService
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
class CreditRestController(private val service: CreditService) {

    @PostMapping
    fun save(@RequestBody @Valid creditDto: CreditDto): ResponseEntity<String> {
        val credit = service.save(creditDto.toEntity())
        val responseBody = "Credit ${credit.code} saved for ${credit.customer.firstName}"
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody)
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam(value = "customerId") customerId: Long): ResponseEntity<List<CreditViewList>> {
        val creditCustomer = Customer(id = customerId)
        val responseBody = service
            .findAllByCustomer(creditCustomer)
            .stream()
            .map { credit -> CreditViewList(credit) }
            .collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(responseBody)
    }

    @GetMapping("/{code}")
    fun findByCode(
        @RequestParam(value = "customerId") customerId: Long,
        @PathVariable code: UUID
    ): ResponseEntity<CreditView> {

        val customer = Customer(id = customerId)
        val credit = service.findByCreditCode(customer = customer, code = code)
        val responseBody = CreditView(credit)
        return ResponseEntity.status(HttpStatus.OK).body(responseBody)

    }
}