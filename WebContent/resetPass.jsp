<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="navbar.jsp"%>

<div class="container">

	<!-- Welcome -->
	<div class="page-header">
		<h1>
			Reset Password <br /> 
			<small> Enter your new password </small>
		</h1>
	</div>
	
	<%
		String username = (String)request.getAttribute("username");
    	String msg = (String) request.getAttribute("msg");
		String password = (String) request.getAttribute("password");
		
    	if (msg == null)
    		msg = "";
 
    	if (password != null && msg.startsWith("User")){
    		password = "";
    		if(!msg.equals("")) out.write("<div class=\"alert alert-danger\" role=\"alert\">"+msg+"</div>");
 %>
	<form class="form-horizontal" action="/ChangingSeasons/ResetPassword" method="post">
	<fieldset>
			<!-- Password input-->
			<div class="form-group required">
				<label class="col-md-4 control-label" for="password">Password</label>
				<div class="col-md-4">
					<input id="password" name="password" type="password"
						placeholder="password" class="form-control input-md">

				</div>
			</div>
			
			
			<input type="hidden" name="hidden-username" value=<%out.write(username);%>>
			

			<!-- Password input-->
			<div class="form-group required">
				<label class="col-md-4 control-label" for="cpassword">Confirm
					Password</label>
				<div class="col-md-4">
					<input id="cpassword" name="cpassword" type="password"
						placeholder="confirm password" class="form-control input-md"
						required="">
				</div>
			</div>
			
			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit"></label>
				<div class="col-md-4">
					<button type="submit" id="submit" name="submit" class="btn btn-info">Reset</button>
				</div>
			</div>
	</fieldset>
	</form>
	
	<%
			}
		    		else if (!msg.startsWith("User")) {
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