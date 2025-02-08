package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import dao.daoImpl.ClientDAOImpl;
import dao.daoImpl.VehicleDAOImpl;
import dao.pool.ConnectionPool;
import models.beans.ClientBean;
import models.beans.LocationBean;
import models.beans.PricingBean;
import models.beans.RentBean;
import models.beans.VehicleBean;
import models.dto.Client;
import models.dto.Location;
import models.dto.Rent;
import utils.DistanceCalculator;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init() throws ServletException {
    	super.init();
    	ConnectionPool.initPool(getServletContext());
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		String address="/WEB-INF/pages/login.jsp";
		HttpSession session=request.getSession();
		session.setAttribute("alert-danger", "");
		session.setAttribute("alert-success", "");
		if(action==null || action.equals("")) {
			address="/WEB-INF/pages/login.jsp";
		}
		else if(action.equals("login")) {
			if(request.getParameter("username")!=null && request.getParameter("password")!=null) {
				String username=request.getParameter("username");
				String password=request.getParameter("password");
				ClientBean clientBean=null;
				if(session.getAttribute("clientBean")==null)
					clientBean=new ClientBean();
				else clientBean=(ClientBean)session.getAttribute("clientBean");
				if(clientBean.login(username, password)!=null) {
					session.setAttribute("clientBean", clientBean);
					RentBean rentBean=new RentBean();
					session.setAttribute("rentBean", rentBean);
					System.out.println("ULOGOVAN");
					address="/WEB-INF/pages/home.jsp";
				}
				else {
					session.setAttribute("alert-danger", "Wrong credentials!");
					address="/WEB-INF/pages/login.jsp";
				}
				
			}else address="/WEB-INF/pages/login.jsp";
		}
		else if(action.equals("registration")) {
			String firstname=request.getParameter("firstName");
			String lastname=request.getParameter("lastName");
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			String idCard=request.getParameter("id_card");
			String driving_license=request.getParameter("driving_license");
			String phone=request.getParameter("phone");
			String email=request.getParameter("email");
			if(isValidProfileInput(firstname,lastname,username,password,idCard,driving_license,phone,email)) {
				ClientBean clientBean=null;
				if(session.getAttribute("clientBean")==null)
					clientBean=new ClientBean();
				else clientBean=(ClientBean)session.getAttribute("clientBean");
				boolean isRegistered=clientBean.register(firstname, lastname, username, password, idCard, driving_license, phone, email);
				if(isRegistered)
					address="/WEB-INF/pages/login.jsp";
				else {
					session.setAttribute("alert-danger", "Something went wrong! Please try again!");
					address="/WEB-INF/pages/registration.jsp";
				}
			}
			else 
				address="/WEB-INF/pages/registration.jsp";
			
		}
		else if(action.equals("rent")) {
			String start=request.getParameter("startLocation");
			String end=request.getParameter("endLocation");
			String vehicle=request.getParameter("vehicle");
			String paymentInfo=request.getParameter("cardNumber");
			if(start!=null && end!=null && vehicle!=null && paymentInfo!=null) {
				LocationBean locationBean=new LocationBean();
				VehicleBean vBean=new VehicleBean();
				ClientBean clientBean=(ClientBean)session.getAttribute("clientBean");
				RentBean rentBean=(RentBean)session.getAttribute("rentBean");
				Location lStart=locationBean.insert(start);
				Location lEnd=locationBean.insert(end);
				Integer clientId=clientBean.getLoggedClient().getId();
				String vId=vBean.getFreeVehicle(vehicle);
				Integer distance=Integer.valueOf(DistanceCalculator.calculateDistance(lStart, lEnd));
				Double price=new PricingBean().getPrice(distance);
				
				boolean isCreated=false;
				if(vId!=null && distance!=null && price!=null)
					isCreated=rentBean.insert(clientId, lStart.getId(), lEnd.getId(), vId, price, paymentInfo);
				if(isCreated) {
					session.setAttribute("price", price);
					address="/WEB-INF/pages/driving.jsp";
				}
				else {
					session.setAttribute("alert-danger", "Something went wrong while creating a rent request!");
					address="/WEB-INF/pages/home.jsp";
				}
			}else address="/WEB-INF/pages/home.jsp";
		}else if(action.equals("driving")) {
			if(request.getParameter("submit")!=null)
				address="/WEB-INF/pages/home.jsp";
			else address="/WEB-INF/pages/driving.jsp";
		}
		else if(action.equals("profile")) {
			
			String update = request.getParameter("update");
			String deactivate = request.getParameter("deactivate");
		    if ("true".equals(update))  {
				String firstname=request.getParameter("firstName");
				String lastname=request.getParameter("lastName");
				String username=request.getParameter("username");
				String password=request.getParameter("password");
				String idCard=request.getParameter("id_card");
				String driving_license=request.getParameter("driving_license");
				String phone=request.getParameter("phone");
				String email=request.getParameter("email");
				
				if(isValidProfileInput(firstname,lastname,username,password,idCard,driving_license,phone,email)) {
					ClientBean clientBean=(ClientBean)session.getAttribute("clientBean");
					Client client=null;
					if(!password.equals(clientBean.getLoggedClient().getPassword())) {
						BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
						String encodedPass=passwordEncoder.encode(password);
						client=clientBean.update(firstname, lastname, username, encodedPass, idCard, driving_license, phone, email);
						
					}
					else client=clientBean.update(firstname, lastname, username, password, idCard, driving_license, phone, email);
						
					if(client!=null) {
						clientBean.setLoggedClient(client);
						session.setAttribute("alert-success", "Informations successfully updated!");
					}else session.setAttribute("alert-danger", "Something went wrong!");
							
					address="/WEB-INF/pages/profile.jsp";
					
				}
				
			}else if("true".equals(deactivate)) {
				ClientBean clientBean=(ClientBean)session.getAttribute("clientBean");
				boolean deactivated=clientBean.deactivate();
				if(deactivated) {
					session.invalidate();
					address="/WEB-INF/pages/login.jsp";
				}else {
					session.setAttribute("alert-danger", "Something went wrong while deactivating your profile!");
					address="/WEB-INF/pages/profile.jsp";
				}
					
			}else address="/WEB-INF/pages/profile.jsp";
		}
		else if(action.equals("logout")) {
			session.invalidate();
			address="/WEB-INF/pages/login.jsp";
		}
		
		RequestDispatcher dispatcher=request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
	private boolean isValidProfileInput(String firstname, String lastname, String username, String password, 
            String idCard, String drivingLicense, String phone, String email) {
		return firstname != null && !firstname.isEmpty() &&
		lastname != null && !lastname.isEmpty() &&
		username != null && !username.isEmpty() &&
		password != null && !password.isEmpty() &&
		idCard != null && !idCard.isEmpty() &&
		drivingLicense != null && !drivingLicense.isEmpty() &&
		phone != null && !phone.isEmpty() &&
		email != null && !email.isEmpty();
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
