<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="<c:url value="/resources/css/common.css" />" rel="stylesheet">
	
	<script type="text/javascript" src="/fileupload/resources/js/core/jquery.js"></script>
	
	
	<ul>
	  <li><a class="active" href="/fileupload/dashboard">Home</a></li>
	  <li><a href="/fileupload/dashboard/edit-user-<%= request.getSession().getAttribute("userId") %>">Profile</a></li>
	  <li><a href="/fileupload/dashboard/uploaded-files">View Files</a></li>
	  <li><a href="#about">About</a></li>
	</ul>
