package com.charlizip.coupon.domain.coupon

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "coupon")
class Coupon(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long,
) {
}