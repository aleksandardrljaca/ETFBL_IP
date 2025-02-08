<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <jsp:useBean id="clientBean" class="models.beans.ClientBean" scope="session"/>
   <jsp:useBean id="rentBean" class="models.beans.RentBean" scope="session"/>
   <%@page import="models.dto.Rent" %>
   <%@page import="models.dto.Client" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" 
    integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" 
	integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
	<script>
		
	</script>
</head>
<body>
<%@include file="/WEB-INF/headers/menu_header.jsp" %>
	<div style="margin-top:5%">
        <div class="container h-100">
            <div class="row d-flex align-items-start justify-content-center h-100">
                <!-- Profile Card -->
                <div class="col-12 col-md-6">
                    <div class="card w-100">
                        <div class="card-header bg-info-subtle d-flex justify-content-between">
                            <div class="d-inline-flex">
                                <img class="img-fluid" style="width:48px;height:48px;cursor:pointer;" src="images/user_icon.png" alt="icon" >
                                
                                <div class="d-inline-block">
                                    <p class="fs-4 fw-semibold m-0 p-0">Update Profile</p>
                                    <p class="m-0 p-0">Update your profile information</p>
                                </div>
                            </div>
                            <form method="POST" action="?action=profile&update=false&deactivate=true">
                            	<input type="submit" value="Deactivate" class="btn bg-danger fw-semibold">
                            </form>
                        </div>
                        <div class="card-body">
                            <form class="mt-4 novalidate" method="post" action="?action=profile&update=true&deactivate=false">
                            <% Client client=clientBean.getLoggedClient(); %>
                                <div class="row g-3">
                                    <div class="col-md-6">
                                        <div class="form-floating">
                                            <input  type="text" name="firstName" class="form-control bg-light" id="firstName" placeholder="First Name" value="<%=client.getFirstname() %>">
                                            <label for="firstName">First Name</label>
                                            
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-floating">
                                            <input  type="text" name="lastName" class="form-control bg-light" id="lastName" placeholder="Last Name" value="<%=client.getLastname() %>">
                                            <label for="lastName">Last Name</label>
                                            
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-floating">
                                            <input type="text" name="username" class="form-control bg-light" id="username" placeholder="Username" value="<%=client.getUsername() %>">
                                            <label for="username">Username</label>
                                            
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-floating">
                                            <input type="password" name="password" class="form-control bg-light" id="password" placeholder="Password" value="<%=client.getPassword() %>">
                                            <label for="password">Password</label>
                                           
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-floating">
                                            <input type="text" name="id_card" class="form-control bg-light" id="id_card" placeholder="ID Card" value="<%=client.getIdCard() %>">
                                            <label for="id_card">ID Card</label>
                                            
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-floating">
                                            <input  type="text" name="driving_license" class="form-control bg-light" id="driving_license" placeholder="Driving license" value="<%=client.getDrivingLicense() %>">
                                            <label for="driving_license">Driving license</label>
                                            
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-floating">
                                            <input type="text" name="phone" class="form-control bg-light" id="phone" placeholder="Phone" value="<%=client.getPhone() %>">
                                            <label for="phone">Phone</label>
                                            
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-floating">
                                            <input type="text" name="email" class="form-control bg-light" id="email" placeholder="Email" value="<%=client.getEmail() %>">
                                            <label for="email">Email</label>
                                            
                                        </div>
                                    </div>
                                </div>
                                <div class="d-flex align-items-end justify-content-end mt-4">
                                    <button class="btn btn-primary bg-info-subtle text-reset fw-semibold border-0" name="submit" type="submit">
                                        Update
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- Table -->
                <div class="col-12 col-md-6">
                    <div class="card w-100">
                        <div class="card-header bg-info-subtle">
                            <p class="fs-4 fw-semibold m-0">Travel Details</p>
                        </div>
                        <div class="card-body">
                            <table class="table table-bordered">
                                <thead class="bg-light">
                                    <tr>
                                        <th>Date</th>
                                        <th>Start Location</th>
                                        <th>End Location</th>
                                        <th>Price</th>
                                        <th>Vehicle ID</th>
                                        <th>Card Number</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for(Rent r:rentBean.getClientsRents(clientBean.getLoggedClient().getId())){
                                    	
                                    	out.println("<tr><td>"+r.getDate()+"</td><td>"+r.getStartLocation()+"</td><td>"+r.getEndLocation()+"</td><td>"+r.getPrice()+"</td><td>"+
                                    	r.getVehicleId()+"</td><td>"+r.getPaymentInfo()+"</td></tr>");
                                    	
                                    }	
                                    	%>
                                    
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>