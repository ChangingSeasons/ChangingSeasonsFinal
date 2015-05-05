<%@page import="java.util.List"%>
<%@page import="model.AdminDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="navbar.jsp"%>

<div class="container">

<% 
	List<User> buyers  = AdminDAO.listCustomers();
	%>
	<%
	String msg = (String) request.getAttribute("msg");
	if(msg == null)
		msg="";
	out.write("<div class=\"alert alert-success\" role=\"alert\">"+msg+"</div>");
	
	%>

	<!-- Welcome -->
	<div class="page-header">
		<h1>
			<span class="title">Customer Details</span> <br /> 
		</h1>
	</div>

	<div class="container">
	  <table class="table table-bordered" align="center">
	    <thead>
	      <tr>
	        <th>User ID</th>
	        <th>User Name</th>
	        <th>First Name</th>
	        <th>Last Name</th>
	        <th>Address</th>
	        <th>Email</th>
	        <th>Phone Number</th>
	        <th>PayPal ID</th>
	        <th>Status</th>
	        <th> Delete User</th>
	      </tr>
	    </thead>
	     <tbody>
	    <%
				for (User b : buyers) {
		%>
	   
	      <tr>
	        <td><%= b.getID()  %></td>
	        <td><%= b.getUsername() %></td>
	        <td><%= b.getFirstname() %></td>
	        <td><%= b.getLastname() %></td>
	        <td><%= b.getAddress() %></td>
	        <td><%= b.getEmail() %></td>
	        <td><%= b.getPhone() %></td>
	        <td><%= b.getPayPalID() %></td>
	        <td><%= b.isStatus() %></td>
	        <td>
	        <button type="submit" id="submit" name="submit"
	        				onclick="location.href = 'AdminServlet?action=DeleteUser&ID=<%=b.getID() %>'"
							class="btn btn-default">Deactivate</button></td>
	      </tr>
	   
	    <%
				}
			%>
			 </tbody>
  </table>
	
</div>
</div>

<%@ include file="footer.jsp"%>
