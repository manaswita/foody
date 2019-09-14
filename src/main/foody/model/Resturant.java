package main.foody.model;

public class Resturant {

	private int resturantId;
	private String resturantName;
	private CartesianCoordinates resturantLocation;
	
	
	public Resturant(int resturantId, String resturantName, CartesianCoordinates resturantLocation) {
		super();
		this.resturantId = resturantId;
		this.resturantName = resturantName;
		this.resturantLocation = resturantLocation;
	}
	
	public int getResturantId() {
		return resturantId;
	}
	public String getResturantName() {
		return resturantName;
	}
	public CartesianCoordinates getResturantLocation() {
		return resturantLocation;
	}
	
	
}
