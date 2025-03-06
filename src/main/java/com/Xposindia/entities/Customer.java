package com.Xposindia.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "customer")
public class Customer {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "country_code")
	private String countryCode;
	
	@Column(name = "customer_mobile")
	private String customerMobile;
	
	@Column(name= "customer_name")
	private String customerName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "customer_otp")
	private String customerOtp;
	
	@Column(name = "customer_otp_status")
	private String customerOtpStatus;
	
	@Column(name = "email_id")
	private String emailId;
	
	@Column(name = "status")
	private String status;

	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCustomerOtp() {
		return customerOtp;
	}

	public void setCustomerOtp(String customerOtp) {
		this.customerOtp = customerOtp;
	}

	public String getCustomerOtpStatus() {
		return customerOtpStatus;
	}

	public void setCustomerOtpStatus(String customerOtpStatus) {
		this.customerOtpStatus = customerOtpStatus;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
