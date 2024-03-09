package model.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dtos.SurveyDto;

public class SurveyDao {
	//-------------------------------------------
	//データベースへの接続情報
	//-------------------------------------------

	//JDBCドライバの相対パス
	//※バージョンによって変わる可能性があります（MySQL5系の場合は「com.mysql.jdbc.Driver」）
	String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	//接続先のデータベース
	//※データベース名が「test_db」でない場合は該当の箇所を変更してください
	String JDBC_URL = "jdbc:mysql://localhost/test_db?characterEncoding=UTF-8&serverTimezone=JST&useSSL=false";

	//接続するユーザー名
	//※ユーザー名が「test_user」でない場合は該当の箇所を変更してください
	String USER_ID = "test_user";

	//接続するユーザーのパスワード
	//※パスワードが「test_pass」でない場合は該当の箇所を変更してください
	String USER_PASS = "test_pass";

	//----------------------------------------------------------------
	//メソッド
	//----------------------------------------------------------------

	/**----------------------------------------------------------------------*
	 *■doInsertメソッド
	 *概要　：「survey」テーブルに対象のアンケートデータを挿入する
	 *引数　：対象のアンケートデータ（SurveyDto型）
	 *戻り値：実行結果（真：成功、偽：例外発生）
	 *----------------------------------------------------------------------**/
	public boolean doInsert(SurveyDto dto) {

		//-------------------------------------------
		//SQL発行
		//-------------------------------------------

		//JDBCの接続に使用するオブジェクトを宣言
		//※finallyブロックでも扱うためtryブロック内で宣言してはいけないことに注意
		Connection con = null; // Connection（DB接続情報）格納用変数
		PreparedStatement ps = null; // PreparedStatement（SQL発行用オブジェクト）格納用変数

		//実行結果（真：成功、偽：例外発生）格納用変数
		//※最終的にreturnするため、tryブロック内で宣言してはいけないことに注意
		boolean isSuccess = true;

		checkMysqlDriver();

		try {

			//-------------------------------------------
			//接続の確立（Connectionオブジェクトの取得）
			//-------------------------------------------
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			//-------------------------------------------
			//トランザクションの開始
			//-------------------------------------------
			//オートコミットをオフにする（トランザクション開始）
			con.setAutoCommit(false);

			//-------------------------------------------
			//SQL文の送信 ＆ 結果の取得
			//-------------------------------------------

			//発行するSQL文の生成（INSERT）
			StringBuffer buf = new StringBuffer();
			buf.append("INSERT INTO SURVEY (  ");
			buf.append("  NAME,               ");
			buf.append("  AGE,                ");
			buf.append("  SEX,                ");
			buf.append("  SATISFACTION_LEVEL, ");
			buf.append("  MESSAGE,            ");
			buf.append("  TIME                ");
			buf.append(") VALUES (            ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?                   ");
			buf.append(")                     ");

			//PreparedStatementオブジェクトを生成＆発行するSQLをセット
			ps = con.prepareStatement(buf.toString());

			//パラメータをセット
			ps.setString(1, dto.getName()); //第1パラメータ：更新データ（名前）
			ps.setInt(2, dto.getAge()); //第2パラメータ：更新データ（年齢）
			ps.setInt(3, dto.getSex()); //第3パラメータ：更新データ（性別）
			ps.setInt(4, dto.getSatisfactionLevel()); //第4パラメータ：更新データ（満足度）
			ps.setString(5, dto.getMessage()); //第5パラメータ：更新データ（メッセージ）
			ps.setTimestamp(6, dto.getTime()); //第6パラメータ：更新データ（更新時刻）

			//SQL文の実行
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

			//実行結果を例外発生として更新
			isSuccess = false;

		} finally {
			terminateDriver(isSuccess, con, ps);
		}
		//実行結果を返す
		return isSuccess;

	}

