<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="navbar.jsp"%>

<div class="container"; style="width:100%;">

	<!-- Welcome -->
	<div class="page-header">
		<h1>
			<span class="title">Contact Us</span> <br /> 
			<small>How can we help you?</small>
		</h1>
	</div>
	<!--  style="float:left; width:20%;margin-left:50px;"  -->
	<div class="col-md-4" style="float:right; width:20%;margin-left:10px;margin-right:50px;">
			<div class="panel panel-default">
				<div class="panel-body">
				<h5>Call Us!</h5>
				1.518.894.7655
				
				<h5>We're Available:</h5>
				24 Hours a day
				7 Days a Week
				</div>
			</div>
	</div>
	<form class="form-horizontal" action="/ChangingSeasons/ContactUsServlet" method="post">
		<fieldset>
			<div style="float:left; width:100%;">
			
			<!-- Text input-->
			<div class="form-group required">
				<label class="col-md-4 control-label" for="email">Email</label>
				<div class="col-md-4">
					<input id="email" name="email" type="text" placeholder="Email"
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

