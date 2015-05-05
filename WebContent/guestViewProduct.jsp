<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@page import="model.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="navbar.jsp"%>

<%
	if (request.getParameter("productId") != null) {
		int productId = Integer.parseInt(request
				.getParameter("productId"));
		Product product = ProductDAO.viewProduct(productId);
%>

<div class="container">
	<p class="text-center">
	<h1><%=product.getProductName()%> <small> Please sign up to purchase this product</small></h1>
	</p>

	<div class="col-md-6">
		<img src="<%=product.getImagePath()%>" alt="" width="300"
			class="img-rounded" />
	</div>
	<form class="form-horizontal"
		action="/ChangingSeasons/AddtoCartServlet" method="post">
		<fieldset>
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="form-group">
						<label class="col-md-4 control-label" for="description">
							Description</label>
						<div class="col-md-4">
							<p class="form-control-static"><%=product.getProductDesc()%></p>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="type"> Jacket
							Type</label>
						<div class="col-md-4">
							<p class="form-control-static"><%=product.getType()%></p>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="price"> Price</label>
						<div class="col-md-4">
							<p class="form-control-static">
								<%=product.getPrice()%>
							</p>
						</div>
					</div>
				</div>
			</div>
		</fieldset>
	</form>
</div>

<%
	}
%>

<%@ include file="footer.jsp"%>