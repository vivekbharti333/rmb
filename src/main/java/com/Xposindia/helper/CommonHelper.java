package com.Xposindia.helper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.json.JsonObject;

import org.json.JSONArray;
import org.springframework.stereotype.Component;

import com.Xposindia.entities.BookingDetails;
import com.Xposindia.entities.Users;
import com.Xposindia.object.request.CustomerRequestObject;
import com.Xposindia.object.request.VehicleRequestObject;
import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import org.json.JSONArray;


@Component
public class CommonHelper {

	public String fallowUpParameter(BookingDetails vehicleRequest) throws Exception {
		JSONObject parameters = new JSONObject();
		JSONObject templete = new JSONObject();
		JSONObject zero = new JSONObject();

		String[] headerValues = { "Alert" };
		String[] zeroValue = { "12344" };

		String[] bodyValues = { vehicleRequest.getCustomerName() };

		templete.put("name", "rmb_followup_b4");
		templete.put("languageCode", "en");
		templete.put("headerValues", headerValues);
		templete.put("fileName", "dumy.pdf");
		templete.put("bodyValues", bodyValues);
		templete.put("buttonValues", zero);

		zero.put("0", zeroValue);

		parameters.put("countryCode", vehicleRequest.getCountryCode());
		parameters.put("phoneNumber", vehicleRequest.getCustomerMobile());
		parameters.put("type", "Template");
		parameters.put("callbackData:", "some_callback_data");
		parameters.put("template", templete);

		return parameters.toString();
	}

	public String bookingConfirmationParameters(BookingDetails bookingDetails) throws Exception {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		String frmDate = formatter.format(bookingDetails.getFromDate());
		String fromDate = frmDate;
		String pickupTime = String.valueOf(bookingDetails.getPickupTime()+":00 Hrs.");
		
		String toDat = formatter.format(bookingDetails.getToDate());
		String toDate = toDat;
		String dropTime = String.valueOf(bookingDetails.getDeliveryTime()+":00 Hrs.");

		String bookingAmount = String.valueOf(bookingDetails.getReceivedAmount());
		String balenceAmount = String.valueOf(bookingDetails.getBalenceAmount());
		String totalAmount = String.valueOf(bookingDetails.getTotalAmount());
		String quantity = String.valueOf(bookingDetails.getVehicleQuantity());
		String securityDeposit = String.valueOf(bookingDetails.getSecurityDeposit());
		
		JSONObject parameters = new JSONObject();
		JSONObject templete = new JSONObject();
		JSONObject zero = new JSONObject();

		String[] headerValues = { "Alert" };
		String[] zeroValue = { "12344" };

		String[] bodyValues = { bookingDetails.getCustomerName(), bookingDetails.getCustomerMobile(),
				bookingDetails.getVehicleName(), bookingDetails.getVehicleDetailsType(), quantity, bookingDetails.getCustomerName(), bookingDetails.getCustomerMobile(),
				fromDate, pickupTime, toDate, dropTime, bookingDetails.getAreaFrom(), bookingDetails.getAreaTo(),
				totalAmount, bookingAmount, balenceAmount, securityDeposit};

//		templete.put("name", "bookingconfirmation_rentmybike");
		if(bookingDetails.getCity().equals("HYD")) {
			templete.put("name", "hydconfirmation");
		}else {
			templete.put("name", "rmbb_confirm");
		}
		
		templete.put("languageCode", "en");
		templete.put("headerValues", headerValues);
		templete.put("fileName", "dumy.pdf");
		templete.put("bodyValues", bodyValues);
		templete.put("buttonValues", zero);

		zero.put("0", zeroValue);

//		parameters.put("countryCode", "+91");
		parameters.put("countryCode", bookingDetails.getCountryCode());
		parameters.put("phoneNumber", bookingDetails.getCustomerMobile());
		parameters.put("type", "Template");
		parameters.put("callbackData:", "some_callback_data");
		parameters.put("template", templete);

		return parameters.toString();
	}
	
