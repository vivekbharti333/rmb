package com.Xposindia.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "call_quality_details")
public class CallQualityDetails {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "agent_name")
	private String agentName;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "mobile_no")
	private String mobileNo;
	
	@Column(name = "one")
	private Long one;
	
	@Column(name = "two")
	private Long two;
	
	@Column(name = "three")
	private Long three;
	
	@Column(name = "four")
	private Long four;
	
	@Column(name = "five")
	private Long five;
	
	@Column(name = "six")
	private Long six;
	
	@Column(name = "seven")
	private Long seven;
	
	@Column(name = "eight")
	private Long eight;
	
	@Column(name = "nine")
	private Long nine;
	
	@Column(name = "ten")
	private Long ten;
	
	@Column(name = "eleven")
	private Long eleven;
	
	@Column(name = "twelve")
	private Long twelve;
	
	@Column(name ="thirteen")
	private Long thirteen;
	
	@Column(name = "fourteen")
	private Long fourteen;
	
	@Column(name = "fifteen")
	private Long fifteen;
	
	@Lob
	@Column(name= "feedback")
	private String feedback;
	
	@Column(name = "score")
	private Long score;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "update_at")
	private Date updatedAt;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getOne() {
		return one;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public void setOne(Long one) {
		this.one = one;
	}

	public Long getTwo() {
		return two;
	}

	public void setTwo(Long two) {
		this.two = two;
	}

	public Long getThree() {
		return three;
	}

	public void setThree(Long three) {
		this.three = three;
	}

	public Long getFour() {
		return four;
	}

	public void setFour(Long four) {
		this.four = four;
	}

	public Long getFive() {
		return five;
	}

	public void setFive(Long five) {
		this.five = five;
	}

	public Long getSix() {
		return six;
	}

	public void setSix(Long six) {
		this.six = six;
	}

	public Long getSeven() {
		return seven;
	}

	public void setSeven(Long seven) {
		this.seven = seven;
	}

	public Long getEight() {
		return eight;
	}

	public void setEight(Long eight) {
		this.eight = eight;
	}

	public Long getNine() {
		return nine;
	}

	public void setNine(Long nine) {
		this.nine = nine;
	}

	public Long getTen() {
		return ten;
	}

	public void setTen(Long ten) {
		this.ten = ten;
	}

	public Long getEleven() {
		return eleven;
	}

	public void setEleven(Long eleven) {
		this.eleven = eleven;
	}

	public Long getTwelve() {
		return twelve;
	}

	public void setTwelve(Long twelve) {
		this.twelve = twelve;
	}

	public Long getThirteen() {
		return thirteen;
	}

	public void setThirteen(Long thirteen) {
		this.thirteen = thirteen;
	}

	public Long getFourteen() {
		return fourteen;
	}

	public void setFourteen(Long fourteen) {
		this.fourteen = fourteen;
	}

	public Long getFifteen() {
		return fifteen;
	}

	public void setFifteen(Long fifteen) {
		this.fifteen = fifteen;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
}
