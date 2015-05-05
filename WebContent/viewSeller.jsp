<%@page import="java.util.List"%>
<%@page import="model.AdminDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="navbar.jsp"%>

<div class="container">

<% 
	List<User> sellers  = AdminDAO.listSellers();
	if (sellers != null) {
%>
	
	<!-- Welcome -->
	<div class="page-header">
		<h1>
			<span class="title">Seller Details</span> <br /> 
		</h1>
	</div>

	<div class="container">
	  <table class="table table-bordered" align="center">
	    <thead>
	      <tr>
	        <th>User ID</th>
	        <th>User Name</th>
	        <th>Company Name</th>
	        <th>Address</th>
	        <th>Email</th>
	        <th>Phone Number</th>
	        <th>PayPal ID</th>
	        <th>Bank Account Number</th>
	        <th>Routing Number</th>
	        <th>Authorization </th>
	        <th>Status</th>
	        <th>Contact Seller</th>
	        <th> Delete User</th>
	      </tr>
	    </thead>
	     <tbody>
	    <%
	  			for (User s : sellers) {
		%>
	   
	      <tr>
		    <td><%= s.getID()  %></td>
	        <td><%= s.getUsername() %></td>
	        <td><%= s.getCompanyName() %></td>
	        <td><%= s.getAddress() %></td>
	        <td><%= s.getEmail() %></td>
	        <td><%= s.getPhone() %></td>
	        <td><%= s.getPayPalID() %></td>
	        <td><%= s.getBankAccount() %></td>
	        <td><%= s.getRoutingNumber() %></td>
	        <td><input type="checkbox" id="myToggleButton"
						<%=s.isAuthorized() ? "checked" : ""%>
						onclick="location.href = 'AdminServlet?action=AuthorizeSeller&ID=<%=s.getID()%>&email+<%=s.getEmail() %>&auth=<%=s.isAuthorized() %>';" />
						<label for="myToggleButton">Authorized</label></td>
	        <td><%= s.isStatus() %></td>
	        <td> <button type="button" id="contact" name="contact"
	        				onclick="location.href = 'contactSeller.jsp?sellerID=<%=s.getID() %>'"
							class="btn btn-default btn-sm">Contact Seller</button></td>
							<%String disabled =  s.isStatus() ? "" : "disabled=\"disabled\""; %>
	        <td>
	        <button type="button" id="delete" name="delete"
	        				onclick="location.href = 'AdminServlet?action=DeleteUser&ID=<%=s.getID() %>'"
							class="btn btn-primary btn-sm" <%=disabled %>>Deactivate</button></td>
	      </tr>
	 
	    <%
				}
			%>
			   </tbody>
  </table>
</div>
<% } else out.write("No Sellers found"); %>
</div>

<%@ include file="footer.jsp"%>
