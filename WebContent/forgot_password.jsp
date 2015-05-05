<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="navbar.jsp"%>

<div class="container">

	<!-- Welcome -->
	<div class="page-header">
		<h1>
			Forgot your password? <br /> 
			<small> No problem! we will help you reset </small>
		</h1>
	</div>
	
	<%
    	String msg = (String) request.getAttribute("msg");
		String password = (String) request.getAttribute("password");
		
    	if (msg == null)
    		msg = "";
 
    	if (password == null && !msg.startsWith("User")){
    		password = "";
    		if(!msg.equals("")) out.write("<div class=\"alert alert-danger\" role=\"alert\">"+msg+"</div>");
 %>
	<form class="form-horizontal" action="/ChangingSeasons/ForgotPassServlet" method="post">
	<fieldset>
			<!-- Text input-->
			<div class="form-group required">
				<label class="col-md-4 control-label" for="username">Username</label>
				<div class="col-md-4">
					<input id="username" name="username" type="text"
						placeholder="username" class="form-control input-md" required="required">

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group required">
				<label class="col-md-4 control-label" for="email">Email</label>
				<div class="col-md-4">
					<input id="email" name="email" type="text" placeholder="Email"
						class="form-control input-md" required="">

				</div>
			</div>
			
			<!-- Text input-->
			<div class="form-group required">
				<label class="col-md-4 control-label" for="phone">Phone Number</label>
				<div class="col-md-4">
					<input id="phone" name="phone" type="text"
						placeholder="Phone Number" class="form-control input-md" required="required">

				</div>
			</div>

			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit"></label>
				<div class="col-md-4">
					<button type="submit" id="submit" name="submit" class="btn btn-info">Submit</button>
				</div>
			</div>
	</fieldset>
	</form>
	
	<%
			}
		    		else if (msg.startsWith("User")) {
				System.out.print("User recieved" + msg);

				out.write("<div class=\"alert alert-info\" role=\"alert\">"
						+ msg + "</div>");
				out.write("<div class=\"alert alert-warning\" role=\"alert\">"
						+ password + "</div>");
				
				out.write("<a class=\"btn btn-default\" href=\"base_login.jsp\" role=\"button\">Log In</a>");
				
			}
    	%>
    	
    	
		
	
</div>
<!-- /container -->



<%@ include file="footer.jsp"%>