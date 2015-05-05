<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="navbar.jsp"%>

<div class="container">
	<form class="form-horizontal" action="/ChangingSeasons/MyAccount" method="post">
		<fieldset>

			<!-- Form Name -->
			<div class="page-header">
				<h1>
					<span class="title">Seller Profile</span>
				</h1>
			</div>
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="username" style="font-weight: bold">Username: 
				</label> <% out.write(user.getUsername()); %>
			</div>
			
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="company" style="font-weight: bold">Company Name:
				</label> <% out.write(user.getCompanyName()); %>
			</div>
			
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="firstname" style="font-weight: bold">First Name:
				</label> <% out.write(user.getFirstname()); %>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="middlename" style="font-weight: bold">Middle Name:
				</label> <% out.write(user.getMiddlename()); %>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="lastname" style="font-weight: bold">Last Name:
				</label> <% out.write(user.getLastname()); %>
			</div>

			<!-- Text area -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="address" style="font-weight: bold">Address:
				</label> <% out.write(user.getAddress()); %>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="phone" style="font-weight: bold">Phone Number:
				</label> <% out.write(user.getPhone()+""); %>
			</div>
			
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="accoutno" style="font-weight: bold">Bank Account Number:
				</label> <% out.write(user.getBankAccount()); %>
			</div>
			
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="routingno" style="font-weight: bold">Routing Number:
				</label> <% out.write(user.getRoutingNumber()); %>
			</div>
			

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="paypal" style="font-weight: bold">Paypal ID:
				</label> <% out.write(user.getPayPalID() != null ? user.getPayPalID() : "Not Entered"); %>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="email" style="font-weight: bold">Email:
				</label> <% out.write(user.getEmail()); %>
			</div>

			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit"></label>
				<div class="col-md-4">
					<button type="button" id="submit" name="submit"
						onclick="location.href = 'editSeller.jsp';"
						class="btn btn-primary">Edit my profile</button>
				</div>
			</div>


		</fieldset>
	</form>

</div>
<!-- /container -->

<%@ include file="footer.jsp"%>