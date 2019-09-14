package main.foody.model;

public class DeliveryPartner {
	
	private int deliveryPartnerId;
	private CartesianCoordinates currentLocation;
	private boolean isAvaliable;
	
	
	
	public DeliveryPartner(int deliveryPartnerId, CartesianCoordinates currentLocation, boolean isAvaliable) {
		super();
		this.deliveryPartnerId = deliveryPartnerId;
		this.currentLocation = currentLocation;
		this.isAvaliable = isAvaliable;
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

	
	
}
