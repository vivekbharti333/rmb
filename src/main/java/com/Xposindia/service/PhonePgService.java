package com.Xposindia.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.MediaType;


import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Xposindia.constant.Constant;
import com.Xposindia.expections.BizException;
import com.Xposindia.helper.BookingHelper;
import com.Xposindia.helper.CommonHelper;
import com.Xposindia.helper.UserHelper;
import com.Xposindia.helper.VehicleHelper;
import com.Xposindia.object.request.VehicleRequestObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;



@Service
public class PhonePgService {

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
	
//	private String clientId = "EST-M22PTZVX5Y144_25041";
//	private String saltKey ="MDc0ZDVjMTUtYTA1MS00NDUzLTk5NDUtNzE1YjEzYTM3OWI1";
	
	private String clientId = "SU2504111552196216732722";
	private String saltKey ="d52ed729-cfc8-4e84-af3b-0d001ad06353";

	public VehicleRequestObject getPhonePePaymentLink(VehicleRequestObject vehicleRequest) throws BizException, Exception {

		// Call Payment Gateway API
		String param = this.getPhonePePaymentGatewayParam(vehicleRequest);

		String jsonResponse = this.getPhonePePaymentPageRequest(param);
		System.out.println("Payment Gateway Response : "+jsonResponse);

//		String jsonResponse = "{\"success\":true,\"code\":\"PAYMENT_INITIATED\",\"message\":\"Payment initiated\",\"data\":{\"merchantId\":\"M22XLI1BBSR4N\",\"merchantTransactionId\":\"CI/CEF/022024/4697\",\"instrumentResponse\":{\"type\":\"PAY_PAGE\",\"redirectInfo\":{\"url\":\"https://mercury-t2.phonepe.com/transact/pg?token=NGMzYzdhZDM5ODkwMWNiM2U0OTc4NmY2MGVhMDU2N2Y5NzM0M2I1MTJkYmZiNDc3MDVhNDYwNjdjNzY3YTc5YjFlNGNkOTkyZTlmYTZhZmRhZjZjYjczOThjYTQ1ODM1OjQ2NWNlYmE3YjIxZDJjNDM3NmMzNWYxMTMxYjdjNDdm\",\"method\":\"GET\"}}}}";

		JSONObject jsonObject = new JSONObject(jsonResponse);
		String code = jsonObject.getString("code");
		boolean success = jsonObject.getBoolean("success");
		if (success) {

			JSONObject data = jsonObject.getJSONObject("data");
			JSONObject instrumentResponse = data.getJSONObject("instrumentResponse");
			JSONObject redirectInfo = instrumentResponse.getJSONObject("redirectInfo");
			String paymentUrl = redirectInfo.getString("url");

			vehicleRequest.setPaymentGatewayPageRedirectUrl(paymentUrl);
			vehicleRequest.setRespCode(Constant.SUCCESS_CODE);
			vehicleRequest.setRespMesg("Successfully Register & Payment Link Send");
			return vehicleRequest;
		} else {
			// payment Failed
			vehicleRequest.setRespCode(Constant.BAD_REQUEST_CODE);
			vehicleRequest.setRespMesg("Booking Saved but paymentgateway Faild " + code);
			return vehicleRequest;
		}
	}

	public String getPhonePePaymentGatewayParam(VehicleRequestObject vehicleRequest)
			throws Exception {
		JSONObject parameters = new JSONObject();
		JSONObject paymentInstrument = new JSONObject();

		paymentInstrument.put("type", "PAY_PAGE");

		parameters.put("merchantId", clientId);
		parameters.put("merchantTransactionId",vehicleRequest.getInvoiceNumber());
		parameters.put("merchantUserId", "WEBBOOKING");
		parameters.put("amount", vehicleRequest.getTotalAmount());
		parameters.put("redirectUrl", "https://datfuslab.com/successfull");
		parameters.put("redirectMode", "REDIRECT");
		parameters.put("callbackUrl", "https://datafusionlab.co.in:8080/mycrm/phonePePgResponse");
		parameters.put("mobileNumber", vehicleRequest.getCustomerMobile());
		parameters.put("paymentInstrument", paymentInstrument);

		return parameters.toString();
	}

	public String getSha256(String base64param) throws NoSuchAlgorithmException {

		String originalString = base64param + "/pg/v1/pay" + saltKey;

		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));

		StringBuilder hexString = new StringBuilder(2 * hash.length);
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

	@SuppressWarnings("deprecation")
	public String getPhonePePaymentPageRequest(String param)
			throws NoSuchAlgorithmException, IOException {
		String result = "";
		String encodedString = Base64.getEncoder().encodeToString(param.getBytes());
		String xVeryfy = getSha256(encodedString);

		OkHttpClient client = new OkHttpClient();

		JSONObject requestedParam = new JSONObject();
		requestedParam.put("request", encodedString);

		// Build the request body with JSON content
		RequestBody body = RequestBody.create(MediaType.parse("application/json"), requestedParam.toString());

		// Build the request
		Request request = new Request.Builder()
//				.url("https://api.phonepe.com/apis/hermes/pg/v1/pay").post(body)
				.url("https://api-preprod.phonepe.com/apis/pg-sandbox/checkout/v2/pay").post(body)
				.addHeader("accept", "application/json")
				.addHeader("Content-Type", "application/json")
				.addHeader("X-VERIFY", xVeryfy + "###")
				 .addHeader("Authorization", saltKey)
				.build();
		
		
		
		// Execute the request
		Response response = null;
		try {
			response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				result = response.body().string();
				System.out.println("Result : "+result);
				return result;
			} else {
				System.out.println("Unexpected response code: " + response);
				result = response.body().string();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
            if (response != null) {
                response.body().close();
            }
        }
		return result;
	}
	
	
	@SuppressWarnings("deprecation")
	public String getAccessToken() throws IOException {
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
				RequestBody body = RequestBody.create(mediaType, "client_id="+clientId+"&client_version=1&client_secret="+saltKey+"&grant_type=client_credentials");
				Request request = new Request.Builder()
				  .url("https://api-preprod.phonepe.com/apis/pg-sandbox/v1/oauth/token")
				  .method("POST", body)
				  .addHeader("Content-Type", "application/x-www-form-urlencoded")
				  .build();
				Response response = client.newCall(request).execute();
				return response.toString();
	}
		

}