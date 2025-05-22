package com.Xposindia.object.request;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CouponRequestObject {
	
	private Long id;
	private String coupon;
	private Long couponAmount;
	private String couponType;
	private int couponLimit;
	private Date couponExpiredOn;
	private String status;
	private String createdBy;
	
	private String requestFor;
	private String respMesg;
	private int respCode;
	
	
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
	public String getRequestFor() {
		return requestFor;
	}
	public void setRequestFor(String requestFor) {
		this.requestFor = requestFor;
	}
	public String getRespMesg() {
		return respMesg;
	}
	public void setRespMesg(String respMesg) {
		this.respMesg = respMesg;
	}
	public int getRespCode() {
		return respCode;
	}
	public void setRespCode(int respCode) {
		this.respCode = respCode;
	}
	
}
