
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../../shared/header.jsp"></jsp:include>


<body>


<div class="container-fluid" style="margin-top: 20px">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            
            
            
            <form:form  modelAttribute="ingredient" action="${pageContext.servletContext.contextPath}/recipe/${ingredient.getRecipeId()}/ingredient'} " method="post">
            
            
<%--                 <form:input type="hidden" path="${id}"/>
				<form:input type="hidden" path="${recipeId}"/>
 --%> 
 
 
				<input type="hidden" name="id" value="${id}"/>
				<input type="hidden" name="recipeId" value="${recipeId}"/>
 
                
                <div class="pannel-group">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h1 class="panel-title">Edit Ingredient Information</h1>
                        </div>
                        <div class="panel-body">


                            <div class="row">
                                <div class="col-md-3 form-group">
                                    <label>Description:</label>
                                    <form:input type="text" cssClass="form-control" path="description"/>
                                </div>

                                <div class="col-md-3 form-group">
                                    <label>Amount:</label>
                                    <form:input type="number" cssClass="form-control" path="amount"/>
                                </div>

                                <div class="col-md-3 form-group">
                                    <label>UOM:</label>
                                    
                                     <select class="form-control" name="uom.id">
                                    <c:forEach items="${uoms}" var="uom" varStatus="status">
                                    
                                    
                                    <%-- <form:select path="uomC.unit"  items="${uom}"></form:select> --%>

                                        <option  value="${uom.unit}" selected="${uom.id.equals(uomC.id)}">${uom.unit }</option>


                                    </c:forEach>


                                    </select> 
                                    
                                    
<%--                                     <select class="form-control" name="uom.id">
                                        <option th:each="uomEach : ${uomList}"
                                                th:value="${uomEach.id}"
                                                th:selected="${uomEach.id.equals(ingredient.uom.id)}"
                                                th:text="${uomEach.description}">Each</option>
                                    </select> --%>
                                </div>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </form:form>
        </div>
    </div>
</div>



</body>
</html>