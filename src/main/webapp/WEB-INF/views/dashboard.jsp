<%@ include file="header.jsp" %>
		
		<title>Dashboard</title> 
	</head> 
	
	<body> 
		<br>
		
		Hi! ${userName}
		<% out.println(request.getSession().getAttribute("userId")); %>
		<div class="info" align="center">${message}</div>
		
		<div align="right">
			<c:url var="logoutUrl" value="/logout"/>
			<form action="${logoutUrl}" method="post">
			    <input type="submit" value="Logout"/>
			    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>
		</div>
		
		
		<c:if test="${not empty param.error_message}">
	   		<p>Upload unsuccessful</p>
		</c:if>
		<h3>Please select a file to upload !</h3>
		
		<c:url var="uploadUrl" value="/dashboard/submitFileUpload?${_csrf.parameterName}=${_csrf.token}"/>
		<form:form method="post" enctype="multipart/form-data" modelAttribute="uploadedFile" action="${uploadUrl}"> 
			<table> 
				<tr> 
				<td>Upload File: </td> 
				<td><input type="file" name="file" /> </td> 
				<td style="color: red; font-style: italic;">
				
				<form:errors path="file" /> 
				</td> </tr> <tr> <td> </td> <td><input type="submit" value="Upload" /> </td> <td> </td> 
				</tr> 
			</table> 
			
		</form:form> 	


<%@ include file="footer.jsp" %>
