<%@page import="model.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="navbar.jsp"%>
<%@page import="model.Product"%>

<div class="container">
<%		
	if(request.getParameter("editProductID")!=null) {
		int productID = Integer.parseInt(request.getParameter("editProductID"));
		Product product = ProductDAO.viewProduct(productID);
	%>
	<form class="form-horizontal" action="EditProductServlet" method="post">
		<fieldset>

			<!-- Form Name -->
			<div class="page-header">
				<h1>
					<span class="title">Update Product</span>
				</h1>
			</div>
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="productName">Product Name: </label> 
				<div class="col-md-4">
				<input id="productName" name="productName" type="text"class="form-control input-md"
					value="<%=product.getProductName() %>">
					</div>
			</div>
			
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="type">Product Type: </label>
				<div class="col-md-4">
					<input id="type" name="type" type="text"class="form-control input-md"
					value="<%=product.getType() %>">
				</div>
			</div>
			
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="productDesc">Product Description: </label>
				<div class="col-md-4">
					<input id="productDesc" name="productDesc" type="text" class="form-control input-md"
					value="<%=product.getProductDesc() %>">
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="price">Price: </label>
				<div class="col-md-4">
					<input id="price" name="price" type="text" class="form-control input-md"
					value="<%=product.getPrice() %>">
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="shippingCost">Shipping Cost: </label>
				<div class="col-md-4">
					<input id="shippingCost" name="shippingCost" type="text" class="form-control input-md"
					value="<%=product.getShippingCost() %>">
				</div>
			</div>

			<!--  
			<div class="form-group">
				<label class="col-md-4 control-label" for="size">Size: </label>
				<div class="col-md-4">
					<input id="size" name="size" type="text" class="form-control input-md"
					value="<%=product.getSize() %>">
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-4 control-label" for="color">Color: </label>
				<div class="col-md-4">
					<input id="color" name="color" type="text" class="form-control input-md"
					value="<%=product.getColor() %>">
				</div>
			</div>
			 -->
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="imageName">Image Name: </label>
				<div class="col-md-4">
					<input id="imageName" name="imageName" type="text" class="form-control input-md"
					value="<%=product.getImageName() %>">
				</div>
			</div>
			
			<input type="hidden" id="hiddenId" name="hiddenId" value="<%=productID %>">

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
<%
		} else
			out.write("No product found");
	%>

</div>
<!-- /container -->

<%@ include file="footer.jsp"%>