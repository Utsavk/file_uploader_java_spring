<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	
<div class="info" align="center">${message}</div>

<div align="center">
	<form:form action="login/forget-password" method="post" commandName="userForgotPasswordForm">
		<table >
			<tr>
				<td colspan="2" align="center"><h2>Password reset form</h2></td>
			</tr>
			
			<tr>
				<td>E-mail:</td>
				<td><form:input path="email" id="emailTextbox"/></td>
				<td align="right"><form:errors path="email" cssClass="error" id="emailError"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Send Link to Reset" /></td>
			</tr>
		</table>
	</form:form>
</div>