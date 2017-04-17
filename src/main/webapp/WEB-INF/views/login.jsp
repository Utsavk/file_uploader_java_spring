<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
		<script type="text/javascript" src="/fileupload/resources/js/core/jquery.js"></script>
		<script type="text/javascript" src="/fileupload/resources/js/user/userAccount.js"></script>
		<style>
	    
	    .info {
	        color: blue; font-weight: bold;
	    }
	    
	    .error 
		{
		    color: red; font-weight: bold;
		}
		</style>
		<title>Login Page</title>
	</head>
	<body > 
	<sec:authorize access="isAuthenticated()">
	    <% response.sendRedirect("dashboard"); %>
	</sec:authorize>
	<div align="center">
		
		<h3>Provide your Login credentials</h3> 
		
		<% String errorString = (String)request.getAttribute("error"); 
		if(errorString != null && errorString.trim().equals("true")){
			out.println("<span class=\"dark\">Incorrect login name or password. Please try again"); 
		} %> 
	
	<form:form name='loginForm' action="/fileupload/login" method='POST'> 
		<table> <tr> <td>Email:</td> <td><input type='text' name='email' id="emailTextbox" value=''> 
		<td align="right"><label cssClass="error" id="emailInvalidLabelError" hidden="true">Invalid Email</label></td>
		</td> </tr> <tr> <td>Password:</td> <td>
		<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
		<input type='password' name='password' />
		</td> </tr> <tr> <td>
		<input name="submit" type="submit" value="submit" /> 
		</td> <td>
		<input name="reset" type="reset" /> </td> 
		</tr> 
		<tr>
		<td colspan="2" align="center"><a href="/fileupload/register">New User - Join Now</a></td>
		<td>
		 	<input type="button" value="Forgot Password" id="forgotPasswordBt">
		 </td>
		 </tr>
		</table>
		</form:form> 
	</div>
	<div class="info" align="center">${message}</div>
	<br><br><br>
	
	<div id="forgotPasswordForm"></div>
	<script>
		if ("${invalidEMailMessage}"=='Invalid address mail.This account doesn\'t exist')
		{
			alert("${invalidEMailMessage}");
		}
		var forgotPasswordUrl = '/fileupload/login/forget-password';
	</script>

<%@ include file="footer.jsp" %>