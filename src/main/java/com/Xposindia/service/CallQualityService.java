package com.Xposindia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Xposindia.constant.Constant;
import com.Xposindia.entities.CallQualityDetails;
import com.Xposindia.entities.Users;
import com.Xposindia.expections.BizException;
import com.Xposindia.helper.CallQualityHelper;
import com.Xposindia.helper.UserHelper;
import com.Xposindia.object.request.QualityRequestObject;
import com.Xposindia.object.request.Request;



@Service
public class CallQualityService {

	@Autowired
	private CallQualityHelper callQualityHelper;
	
	@Autowired
	private UserHelper userHelper;

	public QualityRequestObject saveCallQuality(Request<QualityRequestObject> qualityRequestObject) throws BizException {
		
		QualityRequestObject qualityRequest = qualityRequestObject.getPayload();
		callQualityHelper.validateCallQualityRequest(qualityRequest);
		
		Users user = userHelper.getUserDetailsByUserId(qualityRequest.getUserId());
		if(user != null) {
			qualityRequest.setAgentName(user.getFullName());
		}else {
			qualityRequest.setAgentName("NOT FOUND");
		}
		if(qualityRequest.getEleven()== 0 || qualityRequest.getTwelve()== 0 || qualityRequest.getThirteen()== 0
				|| qualityRequest.getFourteen()== 0 ) {
			qualityRequest.setScore(0L);
			
//			CallQualityDetails callQualityDetails = callQualityHelper.getCallQualityDetailsByObjWithZero(qualityRequest);
//			callQualityDetails = callQualityHelper.saveCallQualityDetails(callQualityDetails);
//			
//			qualityRequest.setRespCode(Constant.SUCCESS_CODE);
//			qualityRequest.setRespMesg("Save Successfull");
//			return qualityRequest;
			
		}else {
			
			Long score = qualityRequest.getOne()+qualityRequest.getTwo()+qualityRequest.getThree()+qualityRequest.getFour()
			+qualityRequest.getFive()+qualityRequest.getSix()+qualityRequest.getSeven()+qualityRequest.getEight()+qualityRequest.getNine()
			+qualityRequest.getTen();
			
			qualityRequest.setScore(score);
			
			
		}
		CallQualityDetails callQualityDetails = callQualityHelper.getCallQualityDetailsByObj(qualityRequest);
		callQualityDetails = callQualityHelper.saveCallQualityDetails(callQualityDetails);
		
		qualityRequest.setRespCode(Constant.SUCCESS_CODE);
		qualityRequest.setRespMesg("Save Successfull");
		return qualityRequest;
		
	}

	public List<CallQualityDetails> getCallQualityDetails(Request<QualityRequestObject> qualityRequestObject) {
		QualityRequestObject qualityRequest = qualityRequestObject.getPayload();
		System.out.println(qualityRequest.getUserId()+ "user id");
		List<CallQualityDetails> callQualityDetails = callQualityHelper.getCallQualityDetailsList(qualityRequest);	
		return callQualityDetails;
	}
	
	public QualityRequestObject getScore(Request<QualityRequestObject> qualityRequestObject) throws BizException {
		
		QualityRequestObject qualityRequest = qualityRequestObject.getPayload();
		callQualityHelper.validateCallQualityRequest(qualityRequest);
		
		List<CallQualityDetails> sum = callQualityHelper.getTotalScoreForCallQualityDetails(qualityRequest);
		

		List<CallQualityDetails> count = callQualityHelper.getTotalCountForCallQualityDetails(qualityRequest);
				
		if(sum.get(0) != null ) {
			Object tota = sum.get(0);
			Object coun = count.get(0);
			Long totalScore = (Long) tota;
			Long totalCount = (Long) coun;
			
			double score = totalScore/totalCount;
			
			qualityRequest.setTotalScore(score);
			
			System.out.println(score+" "+totalCount);
			
			qualityRequest.setRespCode(Constant.SUCCESS_CODE);
			qualityRequest.setRespMesg("Save Successfull");
			return qualityRequest;
		}else {
			qualityRequest.setTotalScore(0);
			
			qualityRequest.setRespCode(Constant.SUCCESS_CODE);
			qualityRequest.setRespMesg("Save Successfull");
			return qualityRequest;
		}
}


}