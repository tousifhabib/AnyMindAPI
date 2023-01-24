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
    @Query("SELECT DATE_TRUNC('hour', transactions.date_time) as dateTime," +
            " SUM(price * price_modifier) as sales" +
            " FROM transactions where date_time >= :startDate AND date_time < :endDate " +
            "GROUP BY dateTime ORDER BY dateTime", nativeQuery = true)
    fun findTransactionsByHour(@Param("startDate") startDate: ZonedDateTime, @Param("endDate") endDate: ZonedDateTime) : List<TransactionOfHour>
}

interface TransactionOfHour{
    val dateTime: Timestamp
    val sales: Double
}