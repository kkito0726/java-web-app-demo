package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dtos.UserInfoDto;

/**
 * Servlet implementation class ExecuteLogout
 */
public class ExecuteLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteLogout() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");

		if (userInfoOnSession != null) {
			session.invalidate();

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/logout.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("Login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
