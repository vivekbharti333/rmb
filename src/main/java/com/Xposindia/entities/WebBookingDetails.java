package com.Xposindia.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "web_booking_details")
public class WebBookingDetails {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "vehicle_details_type")
	private String vehicleDetailsType;
	
	@Column(name = "vehicle_type")
	private String vehicleType;
	
	@Column(name = "Vehicle_brand")
	private String vehicleBrand;
	
	@Column(name = "vehicle_name")
	private String vehicleName;
	
	@Column(name = "vehicle_quantity")
	private int vehicleQuantity;
	
	@Column(name = "country_code")
	private String countryCode;
	
	@Column(name = "customer_mobile")
	private String customerMobile;
	
	@Column(name = "customer_name")
	private String customerName;
	
	@Column(name = "from_date")
	private Date fromDate;
	
	@Column(name = "total_amount")
	private int totalAmount;
	
	@Column(name = "created_by")
	private String createdBy;
	    
	@Column(name = "area_from")
	private String areaFrom;
	 
	@Lob
	@Column(name = "online_notes")
	private String onlineNotes;
	
	@Column(name = "arrivalType")
	private String arrivalType;
	
	@Column(name = "no_of_adult")
	private int noOfAdult;
	
	@Column(name = " no_of_child")
	private int noOfChild;
	
	@Column(name = "total_no_of_person")
	private int totalNoOfPerson;
	
	@Column(name = "payment_type")
	private String paymentType;
	
	@Column(name = "amount_for_child")
	private Long amountForChild;
	
	@Column(name = "amount_for_adult")
	private Long amountForAdult;
	
	@Column(name = "total_amount_for_child")
	private Long totalAmountForChild;
	
	@Column(name = "total_amount_for_adult")
	private Long totalAmountForAdult;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "booking_id")
	private String bookingId;
	
	
	@Column(name = "invoice_number")
	private String invoiceNumber;
	
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getVehicleDetailsType() {
		return vehicleDetailsType;
	}

	public void setVehicleDetailsType(String vehicleDetailsType) {
		this.vehicleDetailsType = vehicleDetailsType;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehicleBrand() {
		return vehicleBrand;
	}

	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public int getVehicleQuantity() {
		return vehicleQuantity;
	}

	public void setVehicleQuantity(int vehicleQuantity) {
		this.vehicleQuantity = vehicleQuantity;
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

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getAreaFrom() {
		return areaFrom;
	}

	public void setAreaFrom(String areaFrom) {
		this.areaFrom = areaFrom;
	}

	public String getOnlineNotes() {
		return onlineNotes;
	}

	public void setOnlineNotes(String onlineNotes) {
		this.onlineNotes = onlineNotes;
	}

	public String getArrivalType() {
		return arrivalType;
	}

	public void setArrivalType(String arrivalType) {
		this.arrivalType = arrivalType;
	}

	public int getNoOfAdult() {
		return noOfAdult;
	}

	public void setNoOfAdult(int noOfAdult) {
		this.noOfAdult = noOfAdult;
	}

	public int getNoOfChild() {
		return noOfChild;
	}

	public void setNoOfChild(int noOfChild) {
		this.noOfChild = noOfChild;
	}

	public int getTotalNoOfPerson() {
		return totalNoOfPerson;
	}

	public void setTotalNoOfPerson(int totalNoOfPerson) {
		this.totalNoOfPerson = totalNoOfPerson;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Long getAmountForChild() {
		return amountForChild;
	}

	public void setAmountForChild(Long amountForChild) {
		this.amountForChild = amountForChild;
	}

	public Long getAmountForAdult() {
		return amountForAdult;
	}

	public void setAmountForAdult(Long amountForAdult) {
		this.amountForAdult = amountForAdult;
	}

	public Long getTotalAmountForChild() {
		return totalAmountForChild;
	}

	public void setTotalAmountForChild(Long totalAmountForChild) {
		this.totalAmountForChild = totalAmountForChild;
	}

	public Long getTotalAmountForAdult() {
		return totalAmountForAdult;
	}

	public void setTotalAmountForAdult(Long totalAmountForAdult) {
		this.totalAmountForAdult = totalAmountForAdult;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
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

	@Override
	public String toString() {
		return "WebBookingDetails [id=" + id + ", vehicleDetailsType=" + vehicleDetailsType + ", vehicleType="
				+ vehicleType + ", vehicleBrand=" + vehicleBrand + ", vehicleName=" + vehicleName + ", vehicleQuantity="
				+ vehicleQuantity + ", countryCode=" + countryCode + ", customerMobile=" + customerMobile
				+ ", customerName=" + customerName + ", fromDate=" + fromDate + ", totalAmount=" + totalAmount
				+ ", createdBy=" + createdBy + ", areaFrom=" + areaFrom + ", onlineNotes=" + onlineNotes
				+ ", arrivalType=" + arrivalType + ", noOfAdult=" + noOfAdult + ", noOfChild=" + noOfChild
				+ ", totalNoOfPerson=" + totalNoOfPerson + ", paymentType=" + paymentType + ", amountForChild="
				+ amountForChild + ", amountForAdult=" + amountForAdult + ", totalAmountForChild=" + totalAmountForChild
				+ ", totalAmountForAdult=" + totalAmountForAdult + ", status=" + status + ", bookingId=" + bookingId
				+ ", invoiceNumber=" + invoiceNumber + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
