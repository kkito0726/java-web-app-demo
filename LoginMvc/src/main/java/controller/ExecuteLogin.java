package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.buisinessLogic.ExecuteLoginBL;
import model.dtos.UserInfoDto;

/**
 * Servlet implementation class ExecuteLogin
 */
public class ExecuteLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//レスポンス（出力データ）の文字コードを設定
		response.setContentType("text/html;charset=UTF-8"); //文字コードをUTF-8で設定
		//リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");

		if (userInfoOnSession != null) {
			response.sendRedirect("InputSurvey");

		} else {
			String userId = request.getParameter("USER_ID");
			String password = request.getParameter("PASSWORD");

			ExecuteLoginBL bl = new ExecuteLoginBL();
			UserInfoDto user = bl.executeSelectUserInfo(userId, password);

			if (user.getUserId() != null) {
				session.setAttribute("LOGIN_INFO", user);
				response.sendRedirect("InputSurvey");
			} else {
				response.sendRedirect("Login");
			}
		}
	}

}
