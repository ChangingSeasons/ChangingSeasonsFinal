<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="navbar.jsp"%>

<div class="container">
	<% 
		String msg = (String)request.getAttribute("msg");
		if(msg == null) msg="";
		if(!msg.equals("")) out.write("<div class=\"alert alert-danger\" role=\"alert\">"+msg+"</div>");
		
		String logIn = (String) session.getAttribute("loggedIn");
		if(loggedIn != null){
	%>
	
	<form class="form-horizontal" action="/ChangingSeasons/VendorServlet" method="post">
		<fieldset>

			<!-- Form Name -->
			<div class="page-header">
				<h1>
					Seller Sign Up <small>Please enter your details</small>
				</h1>
			</div>
			<!-- Text input-->
			<div class="form-group required">
				<label class="col-md-4 control-label" for="username">Username</label>
				<div class="col-md-4">
					<input id="username" name="username" type="text"
						placeholder="username" class="form-control input-md" required="">
					<input type="submit" value="Check Username Availability" name="check">
				</div>
			</div>

			<!-- Password input-->
			<div class="form-group required">
				<label class="col-md-4 control-label" for="password">Password</label>
				<div class="col-md-4">
					<input id="password" name="password" type="password"
						placeholder="password" class="form-control input-md">

				</div>
			</div>

			<!-- Password input-->
			<div class="form-group required">
				<label class="col-md-4 control-label" for="passwordc">Confirm
					Password</label>
				<div class="col-md-4">
					<input id="passwordc" name="passwordc" type="password"
						placeholder="confirm password" class="form-control input-md"
						required="">
				</div>
			</div>
			
			<!-- Text input-->
			<div class="form-group required">
				<label class="col-md-4 control-label" for="company">Company Name</label>
				<div class="col-md-4">
					<input id="company" name="company" type="text"
						placeholder="Company Name" class="form-control input-md" required="">

				</div>
			</div>
			

			<!-- Text input-->
			<div class="form-group required">
				<label class="col-md-4 control-label" for="firstname">First
					Name</label>
				<div class="col-md-4">
					<input id="firstname" name="firstname" type="text"
						placeholder="First Name" class="form-control input-md" required="">

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="middlename">Middle
					Name</label>
				<div class="col-md-4">
					<input id="middlename" name="middlename" type="text"
						placeholder="Middle Name" class="form-control input-md">

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group required">
				<label class="col-md-4 control-label" for="lastname">Last
					Name</label>
				<div class="col-md-4">
					<input id="lastname" name="lastname" type="text"
						placeholder="Last Name" class="form-control input-md">

				</div>
			</div>

			<!-- Textarea -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="address">Address</label>
				<div class="col-md-4">
					<textarea class="form-control" id="address" name="address"></textarea>
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="phone">Phone
					Number</label>
				<div class="col-md-4">
					<input id="phone" name="phone" type="text"
						placeholder="Phone Number" class="form-control input-md">

				</div>
			</div>
			
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="accoutno">Bank Account Number</label>
				<div class="col-md-4">
					<input id="accoutno" name="accoutno" type="text"
						placeholder="Bank Account Number" class="form-control input-md">

				</div>
			</div>
			
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="routingno">Routing Number</label>
				<div class="col-md-4">
					<input id="routingno" name="routingno" type="text"
						placeholder="Routing Number" class="form-control input-md">

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="paypal">Paypal ID</label>
				<div class="col-md-4">
					<input id="paypal" name="paypal" type="text" placeholder="Paypal"
						class="form-control input-md"> <span class="help-block">Your
						Paypal ID can be used for directly recieving payments</span>
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

			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit"></label>
				<div class="col-md-4">
					<button type="submit" id="submit" name="submit" class="btn btn-info">Submit</button>
				</div>
			</div>


		</fieldset>
	</form>
	<% }
	
	if(logIn!=null)
		out.write("<h3>You are already LoggedIn!</h3>");%>

</div>
<!-- /container -->

<%@ include file="footer.jsp"%>