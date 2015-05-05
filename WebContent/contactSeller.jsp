<%@page import="model.AuthDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="navbar.jsp"%>


<div class="container">

<% int userId = Integer.parseInt(request.getParameter("sellerID"));
	User seller = AuthDAO.getUserbyId(userId);

%>
<div class="page-header">
		<h1>
			<span class="title">Contact Seller</span> <br /> 
			<small>Send Email <%=seller.getCompanyName() %></small>
		</h1>
	</div>
<form class="form-horizontal" action="/ChangingSeasons/ContactSellerServlet" method="post">
		<fieldset>
			<div style="float:left; width:100%;">
			
			<!-- Text input-->
			<div class="form-group required">
				<label class="col-md-4 control-label" for="email">Email</label>
				<div class="col-md-4">
					<input id="email" name="email" type="text" value="<%=seller.getEmail() %>"
						class="form-control input-md" required="">
				</div>
			</div>
			
			<!-- Text input -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="title">Title</label>
				<div class="col-md-4">
					<input id="title" name="title" type="text" class="form-control input-md">
				</div>
			</div>
			
			<!-- Text area -->
			<div class="form-group required">
				<label class="col-md-4 control-label" for="message">Message</label>
				<div class="col-md-4">
					<textarea class="form-control" id="message" name="message" required=""></textarea>
				</div>
			</div>

			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit"></label>
				<div class="col-md-4">
					<button type="submit" id="submit" name="submit" class="btn btn-info">Submit</button>
				</div>
			</div>
			</div>
			</fieldset>
	</form>
	</div>

<%@ include file="footer.jsp"%>