package com.example.anymindapi.model
import jakarta.persistence.*

@Entity
@Table(name = "PAYMENT_METHODS")
data class PaymentMethod (
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "payment_method", nullable = false)
    val paymentMethod: String,
    @Column(name = "max_modifier", nullable = false)
    val maxModifier: Double,
    @Column(name = "min_modifier", nullable = false)
    val minModifier: Double,
    @Column(name = "points_modifier", nullable = false)
    val pointsModifier: Double
)