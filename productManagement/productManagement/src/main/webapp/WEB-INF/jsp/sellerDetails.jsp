<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
	<title>Employee Management</title>
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
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.0.0.js"></script>
<script type="text/javascript">
                $(document).ready(
                          function(){
                        	  var today = new Date().toISOString().split('T')[0];
                              $('#datePicker').attr('max',today);
                              
                              });
                </script>
</head>
<body>





	
	<div class="bg-info d-flex justify-content-between shadow">
		<h2 class="text-light font-weight-light p-3">Seller Details</h2>
		<form method="get" action="logout">
		<button type="submit" class="btn btn-outline-light m-3">Logout</button>
	</form>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4 d-flex justify-content-between">
				<div class="my-5 w-100">
	
	
				<div class="form-group">
					<label for="empCode">Seller id</label>
				<input class="form-control mr-1" readonly
					placeholder="Employee Code" required  value="${sellerdetails.sellerid}"/>
				</div>
				<br>
				

				<div class="form-group">
					<label for="empName">Seller Name</label>
				<input class="form-control mr-1"   maxlength="100"
					placeholder="Employee Name" required value="${sellerdetails.ownername}"/>
				</div>
				<br>
				<div class="form-group">
					<label for="empName">Company Name</label>
				<input class="form-control mr-1"   maxlength="100"
					placeholder="Employee Name" required value="${sellerdetails.companyname}"/>
				</div>
				<br>
				<div class="form-group">
					  	<label for="empLocation">Seller Location</label>  
				<input class="form-control mr-1"   maxlength="500"
					placeholder="Employee Location" required value="${sellerdetails.address}"/>
				</div>
				<br>
				<div class="form-group">
					<label for="empEmail">Seller Email</label>	    
				<input class="form-control mr-1"  id="empEmail"
					placeholder="Employee Email" required value="${sellerdetails.email}"/>
				</div>			
				<br>
				
					<div class="form-group">
					<label for="empEmail">Telephone</label>	    
				<input class="form-control mr-1"  id="empEmail"
					placeholder="Employee Email" required value="${sellerdetails.telephone}"/>
				</div>			
				<br>
				<div class="form-group">
				<label for="empEmail">GST Number</label>	    
				<input class="form-control mr-1"  id="empEmail"
					placeholder="Employee Email" required value="${sellerdetails.gst}"/>
				</div>			
				<br>
				
					
				<br>
		
	<c:choose>
  <c:when test="${sellerdetails.status=='APPROVED'}">
   <c:set value="show-button" var="cssClassApproved"></c:set>
   <c:set value="hide-button" var="cssClassRejected"></c:set>
  </c:when>
  
  <c:when test="${sellerdetails.status=='REJECTED'}">
   <c:set value="hide-button" var="cssClassApproved"></c:set>
   <c:set value="show-button" var="cssClassRejected"></c:set>
  </c:when>
  
  <c:otherwise>
 <c:set value="show-button" var="cssClassApproved"></c:set>  
 <c:set value="show-button" var="cssClassRejected"></c:set>  
  </c:otherwise>
</c:choose>
	
<div class="d-flex">

	<form class="form-group ${cssClassRejected}"  method="post" action="update">
			<input class="btn btn-light text-info" type="submit" value="Approve" onclick='saveSeller(${sellerdetails.sellerid},"APPROVED")' />
</form>

	<form class="form-group ${cssClassApproved}" method="post" action="update">
		
			<input class="btn btn-light text-info" type="submit" value="Reject" onclick='saveSeller(${sellerdetails.sellerid},"REJECTED")' />
</form>
</div>

				</div>
			</div>
			<div class="col-sm-4"></div>
		</div>
	
	</div>
	<form class="form-group d-flex justify-content-center" method="get" action="products">
	<button type="submit" class="btn btn-info" onclick='saveSeller(${sellerdetails.sellerid},"")'>Get All Products</button>
	</form>

<script>
            function saveSeller(sellerId,status) {
              document.cookie = "sellerId="+sellerId;
              document.cookie = "status="+status;
          }
</script>


</body>
</html>
