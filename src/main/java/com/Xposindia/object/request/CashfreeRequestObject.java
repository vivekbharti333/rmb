package com.Xposindia.object.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CashfreeRequestObject {

	private Long id;
	private String linkId;
	private String link_status;
	
	private String status;
	private String bookingId;
	
	private Date createdAt;
	private Date updatedAt;
	private String createdBy;
	private String superadminId;
	
	private String respMesg;
	private int respCode;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLinkId() {
		return linkId;
	}
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	public String getLink_status() {
		return link_status;
	}
	public void setLink_status(String link_status) {
		this.link_status = link_status;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getSuperadminId() {
		return superadminId;
	}
	public void setSuperadminId(String superadminId) {
		this.superadminId = superadminId;
	}
	public String getRespMesg() {
		return respMesg;
	}
	public void setRespMesg(String respMesg) {
		this.respMesg = respMesg;
	}
	public int getRespCode() {
		return respCode;
	}
	public void setRespCode(int respCode) {
		this.respCode = respCode;
	}

}