	public String enquiryParameters(BookingDetails bookingDetails) throws Exception {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		String frmDate = formatter.format(bookingDetails.getFromDate());
		String fromDate = frmDate;
		String pickupTime = String.valueOf(bookingDetails.getPickupTime()+":00 Hrs.");
		
		String toDat = formatter.format(bookingDetails.getToDate());
		String toDate = toDat;
		String dropTime = String.valueOf(bookingDetails.getDeliveryTime()+":00 Hrs.");

		String perdayRent = String.valueOf(bookingDetails.getBookingPrice());
		String deliveryCharges = String.valueOf(bookingDetails.getDeliveryCharges());
		String totalAmount = String.valueOf(bookingDetails.getTotalAmount());
		String quantity = String.valueOf(bookingDetails.getVehicleQuantity());
		String securityDeposit = String.valueOf(bookingDetails.getSecurityDeposit());
		
		JSONObject parameters = new JSONObject();
		JSONObject templete = new JSONObject();
		JSONObject zero = new JSONObject();

		String[] headerValues = { "Alert" };
		String[] zeroValue = { "12344" };
	
		String[] bodyValues = {bookingDetails.getCustomerName(),bookingDetails.getCreatedbyName(),bookingDetails.getVehicleName(),
				bookingDetails.getVehicleDetailsType(), quantity, fromDate, pickupTime, toDate, dropTime,bookingDetails.getAreaFrom(),bookingDetails.getAreaTo(),
				perdayRent,deliveryCharges,totalAmount,securityDeposit};

//		templete.put("name", "rentmybike_enquiry_form");
		
		if(bookingDetails.getCity().equals("HYD")) {
			templete.put("name", "hydenq");
		}else {
			templete.put("name", "rmb_enq_new");
		}
		
		
		templete.put("languageCode", "en");
		templete.put("headerValues", headerValues);
		templete.put("fileName", "dumy.pdf");
		templete.put("bodyValues", bodyValues);
		templete.put("buttonValues", zero);

		zero.put("0", zeroValue);

//		parameters.put("countryCode", "+91");
		parameters.put("countryCode", bookingDetails.getCountryCode());
		parameters.put("phoneNumber", bookingDetails.getCustomerMobile());
		parameters.put("type", "Template");
		parameters.put("callbackData:", "some_callback_data");
		parameters.put("template", templete);

		return parameters.toString();
	}

	
	public String enquiryParametersForCar(BookingDetails bookingDetails) throws Exception {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		String frmDate = formatter.format(bookingDetails.getFromDate());
		String fromDate = frmDate;
		String pickupTime = String.valueOf(bookingDetails.getPickupTime()+":00 Hrs.");
		
		String toDat = formatter.format(bookingDetails.getToDate());
		String toDate = toDat;
		String dropTime = String.valueOf(bookingDetails.getDeliveryTime()+":00 Hrs.");

		String perdayRent = String.valueOf(bookingDetails.getBookingPrice());
		String deliveryCharges = String.valueOf(bookingDetails.getDeliveryCharges());
		String totalAmount = String.valueOf(bookingDetails.getTotalAmount());
		String quantity = String.valueOf(bookingDetails.getVehicleQuantity());
		String securityDeposit = String.valueOf(bookingDetails.getSecurityDeposit());
		
		JSONObject parameters = new JSONObject();
		JSONObject templete = new JSONObject();
		JSONObject zero = new JSONObject();

		String[] headerValues = { "Alert" };
		String[] zeroValue = { "12344" };
	
		String[] bodyValues = {bookingDetails.getCustomerName(),bookingDetails.getCreatedbyName(),bookingDetails.getVehicleName(),
				bookingDetails.getVehicleDetailsType(), quantity, fromDate, pickupTime, toDate, dropTime,bookingDetails.getAreaFrom(),bookingDetails.getAreaTo(),
				perdayRent,deliveryCharges,totalAmount,securityDeposit};

//		templete.put("name", "ezeecarrent_enquiry_form");
		templete.put("name", "ezee_enq_new");
		templete.put("languageCode", "en");
		templete.put("headerValues", headerValues);
		templete.put("fileName", "dumy.pdf");
		templete.put("bodyValues", bodyValues);
		templete.put("buttonValues", zero);

		zero.put("0", zeroValue);

//		parameters.put("countryCode", "+91");
		parameters.put("countryCode", bookingDetails.getCountryCode());
		parameters.put("phoneNumber", bookingDetails.getCustomerMobile());
		parameters.put("type", "Template");
		parameters.put("callbackData:", "some_callback_data");
		parameters.put("template", templete);

		return parameters.toString();
	}
	
	
	public String bookingConfirmationParametersForCar(BookingDetails bookingDetails) throws Exception {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		String frmDate = formatter.format(bookingDetails.getFromDate());
		String fromDate = frmDate;
		String pickupTime = String.valueOf(bookingDetails.getPickupTime()+":00 Hrs.");
		
		String toDat = formatter.format(bookingDetails.getToDate());
		String toDate = toDat;
		String dropTime = String.valueOf(bookingDetails.getDeliveryTime()+":00 Hrs.");

		String bookingAmount = String.valueOf(bookingDetails.getReceivedAmount());
		String balenceAmount = String.valueOf(bookingDetails.getBalenceAmount());
		String totalAmount = String.valueOf(bookingDetails.getTotalAmount());
		String quantity = String.valueOf(bookingDetails.getVehicleQuantity());
		String securityDeposit = String.valueOf(bookingDetails.getSecurityDeposit());
		
		JSONObject parameters = new JSONObject();
		JSONObject templete = new JSONObject();
		JSONObject zero = new JSONObject();

		String[] headerValues = { "Alert" };
		String[] zeroValue = { "12344" };

		String[] bodyValues = { bookingDetails.getCustomerName(), bookingDetails.getCustomerMobile(),
				bookingDetails.getVehicleName(), bookingDetails.getVehicleDetailsType(), quantity, bookingDetails.getCustomerName(), bookingDetails.getCustomerMobile(),
				fromDate, pickupTime, toDate, dropTime, bookingDetails.getAreaFrom(), bookingDetails.getAreaTo(),
				totalAmount, bookingAmount, balenceAmount, securityDeposit};

		templete.put("name", "ezze_confirm");
		templete.put("languageCode", "en");
		templete.put("headerValues", headerValues);
		templete.put("fileName", "dumy.pdf");
		templete.put("bodyValues", bodyValues);
		templete.put("buttonValues", zero);

		zero.put("0", zeroValue);

//		parameters.put("countryCode", "+91");
		parameters.put("countryCode", bookingDetails.getCountryCode());
		parameters.put("phoneNumber", bookingDetails.getCustomerMobile());
		parameters.put("type", "Template");
		parameters.put("callbackData:", "some_callback_data");
		parameters.put("template", templete);

		return parameters.toString();
	}
	
