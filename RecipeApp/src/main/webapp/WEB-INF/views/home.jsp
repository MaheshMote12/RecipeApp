<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false" %>
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
	 
	<title>Home</title>
</head>

<body>

<h1>
	Hello world!     
</h1>




<div class="container-fluid" style="margin-top: 20px" >
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<div class="panel panel-primary">
			
				<div class="panel-heading">
					<h1>My Recipies</h1>
				</div>
				<div class="panel-body" >
					<div class="table-responsive">
					
						<table class="table table-hover " >
							<thead class="thead-inverse " >
								<tr>
									<th>ID</th>
									<th>Description</th>
								</tr>
							</thead>
								<c:forEach items="${recipies}" var="recipe" >
									<tr>
										<td>${recipe.recipeId}</td><td>${recipe.description}</td>
									</tr>
								</c:forEach>
						</table>		
					</div>
				
				</div>
			
			</div>
			
		
		</div>
	
	</div>

</div>

</body>
</html>
