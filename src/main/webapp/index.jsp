<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<html>
<head>
	<style>
    
    .info {
        color: blue; font-weight: bold;
    }
</style>
</head>
<body>
	<sec:authorize access="isAuthenticated()">
	    <% response.sendRedirect("dashboard"); %>
	</sec:authorize>
	<div  align="center">${message}</div>
	
	
	<h2>FILE UPLOADER</h2>
	<div class="info" align="center">
		<c:if test="${param.logout==true}">
			<p>You are successfully logged out<p>
		</c:if>
	</div>
	<div ><a href="/fileupload/login">LOGIN</a></div>
	<div ><a href="/fileupload/register">JOIN NOW</a></div>
	</body>
</html>
