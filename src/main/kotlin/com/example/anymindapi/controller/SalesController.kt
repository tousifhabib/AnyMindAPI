package com.example.anymindapi.controller

import com.example.anymindapi.repository.TransactionOfHour
import com.example.anymindapi.repository.TransactionRepository
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.time.ZonedDateTime

@Controller
class SalesController(private val transactionRepository: TransactionRepository)  {
    @QueryMapping
    fun sales(@Argument startDateTime: ZonedDateTime, @Argument endDateTime: ZonedDateTime) : List<TransactionOfHour> {
        return transactionRepository.findTransactionsByHour(startDateTime, endDateTime)
    }

}