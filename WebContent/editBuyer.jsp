<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="navbar.jsp"%>

<div class="container">
	<form class="form-horizontal" action="/ChangingSeasons/UpdateServlet" method="post">
		<fieldset>

			<!-- Form Name -->
			<div class="page-header">
				<h1>
					<span class="title">Update Profile</span>
				</h1>
			</div>
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="username">Username: <% out.write(user.getUsername());%></label>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="firstname">First Name: </label>
				<div class="col-md-4">
					<input id="firstname" name="firstname" type="text" class="form-control input-md" 
					value="<% out.write(user.getFirstname());%>">

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="middlename">Middle Name: </label>
				<div class="col-md-4">
					<input id="middlename" name="middlename" type="text" class="form-control input-md" 
					value=" <% out.write(user.getMiddlename()); %>">

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="lastname">Last Name: </label>
				<div class="col-md-4">
					<input id="lastname" name="lastname" type="text" class="form-control input-md" 
					value="<% out.write(user.getLastname()); %>">

				</div>
			</div>

			<!-- Text area -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="address">Address: </label>
				<div class="col-md-4">
					<textarea class="form-control" id="address" name="address"> 
					<% out.write(user.getAddress()); %></textarea>
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="phone">Phone Number: </label>
				<div class="col-md-4">
					<input id="phone" name="phone" type="text" class="form-control input-md" 
					value="<% out.write(user.getPhone()+""); %>">

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="paypal">Paypal ID: </label>
				<div class="col-md-4">
					<input id="paypal" name="paypal" type="text" class="form-control input-md" 
					value="<% out.write(user.getPayPalID() != null ? user.getPayPalID() : "Not Entered"); %>">
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="email">Email: </label>
				<div class="col-md-4">
					<input id="email" name="email" type="text" class="form-control input-md" 
					value="<% out.write(user.getEmail()); %>">

				</div>
			</div>

			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit"></label>
				<div class="col-md-4">
					<button type="submit" id="submit" name="submit"
						class="btn btn-default">Update</button>
				</div>
			</div>



		</fieldset>
	</form>
</div>
<!-- /container -->

<%@ include file="footer.jsp"%>