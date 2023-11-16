<%--
  Created by IntelliJ IDEA.
  User: hung1
  Date: 11/16/2023
  Time: 9:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/student/add-student" method="post">
  <input type="text" name="id" placeholder="id">
  <br>
  <input type="text" name="name" placeholder="name">
  <br>
  <input type="number" name="age">
  <br>
  <select name="sex">
    <option value="true">Nam</option>
    <option value="false">Nữ</option>
  </select>
  <br>
  <button type="submit">Add</button>
</form>
</body>
</html>
