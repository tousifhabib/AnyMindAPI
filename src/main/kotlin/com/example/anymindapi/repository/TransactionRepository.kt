package com.example.anymindapi.repository

import com.example.anymindapi.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.time.ZonedDateTime


@Repository
interface TransactionRepository : JpaRepository <Transaction, Long>{
    @Query("SELECT DATE_TRUNC('hour', tr.date_time) as dateTime, SUM(tr.price * tr.price_modifier) as sales, SUM(tr.price * pm.points_modifier) as points FROM transactions tr LEFT JOIN payment_methods\n" +
            "pm ON pm.payment_method = tr.payment_method WHERE tr.date_time>=:startDate AND tr.date_time<:endDate GROUP BY dateTime ORDER BY dateTime", nativeQuery = true)
    fun findTransactionsByHour(@Param("startDate") startDate: ZonedDateTime, @Param("endDate") endDate: ZonedDateTime) : List<TransactionOfHour>
}

interface TransactionOfHour{
    val dateTime: Timestamp
    val sales: Double
    val points: Int
}