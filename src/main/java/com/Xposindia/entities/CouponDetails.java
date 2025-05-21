package com.Xposindia.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coupon_details")
public class CouponDetails {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "coupon")
	private String coupon;
	
	@Column(name = "coupon_amount")
	private Long couponAmount;
	
	@Column(name = "coupon_type")
	private String couponType;
	
	@Column(name = "coupon_limit")
	private int couponLimit;
	
	@Column(name = "used_limit")
	private int usedLimit;
	
	@Column(name = "coupon_expired_on")
	private Date couponExpiredOn;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "created_at")
	private Date createdAt;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public Long getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(Long couponAmount) {
		this.couponAmount = couponAmount;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	public int getCouponLimit() {
		return couponLimit;
	}

	public void setCouponLimit(int couponLimit) {
		this.couponLimit = couponLimit;
	}

	public Date getCouponExpiredOn() {
		return couponExpiredOn;
	}

	public void setCouponExpiredOn(Date couponExpiredOn) {
		this.couponExpiredOn = couponExpiredOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
}
