<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="navbar.jsp"%>

<div class="container">
	<form class="form-horizontal" action="/ChangingSeasons/AddProduct" method="post">
		<fieldset>
<% 

String msg = (String)request.getAttribute("msg");

if(msg == null) msg="";

if(!msg.equals("")) out.write("<div class=\"alert alert-danger\" role=\"alert\">"+msg+"</div>");


%>
			<!-- Form Name -->
			<div class="page-header">
				<h1>
					Add Products <br/>
					<small>Please enter the details of the jacket you want to add.</small>
				</h1>
			</div>
			
			<!-- Text input-->
			<div class="form-group required">
				<label class="col-md-4 control-label" for="productName">Product Name</label>
				<div class="col-md-4">
					<input id="productName" name="productName" type="text"
					 class="form-control input-md" required="required">
				</div>
			</div>
			
			<!-- Text input-->
			<div class="form-group required">
				<label class="col-md-4 control-label" for="type">Product Type</label>
				<div class="col-md-4">
					<select name ="type" id="type" class="form-control selectWidth">
	                	<option class="">Leather</option>
						<option class="">Denim</option>
						<option class="">Parka</option>
						<option class="">Peacoat</option>
						<option class="">Wind blockers</option>
						<option class="">Snow Jackets</option>
	                </select>
				</div>
			</div>
			
			<!-- Text area -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="productDesc">Product Description</label>
				<div class="col-md-4">
					<textarea class="form-control" id="productDesc" name="productDesc"></textarea>
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group required">
				<label class="col-md-4 control-label" for="price">Price</label>
				<div class="col-md-4">
					<input id="password" name="price" type="text"
					 class="form-control input-md" required="">

				</div>
			</div>
			
			<!-- Quatity input
			<div class="form-group required">
				<label class="col-md-4 control-label" for="quantity">Quantity</label>
				<div class="col-md-4">
					<input id="quantity" name="quantity" type="text"
					 class="form-control input-md" required="required">
				</div>
			</div> -->

			<!-- Text input-->
			<div class="form-group required">
				<label class="col-md-4 control-label" for="shippingCost">Shipping Cost</label>
				<div class="col-md-4">
					<input id="shippingCost" name="shippingCost" type="text"
					class="form-control input-md" required="">
				</div>
			</div>
			
			
			<!--			
			<div class="form-group">
				<label class="col-md-4 control-label" for="size">Size</label>
				<div class="col-md-4">
	                <input type="checkbox" name="size" value="XS"> XS &nbsp
					<input type="checkbox" name="size" value="S"> S &nbsp
					<input type="checkbox" name="size" value="M"> M &nbsp
					<input type="checkbox" name="size" value="L"> L &nbsp
					<input type="checkbox" name="size" value="XL"> XL &nbsp
					<input type="checkbox" name="size" value="XXL"> XXL &nbsp
				</div>
				
			</div>
			
			
			<div class="form-group">
				<label class="col-md-4 control-label" for="color">Color</label>
				<div class="col-md-4">
					<input type="checkbox" name="color" value="Black"> Black &nbsp
					<input type="checkbox" name="color" value="White"> White &nbsp
					<input type="checkbox" name="color" value="Red"> Red &nbsp
					<input type="checkbox" name="color" value="Brown"> Brown &nbsp
					<input type="checkbox" name="color" value="Grey"> Grey &nbsp
					<input type="checkbox" name="color" value="Blue"> Blue &nbsp
				</div>
			</div>
			 - -->
			
			<!-- Image Upload-->
			<div class="form-group required">
				<label class="col-md-4 control-label" for="image">Image</label>
				<div class="col-md-4">
					<input type="text" name="imageName" placeholder="Name of the Image" class="form-control input-md">
				</div>
			</div>

			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="submit"></label>
				<div class="col-md-4">
				Click on submit to upload the image and complete adding a product
				</br><button type="submit" id="submit" name="submit" class="btn btn-info">Submit</button>
				</div>
			</div>

		</fieldset>
	</form>
</div>
<!-- /container -->

<%@ include file="footer.jsp"%>