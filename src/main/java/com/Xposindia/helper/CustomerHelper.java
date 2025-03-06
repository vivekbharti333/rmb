package com.Xposindia.helper;

import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.Xposindia.constant.Constant;
import com.Xposindia.dao.CustomerDao;
import com.Xposindia.entities.Customer;
import com.Xposindia.enums.Status;
import com.Xposindia.expections.BizException;
import com.Xposindia.object.request.CustomerRequestObject;


@Component
public class CustomerHelper {
		
	@Autowired
	private CustomerDao customerDao;
	
	
	public void validateCustomerRequest(CustomerRequestObject customerRequestObject) 
			throws BizException
	{ 
		if(customerRequestObject == null) {
			throw new BizException(Constant.BAD_REQUEST_CODE, "Bad Request Object Null"); 
		}
	}
	
	
	@Transactional
	public Customer getCustomerLoginDetails(String mobile, String password)
	{ 
		Criteria cr = customerDao.getSession().createCriteria(Customer.class);
		cr.add(Restrictions.eq("mobile", mobile));
		cr.add(Restrictions.eq("password", password));
		Customer  existCustomer = (Customer) cr.uniqueResult();
		return existCustomer;
	}
	
	
	/* Get User Details By Mobile Number */
	@Transactional
	public Customer getCustomerDetailsByMobileNo(String customerMobile)
	{ 
		Criteria cr = customerDao.getSession().createCriteria(Customer.class);
		cr.add(Restrictions.eq("customerMobile", customerMobile));
		Customer  existCustomer = (Customer) cr.uniqueResult();
		return existCustomer;
	}
	
	
	public Customer getcustomerOtpByReqObj(CustomerRequestObject customerRequest) 
	{ 
		Customer customer = new Customer();
		
		customer.setCountryCode(customerRequest.getCountryCode());
		customer.setCustomerMobile(customerRequest.getCustomerMobile());
		customer.setCustomerOtp(customerRequest.getCustomerOtp());
		customer.setCustomerOtpStatus("INIT");
		customer.setStatus(Status.ACTIVE.name());
		
		customer.setCreatedAt(new Date());
		customer.setUpdatedAt(new Date());
		return customer;
	}
	
	@Transactional
	public Customer saveCustomer(Customer customer) 
	{ 
		customerDao.persist(customer);
		return customer;
	}
	
	
	public Customer getUpdatedCustomerOtpByReqObj(CustomerRequestObject customerRequest, Customer customer) 
	{ 
		
		customer.setCustomerOtp(customerRequest.getCustomerOtp());
		customer.setCustomerOtpStatus("INIT");
		
		customer.setCreatedAt(new Date());
		customer.setUpdatedAt(new Date());
		return customer;
	}
	
	@Transactional
	public Customer updateCustomer(Customer customer) 
	{ 
		customerDao.update(customer);
		return customer;
	}
	
}
