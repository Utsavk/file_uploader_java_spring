<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Password</title>
	<style>
    
	    .info {
	        color: blue; font-weight: bold;
	    }
	    
	    .error 
		{
		    color: red; font-weight: bold;
		}
	</style>
</head>
<body>
		<div class="info" align="center">${message}</div>
		
		<div align="center">
			<form:form action="/login/reset-password" method="post" commandName="user">
				<table >
					<tr>
						<td>E-mail:</td>
						<td><form:input path="email"  readonly="true" value="${user.email}"/></td>
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
				</table>
			</form:form>
		</div>
</body>
</html>