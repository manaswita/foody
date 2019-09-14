package main.foody.model;

public class Order {

	private int orderId;
	private CartesianCoordinates deliveryLocation;
	
	
	public Order(int orderId, CartesianCoordinates deliveryLocation) {
		super();
		this.orderId = orderId;
		this.deliveryLocation = deliveryLocation;
	}
	public int getOrderId() {
		return orderId;
	}
	public CartesianCoordinates getDeliveryLocation() {
		return deliveryLocation;
	}
	
	
}
