<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!--/*@thymesVar id="recipe" type="guru.springframework.domain.Recipe"*/-->


<div class="container-fluid" style="margin-top: 20px">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
        
        <spring:hasBindErrors name="recipe">
        	<div class="alert alert-danger">
        		<p>Please correct Errors Below</p>
        	</div>
        </spring:hasBindErrors>
        
        
        <spring:url value="/recipe/recipeForm" var="recipeUrl"/>
            <form:form  action="${recipeUrl}"  modelAttribute="recipe" method="post">


        <form:input type="hidden" path="image"></form:input>
 
<%--                 <div th:if="${#fields.hasErrors('*')}" class="alert alert-danger">
                    <p>Please Correct Errors Below</p>
                </div>
 --%>
     <!--            <input type="hidden" th:field="*{id}"/> -->
                <div class="pannel-group">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h1 class="panel-title">Edit Recipe Information</h1>
                        </div>
                        <div class="panel-body">
                            <div class="row">
     <%--                            <div class="col-md-3 form-group" th:class="${#fields.hasErrors('description')}
                                ? 'col-md-3 form-group has-error' : 'col-md-3 form-group'">
                                
                                
 --%>                                    
 
 							<spring:bind path="description">
 							
 
 							<div class="col-md-3 form-group ${status.error ? 'has-error' : ''} ">
 									<label for="${recipe.description}">Recipe Description:</label>
                                    <form:input type="text" class="form-control" path="description" />
 									<form:errors path="description" ></form:errors>
          <%--                           <span class="help-block" th:if="${#fields.hasErrors('description')}">
                                        <ul>
                                            <li th:each="err : ${#fields.errors('description')}" th:text="${err}"/>
                                        </ul>
                                    </span>
                                </div>
           --%>              
           
           				 </div>
 						</spring:bind>
           				
           					<div class="row">
                                <div class="col-md-3 form-group">
           				
<%--            						<c:if test="${recipe.recipeId gt 0 }">
           							<label>Recipe Id</label>
											<form:input type="text" class="form-control" path="recipeId"  errorclass="has-error"/>           						
           						</c:if>
 --%>           				
           						<form:input type="hidden" path="recipeId" />
           						</div>
           				</div>
           				
           				
           				
           				
                            <div class="row">
                                <div class="col-md-3 form-group">
                                    <label>Categories:</label>
                                </div>
                                
                                
                                <%-- <form:checkboxes items="${cate}" path="categories.categoryName"/> --%>
                                
<%--                                 <c:forEach items="${cate}" var="t" >
                                        <label>
                                            <input type="checkbox" value="${t.categoryName}" name="${t.categoryName}"/>
                                            ${t.categoryName}
                                        </label>
                                	
                                
                                </c:forEach>
 --%>                                
     
								 <c:forEach items="${cate}" var="category" varStatus="status">
                                        <label>
                                            <%-- <input type="checkbox" value="${category}" name="categories[${status.index}].categoryName"/> --%>
                                            
                                            <%-- <form:input type="checkbox" value="${category}" path="categories[${status.index}].categoryName"/> --%>

                                            <%-- <form:checkboxes items="${cate}"  path="categories[${status.index}].categoryName"/> --%>
                                            <form:checkbox value="${category}" path="categories[${status.index}].categoryName"/> 
                                            
                                            ${category} 
                                        </label>
                                </c:forEach> 
     
     
     
     
<!--                                 <div class="col-md-3 form-group">
                                    <div class="radio">
                                        <label>
                                            <input type="checkbox" value=""/>
                                            Cat 1
                                        </label>
                                    </div>
                                    <div class="radio" th:remove="all">
                                        <label>
                                            <input type="checkbox" value=""/>
                                            Cat 2
                                        </label>
                                    </div>
                                </div>
 -->                            </div>
                            <div class="row">
<%--                                 <div class="col-md-3 form-group" th:class="${#fields.hasErrors('prepTime')}
                                ? 'col-md-3 form-group has-error' : 'col-md-3 form-group'">
                                
 --%>                                    
 		
 						<spring:bind path="prepTime">
 	
 							<div class="col-md-3 form-group ${status.error ? 'has-error' : ''} ">
 									<label>Prep Time:</label>
                                    <form:input type="text" class="form-control" path="prepTime" errorclass="has-error"/>
                                    <form:errors path="prepTime"></form:errors>
     <%--                                <span class="help-block" th:if="${#fields.hasErrors('prepTime')}">
                                        <ul>
                                            <li th:each="err : ${#fields.errors('prepTime')}" th:text="${err}"/>
                                        </ul>
                                    </span>
      --%>                  </div>
 						</spring:bind>
          <%--                       <div class="col-md-3 form-group" th:class="${#fields.hasErrors('cookTime')}
                                ? 'col-md-3 form-group has-error' : 'col-md-3 form-group'">
           --%>                          
					
							<spring:bind path="cookTime">
							
           						<div class="col-md-3 form-group ${status.error ? 'has-error' : ''} ">
           						<label>Cook time:</label>
                                    <form:input type="text" class="form-control" path="cookTime" errorclass="has-error"/>
                                    <form:errors path="cookTime"></form:errors>
               <%--                      <span class="help-block" th:if="${#fields.hasErrors('cookTime')}">
                                        <ul>
                                            <li th:each="err : ${#fields.errors('cookTime')}" th:text="${err}"/>
                                        </ul>
                                    </span>
                --%>           </div> 
							</spring:bind>           



	<!-- 	MANY OPTIONS FOR GETTING ENUMERATION VALUES -->


						
                                <div class="col-md-3 form-group">
                                    <label>Difficulty:</label>
                                    
                                     <form:select items="${Difficulty.values()}" path="difficulty">
                                    	</form:select>	
 									
 
 								<c:set var="difficulty"  value="${Difficulty.values()}"/>
