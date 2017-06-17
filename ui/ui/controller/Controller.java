package ui.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.db.ApartmentDB;
import domain.model.Apartment;
import domain.model.ApartmentFactory;
import javafx.util.Pair;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApartmentDB db = ApartmentDB.getDB();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest (HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String action = request.getParameter("action"); 
		switch(action) {
			case "read" :
				showApartments(request, response);
				break;
			case "create" :
				addApartment(request, response);
				break;
			case "delete" :
				deleteApartment(request,response);
				break;
			case "deleteConfirm" :
				deleteConfirm(request,response);
				break;
			case "search" :
				searchApartment(request,response);
				break;
			case "update" :
				updateApartment(request,response);
				break;
			case "updateValuesApartment" :
				updateValuesApartment(request,response);
				break;
			default: 
				RequestDispatcher view = request.getRequestDispatcher("index.jsp"); 
				view.forward(request, response);
		}
	}
	
	protected void showApartments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Apartment> apartments = db.getApartments();
		
		request.setAttribute("totCasino", Double.toString(db.getTotalCasino()));
		request.setAttribute("apartments", apartments);
		RequestDispatcher view = request.getRequestDispatcher("overview.jsp");
		view.forward(request, response);
	}
	
	protected void addApartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		Pair<Map<String,String>, Apartment> result = this.createErrorList(request);
		Map<String,String> errors = result.getKey();
		Apartment newAp = result.getValue();
		
		RequestDispatcher view = request.getRequestDispatcher("form.jsp");
		
		if (errors.size() == 0) {
			boolean jackpot = false;
			int casinoInt = Integer.parseInt(request.getParameter("casino"));
			if (casinoInt >= 1000) {
				jackpot = true;
			}
			
			view = request.getRequestDispatcher("formConfirm.jsp");
			if (jackpot) {
				//view overschreven met jackpot.jsp ipv formConfirm.jsp
				view = request.getRequestDispatcher("jackpot.jsp");
			} else {
				//Apartment enkel toevoegen bij niet jackpot
				
				try {
					db.addApartment(request.getParameter("huurprijs"), request.getParameter("aantalSlaapkamers"), request.getParameter("adres"), request.getParameter("linkAppt"), request.getParameter("casino"));
				} catch (Exception e) {
					errors.put("linkAppt",e.getMessage());
				}
			}
		}
		
		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			request.setAttribute("apartment", newAp);
		}
		
		view.forward(request, response);
	}
	
	protected void deleteApartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		request.setAttribute("ap", db.getApartment(id));
		RequestDispatcher view = request.getRequestDispatcher("deleteConfirm.jsp");
		view.forward(request, response);
	}
	
	protected void deleteConfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		db.deleteApartment(id);
		
		showApartments(request, response);
	}
	
	protected void searchApartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String link = request.getParameter("linkAppt");
		Apartment ap = db.getApartmentFromLink(link);
		
		if (ap!=null) {
			request.setAttribute("foundApp", ap);
		} else {
			request.setAttribute("error", "Geen apartement gevonden met deze link.");
		}
		
		RequestDispatcher view = request.getRequestDispatcher("search.jsp");
		view.forward(request, response);
	}
	
	protected void updateApartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Apartment ap = db.getApartment(id);
		
		request.setAttribute("apartmentToUpdate", ap);
		
		RequestDispatcher view = request.getRequestDispatcher("formUpdate.jsp");
		view.forward(request, response);
	}
	
	protected void updateValuesApartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> errors = this.createErrorList(request).getKey();

		String link = request.getParameter("linkAppt");
		Apartment ap = db.getApartmentFromLink(link);
		
		if (errors.size() == 0) {
			db.updateApartment(Integer.toString(ap.getId()), request.getParameter("huurprijs"), request.getParameter("aantalSlaapkamers"), request.getParameter("adres"), ap.getLink(), request.getParameter("casino"));
			showApartments(request, response);
		} else {
			request.setAttribute("apartmentToUpdate", ap);
			request.setAttribute("errors", errors);
			RequestDispatcher view = request.getRequestDispatcher("formUpdate.jsp");
			view.forward(request, response);
		}
	}
	
	private Pair<Map<String, String>, Apartment> createErrorList(HttpServletRequest request) {
		String price = request.getParameter("huurprijs");
		String rooms = request.getParameter("aantalSlaapkamers");
		String address = request.getParameter("adres");
		String link = request.getParameter("linkAppt");
		String casino = request.getParameter("casino");
		Map<String, String> errors = new HashMap<String, String>();
		
		Apartment newAp = ApartmentFactory.createApartment();
		
		try {
			newAp.setPrice(price);
		} catch (Exception e) {
			errors.put("huurprijs", e.getMessage());
		}
		try {
			newAp.setRooms(rooms);
		} catch (Exception e) {
			errors.put("aantalSlaapkamers", e.getMessage());
		}
		try {
			newAp.setAddress(address);
		} catch (Exception e) {
			errors.put("adres", e.getMessage());
		}
		try {
			newAp.setLink(link);;
		} catch (Exception e) {
			errors.put("linkAppt", e.getMessage());
		}
		try {
			newAp.setCasino(casino);
		} catch (Exception e) {
			errors.put("casino", e.getMessage());
		}
		
		return new Pair<>(errors,newAp);
	}
}
