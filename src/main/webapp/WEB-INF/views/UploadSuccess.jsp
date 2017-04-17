<%@ include file="header.jsp" %>

	<br><br>
	<div class="info" align="center">${message}</div>
	<div align="right">
		<c:url var="logoutUrl" value="/logout"/>
		<form action="${logoutUrl}" method="post">
		    <input type="submit" value="Logout"/>
		    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
	</div>
		
	<h2>You file has been uploaded</h2> 
	FileName : " 
	<strong> ${fileName} </strong>
	" - Uploaded Successful. 
	
	<div align="center"><a href="/fileupload/dashboard">Go Back</a></div>
	
	
<%@ include file="footer.jsp" %>




