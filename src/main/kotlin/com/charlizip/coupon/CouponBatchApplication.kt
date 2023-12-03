package com.charlizip.coupon

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableBatchProcessing
@SpringBootApplication
class CouponBatchApplication

fun main(args: Array<String>) {
	runApplication<CouponBatchApplication>(*args)
}
