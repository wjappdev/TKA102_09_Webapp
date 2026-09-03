package idv.tka10209.orders.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mysql.cj.util.StringUtils;

/**
 * Servlet implementation class OrdersServLet
 */
@WebServlet("/OrdersServLet")
public class OrdersServLet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String GET_ALL = "getAll";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdersServLet() {
        super();
        // TODO Auto-generated constructor stub
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
