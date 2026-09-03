package idv.tka10209.orders.service;

import java.util.List;

import idv.tka10209.orders.entity.Orders;

public interface OrdersService {
	List<Orders> getOrdersList();
	Integer insertOrders(List<Orders> orders);
	Integer editOrders(List<Orders> orders);
	void deleteOrders(List<Orders> orders);
}
