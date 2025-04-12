package com.Xposindia.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Xposindia.expections.BizException;
import com.Xposindia.helper.BookingHelper;
import com.Xposindia.helper.CommonHelper;
import com.Xposindia.helper.UserHelper;
import com.Xposindia.helper.VehicleHelper;
import com.Xposindia.object.request.Request;
import com.Xposindia.object.request.UserRequestObject;
import com.Xposindia.object.request.VehicleRequestObject;
import com.razorpay.PaymentLink;
//import com.razorpay.RazorpayClient;

import org.json.JSONObject;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

@Service
public class RozarpayPgService {

	@Autowired
	private UserHelper userHelper;

	@Autowired
	HttpServletRequest request;

	@Autowired
	private VehicleHelper vehicleHelper;
	
	@Autowired
	private BookingHelper bookingHelper;
	
	@Autowired
	private CommonHelper commonHelper; 

	/**
	 * New User Registration API
	 **/
	public String getRozarpayPaymentLink(VehicleRequestObject vehicleRequest )
			throws BizException, Exception {
//		UserRequestObject userRequest = userRequestObject.getPayload();
//		userHelper.validateUserRequest(userRequest);
		
		
//		RazorpayClient razorpay = new RazorpayClient("rzp_test_KiREBJNBMR41TP", "It78jFUUZQZl7f3d6I6iUAI2");
		RazorpayClient razorpay = new RazorpayClient("rzp_live_udlCDMeLep9nk2", "S0z7n1X96xlkCKURtlxmqoGn");
		JSONObject paymentLinkRequest = new JSONObject();
		paymentLinkRequest.put("amount", vehicleRequest.getTotalAmount());
//		paymentLinkRequest.put("amount", 1.00);
		paymentLinkRequest.put("currency","INR");
		paymentLinkRequest.put("accept_partial",false);
//		paymentLinkRequest.put("first_min_partial_amount",0);
		paymentLinkRequest.put("expire_by",1750346930);
		paymentLinkRequest.put("reference_id", vehicleRequest.getInvoiceNumber());
//		paymentLinkRequest.put("description","Payment for Invoice no. : 23456");
		JSONObject customer = new JSONObject();
		customer.put("name", vehicleRequest.getCustomerName());
		customer.put("contact", vehicleRequest.getCustomerMobile());
		customer.put("email", vehicleRequest.getEmailId());
		paymentLinkRequest.put("customer",customer);
		JSONObject notify = new JSONObject();
		notify.put("sms",true);
		notify.put("email",true);
		paymentLinkRequest.put("notify",notify);
		paymentLinkRequest.put("reminder_enable",true);
//		paymentLinkRequest.put("callback_url","http://localhost:4200/#/payments/payment-success");
//		paymentLinkRequest.put("callback_url","http://localhost:8080/vehicle/razorpay/callback");
		paymentLinkRequest.put("callback_url","https://romeyourway.com/payment-success");
		paymentLinkRequest.put("callback_method","get");
		              
		PaymentLink payment = razorpay.paymentLink.create(paymentLinkRequest);
		System.out.println("Payment : "+payment);
		

//			String payment ="{\"cancelled_at\":0,\"reminders\":[],\"amount_paid\":0,\"notes\":null,\"reference_id\":\"435721274890\",\"payments\":null,\"created_at\":1719737886,\"description\":\"\",\"expired_at\":0,\"notify\":{\"whatsapp\":false,\"sms\":true,\"email\":true},\"short_url\":\"https://rzp.io/i/d8cBhMLc\",\"callback_url\":\"https://example-callback-url.com/\",\"updated_at\":1719737886,\"upi_link\":false,\"accept_partial\":false,\"currency\":\"INR\",\"id\":\"plink_OSul6f1NkoWH99\",\"callback_method\":\"get\",\"expire_by\":1719769671,\"first_min_partial_amount\":0,\"whatsapp_link\":false,\"amount\":1000,\"reminder_enable\":true,\"user_id\":\"\",\"customer\":{\"contact\":\"9876567890\",\"name\":\"Test User\"},\"status\":\"created\"}\n"
//					+ "";
		
		JSONObject jsonObject = new JSONObject(payment.toString());

        // Get and print the short_url
        String shortUrl = jsonObject.getString("short_url");
        System.out.println(shortUrl);
        
        System.out.println("shortUrl : "+shortUrl);

		return shortUrl;
	}
	
	
	public boolean getRozarpayPaymentSignature(VehicleRequestObject vehicleRequest ) throws RazorpayException {
		
		RazorpayClient razorpay = new RazorpayClient("rzp_test_KiREBJNBMR41TP", "It78jFUUZQZl7f3d6I6iUAI2");

		String secret = "EnLs21M47BllR3X8PSFtjtbd";

		JSONObject options = new JSONObject();
		options.put("payment_link_reference_id", "TSsd1989");
		options.put("razorpay_payment_id", "pay_IH3d0ara9bSsjQ");
		options.put("payment_link_status", "paid");
		options.put("payment_link_id", "plink_IH3cNucfVEgV68");
		options.put("razorpay_signature", "07ae18789e35093e51d0a491eb9922646f3f82773547e5b0f67ee3f2d3bf7d5b");

		boolean status =  Utils.verifyPaymentLink(options, secret);
		
		return status;	
	}
		

}