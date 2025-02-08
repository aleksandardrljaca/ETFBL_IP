<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Rent</title>
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
<%@include file="/WEB-INF/headers/menu_header.jsp" %>
<div style="margin-top:5%">
		<div class="container h-100">
			<div class="row d-flex align-items-center justify-content-center h-100">
					<div class="col-12 col-md-10 col-lg-8 col-xl-6">
						<div class="card w-100">
						<div class="card-header bg-info-subtle">
							<div class="d-inline-flex">
								
								<div class="d-inline-block">
									<p class="fs-4 fw-semibold m-0 p-0">Rent Vehicle</p>
									<p class="m-0 p-0">Fill the form to rent a vehicle</p>
								</div>
							</div>
						</div>
						  <div class="card-body">
						      <form class="mt-4 needs-validation" method="post" action="?action=rent">
						        <div class="row g-3">
						            <div class="col-md-6">
						                <div class="form-floating">
						                    <input required type="text" name="startLocation" class="form-control bg-light" id="startLocation" placeholder="Start Location">
						                    <label for="startLocation">Start Location</label>
						                    <div class="invalid-feedback">Please enter a start location.</div>
						                </div>
						            </div>
						            <div class="col-md-6">
						                <div class="form-floating">
						                    <input required type="text" name="endLocation" class="form-control bg-light" id="endLocation" placeholder="End Location">
						                    <label for="endLocation">End Location</label>
						                    <div class="invalid-feedback">Please enter an end location.</div>
						                </div>
						            </div>
						            <div class="col-md-6">
						                <div class="form-floating">
						                    <select required name="vehicle" class="form-select bg-light" id="vehicle">
						                        <option value="" disabled selected>Select Vehicle</option>
						                        <option value="CAR">Car</option>
						                        <option value="ELECTRIC_BICYCLE">Bicycle</option>
						                        <option value="ELECTRIC_SCOOTER">Scooter</option>
						                    </select>
						                    <label for="vehicle">Vehicle</label>
						                    <div class="invalid-feedback">Please select a vehicle.</div>
						                </div>
						            </div>
						           <div class="col-md-6">
						                <div class="form-floating">
						                    <input required type="text" name="cardNumber" class="form-control bg-light" id="cardNumber" placeholder="Card Number">
						                    <label for="cardNumber">Card Number</label>
						                    <div class="invalid-feedback">Please enter your card number.</div>
						                </div>
						            </div>
						        </div>
						        <div class="d-flex align-items-end justify-content-end mt-4">
						            <button class="btn btn-primary bg-info-subtle text-reset fw-semibold border-0" name="rent" type="submit">
						                Rent
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