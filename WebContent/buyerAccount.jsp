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
					<span class="title">User Profile</span>
				</h1>
			</div>
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="username" style="font-weight: bold">Username:
				</label> <%=user.getUsername()%>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="firstname" style="font-weight: bold">First Name:
				</label> <%=user.getFirstname()%>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="middlename" style="font-weight: bold">Middle Name:
				</label> <%=user.getMiddlename()%>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="lastname" style="font-weight: bold">Last Name:
				</label> <%=user.getLastname()%>
			</div>

			<!-- Text area -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="address" style="font-weight: bold">Address:
				</label> <%=user.getAddress()%>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="phone" style="font-weight: bold">Phone Number:
				</label> <%=user.getPhone()+""%>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="paypal" style="font-weight: bold">Paypal ID:
				</label> <%=user.getPayPalID() != null ? user.getPayPalID() : "Not Entered"%>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="email" style="font-weight: bold">Email:
				</label> <%=user.getEmail()%>
			</div>

			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit"></label>
				<div class="col-md-4">
					<button type="button" id="submit" name="submit"
						onclick="location.href = 'editBuyer.jsp';"
						class="btn btn-primary">Edit my profile</button>
				</div>
			</div>


		</fieldset>
	</form>
</div>
<!-- /container -->

<%@ include file="footer.jsp"%>