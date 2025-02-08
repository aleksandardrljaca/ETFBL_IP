<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Client registration</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" 
    integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" 
	integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</head>
<body>
	
	<%@include file="/WEB-INF/headers/no_menu_header.jsp" %>
    <div style="margin-top:5%">
		<div class="container h-100">
			<div class="row d-flex align-items-center justify-content-center h-100">
					<div class="col-12 col-md-10 col-lg-8 col-xl-6">
						<div class="card w-100">
						<div class="card-header bg-info-subtle">
							<div class="d-inline-flex">
								<img class="img-fluid" style="width:48px;height:48px" src="images/user_icon.png" alt="slika">
								<div class="d-inline-block">
									<p class="fs-4 fw-semibold m-0 p-0">Register</p>
									<p class="m-0 p-0">Create your account</p>
								</div>
							</div>
						</div>
						  <div class="card-body">
						      <form class="mt-4 " method="post" action="?action=registration">
						        <div class="row g-3">
						            <div class="col-md-6">
						                <div class="form-floating">
						                    <input type="text" name="firstName" class="form-control bg-light" id="firstName" placeholder="First Name">
						                    <label for="firstName">First Name</label>
						                  
						                </div>
						            </div>
						            <div class="col-md-6">
						                <div class="form-floating">
						                    <input  type="text" name="lastName" class="form-control bg-light" id="lastName" placeholder="Last Name">
						                    <label for="lastName">Last Name</label>
						                    
						                </div>
						            </div>
						            
						            <div class="col-md-6">
						                <div class="form-floating">
						                    <input type="text" name="username" class="form-control bg-light" id="username" placeholder="Username">
						                    <label for="username">Username</label>
						                   
						                </div>
						            </div>
						            <div class="col-md-6">
						                <div class="form-floating">
						                    <input required type="password" name="password" class="form-control bg-light" id="password" placeholder="Password">
						                    <label for="password">Password</label>
						                    
						                </div>
						            </div>
						            <div class="col-md-6">
						                <div class="form-floating">
						                    <input  type="text" name="id_card" class="form-control bg-light" id="id_card" placeholder="ID Card">
						                    <label for="id_card">ID Card</label>
						                    
						                </div>
						            </div>
						            <div class="col-md-6">
						                <div class="form-floating">
						                    <input type="text" name="driving_license" class="form-control bg-light" id="driving_license" placeholder="Driving license">
						                    <label for="driving_license">Driving license</label>
						                    
						                </div>
						            </div>
						            <div class="col-md-6">
						                <div class="form-floating">
						                    <input type="text" name="phone" class="form-control bg-light" id="phone" placeholder="Phone">
						                    <label for="phone">Phone</label>
						                   
						                </div>
						            </div>
						            <div class="col-md-6">
						                <div class="form-floating">
						                    <input  type="text" name="email" class="form-control bg-light" id="email" placeholder="Email">
						                    <label for="email">Email</label>
						                    
						                </div>
						            </div>
						        </div>
						        <div class="d-flex align-items-end justify-content-end mt-4">
						            <button class="btn btn-primary bg-info-subtle text-reset fw-semibold border-0" name="register" type="submit">
						                Register
						            </button>
						        </div>
						      </form>
						  </div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>