package com.example.anymindapi.seeder

import com.example.anymindapi.model.PaymentMethod
import com.example.anymindapi.repository.PaymentMethodRepository
import mu.KotlinLogging
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.util.*

private val logger = KotlinLogging.logger {}
@Component
class DatabaseSeeder(
    private val paymentMethodRepository: PaymentMethodRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        seedPaymentMethodTable()
    }

    private fun seedPaymentMethodTable() {

        val paymentMethodRecords = paymentMethodRepository.count()

        if (paymentMethodRecords == null || paymentMethodRecords <= 0) {

            val paymentMethodList = ArrayList<PaymentMethod>();

            val cash = PaymentMethod(null,"CASH", 1.0, 0.9,0.05)
            val cashOnDelivery = PaymentMethod(null,"CASH_ON_DELIVERY", 1.02, 1.0,0.05)
            val visa = PaymentMethod(null,"VISA", 1.0, 0.95,0.03)
            val mastercard = PaymentMethod(null,"MASTERCARD", 1.0, 0.95,0.03)
            val amex = PaymentMethod(null,"AMEX", 1.01, 0.98,0.02)
            val jcb = PaymentMethod(null,"JCB", 1.0, 0.95,0.05)

            paymentMethodList.add(cash)
            paymentMethodList.add(cashOnDelivery)
            paymentMethodList.add(visa)
            paymentMethodList.add(mastercard)
            paymentMethodList.add(amex)
            paymentMethodList.add(jcb)

            paymentMethodRepository.saveAll(paymentMethodList)

            logger.info("Payment Methods Seeded")
        } else {
            logger.trace("Payment Methods Seeding Not Required")
        }
    }

}