	public String bookingReceiptPdfParametersForVehicle(BookingDetails bookingDetails) throws Exception {

		String bookingAmount = String.valueOf(bookingDetails.getReceivedAmount());
		
		JSONObject parameters = new JSONObject();
		JSONObject templete = new JSONObject();
		JSONObject zero = new JSONObject();

		String[] headerValues = { "Alert" };
		String[] zeroValue = { bookingDetails.getReceiptNumber() };

		String[] bodyValues = { bookingDetails.getReceiptNumber(), bookingAmount};

//		templete.put("name", "payment_receipt");
		templete.put("name", "myraanreceipt");
		templete.put("languageCode", "en");
		templete.put("headerValues", headerValues);
		templete.put("fileName", "dumy.pdf");
		templete.put("bodyValues", bodyValues);
		templete.put("buttonValues", zero);

		zero.put("0", zeroValue);

//		parameters.put("countryCode", "+91");
		parameters.put("countryCode", bookingDetails.getCountryCode());
		parameters.put("phoneNumber", bookingDetails.getCustomerMobile());
//		parameters.put("phoneNumber", "8800689752");
		parameters.put("type", "Template");
		parameters.put("callbackData:", "some_callback_data");
		parameters.put("template", templete);

		return parameters.toString();
	}

	
	public String interaktApi(String parameters) throws Exception {

		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, parameters);
		Request request = new Request.Builder().url("https://api.interakt.ai/v1/public/message/").method("POST", body)
				.addHeader("Content-Type", "application/json")
				.addHeader("Authorization", "Basic dUNkRHc1R01pUlVnaHAtMUx4elAxb2EyamZaT2o2eWtLcjJwYnUzNnlBRTo=")
				.build();
		Response response = client.newCall(request).execute();
		return response.toString();
	}
	
	
//	public void createEvent(Calendar cal) throws IOException {       
//
//		Event event = new Event();
//		event.setSummary("Event name here");
//		event.setLocation("event place here");
//
//		Date startDate = new Date();
//		Date endDate = new Date(startDate.getTime() + 3600000);
//		DateTime start = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
//		event.setStart(new EventDateTime().setDateTime(start));
//		DateTime end = new DateTime(endDate, TimeZone.getTimeZone("UTC"));
//		event.setEnd(new EventDateTime().setDateTime(end));
//		Event createdEvent = cal.events().insert("primary", event).execute();
//		System.out.println("Created event id: " + createdEvent.getId());
//		}
	
	
//	public String forReceipt(BookingDetails bookingDetails) throws Exception {
//		
//		String customerName = bookingDetails.getCustomerName();
//        String[] firstName = customerName.split(" ");
//  
//        System.out.println(firstName[0]+" Name");
//
//		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//		
//		String frmDate = formatter.format(bookingDetails.getFromDate());
//		String fromDate = frmDate;
//		String pickupTime = String.valueOf(bookingDetails.getPickupTime()+":00 Hrs.");
//		
//		String toDat = formatter.format(bookingDetails.getToDate());
//		String toDate = toDat;
//		String dropTime = String.valueOf(bookingDetails.getDeliveryTime()+":00 Hrs.");
//
//		String perdayRent = String.valueOf(bookingDetails.getBookingPrice());
//		String deliveryCharges = String.valueOf(bookingDetails.getDeliveryCharges());
//		String totalAmount = String.valueOf(bookingDetails.getTotalAmount());
//		String quantity = String.valueOf(bookingDetails.getVehicleQuantity());
//		
//		JSONObject parameters = new JSONObject();
//		JSONObject templete = new JSONObject();
//		JSONObject zero = new JSONObject();
//
//		String[] headerValues = { "Alert" };
//		String[] zeroValue = { "12344" };
//	
//		String paidAmount = String.valueOf(bookingDetails.getReceivedAmount());
//		
//		String[] bodyValues = {bookingDetails.getReceiptNumber(),firstName[0],bookingDetails.getReceiptNumber(),paidAmount};
//
////		templete.put("name", "payment_eceipt");
//		templete.put("name", "payment_receipt_new");
//		templete.put("languageCode", "en");
//		templete.put("headerValues", headerValues);
//		templete.put("fileName", "dumy.pdf");
//		templete.put("bodyValues", bodyValues);
//		templete.put("buttonValues", zero);
//
//		zero.put("0", zeroValue);
//
////		parameters.put("countryCode", "+91");
//		parameters.put("countryCode", bookingDetails.getCountryCode());
//		parameters.put("phoneNumber", "8928868317");
//		parameters.put("type", "Template");
//		parameters.put("callbackData:", "some_callback_data");
//		parameters.put("template", templete);
//
//		return parameters.toString();
//	}
	
	
public String forCustomerLoginOtp(CustomerRequestObject customerRequest) throws Exception {
		
		JSONObject parameters = new JSONObject();
		JSONObject templete = new JSONObject();
		JSONObject zero = new JSONObject();

		String[] headerValues = { "Alert" };
		String[] zeroValue = { "12344" };
	
//		String paidAmount = String.valueOf(bookingDetails.getReceivedAmount());
		
		String[] bodyValues = { customerRequest.getCustomerOtp()  };

		templete.put("name", "weblogin");
		templete.put("languageCode", "en");
		templete.put("headerValues", headerValues);
		templete.put("fileName", "dumy.pdf");
		templete.put("bodyValues", bodyValues);
		templete.put("buttonValues", zero);

		zero.put("0", zeroValue);

//		parameters.put("countryCode", "+91");
		parameters.put("countryCode", customerRequest.getCountryCode());
		parameters.put("phoneNumber", customerRequest.getCustomerMobile());
		parameters.put("type", "Template");
		parameters.put("callbackData:", "some_callback_data");
		parameters.put("template", templete);

		return parameters.toString();
	}

	
	
