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
<style type="text/css">
.button {
  font: bold 11px Arial;
  text-decoration: none;
  background-color: #EEEEEE;
  color: #333333;
  padding: 2px 6px 2px 6px;
  border-top: 1px solid #CCCCCC;
  border-right: 1px solid #333333;
  border-bottom: 1px solid #333333;
  border-left: 1px solid #CCCCCC;
}

</style>

</head>
<body style="background-color:#00000; margin:0">



  <div class="container-fluid" style ="height:25px;background-color:black"></div>
 <div class="container"  style=" height:80px;background-color:white">
	<div class="header">
	
	<img src="images/msLogo.png"  style="height: 77px; width: 144px;margin-left: 5px">
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
      <li  ><a href="users">Registration</a></li>
      <li class="active"><a href="getAlluser">User List</a></li>
      <li ><a href=<c:url value="/createMessage"/>>Message </a></li>
      <li><a href="#"> </a></li>
      
    </ul>
  </div>
</nav>
 
 
 <div class="container">
	<div style=" background-color: #4f7aca;">
	<h2 style ="text-align:center"> User Data </h2>
	
	
	</div>
	</div>



 
  
 
    
    
    
    <div class="container"  id="printtable" >
	
	<table  id="example"  class="table table-bordered"  style="width:100%">
		<thead>
        	<tr class="headings">
            <th style="background-color:#F26128"><font color="#fff">First Name</font></th>
            <th style="background-color:#F26128"><font color="#fff">Last Name</th>
            <th style="background-color:#F26128"><font color="#fff">Email Id </th>
            <th style="background-color:#F26128"  > <font color="#fff">Phone No</th>
            <th style="background-color:#F26128"><font color="#fff">User Id</th>
            <th style="background-color:#F26128"><font color="#fff">Role</th>
            <th style="background-color:#F26128"><font color="#fff">Edit User</th>
            
            
        </tr>
    </thead>
 
    <tbody style="color: black">

<c:forEach var="user" items="${listUser}"> 
   

<tr><td>${user.firstname}</td> 
<td>${user.lastname}</td> 
<td>${user.emailId}</td> 
<td>${user.phone}</td> 
<td>${user.userId.userId}</td> 
<td>${user.userId.role}</td> 
<td><a class="button" href=<c:url value="/getUser/${user.userId.userId}"/>>Edit</a></td><tr>
 </c:forEach>   
 

   
   
    </tbody>

</table>
    

    

</body>
</html>