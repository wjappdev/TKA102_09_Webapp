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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.mysql.cj.util.StringUtils;

import idv.tka10209.orders.entity.Member;
import idv.tka10209.orders.entity.Orders;
import idv.tka10209.orders.service.OrdersService;
import idv.tka10209.orders.service.OrdersServiceImpl;

/**
 * Servlet implementation class OrdersServLet
 */
@WebServlet("/OrdersServLet")
public class OrdersServLet extends HttpServlet {
	private static final String DELETE = "delete";

	private static final String INSERT = "insert";

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ")
//		.append(request.getContextPath()).append("");
//		response.getWriter().append(request.getParameter("action"));

		// TODO: check request is valid
		switch (request.getParameter("action")) {
			case GET_ALL: {
				List<Orders> orderList = ordersService.getOrdersList();
				PrintWriter printWriter = response.getWriter();
				for (Orders orders : orderList) {
					printWriter.append(orders.getOrderId().toString() + "<br>");
					String name = orders.getMemberId().getMemberName();
					printWriter.append(name + "<br>");
				}
	
				break;
				
			}
				
			case INSERT: {
				// orders_id 為 AutoIncreament PK，由資料庫自動產生，不需要設定

				// ===== 原本從 request 取值的寫法，先保留不刪 =====
//				Member member = new Member();
//				member.setMemberId(Integer.valueOf(request.getParameter("memberId")));
//
//				Orders newOrders = new Orders();
//				newOrders.setMemberId(member);
//				newOrders.setShippingAddress(request.getParameter("shippingAddress"));
//				newOrders.setPaymentMethod(request.getParameter("paymentMethod"));
//				newOrders.setProductTotal(Integer.valueOf(request.getParameter("productTotal")));
//				newOrders.setDiscount(Integer.valueOf(request.getParameter("discount")));
//				newOrders.setShippingFee(Integer.valueOf(request.getParameter("shippingFee")));
//				newOrders.setShoppingCredit(Integer.valueOf(request.getParameter("shoppingCredit")));
//				newOrders.setActualPaymentAmount(Integer.valueOf(request.getParameter("actualPaymentAmount")));
//				newOrders.setReceiverName(request.getParameter("receiverName"));
//				newOrders.setEmail(request.getParameter("email"));
//				newOrders.setPhoneNumber(request.getParameter("phoneNumber"));
//				newOrders.setLogisticsNote(request.getParameter("logisticsNote"));
//				newOrders.setOrdersNote(request.getParameter("ordersNote"));
//				newOrders.setOrdersDate(LocalDateTime.now());
//				newOrders.setOrdersStatus(Byte.valueOf(request.getParameter("ordersStatus")));
//				newOrders.setEmployeeId(Integer.valueOf(request.getParameter("employeeId")));

				// ===== 測試用：直接寫死建立一個 Orders 物件 =====
				Member member = new Member();
				member.setMemberId(1);

				Orders newOrders = new Orders();
				newOrders.setMemberId(member);
				newOrders.setShippingAddress("台北市大安區羅斯福路四段1號");
				newOrders.setPaymentMethod("信用卡");
				newOrders.setProductTotal(1000);
				newOrders.setDiscount(100);
				newOrders.setShippingFee(60);
				newOrders.setShoppingCredit(0);
				newOrders.setActualPaymentAmount(960);
				newOrders.setReceiverName("王小明");
				newOrders.setEmail("test@example.com");
				newOrders.setPhoneNumber("0912345678");
				newOrders.setLogisticsNote("請於上班時間配送");
				newOrders.setOrdersNote("測試訂單");
				newOrders.setOrdersDate(LocalDateTime.now());
				newOrders.setOrdersStatus((byte) 0);
				newOrders.setEmployeeId(1);

				List<Orders> orderList = new LinkedList<>();
				orderList.add(newOrders);
				Integer insertedCount = ordersService.insert(orderList);

				response.getWriter().append("成功新增 " + insertedCount + " 筆訂單");

				break;
			}
			
			case DELETE: {
				List<Orders> orders = new ArrayList<>();
				Orders newOrders = new Orders();
				newOrders.setOrderId(4);
				orders.add(newOrders);

				try {
					Integer deletedCount = ordersService.delete(orders);
					response.getWriter().append("成功刪除 " + deletedCount + " 筆訂單");
				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().append("刪除失敗：找不到對應的訂單編號 (PK)");
				}

				break;
			}
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
