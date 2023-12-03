package com.charlizip.coupon.domain.coupon

import java.time.LocalDateTime
import javax.persistence.Column
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
    @Column(name = "coupon_id")
    private var id: Long,

    @Column(name = "code", nullable = false)
    private val code: String,

    @Column(name = "quantity")
    private val quantity: Long,

    @Column(name = "expired_at")
    private val expiredAt: LocalDateTime,

    @Column(name = "deleted_yn", columnDefinition = "tinyint(1) default 0")
    private val deletedYn: Boolean,
) : AuditableEntity()