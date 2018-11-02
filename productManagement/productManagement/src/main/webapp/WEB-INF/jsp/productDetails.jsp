<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Details</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
	<style>
	.show-button{
display:block;
margin:5px;
}
.hide-button{
display:none;
margin:5px;
}



	</style>
</head>
<body>

<c:choose>
  <c:when test="${product.status=='APPROVED'}">
   <c:set value="show-button" var="cssClassApproved"></c:set>
   <c:set value="hide-button" var="cssClassRejected"></c:set>
  </c:when>
  
  <c:when test="${product.status=='REJECTED'}">
   <c:set value="hide-button" var="cssClassApproved"></c:set>
   <c:set value="show-button" var="cssClassRejected"></c:set>
  </c:when>
  
  <c:otherwise>
 <c:set value="show-button" var="cssClassApproved"></c:set>  
 <c:set value="show-button" var="cssClassRejected"></c:set>  
  </c:otherwise>
</c:choose>


<div class="container">
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4 d-flex justify-content-between">
				<div class="my-5 w-100">
	
	
			<div class="form-group">
					<label for="empCode">Seller Product Code</label>
				<input class="form-control mr-1" readonly
					placeholder="Product Code" required  value="${product.sellerproductcode}"/>
				</div>
				<br>
				
				
			<div class="form-group">
					<label for="empCode">Product Name</label>
				<input class="form-control mr-1" readonly
					placeholder="Product name" required  value="${product.productname}"/>
				</div>
				<br>
				
				
			<div class="form-group">
					<label for="empCode">Short Discription</label>
				<input class="form-control mr-1" readonly
					placeholder="Short Discription" required  value="${product.shortdiscription}"/>
				</div>
				<br>
				
				
			<div class="form-group">
					<label for="empCode">Dimensions</label>
				<input class="form-control mr-1" readonly
					placeholder="Dimension" required  value="${product.dimensions}"/>
				</div>
				<br>
				
			<div class="form-group">
					<label for="empCode">Long Discription</label>
				<input class="form-control mr-1" readonly
					placeholder="Long Discription" required  value="${product.longdiscription}"/>
				</div>
				<br>
				
				
			<div class="form-group">
					<label for="empCode">MRP</label>
				<input class="form-control mr-1" readonly
					placeholder="MRP" required  value="${product.mrp}"/>
				</div>
				<br>
				
			<div class="form-group">
					<label for="empCode">SSP</label>
				<input class="form-control mr-1" readonly
					placeholder="SSP" required  value="${product.ssp}"/>
				</div>
				<br>
				
				
			<div class="form-group">
					<label for="empCode">YRP</label>
				<input class="form-control mr-1" readonly
					placeholder="YMP" required  value="${product.ymp}"/>
				</div>
				<br>
				
				
				
			<div class="form-group">
					<label for="empCode">Seller Id</label>
				<input class="form-control mr-1" readonly
					placeholder="SELLERID" required  value="${product.sellerId}"/>
				</div>
				<br>
			</div>	
		</div>
	</div>
</div>

						<div class="d-flex justify-content-center">

	<form class="form-group ${cssClassRejected}"  method="post" action="updateproduct">
			<input class="btn btn-light text-info" type="submit" value="Approve" onclick='saveProduct(${product.id},"APPROVED")' />
</form>

	<form class="form-group ${cssClassApproved}" method="post" action="updateproduct">
		<input class="form-control" type="text" required placeholder="Comments" name="comments">
			<input class="btn btn-light text-info" type="submit" value="Reject" onclick='saveProduct(${product.id},"REJECTED")' />
</form>
</div>

<script>
            function saveProduct(productId,status) {
              document.cookie = "productId="+productId;
              document.cookie = "productstatus="+status;
          }
</script>
</body>
</html>