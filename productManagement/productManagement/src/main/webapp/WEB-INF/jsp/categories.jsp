<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">
<style>
.title {
	font-size: 1.8rem;
}
.welcome {
	font-size: 1.3rem;
}
.submit-btn {
	background: none;
	border: none;
	color: #ffffff;
	cursor: pointer;
}
			.card {
				 width:200px;
					}
					.fontawesome{
					font-family: 'Pacifico', cursive;
					}
					.image {
				max-height: 200px;
			}
</style>
</head>
<body>
	<div class="bg-info d-flex justify-content-between p-3 text-light">

		<span class="font-weight-light title">YourMart</span>
		<div class="d-flex justify-content-center">
			<form action="create"  class="form-inline">
			<input type="text" class="form-control mr-2" name="name" placeholder="Enter new Category">
			<input type="submit" class="btn btn-outline-light" value="add">
			</form>
			</div>
		<div class="d-flex">
			<span class="mx-2">
				<form action="/home">
					<input class="submit-btn" type="submit" value="Sellers">
				</form>
			</span> <span class="mx-2">
				<form action="/product/allproducts">
					<input class="submit-btn" type="submit" value="Products">
				</form>
			</span> 
		</div>
	</div>
	
		<div>
			<c:if test="${categoryList.size()==0}">
				<h2>No Categories Found</h2>
			</c:if>
			<c:if test="${categoryList.size()!=0}">
				<div class="row d-flex justify-content-center">
				<c:forEach var="category" items="${categoryList}">
					<div class="card col-sm-3 mx-2 my-2 p-2">
											<a href="category/${category}">
						
						<img class="card-img-top" src="https://i.pinimg.com/originals/10/5d/20/105d204d6b529d179849425f3004f7bb.png"
							alt="Card image cap" height=300 width=100>
						<div class="card-body">
					
						<h2 class="text-sm-center fontawesome">${category}</h2></div>
				</a>
					</div>
					
				</c:forEach>
				</div>
			</c:if>
			
		</div>
	









</body>
</html>