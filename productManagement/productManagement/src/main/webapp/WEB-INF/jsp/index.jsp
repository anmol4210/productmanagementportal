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

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

     <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>

<div class="container row">
            <div class="col-sm-8"></div>
            <div class="col-sm-4 h-100 d-flex">
                  <div class="align-self-center">
                        <h3 class="text-info font-weight-light mb-4">Login</h3>
     	
   <form id="form" class="form-group" method="post" action="admin">    
          <input class="form-control"  name="username" required placeholder="username"/><br>  
          <input class="form-control"  name="password" type required placeholder="password"/><br>  
          <input class="btn btn-info" type="submit" value="Login" />    
      <br><br>
            <div class="g-recaptcha" data-sitekey="6Lf-KngUAAAAAOsJAmh9pT7R3o3cz4z10fQTARzu"></div>
      <br/>
      
       </form>

<%
String isValid=""+session.getAttribute("isValid");
if(!isValid.equalsIgnoreCase("null") && isValid.equalsIgnoreCase("false")){
	session.setAttribute("isValid","true");
	out.print("<font color=red>Username or password is Incorrect</font><br/>");
}
%>
        <hr/>

                        <a href="forgotPassword">Forget Password</a>
                  </div>
            </div>
      </div>
<script>
$('#form').on('submit', function(e) {
	  if(grecaptcha.getResponse() == "") {
	    e.preventDefault();
	    return false
	  } else {
	    return true
	  }
	});
</script>
</body>
</html>


