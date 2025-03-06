package com.Xposindia.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "total_booking_count")
public class TotalBookingCount {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "booking_qty_day")
	private int bookingQtyDay;
	
	@Column(name = "booking_qty_week")
	private int bookingQtyWeek;
	
	@Column(name = "booking_qty_month")
	private int bookingQtyMonth;
	
	@Column(name = "booking_qty_year")
	private int bookingQtyYear;
	
	@Column(name = "amount_receive_day")
	private double amountReceiveDay;
	
	@Column(name = "amount_receive_week")
	private double amountReceiveWeek;
	
	@Column(name = "amount_receive_month")
	private double amountReceiveMonth;
	
	@Column(name = "amount_receive_year")
	private double amountReceiveYear;
	
	@Column(name = "createdby_name")
	private String createdbyName;
	
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getBookingQtyDay() {
		return bookingQtyDay;
	}

	public void setBookingQtyDay(int bookingQtyDay) {
		this.bookingQtyDay = bookingQtyDay;
	}

	public int getBookingQtyWeek() {
		return bookingQtyWeek;
	}

	public void setBookingQtyWeek(int bookingQtyWeek) {
		this.bookingQtyWeek = bookingQtyWeek;
	}

	public int getBookingQtyMonth() {
		return bookingQtyMonth;
	}

	public void setBookingQtyMonth(int bookingQtyMonth) {
		this.bookingQtyMonth = bookingQtyMonth;
	}

	public int getBookingQtyYear() {
		return bookingQtyYear;
	}

	public void setBookingQtyYear(int bookingQtyYear) {
		this.bookingQtyYear = bookingQtyYear;
	}

	public double getAmountReceiveDay() {
		return amountReceiveDay;
	}

	public void setAmountReceiveDay(double amountReceiveDay) {
		this.amountReceiveDay = amountReceiveDay;
	}

	public double getAmountReceiveWeek() {
		return amountReceiveWeek;
	}

	public void setAmountReceiveWeek(double amountReceiveWeek) {
		this.amountReceiveWeek = amountReceiveWeek;
	}

	public double getAmountReceiveMonth() {
		return amountReceiveMonth;
	}

	public void setAmountReceiveMonth(double amountReceiveMonth) {
		this.amountReceiveMonth = amountReceiveMonth;
	}

	public double getAmountReceiveYear() {
		return amountReceiveYear;
	}

	public void setAmountReceiveYear(double amountReceiveYear) {
		this.amountReceiveYear = amountReceiveYear;
	}

	public String getCreatedbyName() {
		return createdbyName;
	}

	public void setCreatedbyName(String createdbyName) {
		this.createdbyName = createdbyName;
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
