<%@ include file="header.jsp" %>
<title>Update Profile Page</title>
</head>
<body>

<div class="info" align="center">${message}</div>
		
		<div align="center">
			<form:form action="/fileupload/dashboard/edit-user-${userId}" method="post" commandName="user">
				<table >
					<tr>
						<td colspan="2" align="center"><h2>Update your profile</h2></td>
					</tr>
					
					<tr>
						<td><form:input hidden="true" path="id" readonly="true" value="${user.id }" /></td>
					</tr>
					<tr>
						<td>E-mail:</td>
						<td><form:input path="email" readonly="true" value="${user.email }" /></td>
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
						<td colspan="2" align="center"><input type="submit" value="Update" /></td>
						<td colspan="2" align="center"><a href="/fileupload/dashboard">Cancel</a></td>
					</tr>
					
				</table>
			</form:form>
			
			<br><br>
			
			<form action="" method="post" >
				<table >
					<tr>
						<td colspan="2" align="center"><h2>Change Password</h2></td>
					</tr>
					
					
					<tr>
						<td>Current Password:</td>
						<td><input type="password" /></td>
					</tr>
					
					
					<tr>
						<td>New Password:</td>
						<td><input type="password" /></td>
					</tr>
					
					<tr>
						<td>Confirm Password:</td>
						<td><input type="password" /></td>
					</tr>
					
					<tr>
						<td colspan="2" align="center"><input type="button" value="Update" /></td>
						<td colspan="2" align="center"><a href="/fileupload/dashboard">Cancel</a></td>
					</tr>
					
				</table>
			</form>
		</div>
<%@ include file="footer.jsp" %>