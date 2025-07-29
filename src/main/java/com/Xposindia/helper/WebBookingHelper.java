package com.Xposindia.helper;

import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.Xposindia.constant.Constant;
import com.Xposindia.dao.BookingDetailsDao;
import com.Xposindia.dao.TotalBookingCountDao;
import com.Xposindia.dao.WebBookingDetailsDao;
import com.Xposindia.entities.BookingDetails;
import com.Xposindia.entities.TotalBookingCount;
import com.Xposindia.entities.WebBookingDetails;
import com.Xposindia.expections.BizException;
import com.Xposindia.object.request.VehicleRequestObject;

@Component
public class WebBookingHelper {

	@Autowired
	private BookingDetailsDao bookingDetailsDao;
	
	@Autowired
	private WebBookingDetailsDao webBookingDetailsDao;

	@Autowired
	private TotalBookingCountDao totalBookingCountDao;

	public void validateBookingRequest(VehicleRequestObject vehicleRequestObject) throws BizException {
		if (vehicleRequestObject == null) {
			throw new BizException(Constant.BAD_REQUEST_CODE, "Bad Request Object Null");
		}
	}
	
	@Transactional
	public WebBookingDetails getVehicleDetailsByBookingId(String bookingId) {
		Criteria cr = bookingDetailsDao.getSession().createCriteria(WebBookingDetails.class);
		cr.add(Restrictions.eq("bookingId", bookingId));
		WebBookingDetails bookingDetails = (WebBookingDetails) cr.uniqueResult();
		return bookingDetails;
	}

	@Transactional
	public WebBookingDetails getVehicleDetailsByInvoiceNumber(String invoiceNumber) {
		Criteria cr = bookingDetailsDao.getSession().createCriteria(WebBookingDetails.class);
		cr.add(Restrictions.eq("invoiceNumber", invoiceNumber));
		WebBookingDetails bookingDetails = (WebBookingDetails) cr.uniqueResult();
		return bookingDetails;
	}

	/* Get User Details By Mobile Number */
	@Transactional
	public BookingDetails getVehicleDetailsById(Long id) {
		Criteria cr = bookingDetailsDao.getSession().createCriteria(BookingDetails.class);
		cr.add(Restrictions.eq("id", id));
		BookingDetails bookingDetails = (BookingDetails) cr.uniqueResult();
		return bookingDetails;
	}

	@Transactional
	public WebBookingDetails getWaterSportsDetailsByObj(VehicleRequestObject vehicleRequest) throws ParseException {

		WebBookingDetails bookingDetails = new WebBookingDetails();

		bookingDetails.setBookingId(StringUtils.substring(RandomStringUtils.random(64, false, true), 0, 16));
		vehicleRequest.setBookingId(bookingDetails.getBookingId());
		bookingDetails.setTransportType(vehicleRequest.getTransportType());
		bookingDetails.setVehicleType(vehicleRequest.getVehicleType());
		bookingDetails.setVehicleDetailsType(vehicleRequest.getVehicleDetailsType());
		bookingDetails.setVehicleBrand(vehicleRequest.getVehicleBrand());
		bookingDetails.setVehicleName(vehicleRequest.getVehicleName());
		bookingDetails.setVehicleQuantity(vehicleRequest.getVehicleQuantity());
		bookingDetails.setCustomerName(vehicleRequest.getCustomerName());

		bookingDetails.setCountryCode(vehicleRequest.getCountryCode());
		bookingDetails.setCustomerMobile(vehicleRequest.getCustomerMobile());
		bookingDetails.setStatus("INIT");

		bookingDetails.setFromDate(vehicleRequest.getFromDate());
		bookingDetails.setAreaFrom(vehicleRequest.getAreaFrom());
		bookingDetails.setTotalAmount(vehicleRequest.getTotalAmount());

		bookingDetails.setCreatedBy(vehicleRequest.getCreatedBy());
		bookingDetails.setCreatedAt(new Date());
		
		
//		bookingDetails.setArrivalType(vehicleRequest.getArrivalType());
//		bookingDetails.setOnlineNotes(vehicleRequest.getOnlineNotes());
		bookingDetails.setNoOfChild(vehicleRequest.getNoOfChild());
		bookingDetails.setAmountForChild(vehicleRequest.getAmountForChild());
		bookingDetails.setTotalAmountForChild(vehicleRequest.getTotalAmountForChild());
		bookingDetails.setNoOfAdult(vehicleRequest.getNoOfAdult());
		bookingDetails.setAmountForAdult(vehicleRequest.getAmountForAdult());
		bookingDetails.setTotalNoOfPerson(vehicleRequest.getTotalNoOfPerson());
		bookingDetails.setPaymentType(vehicleRequest.getPaymentType());
//		bookingDetails.setInvoiceNumber(vehicleRequest.getInvoiceNumber());
		bookingDetails.setTotalPayableAmount(vehicleRequest.getTotalPayableAmount());
		
		return bookingDetails;
	}

