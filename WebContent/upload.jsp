<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="navbar.jsp"%>

<div class="container">
<!-- Welcome -->
	<div class="page-header">
		<h1>
			<span class="title">Image Upload</span>
		</h1>
	</div>

	<form method="post" action="/ChangingSeasons/UploadServlet" enctype="multipart/form-data">
		<label class="col-md-4 control-label" for="image">Please choose an image you want to upload</label>
				<div class="col-md-4">
				<input type="file" name="file" size="60"><br/>
				<input type="submit" value="Upload">
		</div>
	</form>
</div>
<%@ include file="footer.jsp"%>