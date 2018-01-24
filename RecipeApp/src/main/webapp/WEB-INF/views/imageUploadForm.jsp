



<div class="container-fluid" style="margin-top: 20px">



    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="pannel-group">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h1 class="panel-title">Upload a new recipe image</h1>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-5">
                                <form  action="${pageContext.servletContext.contextPath}/recipe/${recipe.recipeId}/image" method="post" enctype="multipart/form-data"">
                                    
                                    <label class="control-label">Select File</label>
                                    <input id="imagefile" name="imagefile" type="file" class="file">
                                    <button type="submit" class="btn btn-primary">Submit</button>
                                
                                
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>