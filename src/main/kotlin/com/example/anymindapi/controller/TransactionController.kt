package com.example.anymindapi.controller

import com.example.anymindapi.model.Transaction
import com.example.anymindapi.repository.TransactionRepository
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Component

@Component
class TransactionController (private val transactionRepository: TransactionRepository){

    @QueryMapping
    fun transactions(): Iterable<Transaction?>? {
        return transactionRepository.findAll()
    }

}