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
	private long expireAt;
	private MetaInfo metaInfo;
	private List<PaymentDetail> paymentDetails;

	// Getters and setters

	public static class MetaInfo {
		private String udf1;
		private String udf2;
		private String udf3;
		private String udf4;
		private String udf5;
		
		

		@Override
		public String toString() {
			return "MetaInfo [udf1=" + udf1 + ", udf2=" + udf2 + ", udf3=" + udf3 + ", udf4=" + udf4 + ", udf5=" + udf5
					+ ", getUdf1()=" + getUdf1() + ", getUdf2()=" + getUdf2() + ", getUdf3()=" + getUdf3()
					+ ", getUdf4()=" + getUdf4() + ", getUdf5()=" + getUdf5() + ", getClass()=" + getClass()
					+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
		}

		// Getters and setters
		public String getUdf1() {
			return udf1;
		}

		public void setUdf1(String udf1) {
			this.udf1 = udf1;
		}

		public String getUdf2() {
			return udf2;
		}

		public void setUdf2(String udf2) {
			this.udf2 = udf2;
		}

		public String getUdf3() {
			return udf3;
		}

		public void setUdf3(String udf3) {
			this.udf3 = udf3;
		}

		public String getUdf4() {
			return udf4;
		}

		public void setUdf4(String udf4) {
			this.udf4 = udf4;
		}

		public String getUdf5() {
			return udf5;
		}

		public void setUdf5(String udf5) {
			this.udf5 = udf5;
		}

	}

	public static class PaymentDetail {
		private String paymentMode;
		private String transactionId;
		private long timestamp;
		private long amount;
		private String state;
		
		

		@Override
		public String toString() {
			return "PaymentDetail [paymentMode=" + paymentMode + ", transactionId=" + transactionId + ", timestamp="
					+ timestamp + ", amount=" + amount + ", state=" + state + ", getPaymentMode()=" + getPaymentMode()
					+ ", getTransactionId()=" + getTransactionId() + ", getTimestamp()=" + getTimestamp()
					+ ", getAmount()=" + getAmount() + ", getState()=" + getState() + ", getClass()=" + getClass()
					+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
		}

		// Getters and setters
		public String getPaymentMode() {
			return paymentMode;
		}

		public void setPaymentMode(String paymentMode) {
			this.paymentMode = paymentMode;
		}

		public String getTransactionId() {
			return transactionId;
		}

		public void setTransactionId(String transactionId) {
			this.transactionId = transactionId;
		}

		public long getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(long timestamp) {
			this.timestamp = timestamp;
		}

		public long getAmount() {
			return amount;
		}

		public void setAmount(long amount) {
			this.amount = amount;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public long getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(long expireAt) {
		this.expireAt = expireAt;
	}

	public MetaInfo getMetaInfo() {
		return metaInfo;
	}

	public void setMetaInfo(MetaInfo metaInfo) {
		this.metaInfo = metaInfo;
	}

	public List<PaymentDetail> getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(List<PaymentDetail> paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	@Override
	public String toString() {
		return "PhonePeStatusRequest [bookingId=" + bookingId + ", orderId=" + orderId + ", state=" + state
				+ ", amount=" + amount + ", expireAt=" + expireAt + ", metaInfo=" + metaInfo + ", paymentDetails="
				+ paymentDetails + ", getBookingId()=" + getBookingId() + ", getOrderId()=" + getOrderId()
				+ ", getState()=" + getState() + ", getAmount()=" + getAmount() + ", getExpireAt()=" + getExpireAt()
				+ ", getMetaInfo()=" + getMetaInfo() + ", getPaymentDetails()=" + getPaymentDetails() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	
	

}
