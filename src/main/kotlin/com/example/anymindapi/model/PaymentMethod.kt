package com.example.anymindapi.model
import jakarta.persistence.*

@Entity
@Table(name = "PAYMENT_METHOD")
data class PaymentMethod (
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,
    @Column(name = "payment_method", nullable = false)
    val paymentMethod: String,
    @Column(name = "max_modifier", nullable = false)
    val maxModifier: Double,
    @Column(name = "min_modifier", nullable = false)
    val minModifier: Double,
    @Column(name = "points_modifier", nullable = false)
    val pointsModifier: Double
)