
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>T-Kart</title>
    <link rel="stylesheet" href="css/register.css" />
</head>
<body>

<div class="container">
    <h2>Create Your Account</h2>
  
    <form action="${pageContext.request.contextPath}/register" method="post">

        <label for="fullname">Full Name</label>
        <input type="text" name="name" required />

        <label for="email">Email Address</label>
        <input type="email" id="email" name="email" placeholder="name@example.com" required />

        <label for="password">Password</label>
        <input type="password" id="password" name="password" required />

        <label for="phone">Phone Number</label>
        <input type="number" id="phone" name="phone"  />
        
        <label for="address">Address</label>
        <input type="text" id="address" name="address"  />
        

        <button type="submit">Sign Up</button>

    </form>

    <div class="footer-link">
        Already have an account? <a href="login.jsp">Login here</a>
    </div>
    <%
String errorMsg = (String) request.getAttribute("error");
if (errorMsg != null && !errorMsg.isEmpty()) {
%>
    <p style="color:red;"><%= errorMsg %></p>
<%
}
%>

<%-- <c:if test=${not empty error} >
<p>${error}
</p>
</c:if> --%>

</div>



</body>
</html>
