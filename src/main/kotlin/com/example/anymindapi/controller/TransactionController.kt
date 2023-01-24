package com.example.anymindapi.controller

import com.example.anymindapi.model.Transaction
import com.example.anymindapi.repository.PaymentMethodRepository
import com.example.anymindapi.repository.TransactionRepository
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import com.example.anymindapi.utils.TransactionInputValidator



@Controller
class TransactionController(
    private val transactionRepository: TransactionRepository,
    private val paymentMethodRepository: PaymentMethodRepository
){


    @QueryMapping
    fun transactions(): Iterable<Transaction> {
        return transactionRepository.findAll()
    }

    @MutationMapping
    fun addTransaction(@Argument transaction: Transaction): TransactionOutput {
        TransactionInputValidator(transaction, paymentMethodRepository)

        val pointsModifier = paymentMethodRepository.findByPaymentMethod(transaction.paymentMethod).pointsModifier

        val newTransaction = Transaction(null, transaction.paymentMethod, transaction.price, transaction.priceModifier,transaction.dateTime)
        transactionRepository.save(newTransaction)

        val finalPrice = transaction.price * transaction.priceModifier
        val points = (transaction.price * pointsModifier).toInt()
        return TransactionOutput( finalPrice, points)
    }
    class TransactionOutput(val finalPrice: Double, val points: Int )
}