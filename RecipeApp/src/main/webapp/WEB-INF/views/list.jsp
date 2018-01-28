<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="container-fluid" style="margin-top: 20px">

    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-primary">

                <div class="panel-heading">
                    <div class="row">
                        <div class="col-md-11 ">
                            <h1 class="panel-title">Ingredients</h1>
                        </div>
                        <div class="col-md-1">
                            <a class="btn btn-default" href="${pageContext.servletContext.contextPath}/recipe/${recipe.recipeId}/ingredient/new" <%-- th:href="@{'/recipe/' + ${recipe.recipeId} + '/ingredient/new'}"  --%>role="button">New</a>
                        </div>
                    </div>

                </div>
                <div class="panel-body">

                    <div class="table-responsive" <%-- th:if="${not #lists.isEmpty(recipe.ingredients)} --%>">
                        <table class="table table-hover ">
                            <thead class="thead-inverse">
                            <tr>
                                <th>ID</th>
                                <th>Description</th>
                                <th>View</th>
                                <th>Update</th>
                                <th>Delete</th>
                            </tr>
                            </thead>
                            	<c:forEach items="${recipe.ingrediants}" var="ingr" varStatus="status" >
                            <tr>
                            
                            	
                            
	                                <td>${ingr.id }</td>
	                                <td>${ingr.description}</td>
	                                <td><a href="${pageContext.servletContext.contextPath}/recipe/${recipe.recipeId}/ingredient/${ingr.id}/show">View</a></td>
	                                <td><a href="#">Update</a></td>
	                                <td><a href="${pageContext.servletContext.contextPath}/recipe/${recipe.recipeId}/ingredient/${ingr.id}/delete">Delete</a></td>
                            </tr>
                            	</c:forEach>
                           
                            <%-- <tr th:each="ingredient : ${recipe.ingredients}">
                                <td th:text="${ingredient.id}">334</td>
                                <td th:text="${ingredient.amount} + ' ' + ${ingredient.uom.getDescription()} + ' ' + ${ingredient.description}">Tasty Goodnees 3</td>
                                <td><a href="#" th:href="@{'/recipe/' + ${recipe.id} + '/ingredient/' + ${ingredient.id} + '/show'}">View</a></td>
                                <td><a href="#" th:href="@{'/recipe/' + ${recipe.id} + '/ingredient/' + ${ingredient.id} + '/update'}">Update</a></td>
                                <td><a href="#" th:href="@{'/recipe/' + ${recipe.id} + '/ingredient/' + ${ingredient.id} + '/delete'}">Delete</a></td>
                            </tr>
 --%>                        
 
 							
 
 
 
 						</table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
