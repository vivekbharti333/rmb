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
@Table(name = "booking_details")
public class BookingDetails {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "enquiry_source")
	private String enquirySource;
	
	@Column(name = "call_status")
	private String callStatus;
	
	@Column(name = "country_code")
	private String countryCode;
	
	@Column(name = "customer_mobile")
	private String customerMobile;
	
	@Column(name= "customer_name")
	private String customerName;
	
	@Column(name  ="booking_id")
	private String bookingId;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "vehicle_owner_type")
	private String vehicleOwnnerType;
	
	@Column(name = "vehicle_owner")
	private String vehicleOwnner;

	@Column(name = "vehicle_type")
	private String vehicleType;
	
	@Column(name = "Vehicle_brand")
	private String vehicleBrand;
	
	@Column(name = "vehicle_name")
	private String vehicleName;
	
	@Column(name = "vehicle_quantity")
	private int vehicleQuantity;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "payment_mode")
	private String modeOfPayment;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "area_from")
	private String areaFrom;
	
	@Lob
	@Column(name = "area_to")
	private String areaTo;
	
	@Column(name = "vehicle_details_type")
	private String vehicleDetailsType;
	
	@Column(name = "vender_vehicle_price")
	private double venderVehiclePrice;
	
	@Column(name = "admin_vehicle_price")
	private double adminVehiclePrice;
	
	@Column(name = "booking_price")
	private double bookingPrice;
	
	@Column(name = "from_date")
	private Date fromDate;
	
	@Column(name = "to_date")
	private Date toDate;
	
	@Column(name = "delivery_time")
	private int deliveryTime;
	
	@Column(name = "pickup_time")
	private int pickupTime;
	
	@Column(name = "delivery_charges")
	private int deliveryCharges;
	
	@Column(name = "received_amount")
	private int receivedAmount;
	
	@Column(name = "balence_amount")
	private int balenceAmount;
	
	@Column(name = "total_amount")
	private int totalAmount;
	
	
	@Lob
	@Column(name = "notes")
	private String notes;
	
	@Column(name = "admin_id")
	private String adminId;
	
	@Column(name = "admin_name")
	private String adminName;
	
	@Column(name = "delivery_executive_id")
	private String deliveryExecutiveId;
	
	@Column(name = "delivery_executive_name")
	private String deliveryExecutiveName;
	
	@Column(name = "re_booking_status")
	private String reBookingStatus;
	
	@Column(name = "miscellaneous")
	private double miscellaneous;
	
	@Lob
	@Column(name = "cancelation_reason")
	private String cancelationReason;
	
	@Column(name = "cancelation_for")
	private String cancelationFor;
	
	@Column(name = "cancelation_request_number")
	private String cancelationRequestNumber;
	
	@Column(name = "cancel_by")
	private String cancelBy;
	
	@Column(name = "cancel_by_name")
	private String cancelByName;
	
	@Column(name = "cancel_date")
	private Date cancelDate;
	
	@Column(name = "receipt_number")
	private String receiptNumber;
	
	@Column(name = "receipt_status")
	private String receiptStatus;
	
	@Column(name = "invoice_number")
	private String invoiceNumber;
	
	@Column(name = "invoice_status")
	private String invoiceStatus;
	
//	@Lob
//	@Column(name = "refund_reason")
//	private String refundReason;
	
	@Column(name = "refund_date")
	private Date refundDate;
//	
//	@Column(name = "refunded_by")
//	private String refundedBy;
//	
//	@Column(name = "refunded_by_name")
//	private String refundedByName;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "createdby_name")
	private String createdbyName;
	
	@Column(name = "vehicle_number")
	private String vehicleNumber;
	
	@Column(name = "vehicle_receipt_image")
	private String vehicleReceiptImage;
	
	@Column(name = "vehicle_number_image")
	private String vehicleNumberImage;
	
	@Column(name = "vehicle_front_image")
	private String vehicleFrontImage;
	
	@Column(name = "vehicle_back_image")
	private String vehicleBackImage;
	
	@Column(name = "vehicle_left_image")
	private String vehicleLeftImage;
	
	@Column(name = "vehicle_right_image")
	private String vehicleRightImage;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@Column(name = "updated_at_latest")
	private Date updatedAtLetest;
	
	@Column(name = "security_deposit")
	private double securityDeposit;
	
	@Column(name = "updated_by")
	private String updatedBy;
	
	
	
	////
