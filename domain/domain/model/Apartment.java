package domain.model;

public class Apartment {
	
	private int id;
	
	private int price;
	private int rooms;
	private String address;
	private String link;
	private int casino;
	
	public Apartment (int id, int price, int rooms, String address, String link, int casino) {
		setID(id);
		setPrice(price);
		setRooms(rooms);
		setAddress(address);
		setLink(link);
		setCasino(casino);
	}

	private void setID(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getCasino() {
		return casino;
	}

	public void setCasino(int casino) {
		this.casino = casino;
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
