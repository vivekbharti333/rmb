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
	<img src="digital-india-internship.jpg" style="width: 190px;">
	</div>
	
	<div class="header">
	<img src="digital.png" style="height: 77px; width: 144px;margin-left: 100px;">
	</div>
	
	<div class="header">
	<img src="download (1).png" style="width: 166px;margin-left: 222px;">
	</div>
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
      <li class="active" ><a href="searchotp">Registration</a></li>
      <li ><a href="searchesign"> </a></li>
      <li><a href="#"> </a></li>
      
    </ul>
  </div>
</nav>
 
 
 <div class="container">
	<div style=" background-color: #4f7aca;">
	<h2 style ="text-align:center"> User Registration </h2>
	
	
	</div>
	</div>



 <div class="container">
  <div class="col-md-offset-2 col-md-7">
   <div class="panel panel-info">
    <div class="panel-heading">
     <div class="panel-title">Sign Up</div>
    </div>
    <div class="panel-body" style="color: forestgreen;">
    
            <form:form action="registerUser" method="POST" modelAttribute="userinfo" class="form-horizontal">
            
            <%-- <form:input type="hidden" path="id" id="id"/>
              --%>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="firstname">First Name</label>
                    <div class="col-md-7">
                        <form:input type="text" path="firstname" id="firstname" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="firstname" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
     
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="lastname">Last Name</label>
                    <div class="col-md-7">
                        <form:input type="text" path="lastname" id="lastname" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="lastname" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
     
      <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="emailId">Email Id</label>
                    <div class="col-md-7">
                        <form:input type="text" path="emailId" id="emailId" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="emailId" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
      
         <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="phone">Phone No</label>
                    <div class="col-md-7">
                        <form:input type="text" path="phone" id="phone" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="phone" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            
              <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="userId.userId">User Id</label>
                    <div class="col-md-7">
                        <form:input type="text" path="userId.userId" id="userId.userId" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="userId.userId" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            
              <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="userId.password">Password</label>
                    <div class="col-md-7">
                        <form:input type="text" path="userId.password" id="userId.password" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="userId.password" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
             <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="userId.role">Role</label>
                    <div class="col-md-7">
                        <form:input type="text" path="userId.role" id="userId.role" class="form-control input-sm" />
                        <div class="has-error">
                            <form:errors path="userId.role" class="help-inline"/>
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
                            <input type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/list' />">Cancel</a>
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