public String forRentalBreakup(BookingDetails bookingDetails) throws Exception {
		
		String customerName = bookingDetails.getCustomerName();
        String[] firstName = customerName.split(" ");
  
        System.out.println(firstName[0]+" Name");

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		String frmDate = formatter.format(bookingDetails.getFromDate());
		String totalAmount = String.valueOf(bookingDetails.getTotalAmount());
		String bookingAmount = String.valueOf(bookingDetails.getReceivedAmount());
		String balenceAmount = String.valueOf(bookingDetails.getBalenceAmount());
		String securityDeposit = String.valueOf(bookingDetails.getSecurityDeposit());
		String quantity = String.valueOf(bookingDetails.getVehicleQuantity());
		
		JSONObject parameters = new JSONObject();
		JSONObject templete = new JSONObject();
		JSONObject zero = new JSONObject();

		String[] headerValues = { "Alert" };
		String[] zeroValue = { "12344" };
	
//		String paidAmount = String.valueOf(bookingDetails.getReceivedAmount());
		
		String[] bodyValues = { quantity, bookingDetails.getVehicleName(), bookingDetails.getVehicleDetailsType(), totalAmount, bookingAmount, balenceAmount, balenceAmount, securityDeposit };

		templete.put("name", "rental_breakup");
		templete.put("languageCode", "en");
		templete.put("headerValues", headerValues);
		templete.put("fileName", "dumy.pdf");
		templete.put("bodyValues", bodyValues);
		templete.put("buttonValues", zero);

		zero.put("0", zeroValue);

//		parameters.put("countryCode", "+91");
		parameters.put("countryCode", bookingDetails.getCountryCode());
		parameters.put("phoneNumber", bookingDetails.getCustomerMobile());
		parameters.put("type", "Template");
		parameters.put("callbackData:", "some_callback_data");
		parameters.put("template", templete);

		return parameters.toString();
	}


public String forPocEzee(BookingDetails bookingDetails) throws Exception {
	
	JSONObject parameters = new JSONObject();
	JSONObject templete = new JSONObject();
	JSONObject zero = new JSONObject();

	String[] headerValues = { "Alert" };
	String[] zeroValue = { "12344" };

//	String paidAmount = String.valueOf(bookingDetails.getReceivedAmount());
	
	String[] bodyValues = { };

	templete.put("name", "poc_ezee");
	templete.put("languageCode", "en");
	templete.put("headerValues", headerValues);
	templete.put("fileName", "dumy.pdf");
	templete.put("bodyValues", bodyValues);
	templete.put("buttonValues", zero);

	zero.put("0", zeroValue);

//	parameters.put("countryCode", "+91");
	parameters.put("countryCode", bookingDetails.getCountryCode());
	parameters.put("phoneNumber", bookingDetails.getCustomerMobile());
	parameters.put("type", "Template");
	parameters.put("callbackData:", "some_callback_data");
	parameters.put("template", templete);

	return parameters.toString();
}



public String forBalancePayment(BookingDetails bookingDetails) throws Exception {
	
	
	String balenceAmount = String.valueOf(bookingDetails.getBalenceAmount());
	
	JSONObject parameters = new JSONObject();
	JSONObject templete = new JSONObject();
	JSONObject zero = new JSONObject();

	String[] headerValues = { "Alert" };
	String[] zeroValue = { "12344" };
	
	String[] bodyValues = { balenceAmount };

	templete.put("name", "balance_payment");
	templete.put("languageCode", "en");
	templete.put("headerValues", headerValues);
	templete.put("fileName", "dumy.pdf");
	templete.put("bodyValues", bodyValues);
	templete.put("buttonValues", zero);

	zero.put("0", zeroValue);

//	parameters.put("countryCode", "+91");
	parameters.put("countryCode", bookingDetails.getCountryCode());
	parameters.put("phoneNumber", bookingDetails.getCustomerMobile());
	parameters.put("type", "Template");
	parameters.put("callbackData:", "some_callback_data");
	parameters.put("template", templete);

	return parameters.toString();
}


