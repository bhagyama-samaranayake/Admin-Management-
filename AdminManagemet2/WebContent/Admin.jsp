<%@page import="com.Admin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/Admin.js"></script>

<meta charset="ISO-8859-1">
<title>Admin Management</title>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Admin Management</h1>

	<form id="formItem" name="formItem">
		
		 Seller Name:
		<input id="sellerName" name="sellerName" type="text" class="form-control form-control-sm"><br> 
		 Seller Category:
		<input id="sellerCategory" name="sellerCategory" type="text" class="form-control form-control-sm"><br>
		 Seller Phone:
		<input id="sellerPhone" name="sellerPhone" type="text" class="form-control form-control-sm"><br>		
		 Seller Email:
		<input id="sellerEmail" name="sellerEmail" type="text" class="form-control form-control-sm"><br>
		 
				
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
	</form>
	
	<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
	<br>
	<div id="divItemGrid">
	<%
		Admin AdminObj = new Admin(); 
			 out.print(AdminObj.readResearch());
	%>
	</div>
</div> </div> </div> 
	
</body>
</html>