<%@ include file="header.jsp" %>
	<title>Upload Records</title>
	<style type="text/css">
		.fileListTable { background-color:#eee;border-collapse:collapse; }
		.fileListTable th { background-color:#000;color:white;width:50%; }
		.fileListTable td, .fileListTable th { padding:5px;border:1px solid #000; }
	</style>
</head>
<body>
	<sec:authentication var="user" property="principal" />
	<br><br>
	<div class="info" align="center">${message}</div>
	<div align="right">
		<c:url var="logoutUrl" value="/logout"/>
		<form action="${logoutUrl}" method="post">
		    <input type="submit" value="Logout"/>
		    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
	</div>
	<div align="center">
				<div align="center">
					<h2>Here your the details.</h2>
					<table >
							<tr>
								<td colspan="2" align="center"><h3>Personal Info!</h3></td>
							</tr>
							<tr>
								<td>Name:</td>
								<td>${name}</td>
							</tr>
							<tr>
								<td>Company:</td>
								<td>${companyName}</td>
							</tr>

						</table>
						    
				</div>
		<br>
		
		<c:if test="${uploadedFiles!=null}">
			Your folder name on our severs:&nbsp;&nbsp;&nbsp;&nbsp;${user.username}
			<br><br>
			<table class="fileListTable">
				<tr>
					<th>S.No.</th><th>File Name</th>
				</tr>
				<c:forEach items="${uploadedFiles}" var="singleFile" varStatus="loop">
					<tr>
			            <td><c:out value="${loop.count}"/></td>
			            <td><c:out value="${singleFile}"/></td>
			        </tr>
	    		</c:forEach>
			</table>
		</c:if>
		
		<c:if test="${uploadedFiles == null}">
			<h3>You have no uploaded files</h3>
		</c:if>
		
	</div>
	<div align="center"><a href="/fileupload/dashboard">Go Back</a></div>


<%@ include file="footer.jsp" %>