	@Transactional
	public WebBookingDetails savewebBookingDetails(WebBookingDetails bookingDetails) {
		webBookingDetailsDao.persist(bookingDetails);
		return bookingDetails;
	}

	@Transactional
	public BookingDetails getUpdatedBookingDetailsByObj(final VehicleRequestObject vehicleRequest,
			final BookingDetails bookingDetails) throws ParseException {

		bookingDetails.setVehicleType(vehicleRequest.getVehicleType());
		bookingDetails.setVehicleDetailsType(vehicleRequest.getVehicleDetailsType());
		bookingDetails.setVehicleBrand(vehicleRequest.getVehicleBrand());
		bookingDetails.setVehicleName(vehicleRequest.getVehicleName());
		bookingDetails.setVehicleQuantity(vehicleRequest.getVehicleQuantity());
		bookingDetails.setCity(bookingDetails.getCity());
		bookingDetails.setVenderVehiclePrice(vehicleRequest.getVenderVehiclePrice());
		bookingDetails.setAdminVehiclePrice(vehicleRequest.getAdminVehiclePrice());
		bookingDetails.setCustomerName(vehicleRequest.getCustomerName());
		bookingDetails.setCountryCode(vehicleRequest.getCountryCode());

//		if(bookingDetails.getCreatedBy().equals(vehicleRequest.getUpdatedBy())) {
		bookingDetails.setCustomerMobile(vehicleRequest.getCustomerMobile());
//		}else {
//			bookingDetails.setCustomerMobile(bookingDetails.getCustomerMobile());
//		}
//		if (vehicleRequest.getCreatedBy().equals("7845273233")) {
//			bookingDetails.setCustomerMobile(vehicleRequest.getCustomerMobile());
//
//		}

		bookingDetails.setStatus(vehicleRequest.getStatus());
//		bookingDetails.setStatus("REQUESTED");
//		bookingDetails.setReBookingStatus(bookingDetails.getReBookingStatus());
//		bookingDetails.setBookingPrice(vehicleRequest.getBookingPrice());
//		bookingDetails.setMiscellaneous(vehicleRequest.getMiscellaneous());
//		bookingDetails.setFromDate(vehicleRequest.getFromDate());
//		bookingDetails.setToDate(vehicleRequest.getToDate());
//		bookingDetails.setDeliveryTime(vehicleRequest.getDeliveryTime());
//		bookingDetails.setPickupTime(vehicleRequest.getPickupTime());
//		bookingDetails.setAreaFrom(vehicleRequest.getAreaFrom());
//		bookingDetails.setAreaTo(vehicleRequest.getAreaTo());
//		bookingDetails.setDeliveryCharges(vehicleRequest.getDeliveryCharges());
//		bookingDetails.setReceivedAmount(vehicleRequest.getReceivedAmount());
//		bookingDetails.setBalenceAmount(vehicleRequest.getBalenceAmount());
//		bookingDetails.setTotalAmount(vehicleRequest.getTotalAmount());
//		bookingDetails.setNotes(vehicleRequest.getNotes());
//		bookingDetails.setAdminId("9922957643");
//		bookingDetails.setAdminName("James N");
//		bookingDetails.setSecurityDeposit(vehicleRequest.getSecurityDeposit());
//		bookingDetails.setCreatedBy(vehicleRequest.getCreatedBy());
//		bookingDetails.setCreatedbyName(vehicleRequest.getCreatedbyName());
//		bookingDetails.setUpdatedAt(new Date());
		
//		bookingDetails.setArrivalType(vehicleRequest.getArrivalType());
//		bookingDetails.setOnlineNotes(vehicleRequest.getOnlineNotes());
//		bookingDetails.setNoOfChild(vehicleRequest.getNoOfChild());
//		bookingDetails.setAmountForChild(vehicleRequest.getAmountForChild());
//		bookingDetails.setTotalAmountForChild(vehicleRequest.getTotalAmountForChild());
//		bookingDetails.setNoOfAdult(vehicleRequest.getNoOfAdult());
//		bookingDetails.setAmountForAdult(vehicleRequest.getAmountForAdult());
//		bookingDetails.setTotalNoOfPerson(vehicleRequest.getTotalNoOfPerson());
//		bookingDetails.setPaymentType(vehicleRequest.getPaymentType());
		return bookingDetails;
	}

