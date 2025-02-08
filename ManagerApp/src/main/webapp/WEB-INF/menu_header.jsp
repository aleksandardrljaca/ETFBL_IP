<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" 
    integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" 
	integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-info-subtle">
  <div class="container-fluid">
    <img src="images/logo.png" height=100px>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
     aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link fw-semibold " aria-current="page" href="announcements.jsp">Annoucements</a>
        </li>
        <li class="nav-item">
          <a class="nav-link fw-semibold " href="promotions.jsp">Promotions</a>
        </li>
        <li class="nav-item">
          <a class="nav-link fw-semibold " href="logout.jsp">Log out</a>
        </li>
      </ul>
      
    </div>
  </div>
</nav>
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