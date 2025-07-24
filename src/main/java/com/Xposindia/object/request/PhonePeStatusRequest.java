package com.Xposindia.object.request;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhonePeStatusRequest {

	private String bookingId;
	private String orderId;
	private String state;
	private long amount;
	private long payableAmount;
	private long feeAmount;
	private long expireAt;
	private MetaInfo metaInfo;
	private List<PaymentDetail> paymentDetails;

	// MetaInfo inner class
	public static class MetaInfo {
		private String udf1;
		private String udf2;
		private String udf3;
		private String udf4;
		private String udf5;

		// Getters and setters
		public String getUdf1() { return udf1; }
		public void setUdf1(String udf1) { this.udf1 = udf1; }

		public String getUdf2() { return udf2; }
		public void setUdf2(String udf2) { this.udf2 = udf2; }

		public String getUdf3() { return udf3; }
		public void setUdf3(String udf3) { this.udf3 = udf3; }

		public String getUdf4() { return udf4; }
		public void setUdf4(String udf4) { this.udf4 = udf4; }

		public String getUdf5() { return udf5; }
		public void setUdf5(String udf5) { this.udf5 = udf5; }
	}

	// PaymentDetail inner class
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class PaymentDetail {
		private String transactionId;
		private String paymentMode;
		private long timestamp;
		private long amount;
		private long payableAmount;
		private long feeAmount;
		private String state;
		private Instrument instrument;
		private Rail rail;
		private List<SplitInstrument> splitInstruments;

		// Getters and setters
		public String getTransactionId() { return transactionId; }
		public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

		public String getPaymentMode() { return paymentMode; }
		public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }

		public long getTimestamp() { return timestamp; }
		public void setTimestamp(long timestamp) { this.timestamp = timestamp; }

		public long getAmount() { return amount; }
		public void setAmount(long amount) { this.amount = amount; }

		public long getPayableAmount() { return payableAmount; }
		public void setPayableAmount(long payableAmount) { this.payableAmount = payableAmount; }

		public long getFeeAmount() { return feeAmount; }
		public void setFeeAmount(long feeAmount) { this.feeAmount = feeAmount; }

		public String getState() { return state; }
		public void setState(String state) { this.state = state; }

		public Instrument getInstrument() { return instrument; }
		public void setInstrument(Instrument instrument) { this.instrument = instrument; }

		public Rail getRail() { return rail; }
		public void setRail(Rail rail) { this.rail = rail; }

		public List<SplitInstrument> getSplitInstruments() { return splitInstruments; }
		public void setSplitInstruments(List<SplitInstrument> splitInstruments) { this.splitInstruments = splitInstruments; }
	}

	// Instrument class
	public static class Instrument {
		private String type;
		private String ifsc;
		private String accountType;

		public String getType() { return type; }
		public void setType(String type) { this.type = type; }

		public String getIfsc() { return ifsc; }
		public void setIfsc(String ifsc) { this.ifsc = ifsc; }

		public String getAccountType() { return accountType; }
		public void setAccountType(String accountType) { this.accountType = accountType; }
	}

	// Rail class
	public static class Rail {
		private String type;
		private String utr;
		private String upiTransactionId;

		public String getType() { return type; }
		public void setType(String type) { this.type = type; }

		public String getUtr() { return utr; }
		public void setUtr(String utr) { this.utr = utr; }

		public String getUpiTransactionId() { return upiTransactionId; }
		public void setUpiTransactionId(String upiTransactionId) { this.upiTransactionId = upiTransactionId; }
	}

	// SplitInstrument class
	public static class SplitInstrument {
		private Instrument instrument;
		private Rail rail;
		private long amount;

		public Instrument getInstrument() { return instrument; }
		public void setInstrument(Instrument instrument) { this.instrument = instrument; }

		public Rail getRail() { return rail; }
		public void setRail(Rail rail) { this.rail = rail; }

		public long getAmount() { return amount; }
		public void setAmount(long amount) { this.amount = amount; }
	}

	// Getters and setters for outer class
	public String getBookingId() { return bookingId; }
	public void setBookingId(String bookingId) { this.bookingId = bookingId; }

	public String getOrderId() { return orderId; }
	public void setOrderId(String orderId) { this.orderId = orderId; }

	public String getState() { return state; }
	public void setState(String state) { this.state = state; }

	public long getAmount() { return amount; }
	public void setAmount(long amount) { this.amount = amount; }

	public long getPayableAmount() { return payableAmount; }
	public void setPayableAmount(long payableAmount) { this.payableAmount = payableAmount; }

	public long getFeeAmount() { return feeAmount; }
	public void setFeeAmount(long feeAmount) { this.feeAmount = feeAmount; }

	public long getExpireAt() { return expireAt; }
	public void setExpireAt(long expireAt) { this.expireAt = expireAt; }

	public MetaInfo getMetaInfo() { return metaInfo; }
	public void setMetaInfo(MetaInfo metaInfo) { this.metaInfo = metaInfo; }

	public List<PaymentDetail> getPaymentDetails() { return paymentDetails; }
	public void setPaymentDetails(List<PaymentDetail> paymentDetails) { this.paymentDetails = paymentDetails; }
}
