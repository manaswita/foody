package main.foody.model;

public class DeliveryPartner {
	
	private int deliveryPartnerId;
	private CartesianCoordinates currentLocation;
	private boolean isAvaliable;
	private int noOfOrdersDeliveredToday;
	private float rating;
	
	
	public DeliveryPartner(int deliveryPartnerId, CartesianCoordinates currentLocation, boolean isAvaliable, int noOfOrdersDeliveredToday, float rating) {
		super();
		this.deliveryPartnerId = deliveryPartnerId;
		this.currentLocation = currentLocation;
		this.isAvaliable = isAvaliable;
		this.noOfOrdersDeliveredToday = noOfOrdersDeliveredToday;
		this.rating = rating;
	}
	public int getDeliveryPartnerId() {
		return deliveryPartnerId;
	}
	public CartesianCoordinates getCurrentLocation() {
		return currentLocation;
	}

	public boolean isAvaliable() {
		return isAvaliable;
	}
	public int getNoOfOrdersDeliveredToday() {
		return noOfOrdersDeliveredToday;
	}
	public float getRating() {
		return rating;
	}
	

	
	
}
