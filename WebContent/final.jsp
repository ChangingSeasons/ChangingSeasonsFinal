<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="navbar.jsp"%>

<div class="container">
	<form class="form-horizontal" action="/ChangingSeasons/" method="post">
		<fieldset>

			<!-- Form Name -->
			<div class="page-header">
				<h1>
					<span class="title">Changing Seasons</span><br/>
					<small>Thank You!</small>
				</h1>
			</div>
			
			<div style="text-align: center;">
			<h4>
			Your order has been successfully placed.<br/>
			Your order ID is <%=request.getParameter("orderID") %>
			</h4>
			</div>
			
			<div class="form-group" style="text-align: center;">
				<label class="col-md-4 control-label" for="signup"></label>
				<div class="col-md-4">
					<button type="button" id="submit" name="submit" onclick="location.href = 'base_index.jsp';"
						class="btn btn-primary">Continue Shopping</button>
				</div>
			</div>
		</fieldset>
	</form>
</div>

<%@ include file="footer.jsp"%>