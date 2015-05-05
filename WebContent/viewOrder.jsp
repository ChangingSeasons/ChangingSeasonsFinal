<%@page import="model.OrderProducts"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="model.OrderDAO"%>
<%@page import="model.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="navbar.jsp"%>

<div class="container">
	<%
		HashMap<Order, List<OrderProducts>> orders = null;

		if (user.getType().equals("sel")) {
			orders = OrderDAO.orderSellers(user.getID());
		} else if (user.getType().equals("buy")) {
			orders = OrderDAO.orderDetails(user.getID());
		} else if (user.getType().equals("adm")) {
			orders = OrderDAO.orderDetails();
		}
	%>

	<!-- Welcome -->
	<div class="page-header">
		<h1>
			<span class="title">My Orders</span> <br />
		</h1>
	</div>

	<div class="container">
		<div class="panel-group" role="tablist" aria-multiselectable="true">
			<%
				int count = 0;
				for (Order order : orders.keySet()) {
					String collapse = "class=\"collapsed\"";
			%>

			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading" data-toggle="collapse">
					<div class="panel-title">
						<ul class="nav nav-pills" role="tablist">
							<li role="presentation"><a data-toggle="collapse"
								data-parent="#accordion" href="#collapse<%=count%>"
								aria-expanded="false" aria-controls="collapse<%=count%>"> Order ID <%=order.getOrderID()%>
								&nbsp; &nbsp; &nbsp; &nbsp;
									<button class="btn btn-primary btn-xs" type="button">
										Products <span class="badge"><%=orders.get(order).size()%></span>
									</button>
							</a></li>

							<%
								if (!user.getType().equals("buy")) {
							%>

							<li class="dropdown"><a class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-expanded="false">Set
									Status <span class="caret"></span>
							</a>
								<ul class="dropdown-menu" role="menu">
									<li><a onclick="window.location.href='UpdateOrderStatusServlet?status=Placed&orderID=<%=order.getOrderID()%>'">Order Placed</a></li>
									<li><a onclick="window.location.href='UpdateOrderStatusServlet?status=Delivered&orderID=<%=order.getOrderID()%>'">Order Delivered</a></li>
									<li><a onclick="window.location.href='UpdateOrderStatusServlet?status=Transit&orderID=<%=order.getOrderID()%>'">Order in Transit</a></li>
									<li><a onclick="window.location.href='UpdateOrderStatusServlet?status=Delayed&orderID=<%=order.getOrderID()%>'">Delayed</a></li>
									<li><a onclick="window.location.href='UpdateOrderStatusServlet?status=Cancelled&orderID=<%=order.getOrderID()%>'">Cancel Order</a></li>
								</ul></li>
							<%
								}
							%>
						</ul>
					</div>
				</div>
				<div class="panel-body">
					<ul class="list-inline">
						<li><strong> Date of Order </strong> <%=order.getDateOfOrder()%>
							<br /></li>
						<li><strong> Shiping Date </strong> <%=order.getDateOfShipping()%>
							<br /></li>
						<li><strong> Current Order Status </strong> <%=order.getOrderStatus()%>
							<br /></li>
						<li><strong> Shipping Address</strong> <%=order.getShippingAddress()%>
							<br /></li>
						<li><strong> Total Price </strong> <%=order.getTotal_price()%>
							<br /></li>
					</ul>
					<!-- Table -->
					<div id="collapse<%=count%>" class="panel-collapse collapse"
						role="tabpanel">
						<table class="table">
							<tr>
								<th>Name</th>
								<th>Size</th>
								<th>Color</th>
								<th>Quantity</th>

								<%
									for (OrderProducts product : orders.get(order)) {
								%>
							</tr>
							<tr>
								<td><%=product.getName()%></td>
								<td><%=product.getSize()%></td>
								<td><%=product.getColor()%></td>
								<td><%=product.getQuantity()%></td>
							</tr>

							<%
								}
									count++;
							%>
						</table>
					</div>
				</div>
			</div>
			<%
				}
			%>

		</div>

		<%
			String buttonText = user.getType().equals("buy") ? "Continue Shopping" : "Return to Stock Page";
		%>
		<!-- Button -->
		<div class="form-group">
			<label class="col-md-4 control-label" for="cart"></label>
			<div class="col-md-4">
				<button type="button" id="lproducts" name="lproducts"
					onclick="location.href = 'base_index.jsp';" class="btn btn-primary"><%=buttonText %></button>
			</div>
		</div>

	</div>
</div>

<%@ include file="footer.jsp"%>
