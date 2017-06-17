package domain.model;

public class ApartmentFactory {
	
	private static int idCounter = 0;
	
	public static Apartment createApartment(String price, String rooms, String address, String link, String casino) {
		return new Apartment(idCounter++, price, rooms, address, link, casino);
	}
	
	public static Apartment createApartment() {
		return new Apartment();
	}

}
