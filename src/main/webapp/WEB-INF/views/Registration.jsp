<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<style>
    
    .info {
        color: blue; font-weight: bold;
    }
    
    .error 
	{
	    color: red; font-weight: bold;
	}
    
    
	</style>
	
	<script type="text/javascript" src="/fileupload/resources/js/core/jquery.js"></script>
	<script type="text/javascript" src="/fileupload/resources/js/user/userAccount.js"></script>
	
	<title>Registration</title>
</head>
<body>
	<body>
	
		<sec:authorize access="isAuthenticated()">
		    <% response.sendRedirect("dashboard"); %>
		</sec:authorize>
		
		<div class="info" align="center">${message}</div>
		
		<div align="center">
			<form:form action="register" method="post" commandName="userForm">
				<table >
					<tr>
						<td colspan="2" align="center"><h2>Service Provider - Registration</h2></td>
					</tr>
					
					
					<tr>
						<td>E-mail:</td>
						<td><form:input path="email" id="emailTextbox"/></td>
						<td align="right"><form:errors path="email" cssClass="error" id="emailError"/></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><form:password path="password" /></td>
						<td align="right"><form:errors path="password" cssClass="error"/></td>
					</tr>
					
					<tr>
						<td>Confirm Password:</td>
						<td><form:password  path="" name="confirm_password"/></td>
					</tr>
					
					<tr>
						<td>Name:</td>
						<td><form:input path="name" /></td>
						<td align="right"><form:errors path="name" cssClass="error"/></td>
					</tr>
					
					<tr>
						<td>Company:</td>
						<td><form:input path="company" /></td>
						<td align="right"><form:errors path="company" cssClass="error"/></td>
					</tr>
					
					<tr>
						<td colspan="2" align="center"><input type="submit" value="Join Now" /></td>
					</tr>
					
					<tr><td colspan="2" align="center"><a href="/fileupload/login">Existing User - Log In</a></td></tr>
				</table>
			</form:form>
			<div align="center" class="error"><p>*All fields are mandatory</p></div>
		</div>
		
		<script>
			var emailCheckUrl = '/fileupload/register/check-email-availability';
		</script>
	
<%@ include file="footer.jsp" %>