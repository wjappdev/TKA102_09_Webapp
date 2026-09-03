package idv.tka10209.orders.service;

import java.util.List;

import idv.tka10209.orders.dao.OrdersDAO;
import idv.tka10209.orders.dao.OrdersDAOImpl;
import idv.tka10209.orders.entity.Orders;

public class OrdersServiceImpl implements OrdersService {
	private OrdersDAO ordersDAO;

	
	public OrdersServiceImpl() {
		ordersDAO = new OrdersDAOImpl();
	}

	@Override
	public List<Orders> getOrdersList() {
		return ordersDAO.getAll();
	}

	@Override
	public Integer insertOrders(List<Orders> orders) {
		return null;
	}

	@Override
	public Integer editOrders(List<Orders> orders) {
		return null;
	}

	@Override
	public void deleteOrders(List<Orders> orders) {
		
	}
	
	
}
