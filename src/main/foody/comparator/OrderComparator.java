package main.foody.comparator;

import java.util.Comparator;

import main.foody.model.Order;

public class OrderComparator implements Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2) {
		if (o1.getFinalDeliveryTime().isBefore(o2.getFinalDeliveryTime()))
			return -1;
		else
			return 1;
	}
}
