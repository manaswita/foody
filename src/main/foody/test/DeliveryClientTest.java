package main.foody.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import main.foody.client.DeliveryClient;
import main.foody.model.CartesianCoordinates;
import main.foody.model.DeliveryPartner;
import main.foody.model.Order;
import main.foody.model.Resturant;

public class DeliveryClientTest {

	private static final List<DeliveryPartner> deliveryPartners = new ArrayList<DeliveryPartner>();
	private DeliveryPartner p1, p2, p3, p4, p5, p6, p7;

	@Before
	public void setup() {
		p1 = new DeliveryPartner(1, new CartesianCoordinates(new Double(0), new Double(20)), true);
		p2 = new DeliveryPartner(2, new CartesianCoordinates(new Double(0), new Double(50)), true);
		p3 = new DeliveryPartner(3, new CartesianCoordinates(new Double(0), new Double(100)),true);
		p4 = new DeliveryPartner(4, new CartesianCoordinates(new Double(0), new Double(100)),true);
		p5 = new DeliveryPartner(5, new CartesianCoordinates(new Double(0), new Double(120)),true);
		
		p6 = new DeliveryPartner(6, new CartesianCoordinates(new Double(0), new Double(150)),false);
		p7 = new DeliveryPartner(7, new CartesianCoordinates(new Double(0), new Double(150)),true);

		deliveryPartners.add(p1);
		deliveryPartners.add(p2);
		deliveryPartners.add(p3);	
		deliveryPartners.add(p4);
		deliveryPartners.add(p5);
		deliveryPartners.add(p6);
		deliveryPartners.add(p7);
	}
	
	@Test
	public void allocatingDriverTestHappyFlow() {
		DeliveryClient deliveryClient = new DeliveryClient();
		Order order = new Order(1, new CartesianCoordinates(new Double(0), new Double(0)));
		Resturant resturant = new Resturant(1, "Hyderbad Biryani",
				new CartesianCoordinates(new Double(0), new Double(30)));
		DeliveryPartner allocatedDriver = deliveryClient.allocateADriver(order, resturant, deliveryPartners);
		assertEquals(p1, allocatedDriver);

	}
	
	@Test
	public void allocatingDriverWhenTwoAgentsHaveEqualDistance() {
		DeliveryClient deliveryClient = new DeliveryClient();
		Order order = new Order(1, new CartesianCoordinates(new Double(0), new Double(0)));
		Resturant resturant = new Resturant(1, "Stoner",
				new CartesianCoordinates(new Double(0), new Double(90)));
		DeliveryPartner allocatedDriver = deliveryClient.allocateADriver(order, resturant, deliveryPartners);
		assertEquals(p3, allocatedDriver);

	}
	@Test
	public void allocatingDriverWhenTwoAgentsHaveEqualDistanceButOneOfThemIsUnavailable() {
		DeliveryClient deliveryClient = new DeliveryClient();
		Order order = new Order(1, new CartesianCoordinates(new Double(0), new Double(140)));
		Resturant resturant = new Resturant(1, "Corner House",
				new CartesianCoordinates(new Double(0), new Double(160)));
		DeliveryPartner allocatedDriver = deliveryClient.allocateADriver(order, resturant, deliveryPartners);
		assertEquals(p7, allocatedDriver);

	}
}
