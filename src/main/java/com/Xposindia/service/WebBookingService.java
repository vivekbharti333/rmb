package com.Xposindia.service;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Xposindia.constant.Constant;
import com.Xposindia.entities.BookingDetails;
import com.Xposindia.entities.WebBookingDetails;
import com.Xposindia.expections.BizException;
import com.Xposindia.helper.BookingHelper;
import com.Xposindia.helper.WebBookingHelper;
import com.Xposindia.object.request.Request;
import com.Xposindia.object.request.VehicleRequestObject;

@Service
public class WebBookingService {

	@Autowired
	private WebBookingHelper webBookingHelper;
	
	@Autowired
	private BookingHelper bookingHelper;
	
	@Autowired
	private RozarpayPgService rozarpayPgService;
	
	@Autowired
	private PhonePgService phonePgService;
	
	

	@Transactional
	public VehicleRequestObject waterSportsBooking(Request<VehicleRequestObject> vehicleRequestObject)
			throws BizException, Exception {
		VehicleRequestObject vehicleRequest = (VehicleRequestObject) vehicleRequestObject.getPayload();
		webBookingHelper.validateBookingRequest(vehicleRequest);

		List<BookingDetails> bookingList = bookingHelper.getBookingDetailsByCustomerMobileNo(vehicleRequest);
		if (!bookingList.isEmpty()) {
			vehicleRequest.setRespCode(Constant.BAD_REQUEST_CODE);
			vehicleRequest.setRespMesg("Booking With Same Mobile No. Already Exists");
			return vehicleRequest;
		} else {
			vehicleRequest.setInvoiceNumber(StringUtils.substring(RandomStringUtils.random(64, false, true), 0, 12));
			WebBookingDetails bookingDetails = webBookingHelper.getWaterSportsDetailsByObj(vehicleRequest);
			webBookingHelper.savewebBookingDetails(bookingDetails);

			System.out.println("Enter 3 : ");
//			phonePgService.getPhonePePaymentUrl(bookingDetails);
//			String paymentLink = rozarpayPgService.getRozarpayPaymentLink(vehicleRequest);

			vehicleRequest.setPaymentLink(phonePgService.getPhonePePaymentUrl(bookingDetails));

		}

		return vehicleRequest;

	}
	

	public List<BookingDetails> getBookingDetailsByCustomerMobileNo(Request<VehicleRequestObject> vehicleRequestObject) {
	      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
	      List<BookingDetails> bookingList = webBookingHelper.getBookingDetailsByCustomerMobileNo(bookingRequest);
	      return bookingList;
	   }
	
	public List<WebBookingDetails> getAllWebBookingDetails(Request<VehicleRequestObject> vehicleRequestObject) {
	      VehicleRequestObject bookingRequest = (VehicleRequestObject)vehicleRequestObject.getPayload();
	      List<WebBookingDetails> bookingList = webBookingHelper.getAllWebBookingDetails(bookingRequest);
	      return bookingList;
	   }
	
	



	

}