	public List<SurveyDto> selectAll() {
		Connection con = null; // Connection（DB接続情報）格納用変数
		PreparedStatement ps = null; // PreparedStatement（SQL発行用オブジェクト）格納用変数
		ResultSet resultSet = null;

		List<SurveyDto> surveys = new ArrayList<SurveyDto>();

		boolean isSuccess = true;

		checkMysqlDriver();

		try {

			//-------------------------------------------
			//接続の確立（Connectionオブジェクトの取得）
			//-------------------------------------------
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			//-------------------------------------------
			//トランザクションの開始
			//-------------------------------------------
			//オートコミットをオフにする（トランザクション開始）
			con.setAutoCommit(false);

			//-------------------------------------------
			//SQL文の送信 ＆ 結果の取得
			//-------------------------------------------

			//発行するSQL文の生成（INSERT）
			String sql = " SELECT * FROM SURVEY ; ";

			//PreparedStatementオブジェクトを生成＆発行するSQLをセット
			ps = con.prepareStatement(sql);

			//SQL文の実行
			resultSet = ps.executeQuery();

			while (resultSet.next()) {
				SurveyDto survey = new SurveyDto();
				survey.setName(resultSet.getString("NAME"));
				survey.setAge(resultSet.getInt("AGE"));
				survey.setSex(resultSet.getInt("SEX"));
				survey.setSatisfactionLevel(resultSet.getInt("SATISFACTION_LEVEL"));
				survey.setMessage(resultSet.getString("MESSAGE"));
				survey.setTime(resultSet.getTimestamp("TIME"));
				surveys.add(survey);
			}

		} catch (SQLException e) {
			e.printStackTrace();

			//実行結果を例外発生として更新
			isSuccess = false;

		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			terminateDriver(isSuccess, con, ps);
		}

		return surveys;
	}

	public List<SurveyDto> selectByName(String userName) {
		Connection con = null; // Connection（DB接続情報）格納用変数
		PreparedStatement ps = null; // PreparedStatement（SQL発行用オブジェクト）格納用変数
		ResultSet resultSet = null;

		checkMysqlDriver();

		List<SurveyDto> surveys = new ArrayList<SurveyDto>();

		try {
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			String sql = "SELECT * FROM SURVEY WHERE NAME = ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, userName);

			resultSet = ps.executeQuery();

			while (resultSet.next()) {
				SurveyDto survey = new SurveyDto();
				survey.setName(resultSet.getString("NAME"));
				survey.setAge(resultSet.getInt("AGE"));
				survey.setSex(resultSet.getInt("SEX"));
				survey.setSatisfactionLevel(resultSet.getInt("SATISFACTION_LEVEL"));
				survey.setMessage(resultSet.getString("MESSAGE"));
				survey.setTime(resultSet.getTimestamp("TIME"));
				surveys.add(survey);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (resultSet != null) { //接続が確認できている場合のみ実施
				try {
					resultSet.close(); //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//PreparedStatementオブジェクトの接続解除
			if (ps != null) { //接続が確認できている場合のみ実施
				try {
					ps.close(); //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//Connectionオブジェクトの接続解除
			if (con != null) { //接続が確認できている場合のみ実施
				try {
					con.close(); //接続の解除
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return surveys;
	}

	private void checkMysqlDriver() {
		//-------------------------------------------
		//JDBCドライバのロード
		//-------------------------------------------
		try {
			Class.forName(DRIVER_NAME); //JDBCドライバをロード＆接続先として指定
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void terminateDriver(boolean isSuccess, Connection con, PreparedStatement ps) {
		//-------------------------------------------
		//トランザクションの終了
		//-------------------------------------------
		if (isSuccess) {
			//明示的にコミットを実施
			try {
				con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			//明示的にロールバックを実施
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		//-------------------------------------------
		//接続の解除
		//-------------------------------------------

		//PreparedStatementオブジェクトの接続解除
		if (ps != null) { //接続が確認できている場合のみ実施
			try {
				ps.close(); //接続の解除
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		//Connectionオブジェクトの接続解除
		if (con != null) { //接続が確認できている場合のみ実施
			try {
				con.close(); //接続の解除
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
