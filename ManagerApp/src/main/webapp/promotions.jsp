<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:useBean id="managerService" class="services.ManagerService" scope="session"/>
    <jsp:useBean id="promotionService" class="services.PromotionService" scope="session"/>
    <%@page import="beans.PromotionBean" %>
    <%@page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Promotions</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
	function filterTable() {
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("searchInput");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("promotionsTable");
	  tr = table.getElementsByTagName("tr");
	
	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[1];
	    if (td) {
	      txtValue = td.textContent || td.innerText;
	      if (txtValue.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    }
	  }
	}
</script>
</head>
<body>
<%	
	session.removeAttribute("alert-danger");
	session.removeAttribute("alert-success");
	if(!managerService.isLoggedIn())
		response.sendRedirect("login.jsp");
	String title=request.getParameter("title");
	String description=request.getParameter("description");
	String date=request.getParameter("date");
	if(title!=null && !"".equals(title) && description!=null && !"".equals(description)){
		boolean inserted=promotionService.insert(new PromotionBean(title,description,LocalDate.parse(date)));
		if(inserted)
			session.setAttribute("alert-success","Promotion created!");
		else session.setAttribute("alert-danger","Something went wrong!");
	}

%>
<%@include file="/WEB-INF/menu_header.jsp" %>
    <div class="container mt-5">
        

        <!-- Search -->
        <div class="input-group mb-3 w-100">
        	<button class="btn bg-info-subtle fw-semibold " id="addBtn" data-bs-toggle="modal" data-bs-target="#addPromotionModal">Add New Promotion</button>
            <input type="text" id="searchInput" class="form-control ms-5" placeholder="Search promotions..." oninput="javascript:filterTable()">
        </div>

        <!-- Table -->
        <table class="table table-striped table-hover" id="promotionsTable">
            <thead class="table-light">
               <tr>
               <th>Title</th>
               <th>Description</th>
               <th>Valid until</th>
               </tr>
            </thead>
            <tbody>
                <%
               		for(PromotionBean promo:promotionService.getPromotions()){%>
               		<tr><td><%=promo.getTitle() %></td><td><%=promo.getDescription() %></td><td><%=promo.getDate() %></td></tr>
               		<%} %>
               
            </tbody>
        </table>
    </div>

    <!-- Modal for Adding Promotion -->
    <div class="modal fade" id="addPromotionModal" tabindex="-1" aria-labelledby="addPromotionModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header bg-info-subtle">
                    <h5 class="modal-title" id="addPromotionModalLabel">Add New Promotion</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="addPromotionForm">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" name="title" placeholder="Title" required>
                            <label for="promotionTitle">Title</label>
                        </div>
                        <div class="form-floating mb-3">
                            <textarea class="form-control" name="description" placeholder="Description" style="height: 100px" required></textarea>
                            <label for="promotionDescription">Description</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="date" class="form-control" name="date" placeholder="Duration Date" required>
                            <label for="promotionDate">Duration Date</label>
                        </div>
                        <div class="d-flex justify-content-end">
                            <button type="button" class="btn btn-secondary text-reset fw-semibold me-2" data-bs-dismiss="modal">Cancel</button>
                            <button type="submit" class="btn bg-info-subtle fw-semibold">Add</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
