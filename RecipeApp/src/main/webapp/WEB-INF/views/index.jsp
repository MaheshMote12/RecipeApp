<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false" import="com.me.model.Difficulty" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- <c:url value="/resources/css" var="css"  />
<c:set  value="${pageContext.servletContext.contextPath}/resources/js" var="js"  />
 --%>

<c:url value="/webjars/bootstrap/css" var="css"  />
<c:set  value="${pageContext.servletContext.contextPath}/webjars/bootstrap/js" var="js"  />
<!DOCTYPE html>
<HTML>
<head>
	<meta charset="UTF-8">

	<script type="text/javascript" src="${js}/bootstrap.min.js">	</script>
	<link href="${css}/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	 
	<title>${titel}</title>
</head>

<body>

<h1>
</h1>


	<!-- home page -->
 	<c:if test="${userclickHome}">
	 	<%@include file="home.jsp"   %> 
	</c:if>
 	
	
	<c:if test="${userclickRecipe}">
		<%@include file="show.jsp"   %> 
	</c:if>
   

	<c:if test="${userclickNewRecipe}">
		<%@include file="recipeForm.jsp"   %> 
	</c:if>
	

	<c:if test="${userclickIngredieants}">
		<%@include file="list.jsp"   %> 
	</c:if>


	<c:if test="${userclickUploadImage}">
		<%@include file="imageUploadForm.jsp"   %> 
	</c:if>


</body>
</html>
