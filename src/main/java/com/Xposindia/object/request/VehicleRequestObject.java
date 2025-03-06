package com.Xposindia.object.request;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleRequestObject {
	
	private Long id;
	private long bookingDetailsId;
	private String userId;
	private String vehicleImages1;
	private String vehicleImages2;
	private String vehicleImages3;
	private String vehicleImages4;
	private String vehicleBrand;
	private String vehicleName;
	private String engineNumber;
	private String vehicleNumber;
	private String vehicleReceiptImage;
	private String vehicleNumberImage;
	private String vehicleFrontImage;
	private String vehicleBackImage;
	private String vehicleLeftImage;
	private String vehicleRightImage;
	private String city;
	private String areaFrom;
	private String areaTo;
	private int vehicleQuantity;
	private double venderVehiclePrice;
	private double priceLimit;
	private double adminVehiclePrice;
	private String vehicleType;
	private String urlPath;
	private String status;
	private String userRole;
	private String discription;
	private String vehicleDetailsType;
	private String bookingId;
	private double bookingPrice;
	private String memberType;
	private String type;
	
	private String customerMobile;
	private String countryCode;
	private String customerName;
	private String firstName;
	private String lastName;
	private int bookedQuantity;
	private String vehicleOwnner;
	private String deliveryExecutiveId;
	private String deliveryExecutiveName;
	private String vehicleOwnnerType;
	private Long totalEarning;
	private Long dailyBonus;
	private Long monthlyBonus;
	
	private String fromDate1;
	private Date fromDate;
	private String toDate1;
	private Date toDate;
	
	private Date startDate;
	private Date endDate;
	
	private int deliveryTime;
	private int pickupTime;

	private String requestFor;
	private String enquirySource;
	private String entryType;
	
	private int deliveryCharges;
	private int receivedAmount;
	private int balenceAmount;
	private int totalAmount;
	private double miscellaneous;
	private String refundReason;
	private Date refundDate;
	private String refundedBy;
	private String refundedByName;
	private String notes;
	
	private String cancelationFor;
	private String cancelationReason;
	private String cancelBy;
	private String cancelByName;
	
	private String adminId;
	private String adminName;
	
	private String dayBookCount;
	private String dayReceivedAmount;
	private String yesterdayReceivedAmount;
	private String weekBookCount;
	private String weekBookAmount;
	private String preWeekBookAmount;
	private String monthBookCount;
	private String monthBookAmount;
	private String previousMonthBookAmount;
	private String enquiryCount;
	private String followUpCount;
	private String lostCount;
	private String modeOfPayment;
	private String paymentLink;
	
	private Long todaysCount;
	private Long tomorrowCount;
	private Long afterTomorrowCont;
	private Long weeklyCount;
	private Long halfMonthCount;
	private Long monthlyCount;
	
	private String receiptNumber;
	private String receiptStatus;
	private String invoiceNumber;
	private String invoiceStatus;
	
	private Date createdAt;
	private Date updatedAt;
	private String createdBy;
	private String updatedBy;
	private String createdbyName;
	private double securityDeposit;
	
	private Long startAmount;
	private Long endAmount;
	private Long bonusAmount;
	private String bonusType;
	private String emailId;
	
	private String arrivalType;
	private String onlineNotes;
	private int noOfChild;
	private Long amountForChild;
	private Long totalAmountForChild;
	private int noOfAdult;
	private Long amountForAdult;
	private Long totalAmountForAdult;
	private int totalNoOfPerson;
	private String paymentType;	
	private String dateType;
	
	private String respMesg;
	private int respCode;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getBookingDetailsId() {
		return bookingDetailsId;
	}
	public void setBookingDetailsId(long bookingDetailsId) {
		this.bookingDetailsId = bookingDetailsId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getVehicleImages1() {
		return vehicleImages1;
	}
	public void setVehicleImages1(String vehicleImages1) {
		this.vehicleImages1 = vehicleImages1;
	}
	public String getVehicleImages2() {
		return vehicleImages2;
	}
	public void setVehicleImages2(String vehicleImages2) {
		this.vehicleImages2 = vehicleImages2;
	}
	public String getVehicleImages3() {
		return vehicleImages3;
	}
	public void setVehicleImages3(String vehicleImages3) {
		this.vehicleImages3 = vehicleImages3;
	}
	public String getVehicleImages4() {
		return vehicleImages4;
	}
	public void setVehicleImages4(String vehicleImages4) {
		this.vehicleImages4 = vehicleImages4;
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
	public String getEngineNumber() {
		return engineNumber;
	}
	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
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
	public void setVehicleNumberImage(String vehicleNumberImage) {
		this.vehicleNumberImage = vehicleNumberImage;
	}
	public String getYesterdayReceivedAmount() {
		return yesterdayReceivedAmount;
	}
	public void setYesterdayReceivedAmount(String yesterdayReceivedAmount) {
		this.yesterdayReceivedAmount = yesterdayReceivedAmount;
	}
	public String getPreWeekBookAmount() {
		return preWeekBookAmount;
	}
	public void setPreWeekBookAmount(String preWeekBookAmount) {
		this.preWeekBookAmount = preWeekBookAmount;
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
	public int getVehicleQuantity() {
		return vehicleQuantity;
	}
	public void setVehicleQuantity(int vehicleQuantity) {
		this.vehicleQuantity = vehicleQuantity;
	}
	public double getVenderVehiclePrice() {
		return venderVehiclePrice;
	}
	public void setVenderVehiclePrice(double venderVehiclePrice) {
		this.venderVehiclePrice = venderVehiclePrice;
	}
	public double getPriceLimit() {
		return priceLimit;
	}
	public void setPriceLimit(double priceLimit) {
		this.priceLimit = priceLimit;
	}
	public double getAdminVehiclePrice() {
		return adminVehiclePrice;
	}
	public void setAdminVehiclePrice(double adminVehiclePrice) {
		this.adminVehiclePrice = adminVehiclePrice;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getUrlPath() {
		return urlPath;
	}
	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public String getVehicleDetailsType() {
		return vehicleDetailsType;
	}
	public void setVehicleDetailsType(String vehicleDetailsType) {
		this.vehicleDetailsType = vehicleDetailsType;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public double getBookingPrice() {
		return bookingPrice;
	}
	public void setBookingPrice(double bookingPrice) {
		this.bookingPrice = bookingPrice;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getBookedQuantity() {
		return bookedQuantity;
	}
	public void setBookedQuantity(int bookedQuantity) {
		this.bookedQuantity = bookedQuantity;
	}
	public String getVehicleOwnner() {
		return vehicleOwnner;
	}
	public void setVehicleOwnner(String vehicleOwnner) {
		this.vehicleOwnner = vehicleOwnner;
	}
	public String getFromDate1() {
		return fromDate1;
	}
	public void setFromDate1(String fromDate1) {
		this.fromDate1 = fromDate1;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate1() {
		return toDate1;
	}
	public void setToDate1(String toDate1) {
		this.toDate1 = toDate1;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	public String getEnquirySource() {
		return enquirySource;
	}
	public void setEnquirySource(String enquirySource) {
		this.enquirySource = enquirySource;
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
	public String getEntryType() {
		return entryType;
	}
	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}
	public String getRequestFor() {
		return requestFor;
	}
	public void setRequestFor(String requestFor) {
		this.requestFor = requestFor;
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
	public double getMiscellaneous() {
		return miscellaneous;
	}
	public void setMiscellaneous(double miscellaneous) {
		this.miscellaneous = miscellaneous;
	}
	public String getRefundReason() {
		return refundReason;
	}
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	public Date getRefundDate() {
		return refundDate;
	}
	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}
	public String getRefundedBy() {
		return refundedBy;
	}
	public void setRefundedBy(String refundedBy) {
		this.refundedBy = refundedBy;
	}
	public String getRefundedByName() {
		return refundedByName;
	}
	public void setRefundedByName(String refundedByName) {
		this.refundedByName = refundedByName;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getCancelationFor() {
		return cancelationFor;
	}
	public void setCancelationFor(String cancelationFor) {
		this.cancelationFor = cancelationFor;
	}
	public String getCancelationReason() {
		return cancelationReason;
	}
	public void setCancelationReason(String cancelationReason) {
		this.cancelationReason = cancelationReason;
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
	public String getDayBookCount() {
		return dayBookCount;
	}
	public void setDayBookCount(String dayBookCount) {
		this.dayBookCount = dayBookCount;
	}
	public String getDayReceivedAmount() {
		return dayReceivedAmount;
	}
	public void setDayReceivedAmount(String dayReceivedAmount) {
		this.dayReceivedAmount = dayReceivedAmount;
	}
	public String getWeekBookCount() {
		return weekBookCount;
	}
	public void setWeekBookCount(String weekBookCount) {
		this.weekBookCount = weekBookCount;
	}
	public String getWeekBookAmount() {
		return weekBookAmount;
	}
	public void setWeekBookAmount(String weekBookAmount) {
		this.weekBookAmount = weekBookAmount;
	}
	public String getMonthBookCount() {
		return monthBookCount;
	}
	public void setMonthBookCount(String monthBookCount) {
		this.monthBookCount = monthBookCount;
	}
	public String getMonthBookAmount() {
		return monthBookAmount;
	}
	public void setMonthBookAmount(String monthBookAmount) {
		this.monthBookAmount = monthBookAmount;
	}
	public String getPreviousMonthBookAmount() {
		return previousMonthBookAmount;
	}
	public void setPreviousMonthBookAmount(String previousMonthBookAmount) {
		this.previousMonthBookAmount = previousMonthBookAmount;
	}
	public String getEnquiryCount() {
		return enquiryCount;
	}
	public void setEnquiryCount(String enquiryCount) {
		this.enquiryCount = enquiryCount;
	}
	public String getFollowUpCount() {
		return followUpCount;
	}
	public void setFollowUpCount(String followUpCount) {
		this.followUpCount = followUpCount;
	}
	public String getLostCount() {
		return lostCount;
	}
	public void setLostCount(String lostCount) {
		this.lostCount = lostCount;
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
	public String getModeOfPayment() {
		return modeOfPayment;
	}
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	public String getPaymentLink() {
		return paymentLink;
	}
	public void setPaymentLink(String paymentLink) {
		this.paymentLink = paymentLink;
	}
	public Long getTodaysCount() {
		return todaysCount;
	}
	public void setTodaysCount(Long todaysCount) {
		this.todaysCount = todaysCount;
	}
	public Long getTomorrowCount() {
		return tomorrowCount;
	}
	public void setTomorrowCount(Long tomorrowCount) {
		this.tomorrowCount = tomorrowCount;
	}
	public Long getAfterTomorrowCont() {
		return afterTomorrowCont;
	}
	public void setAfterTomorrowCont(Long afterTomorrowCont) {
		this.afterTomorrowCont = afterTomorrowCont;
	}
	public Long getWeeklyCount() {
		return weeklyCount;
	}
	public void setWeeklyCount(Long weeklyCount) {
		this.weeklyCount = weeklyCount;
	}
	public Long getHalfMonthCount() {
		return halfMonthCount;
	}
	public void setHalfMonthCount(Long halfMonthCount) {
		this.halfMonthCount = halfMonthCount;
	}
	public Long getMonthlyCount() {
		return monthlyCount;
	}
	public void setMonthlyCount(Long monthlyCount) {
		this.monthlyCount = monthlyCount;
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
	public double getSecurityDeposit() {
		return securityDeposit;
	}
	public void setSecurityDeposit(double securityDeposit) {
		this.securityDeposit = securityDeposit;
	}
	public Long getStartAmount() {
		return startAmount;
	}
	public void setStartAmount(Long startAmount) {
		this.startAmount = startAmount;
	}
	public Long getEndAmount() {
		return endAmount;
	}
	public void setEndAmount(Long endAmount) {
		this.endAmount = endAmount;
	}
	public Long getBonusAmount() {
		return bonusAmount;
	}
	public void setBonusAmount(Long bonusAmount) {
		this.bonusAmount = bonusAmount;
	}
	public String getBonusType() {
		return bonusType;
	}
	public void setBonusType(String bonusType) {
		this.bonusType = bonusType;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getVehicleOwnnerType() {
		return vehicleOwnnerType;
	}
	public void setVehicleOwnnerType(String vehicleOwnnerType) {
		this.vehicleOwnnerType = vehicleOwnnerType;
	}
	public Long getTotalEarning() {
		return totalEarning;
	}
	public void setTotalEarning(Long totalEarning) {
		this.totalEarning = totalEarning;
	}
	public Long getDailyBonus() {
		return dailyBonus;
	}
	public void setDailyBonus(Long dailyBonus) {
		this.dailyBonus = dailyBonus;
	}
	public Long getMonthlyBonus() {
		return monthlyBonus;
	}
	public void setMonthlyBonus(Long monthlyBonus) {
		this.monthlyBonus = monthlyBonus;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getArrivalType() {
		return arrivalType;
	}
	public void setArrivalType(String arrivalType) {
		this.arrivalType = arrivalType;
	}
	public String getOnlineNotes() {
		return onlineNotes;
	}
	public void setOnlineNotes(String onlineNotes) {
		this.onlineNotes = onlineNotes;
	}
	public int getNoOfChild() {
		return noOfChild;
	}
	public void setNoOfChild(int noOfChild) {
		this.noOfChild = noOfChild;
	}
	public Long getAmountForChild() {
		return amountForChild;
	}
	public void setAmountForChild(Long amountForChild) {
		this.amountForChild = amountForChild;
	}
	public Long getTotalAmountForChild() {
		return totalAmountForChild;
	}
	public void setTotalAmountForChild(Long totalAmountForChild) {
		this.totalAmountForChild = totalAmountForChild;
	}
	public int getNoOfAdult() {
		return noOfAdult;
	}
	public void setNoOfAdult(int noOfAdult) {
		this.noOfAdult = noOfAdult;
	}
	public Long getAmountForAdult() {
		return amountForAdult;
	}
	public void setAmountForAdult(Long amountForAdult) {
		this.amountForAdult = amountForAdult;
	}
	public Long getTotalAmountForAdult() {
		return totalAmountForAdult;
	}
	public void setTotalAmountForAdult(Long totalAmountForAdult) {
		this.totalAmountForAdult = totalAmountForAdult;
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
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}


}
