<%@page import="model.ProductDAO"%>
<%@page import="java.util.List"%>
<%@page import="model.RankDAO"%>
<%@page import="model.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="header.jsp"%>
<%@ include file="navbar.jsp"%>

<div class="container">
	<%
	int productID = Integer.parseInt(request.getParameter("productID"));
	Product product = ProductDAO.viewProduct(productID);
		
		if (product != null) {
	%>
	<div class="container">
		<p class="text-center">
		<h1><%=product.getProductName()%></h1>


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
							<label class="col-md-4 control-label" for="color">Color</label>
							<div class="col-md-4">

								<select name="color" id="color" class="form-control selectWidth">
									<option class="">Black</option>
									<option class="">White</option>
									<option class="">Red</option>
									<option class="">Brown</option>
									<option class="">Grey</option>
									<option class="">Blue</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-4 control-label" for="color">Size</label>
							<div class="col-md-4">
								<select name="size" id="size" class="form-control selectWidth">
									<option class="">XS</option>
									<option class="">S</option>
									<option class="">M</option>
									<option class="">L</option>
									<option class="">XL</option>
									<option class="">XXL</option>
								</select>
							</div>
						</div>


						<div class="form-group">
							<label class="col-md-4 control-label" for="color">Quantity</label>
							<div class="col-md-4">
								<select name="quantity" id="quantity"
									class="form-control selectWidth">
									<option class="">01</option>
									<option class="">02</option>
									<option class="">03</option>
									<option class="">04</option>
									<option class="">05</option>
									<option class="">06</option>
									<option class="">07</option>
									<option class="">08</option>
									<option class="">09</option>
									<option class="">10</option>
								</select>
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

						<input type="hidden" name="userID" value="<%=user.getID()%>">
						<input type="hidden" name="productID"
							value="<%=product.getProductID()%>">

						<div class="form-group">
							<label class="col-md-4 control-label" for="price"> </label>
							<div class="col-md-4">
								<%
									if (user.getType().equals("adm") || user.getType().equals("sel")) {
								%>
								<button type="button" class="btn btn-info"
									onclick="location.href = 'editProduct.jsp?editProductID=<%=product.getProductID()%>'">Edit</button>
								<%
									} else {
								%>
								<input type="submit" class="btn btn-success" role="button"
									value="Add to Cart" />
								<%
									}
								%>
							</div>
						</div>

						<p class="text-center">
							<%
								if (user.getType().equals("buy")) {

										int rank = RankDAO.viewRank(product.getProductID(),
												user.getID());
										String value = rank != -1 ? "value=\""+rank+"\"" : "";
										String review = RankDAO.customerViewProductReview(user.getID(), product.getProductID());
							%>

							<input id="input-1" class="rating" data-size="sm" data-min="0"
								data-max="5" data-step="1" <%=value  %>>

							<script type="text/javascript">
							$(document).ready(
									function() { // When the HTML DOM is ready loading, then execute the following function...
										var oldRank = <%=rank %>;
										var productID = <%=product.getProductID() %>;
										var userID = <%=user.getID()   %>;
										var review = <%=review.equals("") ? "\"\"" : "'"+review+"'"%>
										if (review === "") {
											$('.userReview').hide();
										}
										 $('#input-1').on('rating.change', function(event, value, caption) {
											 url = "UpdateRankServlet?rank="+value+"&oldRank="+oldRank+"&customerID="+userID+"&productID="+productID;
											 $('.userReview').show();
											 $.get(url, function(response) {
												 //location.reload(true);
											 });
								        });
										 $('#saveReview').click (function () {
											 var reviewText = $('#reviewText').val();
											 url = "AddReviewServlet?review="+reviewText+"&customerID="+userID+"&productID="+productID;
											 $.get(url, function(response) {
												 location.reload(true);
											 });
										 });
										 
									});
							</script></p><div class="form-group userReview" id=>
							<label class="col-md-4 control-label">Review</label>
							<div class="col-md-7">
								<textarea class="form-control" rows="3" id="reviewText"><%=review %></textarea>
							</div>
						</div>
							
						<div class="form-group userReview" id="userReview">
							<label class="col-md-4 control-label"></label>
							<div class="col-md-7">
								<button type="button" id="saveReview"> Save Review</button>
							</div>
						</div>
						 	<%
								}
							double averageRank = RankDAO.averageRank(product.getProductID());
							String averageRankValue = averageRank != -1 ? "value=\""+averageRank+"\"" : "";
							%>
						
						<hr />
						<h5> All User Reviews    <small>Average Rank<input id="averageStars" class="rating" data-size="xs" data-min="0"
								data-max="5" data-step="1" <%=averageRankValue %> data-disabled="true"> </small></h5>
						<ul class="list-group">
						<% 
							List<String> reviews = RankDAO.viewReview(product.getProductID()); 
							for (String review : reviews) { %>
							<li class="list-group-item">
							<%=review%>
							</li>
						<%
							}
						%>
						</ul>	
					
					</div>
				</div>
			</fieldset>
		</form>


	</div>



</div>
<%
		} else
			out.write("No product found");
	%>



</div>

<%@ include file="footer.jsp"%>