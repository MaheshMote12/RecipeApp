
<jsp:include page="../../shared/header.jsp"></jsp:include>


<body>



<div class="container-fluid" style="margin-top: 20px">

    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-primary">

                <div class="panel-heading">
                    <div class="row">
                        <div class="col-md-11 ">
                            <h1 class="panel-title">Ingredient</h1>
                        </div>
                        <div class="col-md-1">
                            <a class="btn btn-default" href="#" <%-- th:href="@{'/recipe/' + ${recipe.recipeId} + '/ingredient/new'}"  --%>role="button">New</a>
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
                            <tr>
                            	
                            
	                                <td>${ingredient.id}</td>
	                                <td>${ingredient.description}</td>
	                                <td><a href="#">View</a></td>
	                                <td><a href="${pageContext.servletContext.contextPath}/recipe/${ingredient.recipeId}/ingredient/${ingredient.id}/update">Update</a></td>
	                                <td><a href="#">Delete</a></td>
                            </tr>




</table>
</div>
</div>
</div>
</div>
</div>
</div>



</body>
</html>