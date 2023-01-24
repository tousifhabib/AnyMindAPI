package com.example.anymindapi.model

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "TRANSACTIONS")
data class Transaction(
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "payment_method", nullable = false)
    val paymentMethod: String,
    @Column(name = "price", nullable = false)
    val price: Double,
    @Column(name = "price_modifier", nullable = false)
    val priceModifier: Double,
    @Column(name = "date_time", nullable = false)
    val dateTime: ZonedDateTime
)