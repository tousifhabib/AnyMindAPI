package com.example.anymindapi.utils

import com.example.anymindapi.model.Transaction
import com.example.anymindapi.repository.PaymentMethodRepository
import com.example.anymindapi.repository.TransactionRepository
import java.time.ZonedDateTime
import java.time.format.DateTimeParseException

class TransactionInputValidator ( transaction: Transaction, private val paymentMethodRepository: PaymentMethodRepository ) {

    init {
        validatePrice(transaction)
        validatePaymentMethod(transaction)
        validatePriceModifier(transaction)
        validateDateTime(transaction)
    }

    private fun validatePrice(transaction: Transaction) : Boolean {
        if( transaction.price < 0 ) {
            throw Exception("Price cannot be negative")
        }
        return true
    }

    private fun validatePriceModifier(transaction: Transaction) : Boolean {
        val transactionPriceModifier = transaction.priceModifier
        val transactionFromPaymentMethod = paymentMethodRepository.findByPaymentMethod(transaction.paymentMethod)
        val minModifier = transactionFromPaymentMethod.minModifier
        val maxModifier = transactionFromPaymentMethod.maxModifier

        if (transactionPriceModifier in minModifier..maxModifier) {
            return true
        }
        throw Exception("Invalid Payment Modifier")
    }

    private fun validatePaymentMethod(transaction: Transaction) : Boolean {
        val allPaymentMethods  = paymentMethodRepository.findAllPaymentMethods()
        val transactionPaymentMethod = transaction.paymentMethod
        if ( allPaymentMethods.contains(transactionPaymentMethod) ){
            return true
        }
        throw Exception("Invalid Payment Method")
    }

    private fun validateDateTime(transaction: Transaction){
        val transactionDateTime = transaction.dateTime
        try {
            ZonedDateTime.parse(transactionDateTime.toString())
        } catch (error: DateTimeParseException) {
            throw Exception("Invalid Date Time Format")
        }
    }
}