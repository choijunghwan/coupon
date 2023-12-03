package com.charlizip.coupon.job

import com.charlizip.coupon.domain.coupon.Coupon
import com.charlizip.coupon.domain.coupon.CouponRepository
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.database.JpaPagingItemReader
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManagerFactory

@Configuration
class CouponDeleteJobConfiguration(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory,
    private val entityManagerFactory: EntityManagerFactory,
    private val couponRepository: CouponRepository,
) {

    private val chunkSize = 10

    @Bean
    fun couponDeleteJob(): Job {
        return jobBuilderFactory["couponDeleteJob"]
            .incrementer(RunIdIncrementer())
            .start(couponDeleteStep())
            .build()
    }

    @Bean
    fun couponDeleteStep(): Step {
        return stepBuilderFactory["couponDeleteStep"]
            .chunk<Coupon, Coupon>(chunkSize)
            .reader(couponItemReader())
            .writer(couponItemWriter())
            .build()
    }

    @Bean
    fun couponItemReader(): JpaPagingItemReader<Coupon> {
        val jpaPagingItemReader = object : JpaPagingItemReader<Coupon>() {
            override fun getPage(): Int {
                return 0
            }
        }

        jpaPagingItemReader.name = "couponItmeReader"
        jpaPagingItemReader.pageSize = chunkSize
        jpaPagingItemReader.setEntityManagerFactory(entityManagerFactory)
        jpaPagingItemReader.setQueryString("SELECT c FROM Coupon c where c.deletedYn=true ORDER BY c.id ASC")
        return jpaPagingItemReader
    }

    @Bean
    fun couponItemWriter(): ItemWriter<Coupon> {
        return ItemWriter { items ->
            couponRepository.deleteAll(items)
        }
    }
}