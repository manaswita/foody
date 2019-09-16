package main.foody.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;

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
		p1 = new DeliveryPartner(5, new CartesianCoordinates(new Double(0), new Double(120)), false, 6, new Float(5));
		p2 = new DeliveryPartner(2, new CartesianCoordinates(new Double(0), new Double(50)), true, 2, new Float(3.2));
		p3 = new DeliveryPartner(3, new CartesianCoordinates(new Double(0), new Double(100)), true, 10, new Float(4.0));
		p4 = new DeliveryPartner(4, new CartesianCoordinates(new Double(0), new Double(100)), true, 3, new Float(3.3));
		p5 = new DeliveryPartner(5, new CartesianCoordinates(new Double(0), new Double(120)), true, 6, new Float(5));

		p6 = new DeliveryPartner(6, new CartesianCoordinates(new Double(0), new Double(150)), false, 4, new Float(4.1));
		p7 = new DeliveryPartner(7, new CartesianCoordinates(new Double(0), new Double(150)), true, 8, new Float(4.6));
		

		deliveryPartners.add(p1);
		deliveryPartners.add(p2);
		deliveryPartners.add(p3);
		deliveryPartners.add(p4);
		deliveryPartners.add(p5);
		deliveryPartners.add(p6);
		deliveryPartners.add(p7);
	}

	@Test
	public void testFinalDeliveryTimeForOrderHappyFlow() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm:ss");
		Order order1 = new Order(1, new CartesianCoordinates(new Double(0), new Double(0)), 30,
				formatter.parseLocalTime("14:10:30"));
		order1.setFinalDeliveryTime();
		assertEquals(formatter.parseLocalTime("14:40:30"), order1.getFinalDeliveryTime());

	}

	@Test
	public void testFinalDeliveryTimeForOrderOverflowCase() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm:ss");
		Order order1 = new Order(1, new CartesianCoordinates(new Double(0), new Double(0)), 30,
				formatter.parseLocalTime("14:40:30"));
		order1.setFinalDeliveryTime();
		assertEquals(formatter.parseLocalTime("15:10:30"), order1.getFinalDeliveryTime());

	}

	@Test
	public void testIfOrdersAreSortedByTime() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm:ss");
		Order order1 = new Order(1, new CartesianCoordinates(new Double(0), new Double(0)), 55,
				formatter.parseLocalTime("14:10:30"));
		order1.setFinalDeliveryTime();
		Order order2 = new Order(2, new CartesianCoordinates(new Double(0), new Double(10)), 40,
				formatter.parseLocalTime("14:12:15"));
		order2.setFinalDeliveryTime();
		Order order3 = new Order(3, new CartesianCoordinates(new Double(0), new Double(20)), 45,
				formatter.parseLocalTime("14:07:22"));
		order3.setFinalDeliveryTime();
		Order order4 = new Order(4, new CartesianCoordinates(new Double(0), new Double(30)), 43,
				formatter.parseLocalTime("14:05:33"));
		order4.setFinalDeliveryTime();
		Order order5 = new Order(5, new CartesianCoordinates(new Double(0), new Double(40)), 20,
				formatter.parseLocalTime("14:35:20"));
		order5.setFinalDeliveryTime();
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(order1);
		orderList.add(order2);
		orderList.add(order3);
		orderList.add(order4);
		orderList.add(order5);
		DeliveryClient client = new DeliveryClient();
		PriorityQueue<Order> pq = client.addOrdersToTheQueue(orderList);
		Order firstOrder = pq.peek();
		assertEquals(order4, firstOrder);
	}
	
	@Test
	public void testAllocateDriverHappyFlow() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm:ss");
		Order order1 = new Order(1, new CartesianCoordinates(new Double(0), new Double(0)), 55,
				formatter.parseLocalTime("14:10:30"));
		order1.setFinalDeliveryTime();

		DeliveryClient deliveryClient = new DeliveryClient();
		Resturant resturant = new Resturant(1, "Stoner", new CartesianCoordinates(new Double(0), new Double(90)));
		DeliveryPartner allocatedDriver = deliveryClient.allocateADriver(order1, resturant, deliveryPartners);
		assertEquals(p5, allocatedDriver);

	}
	@Test
	public void allocatingDriverWhenTwoAgentsHaveEqualPriorityButOneOfThemIsUnavailable() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm:ss");
		Order order1 = new Order(1, new CartesianCoordinates(new Double(0), new Double(0)), 55,
				formatter.parseLocalTime("14:10:30"));
		order1.setFinalDeliveryTime();

		DeliveryClient deliveryClient = new DeliveryClient();
		Resturant resturant = new Resturant(1, "Stoner", new CartesianCoordinates(new Double(0), new Double(90)));
		DeliveryPartner allocatedDriver = deliveryClient.allocateADriver(order1, resturant, deliveryPartners);
		assertEquals(p5, allocatedDriver);

	}

}
