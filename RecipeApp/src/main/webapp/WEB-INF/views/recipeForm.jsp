<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!--/*@thymesVar id="recipe" type="guru.springframework.domain.Recipe"*/-->


<div class="container-fluid" style="margin-top: 20px">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
        
        <spring:url value="/recipe" var="recipeUrl"/>
            <form:form  action="${recipeUrl}"  modelAttribute="recipe" method="post">
 
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
 
 							<div class="col-md-3 form-group">
 									<label for="${recipe.description}">Recipe Description:</label>
                                    <input type="text" class="form-control" name="description" errorclass="has-error"/>
          <%--                           <span class="help-block" th:if="${#fields.hasErrors('description')}">
                                        <ul>
                                            <li th:each="err : ${#fields.errors('description')}" th:text="${err}"/>
                                        </ul>
                                    </span>
                                </div>
           --%>                  </div>
                            <div class="row">
                                <div class="col-md-3 form-group">
                                    <label>Categories:</label>
                                </div>
                                <div class="col-md-3 form-group">
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
                            </div>
                            <div class="row">
<%--                                 <div class="col-md-3 form-group" th:class="${#fields.hasErrors('prepTime')}
                                ? 'col-md-3 form-group has-error' : 'col-md-3 form-group'">
                                
 --%>                                    
 	
 							<div class="col-md-3 form-group">
 									<label>Prep Time:</label>
                                    <input type="text" class="form-control" name="prepTime" errorclass="has-error"/>
     <%--                                <span class="help-block" th:if="${#fields.hasErrors('prepTime')}">
                                        <ul>
                                            <li th:each="err : ${#fields.errors('prepTime')}" th:text="${err}"/>
                                        </ul>
                                    </span>
      --%>                  </div>
          <%--                       <div class="col-md-3 form-group" th:class="${#fields.hasErrors('cookTime')}
                                ? 'col-md-3 form-group has-error' : 'col-md-3 form-group'">
           --%>                          
           
           						<div class="col-md-3 form-group">
           						<label>Cooktime:</label>
                                    <input type="text" class="form-control" name="cookTime" errorclass="has-error"/>
               <%--                      <span class="help-block" th:if="${#fields.hasErrors('cookTime')}">
                                        <ul>
                                            <li th:each="err : ${#fields.errors('cookTime')}" th:text="${err}"/>
                                        </ul>
                                    </span>
                --%>           </div> 



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
 
 
 							<div class="col-md-3 form-group">
 								<label>Servings:</label>
                                    <input type="text" class="form-control" name="servings" th:errorclass="has-error"/>
<%--                                     <span class="help-block" th:if="${#fields.hasErrors('servings')}">
                                        <ul>
                                            <li th:each="err : ${#fields.errors('servings')}" th:text="${err}"/>
                                        </ul>
                                    </span>
 --%>                       </div>
 
 
 
                                 <div class="col-md-3 form-group">
                                    <label>Source:</label>
                                    <input type="text" class="form-control" name="source"/>
                                </div>
     <%--                            <div class="col-md-3 form-group" th:class="${#fields.hasErrors('url')}
                                ? 'col-md-3 form-group has-error' : 'col-md-3 form-group'">
                                
      --%>                               
      							<div class="col-md-3 form-group">
      								<label>URL:</label>
                                    <input type="text" class="form-control" name="url" />
          <%--                           <span class="help-block" th:if="${#fields.hasErrors('url')}">
                                        <ul>
                                            <li th:each="err : ${#fields.errors('url')}" th:text="${err}"/>
                                        </ul>
                                    </span>
           --%>                       </div>
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
                                       th:href="${pageContext.servletContext.contextPath}/recipe/${recipe.recipeId}/ingrediants <%--  @{'/recipe/' + ${recipe.id} + '/ingredients'} --%>" role="button">View</a>
                                </div>
                        </div>
                        </div>
                        <div class="panel-body">
                            <div class="row">
     <%--                            <div class="col-md-12">
                                    <ul th:if="${not #lists.isEmpty(recipe.ingredients)}">
                                        <li th:remove="all">1 Cup of milk</li>
                                        <li th:remove="all">1 Teaspoon of chocolate</li>
                                        <li th:remove="all">asdf</li>
                                        <li th:each="ingredient : ${recipe.ingredients}"
                                            th:text="${(ingredient.getAmount() +
                                        ' ' + ingredient.uom.getDescription() +
                                        ' - ' + ingredient.getDescription())}">1 Teaspoon of Sugar
                                        </li>
                                    </ul>
                                </div>
      --%>                       </div>
                        </div>
                    </div>
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h1 class="panel-title">Directions</h1>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12 form-group">
                                <form:textarea path="direction" cssClass="form-control" rows="3"/>
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
                                <div class="col-md-12 form-group">
                                    <textarea class="form-control" rows="3" name="notes.notes"></textarea>
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
  
 
 
 
 