//	@Column(name = "arrival_type")
//	private String arrivalType;
//	
//	@Column(name = "online_notes")
//	private String onlineNotes;
//	
//	@Column(name = "no_of_child")
//	private int noOfChild;
//	
//	@Column(name = "amount_for_child")
//	private Long amountForChild;
//	
//	@Column(name = "total_tmount_for_child")
//	private Long totalAmountForChild;
//	
//	@Column(name = "no_of_adult")
//	private int noOfAdult;
//	
//	@Column(name = "amount_for_adult")
//	private Long amountForAdult;
//	
//	@Column(name = "total_amount_for_adult")
//	private Long totalAmountForAdult;
//	
//	@Column(name = "total_no_of_person")
//	private int totalNoOfPerson;
//	
//	@Column(name = "payment_type")
//	private String paymentType;
//	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnquirySource() {
		return enquirySource;
	}

	public void setEnquirySource(String enquirySource) {
		this.enquirySource = enquirySource;
	}

	public String getCallStatus() {
		return callStatus;
	}

	public void setCallStatus(String callStatus) {
		this.callStatus = callStatus;
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

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getVehicleOwnner() {
		return vehicleOwnner;
	}

	public void setVehicleOwnner(String vehicleOwnner) {
		this.vehicleOwnner = vehicleOwnner;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAreaFrom() {
		return areaFrom;
	}

	public void setAreaFrom(String areaFrom) {
		this.areaFrom = areaFrom;
	}

	public String getAreaTo() {
		return areaTo;
	}

	public void setAreaTo(String areaTo) {
		this.areaTo = areaTo;
	}

	public String getVehicleDetailsType() {
		return vehicleDetailsType;
	}

	public void setVehicleDetailsType(String vehicleDetailsType) {
		this.vehicleDetailsType = vehicleDetailsType;
	}

	public double getVenderVehiclePrice() {
		return venderVehiclePrice;
	}

	public void setVenderVehiclePrice(double venderVehiclePrice) {
		this.venderVehiclePrice = venderVehiclePrice;
	}

	public double getAdminVehiclePrice() {
		return adminVehiclePrice;
	}

	public void setAdminVehiclePrice(double adminVehiclePrice) {
		this.adminVehiclePrice = adminVehiclePrice;
	}

	public double getBookingPrice() {
		return bookingPrice;
	}

	public void setBookingPrice(double bookingPrice) {
		this.bookingPrice = bookingPrice;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public int getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(int pickupTime) {
		this.pickupTime = pickupTime;
	}

	public int getDeliveryCharges() {
		return deliveryCharges;
	}

	public void setDeliveryCharges(int deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}

	public int getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(int receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public int getBalenceAmount() {
		return balenceAmount;
	}

	public void setBalenceAmount(int balenceAmount) {
		this.balenceAmount = balenceAmount;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getDeliveryExecutiveId() {
		return deliveryExecutiveId;
	}

	public void setDeliveryExecutiveId(String deliveryExecutiveId) {
		this.deliveryExecutiveId = deliveryExecutiveId;
	}

	public String getDeliveryExecutiveName() {
		return deliveryExecutiveName;
	}

	public void setDeliveryExecutiveName(String deliveryExecutiveName) {
		this.deliveryExecutiveName = deliveryExecutiveName;
	}

	public String getReBookingStatus() {
		return reBookingStatus;
	}

	public void setReBookingStatus(String reBookingStatus) {
		this.reBookingStatus = reBookingStatus;
	}

	public double getMiscellaneous() {
		return miscellaneous;
	}

	public void setMiscellaneous(double miscellaneous) {
		this.miscellaneous = miscellaneous;
	}

	public String getCancelationReason() {
		return cancelationReason;
	}

	public void setCancelationReason(String cancelationReason) {
		this.cancelationReason = cancelationReason;
	}

	public String getCancelationFor() {
		return cancelationFor;
	}

	public void setCancelationFor(String cancelationFor) {
		this.cancelationFor = cancelationFor;
	}

	public String getCancelationRequestNumber() {
		return cancelationRequestNumber;
	}

	public void setCancelationRequestNumber(String cancelationRequestNumber) {
		this.cancelationRequestNumber = cancelationRequestNumber;
	}

	public String getCancelBy() {
		return cancelBy;
	}

	public void setCancelBy(String cancelBy) {
		this.cancelBy = cancelBy;
	}

	public String getCancelByName() {
		return cancelByName;
	}

	public void setCancelByName(String cancelByName) {
		this.cancelByName = cancelByName;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public Date getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public String getReceiptStatus() {
		return receiptStatus;
	}

	public void setReceiptStatus(String receiptStatus) {
		this.receiptStatus = receiptStatus;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}         

	public String getVehicleReceiptImage() {
		return vehicleReceiptImage;
	}

	public void setVehicleReceiptImage(String vehicleReceiptImage) {
		this.vehicleReceiptImage = vehicleReceiptImage;
	}

	public String getVehicleNumberImage() {
		return vehicleNumberImage;
	}

	public void setVehicleNumberImage(String vehicleNumberImage) {
		this.vehicleNumberImage = vehicleNumberImage;
	}

	public String getVehicleFrontImage() {
		return vehicleFrontImage;
	}

	public void setVehicleFrontImage(String vehicleFrontImage) {
		this.vehicleFrontImage = vehicleFrontImage;
	}

	public String getVehicleBackImage() {
		return vehicleBackImage;
	}

	public void setVehicleBackImage(String vehicleBackImage) {
		this.vehicleBackImage = vehicleBackImage;
	}

	public String getVehicleLeftImage() {
		return vehicleLeftImage;
	}

	public void setVehicleLeftImage(String vehicleLeftImage) {
		this.vehicleLeftImage = vehicleLeftImage;
	}

	public String getVehicleRightImage() {
		return vehicleRightImage;
	}

	public void setVehicleRightImage(String vehicleRightImage) {
		this.vehicleRightImage = vehicleRightImage;
	}

	public double getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(double securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	public String getVehicleOwnnerType() {
		return vehicleOwnnerType;
	}

	public void setVehicleOwnnerType(String vehicleOwnnerType) {
		this.vehicleOwnnerType = vehicleOwnnerType;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedAtLetest() {
		return updatedAtLetest;
	}

	public void setUpdatedAtLetest(Date updatedAtLetest) {
		this.updatedAtLetest = updatedAtLetest;
	}

//	public String getArrivalType() {
//		return arrivalType;
//	}
//
//	public void setArrivalType(String arrivalType) {
//		this.arrivalType = arrivalType;
//	}
//
//	public String getOnlineNotes() {
//		return onlineNotes;
//	}
//
//	public void setOnlineNotes(String onlineNotes) {
//		this.onlineNotes = onlineNotes;
//	}
//
//	public int getNoOfChild() {
//		return noOfChild;
//	}
//
//	public void setNoOfChild(int noOfChild) {
//		this.noOfChild = noOfChild;
//	}
//
//	public Long getAmountForChild() {
//		return amountForChild;
//	}
//
//	public void setAmountForChild(Long amountForChild) {
//		this.amountForChild = amountForChild;
//	}
//
//	public Long getTotalAmountForChild() {
//		return totalAmountForChild;
//	}
//
//	public void setTotalAmountForChild(Long totalAmountForChild) {
//		this.totalAmountForChild = totalAmountForChild;
//	}
//
//	public int getNoOfAdult() {
//		return noOfAdult;
//	}
//
//	public void setNoOfAdult(int noOfAdult) {
//		this.noOfAdult = noOfAdult;
//	}
//
//	public Long getAmountForAdult() {
//		return amountForAdult;
//	}
//
//	public void setAmountForAdult(Long amountForAdult) {
//		this.amountForAdult = amountForAdult;
//	}
//
//	public Long getTotalAmountForAdult() {
//		return totalAmountForAdult;
//	}
//
//	public void setTotalAmountForAdult(Long totalAmountForAdult) {
//		this.totalAmountForAdult = totalAmountForAdult;
//	}
//
//	public int getTotalNoOfPerson() {
//		return totalNoOfPerson;
//	}
//
//	public void setTotalNoOfPerson(int totalNoOfPerson) {
//		this.totalNoOfPerson = totalNoOfPerson;
//	}
//
//	public String getPaymentType() {
//		return paymentType;
//	}
//
//	public void setPaymentType(String paymentType) {
//		this.paymentType = paymentType;
//	}
}
