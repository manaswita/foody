package main.foody.client;

import java.util.List;
import java.util.PriorityQueue;

import main.foody.comparator.OrderComparator;
import main.foody.constant.FoodyConstants;
import main.foody.model.DeliveryPartner;
import main.foody.model.Order;
import main.foody.model.Resturant;

public class DeliveryClient {

	public static void main(String[] args) {
		
	}
	
	public static PriorityQueue<Order> addOrdersToTheQueue(List<Order> orderList) {
		
		PriorityQueue<Order> pq = new PriorityQueue<Order>(new OrderComparator());
		pq.addAll(orderList);
		return pq;
		
	}
	
	public static DeliveryPartner allocateADriver(Order order, Resturant resturant, List<DeliveryPartner> deliveryPartners ) {
		
		DeliveryPartner allocatedDriver = null;
		Double distanceFromResturant;
		Double distanceToOrderDestination;
		double maxPriority = 0 ;
		double currentPriority = 0;
		
		for(int i=0; i<deliveryPartners.size(); i++) {
			if(deliveryPartners.get(i).isAvaliable()) {
				distanceFromResturant = calculateDistanceBetweenPoints(deliveryPartners.get(i).getCurrentLocation().getX1(), deliveryPartners.get(i).getCurrentLocation().getY1(), 
						resturant.getResturantLocation().getX1(), resturant.getResturantLocation().getY1());
				distanceToOrderDestination = calculateDistanceBetweenPoints(resturant.getResturantLocation().getX1(), resturant.getResturantLocation().getY1(), 
						order.getDeliveryLocation().getX1(), order.getDeliveryLocation().getY1());
				currentPriority = calculatePriorityBasedOnReviewDistanceAndNoOfDeliveries(deliveryPartners.get(i), distanceFromResturant + distanceToOrderDestination);
				if(i == 0) {
					maxPriority = currentPriority;
					allocatedDriver = deliveryPartners.get(i);
					continue;
				}
				
				if(Double.compare(currentPriority, maxPriority) > 0){
					maxPriority = currentPriority;	
					allocatedDriver = deliveryPartners.get(i);
				}
			}
		}
		return allocatedDriver;
	}
	
	public static double calculatePriorityBasedOnReviewDistanceAndNoOfDeliveries(DeliveryPartner driver, double distance) {
		
		double priorityRating = 0;
		
		if(driver.getNoOfOrdersDeliveredToday() < FoodyConstants.MAX_NO_OF_DELIVERIES_ALLOWED) {
			priorityRating = driver.getRating() / (driver.getNoOfOrdersDeliveredToday() + distance);
		}
		return priorityRating;
	}
	
	private static double calculateDistanceBetweenPoints(double x1, double y1, double x2, double y2) {       
	    return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
	}

}
