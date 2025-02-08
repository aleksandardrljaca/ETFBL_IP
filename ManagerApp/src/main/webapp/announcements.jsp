<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:useBean id="managerService" class="services.ManagerService" scope="session"/>
	<jsp:useBean id="announcementService" class="services.AnnouncementService" scope="session"/>
	 <%@page import="beans.AnnouncementBean" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Announcements</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
     <script>
	function filterTable() {
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("searchInput");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("announcementsTable");
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
	String content=request.getParameter("content");
	if(title!=null && !"".equals(title) && content!=null && !"".equals(content)){
		boolean inserted=announcementService.insert(new AnnouncementBean(title,content));
		if(inserted)
			session.setAttribute("alert-success","Announcement created!");
		else session.setAttribute("alert-danger","Something went wrong!");
	}
	
	%>

<%@include file="/WEB-INF/menu_header.jsp" %>
    <div class="container mt-5">
    <!-- Search -->
    <div class="input-group mb-3 w-100">
        <button class="btn bg-info-subtle fw-semibold" data-bs-toggle="modal" data-bs-target="#addAnnoucementModal">Add New Announcement</button>
         <input  type="text" id="searchInput"  name="searchbar" class="form-control ms-5" placeholder="Search by title" oninput="javascript:filterTable()">
       
    </div>

   
    <table class="table table-striped table-hover" id="announcementsTable">
        <thead class="table-light">
            <tr>
                <th>Title</th>
                <th>Content</th>
            </tr>
        </thead>
        <tbody>
           
           <%
           		for(AnnouncementBean announcement:announcementService.getAnnouncements()){ %>
           			<tr><td><%=announcement.getTitle() %></td><td><%=announcement.getContent()%></td></tr>
           	<%	}
           
           %>
        </tbody>
    </table>
</div>


    
    <div class="modal fade" id="addAnnoucementModal" tabindex="-1" aria-labelledby="addAnnoucementModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header bg-info-subtle">
                    <h5 class="modal-title" id="addAnnoucementModalLabel">Add New Annoucement</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form method="POST" action="announcements.jsp">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" name="title" placeholder="Title" required>
                            <label for="annoucementTitle">Title</label>
                        </div>
                        <div class="form-floating mb-3">
                            <textarea class="form-control" name="content" placeholder="Description" style="height: 100px" required></textarea>
                            <label for="annoucementDescription">Content</label>
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