	@Transactional
	public WebBookingDetails updateWebBookingDetails(WebBookingDetails bookingDetails) {
		webBookingDetailsDao.update(bookingDetails);
		return bookingDetails;
	}

	@Transactional
	public BookingDetails getUpdatedBookingDetailsByMissCallByObj(VehicleRequestObject vehicleRequest,
			BookingDetails bookingDetails) throws ParseException {

		bookingDetails.setEnquirySource(vehicleRequest.getEnquirySource());
		bookingDetails.setVehicleType(vehicleRequest.getVehicleType());
		bookingDetails.setVehicleBrand(vehicleRequest.getVehicleBrand());
		bookingDetails.setVehicleName(vehicleRequest.getVehicleName());
		bookingDetails.setCity(vehicleRequest.getCity());
		bookingDetails.setVehicleQuantity(vehicleRequest.getVehicleQuantity());
		bookingDetails.setAdminVehiclePrice(vehicleRequest.getAdminVehiclePrice());
		bookingDetails.setCustomerName(vehicleRequest.getFirstName().substring(0, 1).toUpperCase() + " "
				+ vehicleRequest.getLastName().substring(0, 1).toUpperCase());
		bookingDetails.setCountryCode(vehicleRequest.getCountryCode());
		bookingDetails.setCustomerMobile(vehicleRequest.getCustomerMobile());
		bookingDetails.setVehicleDetailsType(vehicleRequest.getVehicleDetailsType());
		bookingDetails.setStatus(vehicleRequest.getStatus());
		bookingDetails.setReBookingStatus(bookingDetails.getReBookingStatus());
		bookingDetails.setBookingPrice(vehicleRequest.getBookingPrice());
		bookingDetails.setMiscellaneous(vehicleRequest.getMiscellaneous());
		bookingDetails.setFromDate(vehicleRequest.getFromDate());
		bookingDetails.setToDate(vehicleRequest.getToDate());
		bookingDetails.setDeliveryTime(vehicleRequest.getDeliveryTime());
		bookingDetails.setPickupTime(vehicleRequest.getPickupTime());
		bookingDetails.setAreaFrom(vehicleRequest.getAreaFrom());
		bookingDetails.setAreaTo(vehicleRequest.getAreaTo());
		bookingDetails.setDeliveryCharges(vehicleRequest.getDeliveryCharges());
		bookingDetails.setReceivedAmount(vehicleRequest.getReceivedAmount());
		bookingDetails.setBalenceAmount(vehicleRequest.getBalenceAmount());
		bookingDetails.setTotalAmount(vehicleRequest.getTotalAmount());
		bookingDetails.setNotes(vehicleRequest.getNotes());
		bookingDetails.setReBookingStatus("OPEN");
		bookingDetails.setAdminId("9922957643");
		bookingDetails.setAdminName("James N");
		bookingDetails.setCreatedBy(vehicleRequest.getCreatedBy());
		bookingDetails.setCreatedbyName(vehicleRequest.getCreatedbyName());
		bookingDetails.setSecurityDeposit(vehicleRequest.getSecurityDeposit());
		bookingDetails.setUpdatedAt(new Date());
		return bookingDetails;
	}