public String forSecurityDeposit(BookingDetails bookingDetails) throws Exception {
		
		String customerName = bookingDetails.getCustomerName();
        String[] firstName = customerName.split(" ");
  
        System.out.println(firstName[0]+" Name");

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		String frmDate = formatter.format(bookingDetails.getFromDate());
		String totalAmount = String.valueOf(bookingDetails.getTotalAmount());
		String bookingAmount = String.valueOf(bookingDetails.getReceivedAmount());
		String balenceAmount = String.valueOf(bookingDetails.getBalenceAmount());
		String securityDeposit = String.valueOf(bookingDetails.getSecurityDeposit());
		String quantity = String.valueOf(bookingDetails.getVehicleQuantity());
		
		JSONObject parameters = new JSONObject();
		JSONObject templete = new JSONObject();
		JSONObject zero = new JSONObject();

		String[] headerValues = { "Alert" };
		String[] zeroValue = { "12344" };
	
		String[] bodyValues = { securityDeposit };

		templete.put("name", "security_deposit");
		templete.put("languageCode", "en");
		templete.put("headerValues", headerValues);
		templete.put("fileName", "dumy.pdf");
		templete.put("bodyValues", bodyValues);
		templete.put("buttonValues", zero);

		zero.put("0", zeroValue);

//		parameters.put("countryCode", "+91");
		parameters.put("countryCode", bookingDetails.getCountryCode());
		parameters.put("phoneNumber", bookingDetails.getCustomerMobile());
		parameters.put("type", "Template");
		parameters.put("callbackData:", "some_callback_data");
		parameters.put("template", templete);

		return parameters.toString();
	}

public String forSchedular(BookingDetails bookingDetails) throws Exception {
	
	JSONObject parameters = new JSONObject();
	JSONObject templete = new JSONObject();
	JSONObject zero = new JSONObject();

	String[] headerValues = { "Alert" };
	String[] zeroValue = { "12344" };

	String[] bodyValues = { bookingDetails.getCustomerName() };

	templete.put("name", "rmb_followup_new");
	templete.put("languageCode", "en");
	templete.put("headerValues", headerValues);
	templete.put("fileName", "dumy.pdf");
	templete.put("bodyValues", bodyValues);
	templete.put("buttonValues", zero);

	zero.put("0", zeroValue);

//	parameters.put("countryCode", "+91");
	parameters.put("countryCode", bookingDetails.getCountryCode());
	parameters.put("phoneNumber", bookingDetails.getCustomerMobile());
	parameters.put("type", "Template");
	parameters.put("callbackData:", "some_callback_data");
	parameters.put("template", templete);

	return parameters.toString();
}

////////////////////////// For Hydrabad/////////////////////

///////////////////////////////////////////////////////////





public String getJsonString() throws JSONException {
    // Create the main JSON object
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("countryCode", "+91");
    jsonObject.put("phoneNumber", "8800689752");
    jsonObject.put("callbackData", "some text here");
    jsonObject.put("type", "Template");

    // Create the "template" object
    JSONObject template = new JSONObject();
    template.put("name", "myraanpromo2");
    template.put("languageCode", "en");

    // Add "bodyValues" array
    JSONArray bodyValues = new JSONArray();
    bodyValues.put("test1");
    bodyValues.put("test2");
    bodyValues.put("test3");
    template.put("bodyValues", bodyValues);

    // Add "carouselCards" array
    JSONArray carouselCards = new JSONArray();

    // Add carousel card objects
    String[] imageUrls = {
        "https://interaktprodmediastorage.blob.core.windows.net/mediaprodstoragecontainer/7319ba4d-3517-4ace-bf06-1ed83448311d/message_template_media/KjNg6tbnhJ86/Casino.png?se=2030-02-20T08%3A31%3A39Z&sp=rt&sv=2019-12-12&sr=b&sig=FNtVaCGuaGV3FU66hKUYDtp69fj/8T0zupKuIsABl%2B4%3D",
        "https://interaktprodmediastorage.blob.core.windows.net/mediaprodstoragecontainer/7319ba4d-3517-4ace-bf06-1ed83448311d/message_template_media/MxKjP1lkF2xG/Scuba%2002.png?se=2030-02-20T08%3A32%3A13Z&sp=rt&sv=2019-12-12&sr=b&sig=kh1d5ej4Q7K5%2BnWemyax690514r8a8as6imU%2BeFOYyU%3D",
        "https://interaktprodmediastorage.blob.core.windows.net/mediaprodstoragecontainer/7319ba4d-3517-4ace-bf06-1ed83448311d/message_template_media/GP0ixuXoUG5L/Lux%20Dinner%20Cruise.png?se=2030-02-20T08%3A32%3A44Z&sp=rt&sv=2019-12-12&sr=b&sig=8l9xxg6rZ2hSY9zQo5WplEbX5YBb2ZCUgLsgxMaFRBk%3D",
        "https://interaktprodmediastorage.blob.core.windows.net/mediaprodstoragecontainer/7319ba4d-3517-4ace-bf06-1ed83448311d/message_template_media/ZaT0lR79iqpp/bungee.png?se=2030-02-20T08%3A35%3A13Z&sp=rt&sv=2019-12-12&sr=b&sig=kzm3v9BbByEpk8WODOQ8H%2BEiaAGLRl5ANCDuQhblPEY%3D",
        "https://interaktprodmediastorage.blob.core.windows.net/mediaprodstoragecontainer/7319ba4d-3517-4ace-bf06-1ed83448311d/message_template_media/DhB6LeWbSzy9/Watersport.png?se=2030-02-20T08%3A35%3A33Z&sp=rt&sv=2019-12-12&sr=b&sig=BI6E4XBX3xxy5fAHYwXUU3v82bswqsp4FIyafLueSDI%3D",
        "https://interaktprodmediastorage.blob.core.windows.net/mediaprodstoragecontainer/7319ba4d-3517-4ace-bf06-1ed83448311d/message_template_media/srG9JKMQ6zKP/Dudhsagar.png?se=2030-02-20T08%3A35%3A54Z&sp=rt&sv=2019-12-12&sr=b&sig=prL1LWsIKaSAxHoBYhm22W0xT/Gjpkp9Esr470H26LI%3D",
        "https://interaktprodmediastorage.blob.core.windows.net/mediaprodstoragecontainer/7319ba4d-3517-4ace-bf06-1ed83448311d/message_template_media/JLJY51RXhYo2/Private%20Yetch.png?se=2030-02-20T08%3A36%3A08Z&sp=rt&sv=2019-12-12&sr=b&sig=dCd5yhtYZF3IskIpNkh0uF%2BRpPhWBFqHGEwuhpGhSaM%3D",
        "https://interaktprodmediastorage.blob.core.windows.net/mediaprodstoragecontainer/7319ba4d-3517-4ace-bf06-1ed83448311d/message_template_media/JiQLTeWa7FwJ/Adventure%20Boat%20Party.jpeg?se=2030-02-22T10%3A05%3A08Z&sp=rt&sv=2019-12-12&sr=b&sig=K0n7wP/X32AkMbBp6Onob1pEWk7qrBqHvhiZ4foGx6Y%3D",
        "https://interaktprodmediastorage.blob.core.windows.net/mediaprodstoragecontainer/7319ba4d-3517-4ace-bf06-1ed83448311d/message_template_media/KxNr6MjHSu7C/Dolphin%20Trip.jpeg?se=2030-02-22T10%3A05%3A32Z&sp=rt&sv=2019-12-12&sr=b&sig=rs8ynTUoiPbvtohKfdH5Kra1IE8Fng1HRQyvF2Z3/zU%3D"
    };

    for (String url : imageUrls) {
        JSONObject card = new JSONObject();
        JSONArray headerValues = new JSONArray();
        headerValues.put(url);
        card.put("headerValues", headerValues);
        card.put("buttonValues", new JSONObject()); // Empty object
        card.put("bodyValues", new JSONArray());   // Empty array
        carouselCards.put(card);
    }

    template.put("carouselCards", carouselCards);
    jsonObject.put("template", template);

    // Convert the JSON object to a string
    return jsonObject.toString(4); 
}







