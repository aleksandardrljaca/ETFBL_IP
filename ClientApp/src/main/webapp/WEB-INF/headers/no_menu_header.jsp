<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<header class="bg-info-subtle">
        <div class="container-fluid">
            <img src="images/logo.png" alt="Logo"  class="text-start m-0 p-0" height="100px">
        </div>
    </header>
    <% if(session.getAttribute("alert-danger")!=null && !"".equals(session.getAttribute("alert-danger"))){ %>
<div class="alert alert-danger text-center" role="alert">
 	<%= session.getAttribute("alert-danger") %>
</div>
<% } %>
<% if(session.getAttribute("alert-success")!=null && !"".equals(session.getAttribute("alert-success"))){ %>
<div class="alert alert-success text-center" role="alert">
 	<%= session.getAttribute("alert-success") %>
</div>
<% } %>
</body>
</html>