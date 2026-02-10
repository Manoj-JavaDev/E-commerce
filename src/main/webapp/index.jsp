<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.techouts.dao.*,com.techouts.entity.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<body>
    <%! ProductDAO productDAO = new ProductDAO(); %>
    <%
    List<Product> products = productDAO.findAll();
    if(products != null)
    for (Product product : products) {
    %>
        <h3>Name : <%= product.getName() %> </h3>
    <%
    }
    %>

</body>
<footer></footer>

</html>