public String forAdditonalServices(BookingDetails bookingDetails) throws Exception {
	
	String customerName = bookingDetails.getCustomerName();
    String[] firstName = customerName.split(" ");

//    System.out.println(firstName[0]+" Name");

	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	String vartwo = "";
	String varthree = "";
	if(bookingDetails.getVehicleType().equals("CAR")) {
//		vehicleType = "EZEE CAR RENT";
		vartwo = "MYRAAN RENTALS & ADVENTURES GOA!";
		varthree = "Dinner Cruises, Water Sports & Adventure Trips in Goa!";
		
	}else if(bookingDetails.getVehicleType().equals("BIKE")) {
//		vehicleType = "RENT MY BIKE";
		vartwo = "MYRAAN RENTALS & ADVENTURES GOA! ";
		varthree = "Dinner Cruises, Water Sports & Adventure Trips in Goa!";
		
	}else {
		vartwo = "AQUA ADVENTURE GOA!";
		varthree = "Self-Drive Car or Bike Rental Services in Goa!";
	}
	
	
	JSONObject parameters = new JSONObject();
	JSONObject templete = new JSONObject();
	JSONObject zero = new JSONObject();
	
	// Creating carousel cards array
//    JSONArray carouselCards = new JSONArray();

    // First carousel card
    JSONObject card1 = new JSONObject();
    JSONArray headerValues1 = new JSONArray();
    headerValues1.put("media_url_here"); // Optional media URL
    JSONArray bodyValues1 = new JSONArray();
    bodyValues1.put(bookingDetails.getCustomerName());
    card1.put("headerValues", headerValues1);
    card1.put("bodyValues", bodyValues1);

    // Second carousel card
    JSONObject card2 = new JSONObject();
    JSONArray headerValues2 = new JSONArray();
    headerValues2.put(""); // Optional media URL
    JSONArray bodyValues2 = new JSONArray();
    bodyValues2.put(bookingDetails.getCustomerName());
    card2.put("headerValues", headerValues2);
    card2.put("bodyValues", bodyValues2);

    // Adding cards to carouselCards array
//    carouselCards.put(card1);
//    carouselCards.put(card2);

    // Adding carousel cards to template



	String[] headerValues = { "Alert" };
	String[] zeroValue = { "12344" };

	String[] bodyValues = { bookingDetails.getCustomerName() ,vartwo, varthree };

	templete.put("name", "myraanpromo2");
//	templete.put("name", "additonal_services");
	templete.put("languageCode", "en");
	templete.put("headerValues", headerValues);
	templete.put("fileName", "dumy.pdf");
	templete.put("bodyValues", bodyValues);
//	templete.put("carouselCards", carouselCards);
	templete.put("buttonValues", zero);
	
	
	

	zero.put("0", zeroValue);

//	parameters.put("countryCode", "+91");
	parameters.put("countryCode", bookingDetails.getCountryCode());
	parameters.put("phoneNumber", bookingDetails.getCustomerMobile());
	parameters.put("type", "Template");
	parameters.put("callbackData:", "some_callback_data");
	parameters.put("template", templete);

	return parameters.toString();
}



