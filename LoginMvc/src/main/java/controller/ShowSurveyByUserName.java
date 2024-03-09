package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.buisinessLogic.ExecuteShowSurveybyUserName;
import model.dtos.SurveyDto;
import model.dtos.UserInfoDto;

/**
 * Servlet implementation class ShowSurveyByUserName
 */
public class ShowSurveyByUserName extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowSurveyByUserName() {
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
		String userName = userInfoOnSession.getUserName();

		ExecuteShowSurveybyUserName bl = new ExecuteShowSurveybyUserName();
		List<SurveyDto> surveys = bl.executeShowSurveyByUserName(userName);

		ShowAllSurvey sas = new ShowAllSurvey();
		List<String> genders = surveys.stream().map(survey -> sas.tableGender(survey.getSex())).toList();
		List<String> levels = surveys.stream().map(survey -> sas.tableSatisficationLevel(survey.getSatisfactionLevel()))
				.toList();

		request.setAttribute("SURVEYS", surveys);
		request.setAttribute("GENDERS", genders);
		request.setAttribute("LEVELS", levels);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/showAllSurvey.jsp");
		dispatcher.forward(request, response);
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
