<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
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
		
		.find-btn {
			margin-top: 15px;
			height: 40px;
		}
</style>
</head>
<body>

	<div class="bg-info d-flex justify-content-between shadow">
		<h2 class="text-light font-weight-light p-3">Seller Details</h2>
		<div class="d-flex">
		<form  action="/product/allproducts">
			<button type="submit" class="btn btn-outline-light m-3">Products</button>
		</form>
		<form  action="category">
			<button type="submit" class="btn btn-outline-light m-3">Categories</button>
		</form>
		<form method="get" action="logout">
		<button type="submit" class="btn btn-outline-light m-3">Logout</button>
	</form>
	</div>
	
	</div>
	
	
	<form action="/seller/search" method="get">
                          <div class="">
                          <div class=" row">
                          	<div class="col-sm-4"></div>
                          	<div class="col-sm-4 mx-5  d-flex">
                          		<input type="text" class="form-control m-3 w-50" placeholder="Enter keywords here" name="keyword">
                          		<input type="submit" class="btn btn-info find-btn" value="Find">
                          	</div>
                          	<div class="col-sm-4"></div>
                          </div>
                                 
                                 <div class="d-flex justify-content-center  mx-2 my-2">
                                         <input type="radio" name="searchBy" value="companyname" class="mb-2"> Company</br>
                                           <input type="radio" name="searchBy" value="ownername" class="mb-2"> Owner Name</br>
                                           <input type="radio" name="searchBy" value="telephone"> Telephone</br>
                                         
                                 </div>
                               
                                 
                          </div>
                          </form>
	
	
<div>
<div class="row">
<div class="col-sm-9">
<form method="get" action="/seller/updatestatusall">
		<table class="table text-sm-center text-info">
			<thead>
				<tr>
					<th>Seller Id</th>
					<th>Seller Name</th>
					<th>Status</th>
					<th>Edit</th>
				</tr>
			</thead>

			<tbody>

			<c:forEach var="seller" items="${sellerList}">
				
<c:choose>
  <c:when test="${(seller.status=='NEED_APPROVAL') or (seller.status=='REVIEW')}">
   <c:set value="show-checkbox" var="cssClass"></c:set>
  </c:when>
  
  <c:otherwise>
 <c:set value="hide-checkbox" var="cssClass"></c:set>  
  </c:otherwise>
</c:choose>

				<tr>
					
					<td>${seller.sellerid}</td>
					<td>${seller.ownername}</td>
					<td>${seller.status}</td>
					
					<td >
					
				<input style="cursor:pointer;" class="edit-btn"  value="Update" 
				onclick="location.href='/seller/details/${seller.sellerid}'"/>
					</td>
					
					<td class="${cssClass}" >
						<input type="checkbox" value="${seller.sellerid}" name="id">
					</td>
					
					</tr>
			</c:forEach>
			   
			</tbody>
			
		</table>
		   <input type="submit" value="Approve Sellers" class="mt-4 btn btn-info w-100">
			
		</form>	
			</div>	
			<div class="col-sm-3 px-5 filters text-info">
                          <div class="text-info">
                                 <form action="/seller/search">
                                       <div class="mb-2 form-label text-sm-center">Sort By</div>
                                       <input type="radio" name="sortBy" value="id"> Seller Id<br>
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