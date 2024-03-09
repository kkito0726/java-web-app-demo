<%@page import="model.dtos.SurveyDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	List<SurveyDto> surveys = (List<SurveyDto>) request.getAttribute("SURVEYS");
	List<String> genders = (List<String>) request.getAttribute("GENDERS");
	List<String> levels = (List<String>) request.getAttribute("LEVELS");
%>
<%! 
	String replaceEscapeChar(String inputTxt) {
		inputTxt = inputTxt.replace("&", "&amp");
		inputTxt = inputTxt.replace("<", "&lt");
		inputTxt = inputTxt.replace(">", "&gt");
		inputTxt = inputTxt.replace("\"", "&quot;");
		inputTxt = inputTxt.replace("'", "&#039;");
	
		return inputTxt;
	}
%>
<html>
<head>
   <meta charset="UTF-8" />
   <title>Insert title here</title>
 </head>
 <body>
   <h2>回答全件検索</h2>

   <table border="1">
     <tr>
       <th>名前</th>

       <th>年齢</th>

       <th>性別</th>

       <th>満足度</th>

       <th>メッセージ</th>

       <th>投稿時間</th>
     </tr>
     <% for (int i = 0 ; i < surveys.size() ; i++) { %>
     <tr>
       <td><%= replaceEscapeChar(surveys.get(i).getName()) %></td>
       <td><%= String.valueOf(surveys.get(i).getAge()) %></td>
       <td><%= genders.get(i) %></td>
       <td><%= levels.get(i) %></td>
       <td><%= replaceEscapeChar(surveys.get(i).getMessage()) %></td>
       <td><%= surveys.get(i).getTime() %></td>
     </tr>
     <%} %>
   </table>
   <a href="InputSurvey">Homeの戻る</a>
 </body>
</html>