<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Products</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
	
<style>

	.show-checkbox{
display:block;
}
.hide-checkbox{
display:none;
}
.edit-btn {
			background: url('https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Edit_icon_%28the_Noun_Project_30184%29.svg/1024px-Edit_icon_%28the_Noun_Project_30184%29.svg.png');
			height: 20px;
			width: 20px;
			background-size: cover;
			background-repeat: no-repeat;
    			background-position: center;
			margin: auto;
		}
	</style>	
</head>
<body>
<form action="/allproducts/filterproducts">
                          <div class="">
                                 <input type="text" class="form-control m-3" placeholder="Enter keywords here" name="keyword">
                                 <div class="d-flex justify-content-center  mx-2 my-2">
                                         <span class="bg-success text-light p-2 rounded-left">Search by</span>
                                           <input type="radio" name="searchBy" value="sellerid" class="mb-2"> Seller Id</br>
                                           <input type="radio" name="searchBy" value="companyname" class="mb-2"> Company Name</br>
                                           <input type="radio" name="searchBy" value="productid"> Product Id</br>
                                           <input type="radio" name="searchBy" value="productname"> Product name</br>
                                           <input type="radio" name="searchBy" value="sellerproductcode">Seller Product Code</br>
                                        
                                         
                                         
                                 </div>
                               
                                 <input type="submit" class="btn btn-info" value="Find">
                          </div>
                          </form>
<div class="container">
<div class="row">
<div class="col-sm-9">

<form action="changestatus" method="get">
		<table class="table text-sm-center text-info">
			<thead>
				<tr>
					<th>Product Code</th>
					<th>Name</th>
					<th>Status</th>
					<th>Categories</th>
					<th>MRP</th>
					<th>SSP</th>
					<th>YMP</th>
				</tr>
			</thead>

			<tbody>

			<c:forEach var="product" items="${productList}">
				
				<c:choose>
  					<c:when test="${product.status=='NEED_APPROVAL'}">
   						<c:set value="show-checkbox" var="cssClass"></c:set>
  					</c:when>
  
  					<c:otherwise>
 						<c:set value="hide-checkbox" var="cssClass"></c:set>  
  					</c:otherwise>
				</c:choose>
				
				
				<tr>
					
					<td>${product.sellerproductcode}</td>
					<td>${product.productname}</td>
					<td>${product.status}</td>
					<td>
					<c:forEach var="category" items="${product.categories}">
					${category}
					</c:forEach>
					</td>
					<td>${product.mrp}</td>
					<td>${product.ssp}</td>
					<td>${product.ymp}</td>
					
						<td>
				<input style="cursor:pointer;" class="edit-btn"  value="Update" 
				onclick="location.href='productdetails/${product.id}'"/>
					</td>
					
					<td class="${cssClass}" >
						<input type="checkbox" value="${product.id}" name="id">
					</td>
					
					</tr>
			</c:forEach>
			   
			</tbody>
			
		</table>
		 <input type="submit" value="Approve Products" class="mt-4 btn btn-info w-100">
		</form>
		</div>
			<div class="col-sm-3 px-5 filters text-info">
                          <div class="text-info">
                                 <form action="/allproducts/filterproducts">
                                       <div class="mt-4 mb-2 form-label text-sm-center">Sort By</div>
                                       <input type="radio" name="sortBy" value="mrp"> MRP<br>
                                       <input type="radio" name="sortBy" value="ssp"> SSP<br>
                                       <input type="radio" name="sortBy" value="ymp"> YMP<br>
                                       <input type="radio" name="sortBy" value="createdat"> Registration time<br>
                                       <div class="mt-4 mb-2 form-label  text-sm-center">Filter By</div>
                                       <input type="checkbox" name="status" value="NEED_APPROVAL"> Need Approval<br>
                                       <input type="checkbox" name="status" value="APPROVED"> Approved<br>
                                       <input type="checkbox" name="status" value="REJECTED"> Rejected<br>
                                      
                                       
                                       <div class="">
                                              <input type="submit" value="Apply" class="mt-4 btn btn-info w-100">
                                       </div>
                                 </form>
                          </div>
			
			</div>	
		</div>
		</div>
</body>
</html>