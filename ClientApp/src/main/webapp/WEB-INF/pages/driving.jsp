<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:useBean id="clientBean" class="models.beans.ClientBean" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Driving</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" 
    integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" 
	integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js "></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js"></script>
	<script>
	function generateInvoice() {
        const { jsPDF } = window.jspdf;
        const doc = new jsPDF();

       
        doc.html(document.getElementById("toPrint"), {
            callback: function (doc) {
               
                doc.save("invoice.pdf");
            },
            x: 5, 
            y: 5, 
            width: 180, 
            margin: [2, 2, 2, 2],
            scale: 0.25,  
            windowWidth: 700
        });
    }
	
	
    function startCountdown() {
        const timerElement = document.getElementById("span");
        console.log(timerElement.innerHTML);
        let countdownTime=Number(timerElement.innerHTML);
        const interval = setInterval(() => {
            if (countdownTime <= 0) {
                clearInterval(interval);
                timerElement.textContent = "00:00";
            } else {
                const minutes = String(Math.floor(countdownTime / 60)).padStart(2, '0');
                const seconds = String(countdownTime % 60).padStart(2, '0');
                timerElement.innerHTML =minutes.concat(":",seconds);
                console.log(countdownTime);
                countdownTime--;
            }
        }, 1000);
    }

    document.addEventListener("DOMContentLoaded", () => {
        startCountdown();
    });
	</script>
</head>
<body>
<%@include file="/WEB-INF/headers/menu_header.jsp" %>
 <div style="margin-top:5%">
		<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div id="toPrint" class="card card-custom">
                <div class="card-header bg-info-subtle text-center">
                    <h5 class="fw-bold mb-0">Driving Info</h5>
                </div>
                <div class="card-body">
                    <form method="POST" action="?action=driving">
                    	<p class="fs-5">
                        <strong>First name:</strong> <span><%=clientBean.getLoggedClient().getFirstname()%></span>
                    	</p>
                    	<p class="fs-5">
                        <strong>Last name:</strong> <span><%=clientBean.getLoggedClient().getLastname()%></span>
                    	</p>
                    	<p class="fs-5">
                        <strong>ID Card:</strong> <span><%=clientBean.getLoggedClient().getIdCard()%></span>
                    	</p>
                    	<p class="fs-5">
                        <strong>Driving License:</strong> <span><%=clientBean.getLoggedClient().getDrivingLicense()%></span>
                    	</p>
                    	<p class="fs-5">
                        <strong>Price:</strong> <span><%=session.getAttribute("price")%> $</span>
                    	</p>
                    <p class="fs-5">
                        <strong>Time left:</strong> <span id="span">3600</span>
                    </p>
                    <div class="text-center mt-4">
                       <input type="submit" name="submit" value="STOP" class="btn fs-6 fw-semibold w-75 btn-danger">
                    </div>
                    </form>
                    <div class="text-center mt-4">
                    	<button class="btn bg-secondary w-75 fw-semibold" onClick="javascript:generateInvoice()">Invoice</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
	</div>
</body>
</html>