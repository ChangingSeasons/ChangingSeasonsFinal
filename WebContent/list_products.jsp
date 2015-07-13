<%@page import="model.AdminDAO"%>
<%@page import="model.AuthDAO"%>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@page import="model.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script>
	$(document).ready(function() { 
		    
				$(":checkbox").click(
						function() { 
							var brandcheckValues = $('.brand:checked').map(
									function() {
										return $(this).val();
									}).get();
							var typecheckValues = $('.type:checked').map(
									function() {
										return $(this).val();
									}).get();
							var pricecheckValues = $('.price:checked').map(
									function() {
										return $(this).val();
									}).get();
							url = "ProductFilterServlet" + "?brand="
									+ brandcheckValues + "&type="
									+ typecheckValues + "&price="
									+ pricecheckValues;
							$.get(url, function(responseJson) { 
								$.each(responseJson, function(outer, lists) { // Iterate over the JSON array.
									$.each(lists, function(inner, item) { // Iterate over the Lists 
										if(outer == 0) $( "."+item ).show();
										else $( "."+item ).hide();
									});
								});
							});
						});
			});
</script>
	<div class="col-md-4"
		style="float: left; width: 20%; height: 100%; margin-left: 10px; margin-right: 10px;">
		
		<div class="panel panel-default">
			<div class="panel-body">
				<h3>Filter by </h3>
				<br />
				 
				 <h4>Brand</h4>
				 <%
				 List<User> sellers = AdminDAO.listSellers();
				 for (User seller : sellers) {
				 %>
				<input type="checkbox" class="brand" name="color" checked value="<%=seller.getCompanyName() %>">
				<%=seller.getCompanyName() %> <br />
				<%} %>
				
				

				<h4>Type</h4>
				<input type="checkbox" class="type" value="Leather" checked> Leather
				<br /> 
				<input type="checkbox" class="type" name="color"
					value="Denim" checked> Denim<br /> 
				<input type="checkbox" class="type"
					name="color" value="Parka" checked> Parka <br /> 
				<input type="checkbox"
					class="type" name="color" value="Peacoat" checked> Peacoat <br /> 
				<input type="checkbox" class="type" name="color" value="Windblockers" checked>
				Wind blockers <br /> 
				<input type="checkbox" class="type"
					name="color" value="SnowJackets" checked> Snow Jackets <br /> <br />

				<h4>Prices</h4>
				<input type="checkbox" class="price" name="color" value="25" checked> Under $25 <br /> 
				<input type="checkbox" class="price" name="color" value="50" checked> Under $50 <br /> 
				<input type="checkbox" class="price" name="color" value="100" checked> Under $100 <br />
				<input type="checkbox" class="price" name="color" value="200" checked> Under $200 <br /> 
				<input type="checkbox" class="price" name="color" value="9999" checked> Under $999 <br /> <br/>
			</div>
		</div>
	</div>
	
	<div class="col-md-10" style=" width: 75%; height: 100%; ">
		
	<%@page import="model.Product"%>
	<%@page import="model.ProductDAO.*"%>

	<% 
	List<Product> products;
		if (request.getAttribute("searchProducts") != null) {
			products = (List<Product>) request.getAttribute("searchProducts");
		} else 
		 products = (List<Product>) session.getAttribute("products");
%>
		<%
		if( session.getAttribute("user") != null) {
			User listUser = (User) session.getAttribute("user");
		if (products != null) {
			String clearfix = "";
			int count=0;
			for (Product p : products) {
				int id = p.getProductID();
				//if ((count % 2)==0)  clearfix = "<div class=\"clearfix visible-sm-block\">";
				//if ((count % 3)==0)  clearfix = "<div class=\"clearfix visible-md-block\">";
				//if ((count % 4)==0)  clearfix = "<div class=\"clearfix visible-lg-block\">";
				count++;
				out.write(clearfix);
				
				
		%>
		<div id="<%=p.getProductID() %>" class="<%=p.getProductID()%>">
		 
			<div class="panel panel-default">
			<div class="panel-heading text-center">
				<img src="<%=p.getImagePath()%>" alt="<%=p.getImageName()%>" width="250px">
				<div class="caption">
					<h3>
						<%=p.getProductName()%></h3>
					<p>
						Price: $<%=p.getPrice()+""%>
					</p>
					<p>
					<form action="view_product.jsp?productID=<%=p.getProductID() %>" method="post">
						<input type="hidden" name="productID" value="<%=(id+"")%>">
						<input type="submit" class="btn btn-primary btn-xs" role="button"
							value="View Product" />
						<%
						
							if (!listUser.getType().equals("buy")) {
						%>
							<a href="#" class="btn btn-default btn-xs" role="button" onclick="location.href = 'editProduct.jsp?editProductID=<%=p.getProductID()%>'">Edit
								Product</a>
								<%
										}
								%>
					</form>
					</p>
				</div>
			</div>
			</div>
		</div>
		<%
					}
				} else
					out.write("No products found");
			} else
				out.write("No user found");
		%>
</div>


