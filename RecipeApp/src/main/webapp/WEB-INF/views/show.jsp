
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="container-fluid" style="">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">  
			<div class="panel-group">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h1 class="panel-title" title="${recipe.description }">${recipe.description }</h1>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3">
								<h5>Categories:</h5>
							</div>
							<div class="col-md-9">
								<ul>
									<c:forEach items="${recipe.categories}" var="category">

										<li>${category.categoryName}</li>

									</c:forEach>
								</ul>

							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<h5>Prep Time:</h5>
							</div>
							<div class="col-md-3">
								<p>${recipe.prepTime} Min</p>
							</div>
							<div class="col-md-3">
								<h5>Difficulty:</h5>
							</div>
							<div class="col-md-3">
								<p>${recipe.difficulty}</p>
							</div>
						</div>

						<div class="row">
							<div class="col-md-3">
								<h5>Cook Time:</h5>
							</div>
							<div class="col-md-3">
								<p>${recipe.cookTime} Min</p>
							</div> 
							<div class="col-md-3">
								<h5>Servings:</h5>
							</div>
							<div class="col-md-3">
								<p>${recipe.servings}</p>
							</div>
						</div>


						<div class="row">
							<div class="col-md-3">
								<h5>Source:</h5>
							</div>
							<div class="col-md-3">
								<p><a href="${recipe.source}" >${recipe.source}</a></p>
							</div>
							<div class="col-md-3">
								<h5>URL:</h5>
							</div>
							<div class="col-md-3">
								<p><a href="${recipe.url}" >${recipe.url}</a></p>
							</div>
						</div>
					</div>
				</div>

				<div class="panel panel-primary">
					<div class="panel-heading">
						<h1 class="panel-title" title="panel title here">Ingredients!</h1>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12">
								<ul>

									<c:forEach items="${recipe.ingrediants}" var="ingradients">
										<li>${ingradients.amount}</li>
										<li>${ingradients.description}</li>
										<li>${ingradients.uom.unit}</li>
									</c:forEach> 
								</ul>
							</div>

						</div>
					</div>
				</div>



				<div class="panel panel-primary">
					<div class="panel-heading">
						<h1 class="panel-title">Directions</h1>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12">
								<p> ${recipe.direction}</p>
							</div>
						</div>
					</div>
				</div>


				<div class="panel panel-primary">
					<div class="panel-heading">
						<h1 class="panel-title">Notes</h1>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12">
								<p> ${recipe.notes.notes}</p>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>

	</div>

</div>







