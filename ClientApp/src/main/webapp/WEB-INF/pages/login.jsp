<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Client login</title>
    <meta charset="utf-8">
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
					<div class="col-12 col-md-8 col-lg-6 col-xl-4">
						<div class="card w-100">
						<div class="card-header bg-info-subtle">
							<div class="d-inline-flex">
								<img class="img-fluid" style="width:48px;height:48px" src="images/user_icon.png" alt="slika">
								<div class="d-inline-block">
									<p class="fs-4 fw-semibold m-0 p-0">Log in</p>
									<p class="m-0 p-0">Please log in to continue</p>
								</div>
							</div>
							
						</div>
						  <div class="card-body">
						     	<form class="mt-4 needs-validation" method="post"
								action="?action=login">
								<div class="mb-4">
									<div class="form-floating">
										<input required type="text"
											class="form-control form-control-lg bg-light" id="typeEmailX"
											placeholder="name@example.com" name="username"> <label
											for="typeEmailX">Username</label>
										<div class="invalid-feedback">Please enter a username.</div>
									</div>
								</div>
								<div class="mb-3">
									<div class="form-floating">
										<input required type="password" name="password"
											class="form-control form-control-lg bg-light" id="typePasswordX"
											placeholder="Password"> <label for="typePasswordX">Password</label>
										<div class="invalid-feedback">Please enter a password.</div>
									</div>
								</div>
								<div class="d-flex align-items-start justify-content-start">
									<a href="?action=registration" class="text-reset fw-semibold">
									Register
									</a>
								</div>
								<div class="d-flex align-items-end justify-content-end">
									<button
										class="btn btn-primary bg-info-subtle text-reset fw-semibold border-0"
										name="login" type="submit">
										 Log in
									</button>
								</div>
							</form>
						     	
						     
						    	
						  </div>
						</div>
					</div>
				
			</div>
		</div>
	</div>
</body>
</html>