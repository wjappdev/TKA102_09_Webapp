package idv.tka10209.orders.filter;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import idv.tka10209.util.HibernateUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = { "/*" })
public class OpenSessionInViewFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		res.setContentType("text/html;charset=UTF-8");

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		System.out.println("filter open transaction");
		Transaction tx = session.beginTransaction();

		try {
			chain.doFilter(req, res); // 一個 request 只跑一次，不要在 catch 裡重跑
			tx.commit();
		} catch (Exception e) {
			// rollback 前先確認 transaction 還是 active，避免對已結束的 transaction 再 rollback 又丟一次例外
			if (tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new ServletException(e); // 往外拋給容器處理，不要吞掉後靜默重試
		}
	}

}
