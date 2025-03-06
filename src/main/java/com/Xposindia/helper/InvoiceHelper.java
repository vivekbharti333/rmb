package com.Xposindia.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Xposindia.entities.BookingDetails;
import com.Xposindia.object.request.VehicleRequestObject;
import com.amazonaws.util.json.JSONObject;
import com.google.api.client.util.DateTime;
//import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.RequestBody;



import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.record.SupBookRecord;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.joda.time.Days;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.Xposindia.constant.Constant;
import com.Xposindia.expections.BizException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.itextpdf.html2pdf.HtmlConverter;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class InvoiceHelper {
	
	
	@Autowired
	private UserHelper userHelper;
	
	@Autowired
	private BookingHelper bookingHelper;
	
	public String htmlForInvoice(BookingDetails bookingDetails) {
		
		long noOfDays = 0;
		long pickupTime = 0;
		long deliveryTime = 0;
		
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String currentDate = simpleDateFormat.format(new Date());
		
		String fromDate = simpleDateFormat.format(bookingDetails.getFromDate());
		String toDate = simpleDateFormat.format(bookingDetails.getToDate());
		
		long diff = bookingDetails.getToDate().getTime() - bookingDetails.getFromDate().getTime();
		noOfDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		
		if(bookingDetails.getPickupTime() < 6) {
			pickupTime = 1;
		}else {
			pickupTime = 0;
		}
		
		if(bookingDetails.getDeliveryTime() > 9) {
			deliveryTime = 1;
		}else {
			deliveryTime = 0;
		}
		
		noOfDays = noOfDays + pickupTime + deliveryTime;
		long total = (long) ((bookingDetails.getBookingPrice() * noOfDays)*bookingDetails.getVehicleQuantity());
		
		long totalTotal = bookingDetails.getDeliveryCharges()+total;
		
		long securityDeposit = (long)bookingDetails.getSecurityDeposit() * bookingDetails.getVehicleQuantity();
//		if(bookingDetails.getVehicleType().equals("CAR")) {
//			securityDeposit = 3000;
//		}else {
//			securityDeposit = 1000;
//		}
		
		long deliveryCharge = bookingDetails.getDeliveryCharges() * bookingDetails.getVehicleQuantity();
		long balenceWithSecurity = bookingDetails.getBalenceAmount()+securityDeposit;
		long balenceAmount = bookingDetails.getBalenceAmount();
		
		// invoice no
		List<BookingDetails> hi= bookingHelper.getBookingCount();
		Date today = new Date(); 
		Calendar cal = Calendar.getInstance(); 
		int month = cal.get(Calendar.MONTH)+1; 
		int year = cal.get(Calendar.YEAR); 
		
		String invoiceNo = "MYRAAG"+year+"/"+month+"/"+hi.get(0);
		
		String HTML = "";
		
		if(bookingDetails.getVehicleType().equalsIgnoreCase("WATERSPORTS")) {
			
			HTML = "\n"
					+ "<html>\n"
					+ "<head>\n"
					+ "<style>\n"
					+ "table {\n"
					+ "  font-family: arial, sans-serif;\n"
					+ "  border-collapse: collapse;\n"
					+ "  width: 100%;\n"
					+ "}\n"
					+ "\n"
					+ "td, th {\n"
					+ "  border: 1px solid #dddddd;\n"
					+ "  text-align: left;\n"
					+ "  padding: 8px;\n"
					+ "}\n"
					+ "\n"
					+ "tr:nth-child(even) {\n"
					+ "  background-color: #dddddd;\n"
					+ "}\n"
					+ "\n"
					+ ".column {\n"
					+ "  float: left;\n"
					+ "  width: 50%;\n"
					+ "  padding: 10px;\n"
					+ "  text-align: justify;\n"
					+ "  text-justify: inter-word;\n"
					+ "  \n"
					+ "}\n"
					+ "\n"
					+ "input {border:0;outline:0;}\n"
					+ "input:focus {outline:none!important;}\n"
					+ "\n"
					+ "</style>\n"
					+ "</head>\n"
					+ "<body>\n"
					+ "<div align='center'>\n"
					+ "<img src=\"D:\\Letterhead_T.jpeg\" alt=\"Myraan Rentals And Adventures Goa Pvt. Ltd.\" style=\"width:100%;height:80px;\" align = 'left'>\n"
//					+ "<img src=\"D:\\Letterhead_T.jpeg\" alt=\"Travolo Goa\" style=\"width:100%;height:80px;\" align = 'left'>\n"
					+ "</div>		\n"
					+ "<div align = 'right'>\n"
					+ "<h1 align= 'left' style=\"margin:10px\">PAYMENT RECEIPT</h1>\n"
					+ "<label for=\"serialNo\" align = \"right\">#</label>\n"
					+ "<input type=\"text\" id=\"invoiceNo\" name=\"invoiceNo\" value=\""+invoiceNo+"\"><br>\n"
					+ "</div>\n"
					+ "<br>\n"
					+ "\n"
					+ "  <div class=\"column\" style=\"\">\n"
//					+ "<b>Myraan Rentals And Adventures Goa Pvt. Ltd.</b><br>\n"
					+ "<b>Travolo Goa</b><br>\n"
					+ "123/1, Marinha Dourada Rd, Tamudki Vado,<br>\n"
					+ "Arpora, Baga, Goa - 403518<br>\n"
//					+ "Phone: +91 93218 84170<br>\n"
					+ "Phone: +91 80100 93398<br>\n"
					+ "\n"
					+ "  </div>\n"
					+ "<div align = 'right'>\n"
					+ "<label for=\"serialNo\" align = \"right\">Date:</label>\n"
					+ "<input type=\"text\" id=\"invoiceNo\" name=\"invoiceNo\" value = \""+currentDate+"\"><br><br>\n"
					+ "<label for=\"serialNo\" align = \"right\">Payment Terms:</label>\n"
					+ "<input type=\"text\" id=\"invoiceNo\" name=\"invoiceNo\" value = \"PIA\"><br><br>\n"
//					+ "<label for=\"serialNo\" align = \"right\">Balance Due:</label>\n"
//					+ "<input type=\"text\" id=\"invoiceNo\" name=\"invoiceNo\" value = \""+bookingDetails.getBalenceAmount()+"\"><br><br>\n"
					+ "<label for=\"serialNo\" align = \"right\"><b>Balance Payment at site:</b></label>\n"
//					+ "<input type=\"text\" id=\"invoiceNo\" name=\"invoiceNo\" value = \" "+balenceAmount+".00\"><br><br>\n"
					+ "<input type=\"text\" id=\"invoiceNo\" name=\"invoiceNo\" value = \" "+balenceAmount+".00\"><br><br>\n"
					+ "</div>\n"
					+ "\n"
					+ "<div style=\"margin:10px\">\n"
					+ "<label for=\"Bill TO\" align = \"left\">Bill To</label><br>\n"
					+ "\n"
					+ "<p>"+bookingDetails.getCustomerName()+"</p>\n"
					+ "\n"
					+ "<p>"+bookingDetails.getCountryCode()+" "+bookingDetails.getCustomerMobile()+"</p>\n"
					+ "\n"
					+ "\n"
					+ "</div>\n"
					+ "<br>\n"
					+ "\n"
					+ "<table>\n"
					+ "  <tr>\n"
					+ "    <th>Description</th>\n"
					+ "    <th>NOP</th>\n"
					+ "    <th>PMC</th>\n"
					+ "	<th>Total</th>\n"
					+ "  </tr>\n"
					+ "  <tr>\n"
					+ "    <td> "+bookingDetails.getVehicleName()+" | "+bookingDetails.getVehicleDetailsType()+" | "+bookingDetails.getVehicleQuantity()+" |"+fromDate+" & "+bookingDetails.getPickupTime()+":00 Hours | "+toDate+" & "+bookingDetails.getDeliveryTime()+":00 Hours | "+bookingDetails.getAreaFrom()+" & "+bookingDetails.getAreaTo()+" </td>\n"
					+ "    <td> "+bookingDetails.getVehicleQuantity()+" </td>\n"
					+ "    <td> "+bookingDetails.getBookingPrice()+"</td>\n"
					+ "	<td> "+total+".00</td>\n"
					+ "  </tr>\n"
					+ "</table>\n"
					+ "<br>\n"
					+ "\n"
					+ "\n"
					+ "  <div class=\"column\" style=\"\">\n"
					+ "  <label for=\"serialNo\" align = \"right\"><b>Notes</b></label>\n"
					+ "	<p><b>NOP</b> : Number of Person</p>\n"
					+ "	<p><b>PMC</b> : Per Member Cost</p>\n"
					+ "  </div>\n"
					+ "\n"
					+ "\n"
					+ "<div align = 'right'>\n"
					+ "<label for=\"serialNo\" align = \"right\">Sub-total:<label>\n"
					+ "<input type=\"text\" id=\"invoiceNo\" name=\"invoiceNo\" value = \" "+total+"\"><br><br>\n"
					+ "<label for=\"serialNo\" align = \"right\">Discount:</label>\n"
					+ "<input type=\"text\" id=\"invoiceNo\" name=\"invoiceNo\" value = \" 0.00\"><br><br>\n"
					+ "<label for=\"serialNo\" align = \"right\">Tax:</label>\n"
					+ "<input type=\"text\" id=\"invoiceNo\" name=\"invoiceNo\" value = \"0 %\"><br><br>\n"
//					+ "<label for=\"serialNo\" align = \"right\">Delivery:</label>\n"
//					+ "<input type=\"text\" id=\"invoiceNo\" name=\"invoiceNo\" value = \" "+deliveryCharge+"\"><br><br>\n"
					+ "<label for=\"serialNo\" align = \"right\">Total:</label>\n"
					+ "<input type=\"text\" id=\"invoiceNo\" name=\"invoiceNo\" value = \" "+bookingDetails.getTotalAmount()+"\"><br><br>\n"
					+ "<label for=\"serialNo\" align = \"right\">Amount Paid:</label>\n"
					+ "<input type=\"text\" id=\"invoiceNo\" name=\"invoiceNo\" value = \" "+bookingDetails.getReceivedAmount()+"\"><br><br>\n"
//					+ "<label for=\"serialNo\" align = \"right\">Security Deposit:	</label>\n"
//					+ "<input type=\"text\" id=\"invoiceNo\" name=\"invoiceNo\" value = \" "+securityDeposit+"\"><br><br>\n"
					+ "</div>\n"
					+ "<div class=\"row\" style=\"text-align: justify; text-justify: inter-word;\">\n"
					+"\n"
					+ "<h4>TERMS & CONDITIONS:</h4>\n"
					+ "<br>\n"
//					+ "https://myraanadventures.com/refund-and-cancellation<br>\n"
					+ "https://travolo.in/terms-and-conditions.html<br>\n"
					+ "</body>\n"
					+ "</html>";
		}else {
			
			 HTML = "\n"
					+ "<html>\n"
					+ "<head>\n"
					+ "<style>\n"
					+ "table {\n"
					+ "  font-family: arial, sans-serif;\n"
					+ "  border-collapse: collapse;\n"
					+ "  width: 100%;\n"
					+ "}\n"
					+ "\n"
					+ "td, th {\n"
					+ "  border: 1px solid #dddddd;\n"
					+ "  text-align: left;\n"
					+ "  padding: 8px;\n"
					+ "}\n"
					+ "\n"
					+ "tr:nth-child(even) {\n"
					+ "  background-color: #dddddd;\n"
					+ "}\n"
					+ "\n"
					+ ".column {\n"
					+ "  float: left;\n"
					+ "  width: 50%;\n"
					+ "  padding: 10px;\n"
					+ "  text-align: justify;\n"
					+ "  text-justify: inter-word;\n"
					+ "  \n"
					+ "}\n"
					+ "\n"
					+ "input {border:0;outline:0;}\n"
					+ "input:focus {outline:none!important;}\n"
					+ "\n"
					+ "</style>\n"
					+ "</head>\n"
					+ "<body>\n"
					+ "<div align='center'>\n"
					+ "<img src=\"D:\\Letterhead_T.jpeg\" alt=\"Myraan Rentals And Adventures\" style=\"width:100%;height:80px;\" align = 'left'>\n"
					+ "</div>		\n"
					+ "<div align = 'right'>\n"
					+ "<h1 align= 'left' style=\"margin:10px\">PAYMENT RECEIPT</h1>\n"
					+ "<label for=\"serialNo\" align = \"right\">#</label>\n"
					+ "<input type=\"text\" id=\"invoiceNo\" name=\"invoiceNo\" value=\""+invoiceNo+"\"><br>\n"
					+ "</div>\n"
					+ "<br>\n"
					+ "\n"
					+ "  <div class=\"column\" style=\"\">\n"
					+ "    <b>Myraan Rentals And Adventures Goa Pvt. Ltd.</b><br>\n"
					+ "123/1, Marinha Dourada Rd, Tamudki Vado,<br>\n"
					+ "Arpora, Baga, Goa - 403518<br>\n"
					+ "Phone: +91 95131 66378<br>\n"
					+ "\n"
					+ "  </div>\n"
					+ "<div align = 'right'>\n"
					+ "<label for=\"serialNo\" align = \"right\">Date:</label>\n"
					+ "<input type=\"text\" id=\"invoiceNo\" name=\"invoiceNo\" value = \""+currentDate+"\"><br><br>\n"
					+ "<label for=\"serialNo\" align = \"right\">Payment Terms:</label>\n"
					+ "<input type=\"text\" id=\"invoiceNo\" name=\"invoiceNo\" value = \"PIA\"><br><br>\n"
					+ "<label for=\"serialNo\" align = \"right\">Balance Due:</label>\n"
					+ "<input type=\"text\" id=\"invoiceNo\" name=\"invoiceNo\" value = \""+bookingDetails.getBalenceAmount()+"\"><br><br>\n"
					+ "<label for=\"serialNo\" align = \"right\"><b>Balance Due with Security Deposit:</b></label>\n"
					+ "<input type=\"text\" id=\"invoiceNo\" name=\"invoiceNo\" value = \" "+balenceWithSecurity+".00\"><br><br>\n"
					+ "</div>\n"
					+ "\n"
					+ "<div style=\"margin:10px\">\n"
					+ "<label for=\"Bill TO\" align = \"left\">Bill To</label><br>\n"
					+ "\n"
					+ "<p>"+bookingDetails.getCustomerName()+"</p>\n"
					+ "\n"
					+ "<p>"+bookingDetails.getCountryCode()+" "+bookingDetails.getCustomerMobile()+"</p>\n"
					+ "\n"
					+ "\n"
					+ "</div>\n"
					+ "<br>\n"
					+ "\n"
					+ "<table>\n"
					+ "  <tr>\n"
					+ "    <th>Description</th>\n"
					+ "    <th>NOD</th>\n"
					+ "    <th>PDT</th>\n"
					+ "	<th>Total</th>\n"
					+ "  </tr>\n"
					+ "  <tr>\n"
					+ "    <td> "+bookingDetails.getVehicleName()+" | "+bookingDetails.getVehicleDetailsType()+" | "+bookingDetails.getVehicleQuantity()+" |"+fromDate+" & "+bookingDetails.getPickupTime()+":00 Hours | "+toDate+" & "+bookingDetails.getDeliveryTime()+":00 Hours | "+bookingDetails.getAreaFrom()+" & "+bookingDetails.getAreaTo()+" </td>\n"
					+ "    <td> "+noOfDays+" </td>\n"
					+ "    <td> "+bookingDetails.getBookingPrice()+"</td>\n"
					+ "	<td> "+total+".00</td>\n"
					+ "  </tr>\n"
					+ "</table>\n"
					+ "<br>\n"
					+ "\n"
					+ "\n"
					+ "  <div class=\"column\" style=\"\">\n"
					+ "  <label for=\"serialNo\" align = \"right\"><b>Notes</b></label>\n"
					+ "	<p><b>NOD</b> : Number of days</p>\n"
					+ "	<p><b>PDT</b> : Per day rent</p>\n"
					+ "  </div>\n"
					+ "\n"
					+ "\n"
					+ "<div align = 'right'>\n"
					+ "<label for=\"serialNo\" align = \"right\">Sub-total:<label>\n"
					+ "<input type=\"text\" id=\"invoiceNo\" name=\"invoiceNo\" value = \" "+total+"\"><br><br>\n"
					+ "<label for=\"serialNo\" align = \"right\">Discount:</label>\n"
					+ "<input type=\"text\" id=\"invoiceNo\" name=\"invoiceNo\" value = \" 0.00\"><br><br>\n"
					+ "<label for=\"serialNo\" align = \"right\">Tax:</label>\n"
					+ "<input type=\"text\" id=\"invoiceNo\" name=\"invoiceNo\" value = \"0 %\"><br><br>\n"
					+ "<label for=\"serialNo\" align = \"right\">Delivery:</label>\n"
					+ "<input type=\"text\" id=\"invoiceNo\" name=\"invoiceNo\" value = \" "+deliveryCharge+"\"><br><br>\n"
					+ "<label for=\"serialNo\" align = \"right\">Total:</label>\n"
					+ "<input type=\"text\" id=\"invoiceNo\" name=\"invoiceNo\" value = \" "+bookingDetails.getTotalAmount()+"\"><br><br>\n"
					+ "<label for=\"serialNo\" align = \"right\">Amount Paid:</label>\n"
					+ "<input type=\"text\" id=\"invoiceNo\" name=\"invoiceNo\" value = \" "+bookingDetails.getReceivedAmount()+"\"><br><br>\n"
					+ "<label for=\"serialNo\" align = \"right\">Security Deposit:	</label>\n"
					+ "<input type=\"text\" id=\"invoiceNo\" name=\"invoiceNo\" value = \" "+securityDeposit+"\"><br><br>\n"
					+ "</div>\n"
					+ "<div class=\"row\" style=\"text-align: justify; text-justify: inter-word;\">\n"
					+"\n"
					+ "<h4>TERMS & CONDITIONS:</h4>\n"
					+ "<br>\n"
					+ "https://myraanrentals.com/refund-and-cancellation<br>\n"
					+ "</body>\n"
					+ "</html>";
			
		}
		
			
		return HTML;
	}
	
	public String receiptpdf(BookingDetails bookingDetails) throws FileNotFoundException, IOException {
		
		String receiptNumber = StringUtils.substring(RandomStringUtils.random(64, false, true), 0, 6);
		
		bookingDetails.setReceiptNumber(receiptNumber);
		
		String basePath = userHelper.getPathToUploadFile("RECIEPT");
		String path = basePath + File.separator + receiptNumber + ".pdf";
		HtmlConverter.convertToPdf(htmlForInvoice(bookingDetails), new FileOutputStream(path));
		return receiptNumber;
	}

	}
