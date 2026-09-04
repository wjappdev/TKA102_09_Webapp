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
		String sql = "SELECT O FROM Orders O JOIN FETCH O.memberId";
		return getSession().createQuery(sql, Orders.class).getResultList();
	}

	@Override
	public Integer insert(List<Orders> orders) {
		Session session = getSession();

		for (Orders entity : orders) {
			session.persist(entity);
		}

		session.flush(); // 讓 Hibernate 立刻送出 INSERT SQL，並把 AI 主鍵回填到 orderId

		// flush 沒有丟例外的話，這裡每一筆的 orderId 都應該已經有值
		long successCount = orders.stream()
				.filter(o -> o.getOrderId() != null)
				.count();

		return (int) successCount;
	}

	@Override
	public Integer delete(List<Orders> orders) {
		Session session = getSession();
		int successCount = 0;

		for (Orders entity : orders) {
			session.remove(entity);
			session.flush(); // 立刻送出這一筆 DELETE，失敗只影響這一筆，不用事先多查一次
			successCount++;
		}

		return successCount;
	}

}
