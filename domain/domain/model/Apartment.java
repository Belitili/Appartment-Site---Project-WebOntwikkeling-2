package domain.model;

import java.net.URL;

public class Apartment {
	
	private int id;
	
	private String price;
	private String rooms;
	private String address;
	private String link;
	private String casino;
	
	Apartment (int id, String price, String rooms, String address, String link, String casino) {
		setID(id);
		setPrice(price);
		setRooms(rooms);
		setAddress(address);
		setLink(link);
		setCasino(casino);
	}
	
	Apartment() {
		//empty stub constructor
	}

	private void setID(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.checkStringNotEmptyOrNull(price, "Prijs");
		this.checkIfNumberAndPositive(price, "Prijs");
		int priceInt = Integer.valueOf(price);
		if(priceInt < 200) {
			throw new IllegalArgumentException("Zo'n lage huurprijs kan niet, helaas!");
		}
		if(priceInt > 1000) {
			throw new IllegalArgumentException("Dat is te duur!");
		}
		this.price = price;
	}

	public String getRooms() {
		return rooms;
	}

	public void setRooms(String rooms) {
		this.checkStringNotEmptyOrNull(rooms, "Aantal kamers");
		this.checkIfNumberAndPositive(rooms, "Aantal kamers");
		if (Integer.valueOf(rooms)<1) {
			throw new IllegalArgumentException("Er moet minstens 1 slaapkamer zijn");
		}
		this.rooms = rooms;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.checkStringNotEmptyOrNull(address, "Adres");
		this.address = address;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.checkStringNotEmptyOrNull(link, "Link");
		if (!this.isValidURL(link)) {
			throw new IllegalArgumentException("Link moet valid URL zijn.");
		}
		this.link = link;
	}

	public String getCasino() {
		return casino;
	}

	public void setCasino(String casino) {
		this.checkStringNotEmptyOrNull(casino, "Casino");
		this.checkIfNumberAndPositive(casino, "Casino");
		this.casino = casino;
	}
	
	private void checkIfNumberAndPositive(String value, String key) {
		if (!(Integer.valueOf(value) instanceof Integer)) {
			throw new IllegalArgumentException(key + "moet een nummer zijn.");
		} else {
			if(Integer.valueOf(value) < 0) {
				throw new IllegalArgumentException(key + " moet positief getal zijn.");
			}
		}
	}
	
	private void checkStringNotEmptyOrNull(String value, String key) {
		if (value==null) {
			throw new IllegalArgumentException(key + " mag niet null zijn.");
		}
		if (value.trim().isEmpty()) {
			throw new IllegalArgumentException(key + " mag niet leeg zijn.");
		}
	}
	
	private boolean isValidURL(String urlString)
	{
	    try
	    {
	        URL url = new URL(urlString);
	        url.toURI();
	        return true;
	    } catch (Exception exception)
	    {
	        return false;
	    }
	}
	
	@Override
	public boolean equals(Object object) {
		if (object instanceof Apartment) {
			Apartment ap2 = (Apartment) object;
			if (this.getId() == ap2.getId()) {
				return true;
			}
		}
		return false;
	}

}
