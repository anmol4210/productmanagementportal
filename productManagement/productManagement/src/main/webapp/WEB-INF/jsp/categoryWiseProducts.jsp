<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
      
<html>
<head>
       <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.2/css/all.css" integrity="sha384-/rXc/GQVaYpyDdyxK+ecHPVYJSN9bmVFBvjA/9eOB+pb3F2w2N6fc5qB9Ew5yIns" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Mali" rel="stylesheet">
		
       <style>
       
             .title {
                    font-size: 1.8rem;
             }
             
             .welcome {
                    font-size: 1.3rem;
             }
             
             .list-title {
                    font-size: 2rem;
             }
             
             .form-label {
                    
             }
             
             .small-label {
                    font-size: 0.75rem;
             }
             
             .filters {
                    margin-top: 75px;
             }
             
             .seller-list-item {
                    border: 1px solid #EAECEE;
                    box-shadow: 1px 1px #EAECEE;
                    border-radius: 10px;
                    padding: 15px;
             }
             
             .dropbtn {
			    background-color: #4CAF50;
			    color: white;
			    padding: 16px;
			    font-size: 16px;
			    border: none;
			}
			
			.dropdown {
			    position: relative;
			    display: inline-block;
			}
			
			.dropdown-content {
			    display: none;
			    position: absolute;
			    background-color: #f1f1f1;
			    min-width: 160px;
			    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
			    z-index: 1;
			}
			
			.dropdown-content a {
			    color: black;
			    padding: 12px 16px;
			    text-decoration: none;
			    display: block;
			}
			
			.dropdown-content a:hover {background-color: #ddd;}
			
			.dropdown:hover .dropdown-content {display: block;}
			
			.dropdown:hover .dropbtn {background-color: #3e8e41;}
		     .card {
				 width:300px;
					}
					.checkbox{
					width:10px;}
					 .submit-btn {
			    background: none;
			    border: none;
			    color: #ffffff;
			    cursor: pointer;
			}
			
			.image {
				max-height: 200px;
			}
			.fontawesome{
					font-family: 'Mali', cursive;
					}
       
       
       </style>
</head>
<body class="bg-light">
	<div class="bg-info d-flex justify-content-between p-3 text-light">
    	<span class="font-weight-light title">YourMart</span>
       
		<div class="d-flex">
        <span class="mx-2">
        	<form action="/home">
            	<input  class="submit-btn" type="submit" value="Seller Page">
            </form>
        </span>
        <span class="ml-2 mr-3">
             		<form action="category">
             			<input class="submit-btn" type="submit" value="Category Page">
             		</form>
             	</span>
       
	</div>
	</div>
    <div class="container py-4">
 		<div class="row">
        	
            <div class="col-sm-12">
            <c:if test="${show}">
            	<div class="list-title font-weight-light">Products</div>
            	</c:if>
                <div>
                	
                		<div class="row d-flex justify-content-center">
	                    	
	                    	<c:if test="${!show}">
	                    	
	                    	<div class="d-flex flex-column ">	      
	                    		<h2 style="color: red" class="fontawesome">No Products of this Category</h2>
	                    	
	                    
	                    	<form action="delete/${category}" class="my-4 d-flex justify-content-center">
	                    	<input type="submit" class="btn btn-danger" value="Delete ${category } Category?">
	                    	</form>
	                    	
	                    	</div>
	                    	
	                    	</c:if>
	                    	<c:if test="${show}">
	                    	<c:forEach var="product" items="${productList}">
	                        	<div class="col-sm-3 m-3">
	                            	<div class="card">
	                            		<a href="productprofile/${product.product.id}">
								  			<img class="card-img-top image"  src="${product.product.primaryimage}" alt="Card image cap">
								  			<h4 class="px-3">"${product.product.shortdiscription }"</h4> 
								   		</a>
								   		<div class="card-body">
										    <h6 class="text-secondary"><c:out value="${product.product.sellerproductcode}" /></h6>
										    <h5 class="text-secondary">MRP<c:out value="${product.product.mrp}" /></h5>
										    <h6 class="text-secondary"><c:out value="${product.product.shortdiscription}" /></h6>
											<div>
		                                        
	                                        </div>
									  	</div>
									</div>
								</div>
							</c:forEach>
							</c:if>
						</div>
						
				
				</div>
			</div>
		</div> 
	</div>
</body>
</html>