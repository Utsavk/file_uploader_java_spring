<%@ include file="header.jsp" %>
		<title>Spring MVC file upload</title> 
	</head> 
<body> 
	
	<h2>Spring MVC file upload</h2> 
	<c:if test="${not empty param.error_message}">
   		<p>Upload unsuccessful</p>
	</c:if>
	<h3>Please select a file to upload !</h3>
	<form:form method="post" enctype="multipart/form-data" modelAttribute="uploadedFile" action="submitFileUpload"> 
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