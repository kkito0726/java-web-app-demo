<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>ログイン画面</title>
  </head>
  <body>
    <h1>ログインSample②（ログイン画面）</h1>
    <form action="ExecuteLogin" method="post">
      <p>
        ユーザーID：<br />
        <input type="text" name="USER_ID" maxlength="20" id="ID_USER_ID" />
      </p>

      <p>
        パスワード：<br />
        <input
          type="password"
          name="PASSWORD"
          maxlength="20"
          id="USER_PASSWORD"
        />
      </p>
      <input type="submit" value="ログイン" id="SUBMIT" />
    </form>
  </body>
   <script type="text/javascript" src="js/validateLogin.js"></script> 
</html>
