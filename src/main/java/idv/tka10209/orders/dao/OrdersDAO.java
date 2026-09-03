package idv.tka10209.orders.dao;

import java.util.List;

import idv.tka10209.orders.entity.Orders;

public interface OrdersDAO {
	List<Orders> getAll();
}
