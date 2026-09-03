package idv.tka10209.orders.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Documented;
import java.util.Iterator;
import java.util.List;

import com.mysql.cj.util.StringUtils;

import idv.tka10209.orders.entity.Orders;
import idv.tka10209.orders.service.OrdersService;
import idv.tka10209.orders.service.OrdersServiceImpl;

/**
 * Servlet implementation class OrdersServLet
 */
@WebServlet("/OrdersServLet")
public class OrdersServLet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String GET_ALL = "getAll";
	private OrdersService ordersService = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdersServLet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ordersService = new OrdersServiceImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ")
//		.append(request.getContextPath()).append("");
		
		
		//TODO: check request is valid
		switch (request.getParameter("action")) {
		case GET_ALL:
			List<Orders> orderList = ordersService.getOrdersList();
			PrintWriter printWriter = response.getWriter();
			for (Orders orders : orderList) {
				printWriter.append(orders.getOrderId().toString() + "<br>");
			}
			
			response.getWriter().append(request.getParameter("action"));
			break;

		default:
			break;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
