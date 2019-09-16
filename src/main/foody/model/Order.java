package main.foody.model;

import org.joda.time.LocalTime;

public class Order {

	private int orderId;
	private CartesianCoordinates deliveryLocation;
	private int preparationTime;
	private LocalTime orderedTime;
	private LocalTime finalDeliveryTime;

	public Order(int orderId, CartesianCoordinates deliveryLocation, int preparationTime, LocalTime orderedTime) {
		super();
		this.orderId = orderId;
		this.deliveryLocation = deliveryLocation;
		this.preparationTime = preparationTime;
		this.orderedTime = orderedTime;
	}

	public int getOrderId() {
		return orderId;
	}

	public CartesianCoordinates getDeliveryLocation() {
		return deliveryLocation;
	}

	public int getPreparationTime() {
		return preparationTime;
	}

	public LocalTime getOrderedTime() {
		return orderedTime;
	}

	public LocalTime getFinalDeliveryTime() {
		return finalDeliveryTime;
	}

	public void setFinalDeliveryTime() {
		LocalTime time = this.orderedTime.plusMinutes(this.preparationTime);
		this.finalDeliveryTime =time;
	}

}
