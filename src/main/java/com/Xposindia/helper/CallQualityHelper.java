package com.Xposindia.helper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;

import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.Xposindia.constant.Constant;
import com.Xposindia.dao.CallQualityDetailsDao;
import com.Xposindia.entities.BookingDetails;
import com.Xposindia.entities.CallQualityDetails;
import com.Xposindia.entities.Users;
import com.Xposindia.entities.VehicleBrand;
import com.Xposindia.expections.BizException;
import com.Xposindia.object.request.QualityRequestObject;
import com.Xposindia.object.request.UserRequestObject;
import com.Xposindia.object.request.VehicleRequestObject;
import com.amazonaws.util.json.JSONObject;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.RequestBody;

@Component
public class CallQualityHelper {
	
	@Autowired
	private CallQualityDetailsDao callQualityDetailsDao;

	public void validateCallQualityRequest(QualityRequestObject qualityRequestObject) 
			throws BizException
	{ 
		if(qualityRequestObject == null) {
			throw new BizException(Constant.BAD_REQUEST_CODE, "Bad Request Object Null"); 
		}
	}
	
//	@Transactional
//	public CallQualityDetails getCallQualityDetailsByObjWithZero(QualityRequestObject qualityRequest) 
//	{ 
//		CallQualityDetails callQualityDetails = new CallQualityDetails();
//		
//		callQualityDetails.setUserId(qualityRequest.getUserId());
//		callQualityDetails.setAgentName(qualityRequest.getAgentName());
//		callQualityDetails.setMobileNo(qualityRequest.getMobileNo());
//		callQualityDetails.setOne(0L);
//		callQualityDetails.setTwo(0L);
//		callQualityDetails.setThree(0L);
//		callQualityDetails.setFour(0L);
//		callQualityDetails.setFive(0L);
//		callQualityDetails.setSix(0L);
//		callQualityDetails.setSeven(0L);
//		callQualityDetails.setEight(0L);
//		callQualityDetails.setNine(0L);
//		callQualityDetails.setTen(0L);
//		callQualityDetails.setEleven(0L);
//		callQualityDetails.setTwelve(0L);
//		callQualityDetails.setThirteen(0L);
//		callQualityDetails.setFourteen(0L);
//		callQualityDetails.setFeedback(qualityRequest.getFeedback());
//		callQualityDetails.setCreatedBy(qualityRequest.getCreatedBy());
//		callQualityDetails.setCreatedAt(new Date());
//		callQualityDetails.setUpdatedAt(new Date());
//		return callQualityDetails;
//	}
	
	@Transactional
	public CallQualityDetails getCallQualityDetailsByObj(QualityRequestObject qualityRequest) 
	{ 
		CallQualityDetails callQualityDetails = new CallQualityDetails();
		
		callQualityDetails.setUserId(qualityRequest.getUserId());
		callQualityDetails.setAgentName(qualityRequest.getAgentName());
		callQualityDetails.setMobileNo(qualityRequest.getMobileNo());
		callQualityDetails.setOne(qualityRequest.getOne());
		callQualityDetails.setTwo(qualityRequest.getTwo());
		callQualityDetails.setThree(qualityRequest.getThree());
		callQualityDetails.setFour(qualityRequest.getFour());
		callQualityDetails.setFive(qualityRequest.getFive());
		callQualityDetails.setSix(qualityRequest.getSix());
		callQualityDetails.setSeven(qualityRequest.getSeven());
		callQualityDetails.setEight(qualityRequest.getEight());
		callQualityDetails.setNine(qualityRequest.getNine());
		callQualityDetails.setTen(qualityRequest.getTen());
		callQualityDetails.setEleven(qualityRequest.getEleven());
		callQualityDetails.setTwelve(qualityRequest.getTwelve());
		callQualityDetails.setThirteen(qualityRequest.getThirteen());
		callQualityDetails.setFourteen(qualityRequest.getFourteen());
		callQualityDetails.setFeedback(qualityRequest.getFeedback());
		callQualityDetails.setScore(qualityRequest.getScore());
		callQualityDetails.setCreatedBy(qualityRequest.getCreatedBy());
		callQualityDetails.setCreatedAt(new Date());
		callQualityDetails.setUpdatedAt(new Date());
		return callQualityDetails;
	}
	
	@Transactional
	public CallQualityDetails saveCallQualityDetails(CallQualityDetails callQualityDetails) 
	{ 
		callQualityDetailsDao.persist(callQualityDetails);
		return callQualityDetails;
	}

	@SuppressWarnings("unchecked")
	public List<CallQualityDetails> getCallQualityDetailsList(QualityRequestObject qualityRequest) {
		if(qualityRequest.getRequestFor().equalsIgnoreCase("SCORE")) {
			List<CallQualityDetails> results = callQualityDetailsDao.getEntityManager()
					.createQuery("SELECT UD FROM CallQualityDetails UD WHERE UD.userId =:userId ORDER BY UD.id DESC")
					.setParameter("userId", qualityRequest.getUserId())
					.getResultList();
			return results;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CallQualityDetails> getTotalScoreForCallQualityDetails(QualityRequestObject qualityRequest) {
//		Date date = new Date();
//		Date nextDay = Date.from(date.toInstant().plus(1, ChronoUnit.DAYS));
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

		c.set(Calendar.DAY_OF_MONTH, 1);
		Date monthFirstDay = c.getTime();

		c.set(Calendar.DATE, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date monthLastDay = c.getTime();
		
		if(qualityRequest.getRequestFor().equalsIgnoreCase("SCORE")) {
			List<CallQualityDetails> results = callQualityDetailsDao.getEntityManager()
					.createQuery("SELECT SUM(score) AS score from CallQualityDetails WHERE userId =:userId AND createdAt BETWEEN :currentDate AND :lastDate")
					.setParameter("userId", qualityRequest.getUserId())
					.setParameter("currentDate", monthFirstDay, TemporalType.DATE)
					.setParameter("lastDate", monthLastDay, TemporalType.DATE)
					.getResultList();
			return results;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CallQualityDetails> getTotalCountForCallQualityDetails(QualityRequestObject qualityRequest) {
//		Date date = new Date();
//		Date nextDay = Date.from(date.toInstant().plus(1, ChronoUnit.DAYS));
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

		c.set(Calendar.DAY_OF_MONTH, 1);
		Date monthFirstDay = c.getTime();

		c.set(Calendar.DATE, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date monthLastDay = c.getTime();
		
		
		
		if(qualityRequest.getRequestFor().equalsIgnoreCase("SCORE")) {
			List<CallQualityDetails> results = callQualityDetailsDao.getEntityManager()
					.createQuery("SELECT COUNT(*) from CallQualityDetails CQ WHERE CQ.userId =:userId AND CQ.createdAt BETWEEN :currentDate AND :lastDate")
					.setParameter("userId", qualityRequest.getUserId())
					.setParameter("currentDate", monthFirstDay, TemporalType.DATE)
					.setParameter("lastDate", monthLastDay, TemporalType.DATE)
					.getResultList();
			return results;
		}
		return null;
	}
}
