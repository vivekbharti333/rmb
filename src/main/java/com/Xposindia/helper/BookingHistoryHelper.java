package com.Xposindia.helper;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.TemporalType;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.Xposindia.constant.Constant;
import com.Xposindia.dao.BookingDetailsDao;
import com.Xposindia.dao.BookingDetailsHistoryDao;
import com.Xposindia.dao.TotalBookingCountDao;
import com.Xposindia.entities.BonusSlab;
import com.Xposindia.entities.BookingDetails;
import com.Xposindia.entities.BookingDetailsHistory;
import com.Xposindia.entities.TotalBookingCount;
import com.Xposindia.entities.Users;
import com.Xposindia.entities.VehicleDetails;
import com.Xposindia.expections.BizException;
import com.Xposindia.object.request.Request;
import com.Xposindia.object.request.VehicleRequestObject;
import com.itextpdf.text.log.SysoCounter;

@Component
public class BookingHistoryHelper {

	@Autowired
	private BookingDetailsHistoryDao bookingDetailsHistoryDao;


	public void validateBookingRequest(VehicleRequestObject vehicleRequestObject) throws BizException {
		if (vehicleRequestObject == null) {
			throw new BizException(Constant.BAD_REQUEST_CODE, "Bad Request Object Null");
		}
	}

	
	@Transactional
	public BookingDetailsHistory getVehicleDetailsHistoryByObj(BookingDetails bookingDetails) {

		BookingDetailsHistory bookingDetailsHistory = new BookingDetailsHistory();

		bookingDetailsHistory.setBookingDetailsId(bookingDetails.getId());
		bookingDetailsHistory.setEnquirySource(bookingDetails.getEnquirySource());
		bookingDetailsHistory.setVehicleType(bookingDetails.getVehicleType());
		bookingDetailsHistory.setVehicleBrand(bookingDetails.getVehicleBrand());
		bookingDetailsHistory.setVehicleName(bookingDetails.getVehicleName());
		bookingDetailsHistory.setCity(bookingDetails.getCity());
		bookingDetailsHistory.setVehicleQuantity(bookingDetails.getVehicleQuantity());
		bookingDetailsHistory.setAdminVehiclePrice(bookingDetails.getAdminVehiclePrice());
		bookingDetailsHistory.setCustomerName(bookingDetails.getCustomerName());

		bookingDetailsHistory.setCountryCode(bookingDetails.getCountryCode());
		bookingDetailsHistory.setCustomerMobile(bookingDetails.getCustomerMobile());
		bookingDetailsHistory.setVehicleDetailsType(bookingDetails.getVehicleDetailsType());
		bookingDetailsHistory.setStatus(bookingDetails.getStatus());
		bookingDetailsHistory.setReBookingStatus("OPEN");
		bookingDetailsHistory.setBookingPrice(bookingDetails.getBookingPrice());
		bookingDetailsHistory.setMiscellaneous(bookingDetails.getMiscellaneous());
		bookingDetailsHistory.setFromDate(bookingDetails.getFromDate());
		bookingDetailsHistory.setToDate(bookingDetails.getToDate());
		bookingDetailsHistory.setDeliveryTime(bookingDetails.getDeliveryTime());
		bookingDetailsHistory.setPickupTime(bookingDetails.getPickupTime());
		bookingDetailsHistory.setAreaFrom(bookingDetails.getAreaFrom());
		bookingDetailsHistory.setAreaTo(bookingDetails.getAreaTo());

		bookingDetailsHistory.setDeliveryCharges(bookingDetails.getDeliveryCharges());
		bookingDetailsHistory.setReceivedAmount(bookingDetails.getReceivedAmount());
		bookingDetailsHistory.setBalenceAmount(bookingDetails.getBalenceAmount());
		bookingDetailsHistory.setTotalAmount(bookingDetails.getTotalAmount());
		bookingDetailsHistory.setNotes(bookingDetails.getNotes());
		bookingDetailsHistory.setSecurityDeposit(bookingDetails.getSecurityDeposit());

		bookingDetailsHistory.setAdminId("9922957643");
		bookingDetailsHistory.setAdminName("James N");
		
		bookingDetailsHistory.setDeliveryExecutiveId("N/A");
		bookingDetailsHistory.setDeliveryExecutiveName("N/A");

		bookingDetailsHistory.setCreatedBy(bookingDetails.getCreatedBy());
		bookingDetailsHistory.setCreatedbyName(bookingDetails.getCreatedbyName());
		bookingDetailsHistory.setCreatedAt(new Date());
		return bookingDetailsHistory;
	}

	@Transactional
	public BookingDetailsHistory saveBookingDetailsHistory(BookingDetailsHistory bookingDetailsHistory) {
		bookingDetailsHistoryDao.persist(bookingDetailsHistory);
		return bookingDetailsHistory;
	}

	

	


}
