package me.dio.credit.repository

import me.dio.credit.entity.Credit
import me.dio.credit.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CreditRepository : JpaRepository<Credit, Long> {
    fun findByCreditCode(creditCode: UUID): Optional<Credit>

    @Query(value = "select c from Credit c where c.customer.id = :customerId")
    fun findAllByCustomerId(@Param("customerId") customerId: Long): List<Credit>
}