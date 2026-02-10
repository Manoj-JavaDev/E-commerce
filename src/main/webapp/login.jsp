<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/register.css">
</head>
<body>
    <div class="container">
        <h2>Login</h2>
        <form action="${pageContext.request.contextPath}/login" method="post">

            <label for="email">Email Address</label>
            <input type="email" id="email" name="email" placeholder="name@example.com" required />

            <label for="password">Password</label>
            <input type="password" id="password" name="password" required />

            <button type="submit">Login</button>

        </form>
        <%
    String errorMsg = (String) request.getAttribute("error");
    if (errorMsg != null && !errorMsg.isEmpty()) {
    %>
        <p style="color:red;"><%= errorMsg %></p>
    <%
    }
    %>
</body>
</html>