<%-- 									<select class="form-control"  name="difficulty">
                                        <option value="${ diff[0]}"> d
                                        </option>
                                        <option value="${ diff[1]}">df</option>
                                    </select>
 --%>                                    
     						<%-- 		<select class="form-control"  name="difficulty">
                                        <option value=" ${difficulty[0]}"> d
                                        </option>
                                        <option value="${ difficulty[1]}">4554</option>
                                    </select>
 --%>                                    
                           </div>

                            </div>
                            <div class="row">
<%--                                 <div class="col-md-3 form-group" th:class="${#fields.hasErrors('servings')}
                                ? 'col-md-3 form-group has-error' : 'col-md-3 form-group'">
                                
                                
 --%>                                    
 					<spring:bind path="servings">
 
 							<div class="col-md-3 form-group ${status.error ? 'has-error' : ''} ">
 								<label>Servings:</label>
                                    <form:input type="text" class="form-control" path="servings"  />
                                    <form:errors path="servings"></form:errors>
<%--                                     <span class="help-block" th:if="${#fields.hasErrors('servings')}">
                                        <ul>
                                            <li th:each="err : ${#fields.errors('servings')}" th:text="${err}"/>
                                        </ul>
                                    </span>
 --%>                       </div>
 					</spring:bind>
 
 
 
 							<spring:bind path="source">
                                 <div class="col-md-3 form-group ${status.error ? 'has-error' : ''} ">
                                    <label>Source:</label>
                                    <form:input type="text" class="form-control" path="source"/>
                                    <form:errors path="source"></form:errors>
                                </div>
 							</spring:bind>
     <%--                            <div class="col-md-3 form-group" th:class="${#fields.hasErrors('url')}
                                ? 'col-md-3 form-group has-error' : 'col-md-3 form-group'">
                                
      --%>                     <spring:bind path="url">
      							<div class="col-md-3 form-group ${status.error ? 'has-error' : ''} ">
      								<label>URL:</label>
                                    <form:input type="text" class="form-control" path="url" />
                                    <form:errors path="url"></form:errors>
          <%--                           <span class="help-block" th:if="${#fields.hasErrors('url')}">
                                        <ul>
                                            <li th:each="err : ${#fields.errors('url')}" th:text="${err}"/>
                                        </ul>
                                    </span>
           --%>                       </div>
      							</spring:bind>          
                           </div>
                        </div>
                    </div>
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-md-11 ">
                                    <h1 class="panel-title">Ingredients</h1>
                                </div>
                                 <div class="col-md-1">
                                    <a class="btn btn-default" href="#"
                                    
                                       th:href="${pageContext.servletContext.contextPath}/recipe/${recipe.recipeId}/ingrediants <%--  @{'/recipe/' + ${recipe.id} + '/ingredients'} --%>" role="button">Edit</a>
                                </div>
                        </div>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                 <div class="col-md-12">
                                    <%-- <ul th:if="${not #lists.isEmpty(recipe.ingredients)}">
                                        <li th:remove="all">1 Cup of milk</li>
                                        <li th:remove="all">1 Teaspoon of chocolate</li>
                                        <li th:remove="all">asdf</li>
 --%>                                        
                                       <ul>
                                       <%-- <c:set value="${recipe.ingrediants }" var="ig" ></c:set> --%>
                                       
                                          <%-- <c:forEach items="${ingrediants}" var="ig" varStatus="status" >   --%>
                                        	
                                        	
                                        <li >
                                     		<form:input type="hidden" path="ingrediants[0].id" />    	
                                     		<form:input type="hidden" path="ingrediants[0].uomC.id" />
                                     		
                                     		
                                        	<label>Amount:</label>
                                    		<form:input type="text" class="form-control" path="ingrediants[0].amount" />
                                    		

											<label>Unit Of Measure:</label>
                                    		<form:input type="text" class="form-control" path="ingrediants[0].uomC.unit" />


											<label>Description:</label>
                                    		<form:input type="text" class="form-control" path="ingrediants[0].description" />
                                    		
                                       	<%--  ${ingredients.getAmount() + ' ' + ingredients.getDescription()+ ' ' + ingredients.getUomC() } --%>
                                        </li>
                                        
								<%-- </c:forEach> --%> 
                                        	
                                        
<%--                                      </c:forEach>  --%>
                                        
                                        
                                        
<%--                                         <li th:each="ingredient : ${recipe.ingredients}"
                                            th:text="${(ingredient.getAmmount() +
                                        ' ' + ingredient.uom.getDescription() +
                                        ' - ' + ingredient.getDescription())}">1 Teaspoon of Sugar
                                        </li>
 --%>                                    </ul>
                                </div>
      						</div>
                        </div>
                    </div>
                    
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h1 class="panel-title">Directions</h1>
                        </div>
                        <div class="panel-body  ">
                            <div class="row">
                    <spring:bind path="direction">
                                <div class="col-md-12 form-group ${status.error ? 'has-error' : ''}">
                                <form:textarea path="direction" cssClass="form-control" rows="3"/>
                                <form:errors path="direction"></form:errors>
                                   </div>
                    </spring:bind>
                            </div> 
                        </div>
                    </div>
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h1 class="panel-title">Notes</h1>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12 form-group">
                                
                                <form:input type="hidden" path="notes.id" />
                                
                                
                                    <form:textarea class="form-control" rows="3" path="notes.notes"></form:textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            
            </div>
            
            </form:form>
        </div>
    </div>
</div>
  
 
 
 
 