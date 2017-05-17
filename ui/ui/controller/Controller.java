package ui.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.db.ApartmentDB;
import domain.model.Apartment;

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
		ApartmentDB db = ApartmentDB.getDB();
		ArrayList<Apartment> apartments = db.getApartments();
		
		request.setAttribute("totCasino", Double.toString(db.getTotalCasino()));
		request.setAttribute("apartments", apartments);
		RequestDispatcher view = request.getRequestDispatcher("overview.jsp");
		view.forward(request, response);
	}
	
	protected void addApartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> errors = new ArrayList<String>();
		
		String link = request.getParameter("linkAppt");
		ApartmentDB db = ApartmentDB.getDB();
		if (db.getApartmentFromLink(link)!=null) {
			errors.add("Er is al een appartement met deze link.");
		}
		
		if (Integer.parseInt(request.getParameter("huurprijs"))<200) {
			errors.add("Zo'n lage huurprijs kan niet, helaas!");
		}
		
		RequestDispatcher view = request.getRequestDispatcher("form.jsp");
		
		if (errors.size() == 0) {
			boolean jackpot = false;
			int casino = Integer.parseInt(request.getParameter("casino"));
			if (casino >= 1000) {
				jackpot = true;
			}
			
			view = request.getRequestDispatcher("formConfirm.jsp");
			if (jackpot) {
				//view overschreven met jackpot.jsp ipv formConfirm.jsp
				view = request.getRequestDispatcher("jackpot.jsp");
			} else {
				//Apartment enkel toevoegen bij niet jackpot
				
				db.addApartment(Integer.parseInt(request.getParameter("huurprijs")), Integer.parseInt(request.getParameter("aantalSlaapkamers")), request.getParameter("adres"), request.getParameter("linkAppt"), Integer.parseInt(request.getParameter("casino")));
			}
		}
		
		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
		}
		
		view.forward(request, response);
	}
	
	protected void deleteApartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApartmentDB db = ApartmentDB.getDB();
		String id = request.getParameter("id");
		db.deleteApartment(id);
		
		showApartments(request, response);
	}
	
	protected void updateApartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApartmentDB db = ApartmentDB.getDB();
		String id = request.getParameter("id");
		Apartment ap = db.getApartment(id);
		
		request.setAttribute("apartmentToUpdate", ap);
		
		RequestDispatcher view = request.getRequestDispatcher("formUpdate.jsp");
		view.forward(request, response);
	}
	
	protected void updateValuesApartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApartmentDB db = ApartmentDB.getDB();
		List<String> errors = new ArrayList<String>();
		
		if (Integer.parseInt(request.getParameter("huurprijs"))<200) {
			errors.add("Zo'n lage huurprijs kan niet, helaas! Zelfs slechts 1 kamer is zeker 200 euro.");
		}
		
		RequestDispatcher view = request.getRequestDispatcher("form.jsp");
		
		if (errors.size() == 0) {
			String link = request.getParameter("linkAppt");
			Apartment ap = db.getApartmentFromLink(link);
			db.updateApartment(Integer.toString(ap.getId()), request.getParameter("huurprijs"), request.getParameter("aantalSlaapkamers"), request.getParameter("adres"), ap.getLink(), request.getParameter("casino"));
			showApartments(request, response);
		} else {
			request.setAttribute("errors", errors);
		}
		
		view.forward(request, response);
	}
}
