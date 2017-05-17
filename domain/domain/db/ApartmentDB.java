package domain.db;

import java.util.*;

import domain.model.Apartment;

//Singleton DB
public final class ApartmentDB {
	
	private static volatile ApartmentDB instance = null;
	private static ArrayList<Apartment> apartments =  new ArrayList<Apartment>();
	//Some starter values in DB
	private static Apartment ap1 = new Apartment(0, 650, 2, "Spreeuwenstraat, Kessel-Lo", "http://www.immoweb.be/nl/zoekertje/appartement/te-huur/kessel-lo/3010/id6772319", 875);
	private static Apartment ap2 = new Apartment(1, 700, 2, "Heverlee", "http://www.immojanstas.be/nl/aanbod/detail.php?pageid=14&sort=&type=&id=6518", 456);
	private static int idCounter = 2;
	
	private ApartmentDB() {}
	
	public static ApartmentDB getDB() {
		if (instance == null) {
			synchronized(ApartmentDB.class) {
				if (instance == null) {
					instance = new ApartmentDB();
					apartments.add(ap1);
					apartments.add(ap2);
				}
			}
		}
		return instance;
	}
	
	public void addApartment(int price, int rooms, String address, String link, int casino) {
		Apartment apartment = new Apartment(idCounter, price, rooms, address, link, casino);
		idCounter++;
		apartments.add(apartment);
	}
	
	public void deleteApartment(String id) {
		for (int i=0; i<apartments.size(); i++) {
			if (apartments.get(i).getId() == Integer.parseInt(id)) {
				apartments.remove(i);
				return;
			}
		}
		apartments.remove(Integer.parseInt(id));
	}
	
	public Apartment getApartment(String id) {
		for (Apartment ap: apartments) {
			if (ap.getId() == Integer.parseInt(id)) {
				return ap;
			}
		}
		return null;
	}
	
	public Apartment getApartmentFromLink(String link) {
		for (Apartment apartment: apartments) {
			if(apartment.getLink().equals(link)) {
				return apartment;
			}
		}
		return null;
	}
	
	public void updateApartment(String id, String price, String rooms, String address, String link, String casino) {
		
		Apartment ap = this.getApartment(id);
		ap.setPrice(Integer.parseInt(price));
		ap.setRooms(Integer.parseInt(rooms));
		ap.setAddress(address);
		ap.setCasino(Integer.parseInt(casino));
	}
	
	public ArrayList<Apartment> getApartments() {
		return apartments;
	}
	
	public int getAvaragePrice() {
		int total = 0;
		int nrApp = 0;
		for (Apartment apartment: apartments) {
			nrApp++;
			total = total + apartment.getPrice();
		}
		return total/nrApp;
	}
	
	public int getTotalCasino() {
		int total = 0;
		for (Apartment apartment: apartments) {
			total = total + apartment.getCasino();
		}
		return total;
	}
	
}
