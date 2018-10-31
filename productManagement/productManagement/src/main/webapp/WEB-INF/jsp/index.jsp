<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<style>
body {
	background-image:
		url("https://www.backgroundsy.com/file/large/digital-waves-background.jpg");
	background-repeat: no-repeat;
	background-size: cover;
	background-position: center;
	background-attachment: fixed;
	height: 100%;
}
</style>
</head>
<body>

<div class="container row">
            <div class="col-sm-8"></div>
            <div class="col-sm-4 h-100 d-flex">
                  <div class="align-self-center">
                        <h3 class="text-info font-weight-light mb-4">Login</h3>
     	
   <form class="form-group" method="post" action="admin">    
          <input class="form-control" path="username" required placeholder="username"/><br>  
          <input class="form-control" path="password" type required placeholder="password"/><br>  
          <input class="btn btn-info" type="submit" value="Login" />    
       </form>

        <hr/>

                        <a href="forgotPassword">Forget Password</a>
                  </div>
            </div>
      </div>

</body>
</html>


