<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String userName = (String)request.getAttribute("USER_NAME");
%>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
  </head>
  <body>
    <h2>わんちゃん暮らし改善アンケートフォーム</h2>
    <form action="SaveSurvey" method="post">
      <p>名前： <%= userName %></p>
      <p>
        年齢：

        <input type="number" name="AGE" maxlength="3" id="AGE" />
      </p>
      <p>
        性別： <input type="radio" name="SEX" value="1" checked />オス
        <input type="radio" name="SEX" value="2" />メス
      </p>
      <p>
        満足度：
        <select name="SATISFACTION_LEVEL">
          <option value="5">とても満足</option>
          <option value="4">満足</option>
          <option value="3">普通</option>
          <option value="2">不満</option>
          <option value="1">とても不満</option>
        </select>
      </p>
      <p>
        飼い主へのご意見・ご感想をご記入ください：<br />
        <textarea
          name="MESSAGE"
          rows="4"
          cols="50"
          maxlength="250"
          id="MESSAGE"
        ></textarea>
      </p>
      <input type="submit" value="回答する(SaveSurveyを起動)" id="SUBMIT" />
    </form>
    <a href="ShowSurveyByUserName">自分の回答を確認する</a>
    <a href="ShowAllSurvey">回答全件表示</a>
    <a href="ExecuteLogout">ログアウトする</a>
  </body>
  <script type=\"text/javascript\" src=\"js/validateHome.js\"></script> 
</html>
