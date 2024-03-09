package controller;

import java.io.IOException;
import java.sql.Timestamp;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.buisinessLogic.ExecuteInsertBL;
import model.dtos.SurveyDto;
import model.dtos.UserInfoDto;

/**
 * Servlet implementation class SaveSurvey
 */
public class SaveSurvey extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveSurvey() {
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
		// TODO Auto-generated method stub
		//レスポンス（出力データ）の文字コードを設定
		response.setContentType("text/html;charset=UTF-8"); //文字コードをUTF-8で設定
		//リクエスト（受信データ）の文字コードを設定
		request.setCharacterEncoding("UTF-8"); //文字コードをUTF-8で設定

		boolean isSuccess = true;

		HttpSession session = request.getSession();
		UserInfoDto userInfoOnSession = (UserInfoDto) session.getAttribute("LOGIN_INFO");

		//リクエストパラメータを取得
		int age = Integer.parseInt(request.getParameter("AGE")); //リクエストパラメータ（AGE）
		int sex = Integer.parseInt(request.getParameter("SEX")); //リクエストパラメータ（SEX）
		int satisfactionLevel = Integer.parseInt(request.getParameter("SATISFACTION_LEVEL")); //リクエストパラメータ（SATISFACTION_LEVEL）
		String message = request.getParameter("MESSAGE"); //リクエストパラメータ（MESSAGE）

		// ログイン中であればuserNameで登録
		String name = null;
		if (userInfoOnSession.getUserName() != null) {
			name = userInfoOnSession.getUserName();
		}

		//アンケートデータ（SurveyDto型）の作成
		SurveyDto dto = new SurveyDto();
		dto.setName(name);
		dto.setAge(age);
		dto.setSex(sex);
		dto.setSatisfactionLevel(satisfactionLevel);
		dto.setMessage(message);
		dto.setTime(new Timestamp(System.currentTimeMillis())); //現在時刻を更新時刻として設定

		if (dto.getName().equals("") || String.valueOf(dto.getAge()).equals("") || dto.getMessage().equals("")) {
			isSuccess = false;
		}
		if (dto.getAge() < 0) {
			isSuccess = false;
		}
		if (dto.getSex() < 1 || 2 < dto.getSex()) {
			isSuccess = false;
		}
		if (dto.getSatisfactionLevel() < 1 || 5 < dto.getSatisfactionLevel()) {
			isSuccess = false;
		}
		if (isSuccess) {
			//アンケートデータをDBに登録
			ExecuteInsertBL logic = new ExecuteInsertBL();
			boolean succesInsert = logic.executeInsertSurvey(dto); //DB操作成功フラグ（true:成功/false:失敗）

			//DB操作の成功/失敗に応じて表示させる画面を振り分ける
			if (succesInsert) {

				//DB登録に成功した場合、回答完了画面（finish.html）を表示する
				response.sendRedirect("htmls/finish.html");

			} else {

				//DB登録に失敗した場合、エラー画面（error.html）を表示する
				response.sendRedirect("htmls/error.html");

			}
		} else {
			response.sendRedirect("htmls/error.html");
		}
	}

}
