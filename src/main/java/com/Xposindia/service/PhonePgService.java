package com.Xposindia.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Xposindia.entities.WebBookingDetails;
import com.Xposindia.object.request.PhonePeStatusRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



@Service
public class PhonePgService {

	@Autowired
	HttpServletRequest request;

	
	private String clientId = "TEST-M22PTZVX5Y144_25041";
	private String saltKey ="MDc0ZDVjMTUtYTA1MS00NDUzLTk5NDUtNzE1YjEzYTM3OWI1";
	
//	private String clientId = "SU2504111552196216732722";
//	private String saltKey ="d52ed729-cfc8-4e84-af3b-0d001ad06353";
//	private String authurl = "https://api.phonepe.com/apis/identity-manager/v1/oauth/token";
//	private String paymentLinkUrl = "https://api.phonepe.com/apis/pg/checkout/v2/pay";
	

	
	public String getParameter(WebBookingDetails bookingDetails) throws Exception {
		JSONObject metaInfo = new JSONObject()
                .put("udf1", bookingDetails.getCustomerName())
                .put("udf2", bookingDetails.getCustomerMobile())
                .put("udf3", "test3")
                .put("udf4", "dummy value 4")
                .put("udf5", "addition infor ref1");

        JSONObject merchantUrls = new JSONObject()
                .put("redirectUrl", "http://romeyourway.com/payment-success");

        JSONObject paymentFlow = new JSONObject()
                .put("type", "PG_CHECKOUT")
                .put("message", "Payment message used for collect requests")
                .put("merchantUrls", merchantUrls);

        JSONObject requestBody = new JSONObject()
                .put("merchantOrderId", bookingDetails.getBookingId())
                .put("amount", bookingDetails.getTotalAmount()*100)
                .put("expireAfter", 1200)
                .put("metaInfo", metaInfo)
                .put("paymentFlow", paymentFlow);

        System.out.println(requestBody.toString(4)); // Pretty print
		return requestBody.toString(4);
    }
	
	
	public String getAccessToken() throws IOException {
        OkHttpClient client = new OkHttpClient();

        // Proper form-data using FormBody builder
        RequestBody body = new FormBody.Builder()
                .add("client_id", clientId)
                .add("client_version", "1")
                .add("client_secret", saltKey)
                .add("grant_type", "client_credentials")
                .build();

        // Construct POST request
        Request request = new Request.Builder()
                .url("https://api-preprod.phonepe.com/apis/pg-sandbox/v1/oauth/token")
//                .url(authurl)
                .post(body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        // Execute and handle response
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code: " + response + "\n" + response.body().string());
            }

            String responseBody = response.body().string();
            JSONObject json = new JSONObject(responseBody);
            String encryptedAccessToken = json.getString("encrypted_access_token");

            return encryptedAccessToken;
        }
    }
		
	
	@SuppressWarnings("deprecation")
	public String getPhonePePaymentUrl(WebBookingDetails bookingDetails) throws Exception {
		String token = this.getAccessToken();
		String parameter = this.getParameter(bookingDetails);
		
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, parameter);
		Request request = new Request.Builder().url("https://api-preprod.phonepe.com/apis/pg-sandbox/checkout/v2/pay")
//		Request request = new Request.Builder().url(paymentLinkUrl)
				.method("POST", body).addHeader("Content-Type", "application/json")
				.addHeader("Authorization", "O-Bearer " + token).build();

		try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful()) {
				throw new IOException("Unexpected code: " + response);
			}

			String responseBody = response.body().string();
			JSONObject json = new JSONObject(responseBody);

			String redirectUrl = json.getString("redirectUrl");
			return redirectUrl;
		}
	}
	
	public PhonePeStatusRequest checkPhonePePaymentStatus(String merchantOrderId) throws IOException {
	    System.out.println("1");
	    String token = this.getAccessToken();
	    System.out.println("2 : " + token);

	    OkHttpClient client = new OkHttpClient().newBuilder().build();

	    Request request = new Request.Builder()
	        .url("https://api-preprod.phonepe.com/apis/pg-sandbox/checkout/v2/order/" + merchantOrderId + "/status")
	        .get()
	        .addHeader("Content-Type", "application/json")
	        .addHeader("Authorization", "O-Bearer " + token)
	        .build();

	    try (Response response = client.newCall(request).execute()) {
	        if (!response.isSuccessful()) {
	            throw new IOException("Unexpected code " + response);
	        }

	        String responseBody = response.body().string();
	        System.out.println("Status Response: " + responseBody);

	        ObjectMapper mapper = new ObjectMapper();
	        return mapper.readValue(responseBody, PhonePeStatusRequest.class);
	    }
	}

}