	@Transactional
	public TotalBookingCount getTotalBookingCountByuserId(String userId) {
		Criteria cr = totalBookingCountDao.getSession().createCriteria(TotalBookingCount.class);
		cr.add(Restrictions.eq("userId", userId));
		TotalBookingCount totalBookingCount = (TotalBookingCount) cr.uniqueResult();
		return totalBookingCount;
	}

	public TotalBookingCount getTotalBookingCountByObj(VehicleRequestObject vehicleRequest) throws ParseException {

		TotalBookingCount totalBookingCount = new TotalBookingCount();

		totalBookingCount.setUserId(vehicleRequest.getCreatedBy());
		totalBookingCount.setBookingQtyDay(1);
		totalBookingCount.setBookingQtyWeek(1);
		totalBookingCount.setBookingQtyMonth(1);
		totalBookingCount.setBookingQtyYear(1);
		totalBookingCount.setAmountReceiveDay(vehicleRequest.getReceivedAmount());
		totalBookingCount.setAmountReceiveWeek(vehicleRequest.getReceivedAmount());
		totalBookingCount.setAmountReceiveMonth(vehicleRequest.getReceivedAmount());
		totalBookingCount.setAmountReceiveYear(vehicleRequest.getReceivedAmount());

		return totalBookingCount;
	}

	public TotalBookingCount getUpdatedTotalBookingCountByObj(VehicleRequestObject vehicleRequest,
			TotalBookingCount totalBookingCount) throws ParseException {

		totalBookingCount.setUserId(vehicleRequest.getCreatedBy());
		totalBookingCount.setBookingQtyDay(1);
		totalBookingCount.setBookingQtyWeek(1);
		totalBookingCount.setBookingQtyMonth(1);
		totalBookingCount.setBookingQtyYear(1);
		totalBookingCount.setAmountReceiveDay(vehicleRequest.getReceivedAmount());
		totalBookingCount.setAmountReceiveWeek(vehicleRequest.getReceivedAmount());
		totalBookingCount.setAmountReceiveMonth(vehicleRequest.getReceivedAmount());
		totalBookingCount.setAmountReceiveYear(vehicleRequest.getReceivedAmount());

		return totalBookingCount;
	}

	@Transactional
	public TotalBookingCount saveTotalBookingCount(TotalBookingCount totalBookingCount) {
		totalBookingCountDao.persist(totalBookingCount);
		return totalBookingCount;
	}

	@SuppressWarnings("unchecked")
	public List<BookingDetails> getBookingDetailsByCustomerMobileNo(VehicleRequestObject bookingRequest) {
		Date date = new Date();
		Date previousDay = Date.from(date.toInstant().minus(15L, ChronoUnit.DAYS));
		Date nextDay = Date.from(date.toInstant().plus(1L, ChronoUnit.DAYS));		
		
		List<BookingDetails> results = this.bookingDetailsDao.getEntityManager().createQuery(
					"SELECT UD FROM BookingDetails UD WHERE UD.customerMobile =:customerMobile")
					.setParameter("customerMobile", bookingRequest.getCustomerMobile())
//					.setParameter("BOOKED", "BOOKED")
					.getResultList();
			return results;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<WebBookingDetails> getAllWebBookingDetails(VehicleRequestObject bookingRequest) {
		
		List<WebBookingDetails> results = this.bookingDetailsDao.getEntityManager().createQuery(
					"SELECT UD FROM WebBookingDetails UD order by UD.id desc")
					.getResultList();
			return results;
		
	}

}
