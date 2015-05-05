<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="navbar.jsp"%>

<div class="container">

<ul class="breadcrumb">
  <li><a href="shoppingCart.jsp">Shopping Cart</a></li>
  <li><a href="checkout.jsp">Shipping Address</a></li>
  <li class="active">Cart Details</li>
</ul>

	<% 
		String msg = (String)request.getAttribute("msg");
		if(msg == null) msg="";
		if(!msg.equals("")) out.write("<div class=\"alert alert-danger\" role=\"alert\">"+msg+"</div>");
	%>
	<form class="form-horizontal" action="/ChangingSeasons/CardDetailsServlet" method="get">
		<fieldset>

			<!-- Form Name -->
			<div class="page-header">
				<h1>
					<span class="title">Card Details</span></br>
					<small>Select a payment method</small>
				</h1>
			</div>
			
			<h3>PayPal Account &nbsp&nbsp<img src="imgs/Paypal.jpg" alt="" width="100" height="30"></h3>
			
			<div class="form-group">
				<label class="col-md-4 control-label" name ="paypal" for="paypal">Paypal ID: 
				</label> <p class="form-control-static"> <%=user.getPayPalID() != null ? user.getPayPalID() : "Not Entered"%>  </p>
			</div>
			
			<!--  Hidden input for userId -->
			<input type="hidden" name = "user" id="user" value="<%=user.getID()%>" />
			
			<!--  Hidden input for userId -->
			<input type="hidden" name = "hidden-paypal" id="user" value="<%=user.getPayPalID() != null ? user.getPayPalID() : "Not Entered"%>" />
			
			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit"></label>
				<div class="col-md-4">
					<button type="submit" id="submit" name="submit" 
					class="btn btn-primary">Confirm Payment</button>
				</div>
			</div>
			</fieldset>
			</form>
			<hr>
			<form class="form-horizontal" action="/ChangingSeasons/CardDetailsServlet" method="post">
		<fieldset>
			<h3> Enter your card details &nbsp&nbsp<img src="imgs/Cards.jpg" alt="" width="250" height="30"></h3>
			
			<!-- Text input-->
			<div class="form-group required">
				<label class="col-md-4 control-label" for="cardno">Card Number</label>
				<div class="col-md-4">
					<input id="cardno" name="cardno" type="text"
						placeholder="0000111166660000" class="form-control input-md" required="required">
				</div>
			</div>
			
			<!-- Month and Year input-->
			<div class="form-group required" >
			<label class="col-md-4 control-label" for="selectDate">Expiry Date</label>
			<div class="col-md-4">
	                        <select id="selectMonth" class="form-control selectWidth">
	                            <option class="">Jan</option>
								<option class="">Feb</option>
								<option class="">Mar</option>
								<option class="">Apr</option>
								<option class="">May</option>
								<option class="">Jun</option>
								<option class="">Jul</option>
								<option class="">Aug</option>
								<option class="">Sep</option>
								<option class="">Oct</option>
								<option class="">Nov</option>
								<option class="">Dec</option>
	                        </select>
	                        <br/>
                      	    <select id="selectYear" class="form-control selectWidth">
                            <option class="">2015</option>
                            <option class="">2016</option>
                            <option class="">2017</option>
                            <option class="">2018</option>
                            <option class="">2019</option>
                            <option class="">2020</option>
                            <option class="">2021</option>
                            <option class="">2022</option>
                            <option class="">2023</option>
                            <option class="">2024</option>
                            <option class="">2025</option>
                        </select>
                        </div>
                    </div>

			<!-- text input-->
			<div class="form-group required">
				<label class="col-md-4 control-label" for="cvv">CVV</label>
				<div class="col-md-4">
					<input id="cvv" name="cvv" type="text"
						placeholder="123" class="form-control input-md">

				</div>
			</div>

			<!-- Text input-->
			<div class="form-group required">
				<label class="col-md-4 control-label" for="cardholder">Card Holder</label>
				<div class="col-md-4">
					<input id="cardholder" name="cardholder" type="text"
						placeholder="John Doe" class="form-control input-md" required="">

				</div>
			</div>
			
			<!--  Hidden input for userId -->
			<input type="hidden" name = "user" id="user" value="<%=user.getID()%>" />
			
			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit"></label>
				<div class="col-md-4">
					<button type="submit" id="submit" name="submit" 
					class="btn btn-primary">Confirm Payment</button>
				</div>
			</div>


		</fieldset>
	</form>

</div>
<!-- /container -->

<%@ include file="footer.jsp"%>
