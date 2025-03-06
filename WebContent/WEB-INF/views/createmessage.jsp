<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Spring MVC 5 - form handling | Java Guides</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body style="background-color:#00000; margin:0">



  <div class="container-fluid" style ="height:25px;background-color:black"></div>
  <div class="container"  style=" height:80px;background-color:white">
	<div class="header">
	
	<img src="<c:url value="/images/msLogo.png"/>"  style="height: 77px; width: 144px;margin-left: 5px">
	<!-- <img src="/Users/thakur/Documents/JavaProjects/MessageSystem/src/main/resources/images/msLogo.png" style="width: 190px;"> -->
	</div>
	
	<!-- <div class="header">
	<img src="digital.png" style="height: 77px; width: 144px;margin-left: 100px;">
	</div> -->
	
	<!-- <div class="header">
	<img src="download (1).png" style="width: 166px;margin-left: 222px;">
	</div> -->
  </div>
  <div class="container-fluid" style ="width:100%;height:25px;background-color:#0000cc"> 
 <div class="col-md-12">
  <div class="col-md-9 right">
 
</div>
  
  <div class=" container col-md-3 right">
  <font color="#fff">WELCOME <%=session.getAttribute("username") %> 
   &nbsp;&nbsp;
    <a href="invalidate"><font color="#fff"> Logout</font></a> </div>
  
  </div>
  </div>


 
 <nav class="navbar navbar-default">
  <div class="container" >
    
    <ul class="nav navbar-nav">
      <li  ><a href=<c:url value="/users"/>>Registration</a></li>
      <li ><a href=<c:url value="/getAlluser"/>>User List </a></li>
      <li class="active"><a href=<c:url value="/createMessage"/>>Message </a></li>
      <li><a href="#"> </a></li>
      
    </ul>
  </div>
</nav>
 
 
 <div class="container">
	<div style=" background-color: #4f7aca;">
	<h2 style ="text-align:center"> New Message </h2>
	
	
	</div>
	</div>



 <div class="container">
  <div class="col-md-offset-2 col-md-7">
   <div class="panel panel-info">
    <div class="panel-heading">
     <div class="panel-title">Message</div>
    </div>
    <div class="panel-body" style="color: forestgreen;">
    
            <form:form action="registerUser" method="POST" modelAttribute="message" class="form-horizontal">
            
            <%-- <form:input type="hidden" path="id" id="id"/>
            
            
              --%>
              
              
              
                   <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="campiagnId">Bulk Message Id</label>
                    <div class="col-md-7">
                        <form:input type="text" path="campiagnId" id="campiagnId" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="campiagnId" class="help-inline"/>
                        </div>
                    </div>
                </div>
           
              <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="mobileNos">Enter Mobile No.</label>
                    <div class="col-md-7">
                        <form:textarea path="mobileNos" id="mobileNos" class="form-control textarea-sm"/>
                        <div class="has-error">
                            <form:errors path="mobileNos" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            
              <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="mobileUoload">Upload Mobile No.</label>
                    <div class="col-md-7">
                        <form:input type="file" path="mobileUoload" id="mobileUoload" class="form-control file-sm" />
                        <div class="has-error">
                            <form:errors path="mobileUoload" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="message">Message</label>
                    <div class="col-md-7">
                        <form:textarea path="message" id="message" class="form-control textarea-sm"/>
                        <div class="has-error">
                            <form:errors path="message" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            
     
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="msgAudio">Audio File</label>
                    <div class="col-md-7">
                        <form:input type="file" path="msgAudio" id="msgAudio" class="form-control file-sm" />
                        <div class="has-error">
                            <form:errors path="msgAudio" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
     
      <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="msgVideo">Video File</label>
                    <div class="col-md-7">
                        <form:input type="file" path="msgVideo" id="msgVideo" class="form-control file-sm" />
                        <div class="has-error">
                            <form:errors path="msgVideo" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            
      
         <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="msgDoc">Document</label>
                    <div class="col-md-7">
                        <form:input type="file" path="msgDoc" id="msgDoc" class="form-control file-sm" />
                        <div class="has-error">
                            <form:errors path="msgDoc" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            
           
            
             
    		  <div class="row">
                <div class="form-actions floatRight">
                    <c:choose>
                        <c:when test="${edit}">
                            <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/list' />">Cancel</a>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Submit" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/list' />">Cancel</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
     

        </form:form>
    
    </div>
   </div>
  </div>
 </div>
</body>
</html>