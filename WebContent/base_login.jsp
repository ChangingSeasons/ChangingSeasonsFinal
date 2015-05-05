<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@page import="model.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="navbar.jsp"%>

<%
	// Getting last 4 added products
	List<Product> lastFour = ProductDAO.lastFour();
	int count = 0;
%>
<div id="carousel-example-generic" class="carousel slide" style="height:310px; width: x 880px;" align="middle"
		data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
			<li data-target="#carousel-example-generic" data-slide-to="1"></li>
			<li data-target="#carousel-example-generic" data-slide-to="2"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			
				<% for (Product product : lastFour) { %>
			<div class="item <%= count == 0 ? "active" : "" %>">
				<img src="<%=product.getImagePath() %>" alt="<%=product.getImageName() %>" style="height:310px; width:x 880px" align="middle">
			</div>
				<% count++; } %>
	
		</div>

		<!-- Controls -->
		<a class="left carousel-control" href="#carousel-example-generic"
			role="button" data-slide="prev"> <span
			class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span
			class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#carousel-example-generic"
			role="button" data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>

<div class="container">

	<!-- Welcome -->
	<div class="page-header">
		<h1>
			Welcome to <span class="title">Changing Seasons</span> <br /> <small>Please
				login to continue</small>
		</h1>
	</div>


	<% String msg = (String)request.getAttribute("msg");
	if(msg != null) {
		if (msg.equals("You have logged out!")) out.write("<div class=\"alert alert-success\" role=\"alert\">"+msg+"</div>");
		else out.write("<div class=\"alert alert-danger\" role=\"alert\">"+msg+"</div>");
	}
%>

	<form class="form-horizontal" action="/ChangingSeasons/LoginServlet"
		method="post">
		<fieldset>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="username">Username</label>
				<div class="col-md-4">
					<input id="username" name="username" type="text"
						placeholder="username" class="form-control input-md" required="">

				</div>
			</div>

			<!-- Password input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="password">Password</label>
				<div class="col-md-4">
					<input id="password" name="password" type="password"
						placeholder="password" class="form-control input-md">

				</div>
			</div>

			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit"></label>
				<div class="col-md-4">
					<button type="submit" id="submit" name="submit"
						class="btn btn-default">Submit</button>
					<a class="btn btn-link" href="forgot_password.jsp" role="button">Forgot
						Password</a>
				</div>
			</div>



			<hr />
			<!-- Multiple Radios (inline) -->
			<!--  
			
			<div class="form-group">
				<label class="col-md-4 control-label" for="signupradio">Sign
					Up As</label>
				<div class="col-md-4">
					<label class="radio-inline" for="signupradio-0"> <input
						type="radio" name="signupradio" id="signupradio-0" value="Buyer"
						checked="checked"> Buyer
					</label> <label class="radio-inline" for="signupradio-1"> <input
						type="radio" name="signupradio" id="signupradio-1" value="Seller">
						Seller
					</label>

				</div>
			</div>
 -->
			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="signup"></label>
				<div class="col-md-4">
					<button type="button" id="bsignup" name="bsignup"
						onclick="location.href = 'customer_signup.jsp';"
						class="btn btn-primary">Buyer Sign Up</button>
					<button type="button" id="ssignup" name="ssignup"
						onclick="location.href = 'seller_signup.jsp';"
						class="btn btn-primary">Seller Sign Up</button>
				</div>
			</div>

		</fieldset>
	</form>
</div>
<!-- /container -->

<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Latest Products</h3>
		</div>
		<div class="panel-body">
			<div class="row">
			<% for (Product product : lastFour) { %>
				<div class="col-xs-6 col-md-3">
					<a href="guestViewProduct.jsp?productId=<%=product.getProductID() %>" class="thumbnail"> <img src="<%=product.getImagePath() %>"
						alt="<%=product.getImageName() %>">
					</a>
				</div>
				<% } %>

			</div>
		</div>
	</div>
</div>

<%@ include file="footer.jsp"%>
