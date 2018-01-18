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
									<th>View</th>
									<th>Update</th>
									<th>Delete</th>
									<th><a href="${pageContext.servletContext.contextPath}/recipe/new">New</a></th>
								</tr>
							</thead>
								<c:forEach items="${recipies}" var="recipe" >
									<tr>
										<td>${recipe.recipeId}</td>
										<td>${recipe.description}</td>
										<td><a href="${pageContext.servletContext.contextPath}/recipe/show/${recipe.recipeId}" >View</a></td>
										<td><a href="${pageContext.servletContext.contextPath}/recipe/${recipe.recipeId}/update">Update</a></td>
										<td><a href="${pageContext.servletContext.contextPath}/recipe/${recipe.recipeId}/delete">Delete</a></td>
									</tr>
								</c:forEach>
						</table>		
					</div>
				
				</div>
			 
			</div>
		</div>
	
	</div>

</div>