public String forAdditionalServices(BookingDetails bookingDetails) throws Exception {
    
    String vartwo = "";
    String varthree = "";
    if (bookingDetails.getVehicleType().equals("CAR")) {
        vartwo = "MYRAAN RENTALS & ADVENTURES GOA!";
        varthree = "Dinner Cruises, Water Sports & Adventure Trips in Goa!";
    } else if (bookingDetails.getVehicleType().equals("BIKE")) {
        vartwo = "MYRAAN RENTALS & ADVENTURES GOA!";
        varthree = "Dinner Cruises, Water Sports & Adventure Trips in Goa!";
    } else {
        vartwo = "AQUA ADVENTURE GOA!";
        varthree = "Self-Drive Car or Bike Rental Services in Goa!";
    }

    // Creating template object
    JSONObject template = new JSONObject();
    template.put("name", "template_name_here");
    template.put("languageCode", "en");

    // Body values array
    JSONArray bodyValues = new JSONArray();
    bodyValues.put(bookingDetails.getCustomerName());
    bodyValues.put(vartwo);
    bodyValues.put(varthree);
    template.put("bodyValues", bodyValues);

    // Creating carousel cards array
    JSONArray carouselCards = new JSONArray();

    // First carousel card
    JSONObject card1 = new JSONObject();
    JSONArray headerValues1 = new JSONArray();
    headerValues1.put("media_url_here"); // Optional media URL
    JSONArray bodyValues1 = new JSONArray();
    bodyValues1.put(bookingDetails.getCustomerName());
    card1.put("headerValues", headerValues1);
    card1.put("bodyValues", bodyValues1);

    // Second carousel card
    JSONObject card2 = new JSONObject();
    JSONArray headerValues2 = new JSONArray();
    headerValues2.put("media_url_here"); // Optional media URL
    JSONArray bodyValues2 = new JSONArray();
    bodyValues2.put(bookingDetails.getCustomerName());
    card2.put("headerValues", headerValues2);
    card2.put("bodyValues", bodyValues2);

    // Adding cards to carouselCards array
    carouselCards.put(card1);
    carouselCards.put(card2);

    // Adding carousel cards to template
    template.put("carouselCards", carouselCards);

    // Creating final parameters object
    JSONObject parameters = new JSONObject();
    parameters.put("countryCode", bookingDetails.getCountryCode());
    parameters.put("phoneNumber", bookingDetails.getCustomerMobile());
    parameters.put("campaignId", ""); // Optional
    parameters.put("callbackData", "some text here");
    parameters.put("type", "Template");
    parameters.put("template", template);

    return parameters.toString(); // Return JSON as a string
}




//public String forAdditonalServices(BookingDetails bookingDetails) throws Exception {
//
//    String vartwo = "";
//    String varthree = "";
//
//    if (bookingDetails.getVehicleType().equals("CAR")) {
//        vartwo = "MYRAAN RENTALS & ADVENTURES GOA!";
//        varthree = "Dinner Cruises, Water Sports & Adventure Trips in Goa!";
//    } else if (bookingDetails.getVehicleType().equals("BIKE")) {
//        vartwo = "MYRAAN RENTALS & ADVENTURES GOA!";
//        varthree = "Dinner Cruises, Water Sports & Adventure Trips in Goa!";
//    } else {
//        vartwo = "AQUA ADVENTURE GOA!";
//        varthree = "Self-Drive Car or Bike Rental Services in Goa!";
//    }
//
//    // Create JSON Objects
//    JSONObject parameters = new JSONObject();
//    JSONObject template = new JSONObject();
//    JSONObject buttonValues = new JSONObject();
//    JSONArray carouselCardsArray = new JSONArray();
//    
//    // Header and body values
//    JSONArray headerValues = new JSONArray().put("Alert");
//    JSONArray zeroValue = new JSONArray().put("12344");
//    JSONArray bodyValues = new JSONArray()
//            .put(bookingDetails.getCustomerName())
//            .put(vartwo)
//            .put(varthree);
//
//    // Create carousel cards (expected format)
//    JSONObject carouselCard1 = new JSONObject();
//    carouselCard1.put("headerValues", new JSONArray()); // Empty as it's optional
//    carouselCard1.put("bodyValues", new JSONArray().put(bookingDetails.getCustomerName()).put(vartwo).put(varthree));
//
//    JSONObject carouselCard2 = new JSONObject();
//    carouselCard2.put("headerValues", new JSONArray()); // Empty as it's optional
//    carouselCard2.put("bodyValues", new JSONArray()
//            .put(vartwo)
//            .put(varthree));
//
//    // Add to carouselCards array
//    carouselCardsArray.put(carouselCard1);
//    carouselCardsArray.put(carouselCard2);
//
//    // Populate template object
//    template.put("name", "myraanpromo2");
//    template.put("languageCode", "en");
//    template.put("bodyValues", bodyValues);
//    template.put("carouselCards", carouselCardsArray);
//    template.put("headerValues", headerValues);
//    template.put("fileName", "dumy.pdf");
//    template.put("buttonValues", buttonValues);
//
//    // Populate button values
//    buttonValues.put("0", zeroValue);
//
//    // Populate main parameters
//    parameters.put("countryCode", bookingDetails.getCountryCode());
//    parameters.put("phoneNumber", bookingDetails.getCustomerMobile());
//    parameters.put("type", "Template");
//    parameters.put("callbackData", "some_callback_data");
//    parameters.put("template", template);
//
//    return parameters.toString();
//}



