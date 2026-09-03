package idv.tka10209.orders.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import idv.tka10209.orders.entity.Orders;
import idv.tka10209.util.HibernateUtil;

public class OrdersDAOImpl implements OrdersDAO {
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;
	
	public OrdersDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}
	
	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public List<Orders> getAll() {
		String sql = "Select * from Orders";
		return getSession().createNativeQuery(sql, Orders.class).getResultList();
	}

}
