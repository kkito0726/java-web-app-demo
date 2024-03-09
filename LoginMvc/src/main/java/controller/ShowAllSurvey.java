package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.buisinessLogic.ExecuteShowAllSurveyBL;
import model.dtos.SurveyDto;

/**
 * Servlet implementation class ShowAllSurvey
 */
public class ShowAllSurvey extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowAllSurvey() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String tableGender(int g) {
		if (g == 1) {
			return "オス";
		}
		return "メス";
	}

	public String tableSatisficationLevel(int s) {
		switch (s) {
		case 1:
			return "とても不満";
		case 2:
			return "不満";
		case 3:
			return "普通";
		case 4:
			return "満足";
		case 5:
			return "とても満足";
		}
		return "";
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");

		ExecuteShowAllSurveyBL bl = new ExecuteShowAllSurveyBL();
		List<SurveyDto> surveys = bl.executeShowAllSurvey();
		List<String> genders = surveys.stream().map(survey -> tableGender(survey.getSex())).toList();
		List<String> satisficationLevels = surveys.stream()
				.map(survey -> tableSatisficationLevel(survey.getSatisfactionLevel())).toList();

		request.setAttribute("SURVEYS", surveys);
		request.setAttribute("GENDERS", genders);
		request.setAttribute("LEVELS", satisficationLevels);

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