public String enquiryForWaterSports(BookingDetails bookingDetails) throws Exception {
	
	String customerName = bookingDetails.getCustomerName();
    String[] firstName = customerName.split(" ");

	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	String frmDate = formatter.format(bookingDetails.getFromDate());
	String pickupTime = String.valueOf(bookingDetails.getPickupTime());
	String toDate = formatter.format(bookingDetails.getToDate());
	String dropTime = String.valueOf(bookingDetails.getDeliveryTime());
	
	String totalAmount = String.valueOf(bookingDetails.getTotalAmount());
	String quantity = String.valueOf(bookingDetails.getVehicleQuantity());
	
	String perdayRent = String.valueOf(bookingDetails.getBookingPrice());
	
	
	JSONObject parameters = new JSONObject();
	JSONObject templete = new JSONObject();
	JSONObject zero = new JSONObject();

	String[] headerValues = { "Alert" };
	String[] zeroValue = { "12344" };
	
	String[] bodyValues = { firstName[0], bookingDetails.getCreatedbyName(), bookingDetails.getVehicleName(),
			bookingDetails.getVehicleDetailsType(), quantity, frmDate, pickupTime+":00 Hrs.",toDate, dropTime+":00 Hrs.", bookingDetails.getAreaFrom(),
			bookingDetails.getAreaTo(), perdayRent, totalAmount, "divein.co.in" };

	templete.put("name", "diveinenq");
	templete.put("languageCode", "en");
	templete.put("headerValues", headerValues);
	templete.put("fileName", "dumy.pdf");
	templete.put("bodyValues", bodyValues);
	templete.put("buttonValues", zero);

	zero.put("0", zeroValue);

	parameters.put("countryCode", bookingDetails.getCountryCode());
	parameters.put("phoneNumber", bookingDetails.getCustomerMobile());
	parameters.put("type", "Template");
	parameters.put("callbackData:", "some_callback_data");
	parameters.put("template", templete);

	return parameters.toString();
}


public String conformationForWaterSports(BookingDetails bookingDetails) throws Exception {
	
	String customerName = bookingDetails.getCustomerName();
    String[] firstName = customerName.split(" ");

	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	String frmDate = formatter.format(bookingDetails.getFromDate());
	String pickupTime = String.valueOf(bookingDetails.getPickupTime());
	String toDate = formatter.format(bookingDetails.getToDate());
	String dropTime = String.valueOf(bookingDetails.getDeliveryTime());
	
	String totalAmount = String.valueOf(bookingDetails.getTotalAmount());
	String quantity = String.valueOf(bookingDetails.getVehicleQuantity());
	
	String perdayRent = String.valueOf(bookingDetails.getBookingPrice());
	
	String bookingAmount = String.valueOf(bookingDetails.getReceivedAmount());
	
	String balanceAmount = String.valueOf(bookingDetails.getBalenceAmount());
	
	
	
	JSONObject parameters = new JSONObject();
	JSONObject templete = new JSONObject();
	JSONObject zero = new JSONObject();

	String[] headerValues = { "Alert" };
	String[] zeroValue = { "12344" };
	
	String[] bodyValues = { firstName[0], bookingDetails.getCustomerMobile(), bookingDetails.getVehicleName(),
			bookingDetails.getVehicleDetailsType(), quantity, bookingDetails.getCustomerName(),bookingDetails.getCustomerMobile(),frmDate, pickupTime, toDate,
			dropTime,bookingDetails.getAreaFrom(), bookingDetails.getAreaTo(), totalAmount, bookingAmount,balanceAmount};

	templete.put("name", "diveinconf");
	templete.put("languageCode", "en");
	templete.put("headerValues", headerValues);
	templete.put("fileName", "dumy.pdf");
	templete.put("bodyValues", bodyValues);
	templete.put("buttonValues", zero);

	zero.put("0", zeroValue);

	parameters.put("countryCode", bookingDetails.getCountryCode());
	parameters.put("phoneNumber", bookingDetails.getCustomerMobile());
	parameters.put("type", "Template");
	parameters.put("callbackData:", "some_callback_data");
	parameters.put("template", templete);

	return parameters.toString();
}


public String unauthorizedLogin(Users users, String clientIp) throws Exception {

//	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	JSONObject parameters = new JSONObject();
	JSONObject templete = new JSONObject();
	JSONObject zero = new JSONObject();

	String[] headerValues = { "Alert" };
	String[] zeroValue = { "12344" };
	
	String[] bodyValues = { users.getFullName(), users.getUserId(), clientIp };

	templete.put("name", "agent");
	templete.put("languageCode", "en");
	templete.put("headerValues", headerValues);
	templete.put("fileName", "dumy.pdf");
	templete.put("bodyValues", bodyValues);
	templete.put("buttonValues", zero);

	zero.put("0", zeroValue);

	parameters.put("countryCode", "+91");
//	parameters.put("phoneNumber","8800689752");
	parameters.put("phoneNumber","7845273233");
	parameters.put("type", "Template");
	parameters.put("callbackData:", "some_callback_data");
	parameters.put("template", templete);

	return parameters.toString();
}

}
