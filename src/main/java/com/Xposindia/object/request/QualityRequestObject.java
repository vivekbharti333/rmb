package com.Xposindia.object.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class QualityRequestObject {
	
	private Long Id;
	private String agentName;
	private String userId;
	private String mobileNo;
	private Long one;
	private Long two;
	private Long three;
	private Long four;
	private Long five;
	private Long six;
	private Long seven;
	private Long eight;
	private Long nine;
	private Long ten;
	private Long eleven;
	private Long twelve;
	private Long thirteen;
	private Long fourteen;
	private Long fifteen;
	private String feedback;
	private Long score;
	private double totalScore;
	private String createdBy;
	
	private int sum;
	private int count;
	private String requestFor;
	private String respMesg;
	private int respCode;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
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
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Long getOne() {
		return one;
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
	public double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getRequestFor() {
		return requestFor;
	}
	public void setRequestFor(String requestFor) {
		this.requestFor = requestFor;
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
