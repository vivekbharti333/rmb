package com.Xposindia.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.Xposindia.constant.Constant;
import com.Xposindia.entities.BookingDetails;
import com.Xposindia.entities.VehicleDetails;
import com.Xposindia.entities.WebBookingDetails;
import com.Xposindia.expections.BizException;
import com.Xposindia.helper.UserHelper;
import com.Xposindia.helper.WebBookingHelper;
import com.Xposindia.object.request.Request;
import com.Xposindia.object.request.VehicleRequestObject;
import com.Xposindia.object.response.GenricResponse;
import com.Xposindia.object.response.Response;
import com.Xposindia.service.BookingService;
import com.Xposindia.service.WebBookingService;


@CrossOrigin(origins = "*")
@RestController
public class WebBookingController 
{	
	
	@Autowired
	private UserHelper userHelper;
	
	@Autowired
	private WebBookingService webBookingService;
	
	@Autowired
	private WebBookingHelper webBookingHelper;
		
	
	@RequestMapping(path = "waterSportsBooking", method = RequestMethod.POST)
	public Response<VehicleRequestObject>waterSportsBooking(@RequestBody Request<VehicleRequestObject> vehicleRequestObject)
	{
		GenricResponse<VehicleRequestObject> responseObj = new GenricResponse<VehicleRequestObject>();
		try {
			VehicleRequestObject responce =  webBookingService.waterSportsBooking(vehicleRequestObject);
			return responseObj.createSuccessResponse(responce, Constant.SUCCESS_CODE);
		}catch (BizException e) {
			return responseObj.createErrorResponse(Constant.BAD_REQUEST_CODE,e.getMessage());
		} 
 		catch (Exception e) {
			return responseObj.createErrorResponse(Constant.INTERNAL_SERVER_ERR, e.getMessage());
		}
	 }
	
//	@RequestMapping(path = "getBookingDetailsByCustomerMobileNo", method = RequestMethod.POST)
//	public Response<WebBookingDetails> getBookingDetailsByCustomerMobileNo(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
//		GenricResponse<WebBookingDetails> response = new GenricResponse<WebBookingDetails>();
//		try {
//			List<WebBookingDetails> bikeList = webBookingService.getBookingDetailsByCustomerMobileNo(vehicleRequestObject);
//			return response.createListResponse(bikeList, 200);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return response.createErrorResponse(400, e.getMessage());
//		}
//	}
	
	
	@RequestMapping(path = "getAllWebBookingDetails", method = RequestMethod.POST)
	public Response<WebBookingDetails> getAllWebBookingDetails(@RequestBody Request<VehicleRequestObject> vehicleRequestObject) {
		GenricResponse<WebBookingDetails> response = new GenricResponse<WebBookingDetails>();
		try {
			List<WebBookingDetails> bikeList = webBookingService.getAllWebBookingDetails(vehicleRequestObject);
			return response.createListResponse(bikeList, 200);
		} catch (Exception e) {
			e.printStackTrace();
			return response.createErrorResponse(400, e.getMessage());
		}
	}
	
	
	@RequestMapping(path = "razorpay/callback", method = RequestMethod.GET)
    public String handleRazorpayCallback(
            @RequestParam("razorpay_payment_id") String razorpayPaymentId,
            @RequestParam("razorpay_payment_link_id") String razorpayPaymentLinkId,
            @RequestParam("razorpay_payment_link_reference_id") String razorpayPaymentLinkReferenceId,
            @RequestParam("razorpay_payment_link_status") String razorpayPaymentLinkStatus,
            @RequestParam("razorpay_signature") String razorpaySignature) {

        // You can process the parameters here as needed
        // For demonstration, we will just print them
//        System.out.println("Razorpay Payment ID: " + razorpayPaymentId);
//        System.out.println("Razorpay Payment Link ID: " + razorpayPaymentLinkId);
//        System.out.println("Razorpay Payment Link Reference ID: " + razorpayPaymentLinkReferenceId);
//        System.out.println("Razorpay Payment Link Status: " + razorpayPaymentLinkStatus);
//        System.out.println("Razorpay Signature: " + razorpaySignature);
        
        
        WebBookingDetails bookingDetails = webBookingHelper.getVehicleDetailsByInvoiceNumber(razorpayPaymentLinkReferenceId);
        
        
        if(bookingDetails != null ) {
        	
        	System.out.println("enter 1");
        	
        	bookingDetails.setStatus("PAID ONLINE");
        	bookingDetails.setUpdatedAt(new Date());
        	webBookingHelper.updateWebBookingDetails(bookingDetails);
        }

        // Return a response if needed
        return "Call Admin for Payment Confirmation";
    }
	

} 

