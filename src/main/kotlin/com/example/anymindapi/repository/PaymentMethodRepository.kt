package com.example.anymindapi.repository

import  com.example.anymindapi.model.PaymentMethod
import  org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import  org.springframework.stereotype.Repository

@Repository
interface PaymentMethodRepository : JpaRepository <PaymentMethod, Long>{
    @Query("SELECT payment_method FROM payment_methods", nativeQuery = true)
    fun findAllPaymentMethods(): List<String>

    fun findByPaymentMethod(paymentMethod:String) : PaymentMethod
}