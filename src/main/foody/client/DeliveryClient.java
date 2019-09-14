package main.foody.client;

import java.util.List;

import main.foody.model.DeliveryPartner;
import main.foody.model.Order;
import main.foody.model.Resturant;

public class DeliveryClient {

	public static void main(String[] args) {
		
		
		

	}
	
	public static DeliveryPartner allocateADriver(Order order, Resturant resturant, List<DeliveryPartner> deliveryPartners ) {
		
		DeliveryPartner allocatedDriver = null;
		Double distanceFromResturant;
		Double distanceToOrderDestination;
		double minimumDistance = 0 ;
		
		for(int i=0; i<deliveryPartners.size(); i++) {
			if(deliveryPartners.get(i).isAvaliable()) {
				distanceFromResturant = calculateDistanceBetweenPoints(deliveryPartners.get(i).getCurrentLocation().getX1(), deliveryPartners.get(i).getCurrentLocation().getY1(), 
						resturant.getResturantLocation().getX1(), resturant.getResturantLocation().getY1());
				distanceToOrderDestination = calculateDistanceBetweenPoints(resturant.getResturantLocation().getX1(), resturant.getResturantLocation().getY1(), 
						order.getDeliveryLocation().getX1(), order.getDeliveryLocation().getY1());
				if(i == 0) {
					minimumDistance = distanceFromResturant + distanceToOrderDestination;
					allocatedDriver = deliveryPartners.get(i);
					continue;
				}
				
				if(Double.compare((distanceFromResturant + distanceToOrderDestination), minimumDistance) < 0){
					minimumDistance = distanceFromResturant + distanceToOrderDestination;	
					allocatedDriver = deliveryPartners.get(i);
				}
			}
		}
		return allocatedDriver;
	}
	
	private static double calculateDistanceBetweenPoints(
			  double x1, 
			  double y1, 
			  double x2, 
			  double y2) {       
			    return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
			}

}
