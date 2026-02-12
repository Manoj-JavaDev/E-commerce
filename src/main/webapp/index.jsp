
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Techouts Store</title>
<head>
<link href="css/index.css" rel="stylesheet"/>

</head>

<body>

<!-- ================= HEADER ================= -->

<header>

    <!-- Logo -->
    <div class="logo">Techouts</div>

    <!-- Navigation -->
    <nav>
        <a href="index.jsp">Home</a>
        <div class="dropdown">
            <a href="#">Categories</a>
            <div class="dropdown-content">
                <a href="products?category=Mobiles">Mobiles</a>
                <a href="#">Clothing</a>
                <a href="#">Books</a>
                <a href="#">Accessories</a>
            </div>
        </div>
    </nav>

    <!-- Right Section -->
    <div class="right-section">

        <!-- Cart -->
        <div class="cart">

            <span class="cart-count">
                ${sessionScope.cartCount != null ? sessionScope.cartCount : 0}
            </span>
        </div>

        <!-- Login / Logout Logic -->
        <c:choose>

            <c:when test="${sessionScope.user == null}">
                <a href="register.jsp">
                    <button class="btn login-btn">Register/Login</button>
                </a>

            </c:when>

            <c:otherwise>
                <span style="color : white">Welcome, ${sessionScope.user.name}</span>
                <a href="#">
                    <button class="btn login-btn">Profile</button>
                </a>

                <a href="logout">
                    <button class="btn logout-btn">Logout</button>
                </a>
            </c:otherwise>

        </c:choose>

    </div>

</header>


<!-- ================= MAIN ================= -->

<main>
    <h2>Our Products</h2>
    <br>
    <div class="products">

        <c:forEach var="item" items="${products}">
            <div class="product-card">
                <img src="${item.imageUrl}" alt="Product">
                <h3>${item.name}</h3>
                <p>${item.description}</p>
                <p><b>${item.price}</b></p>
                <button>
               <form action="${pageContext.request.contextPath}/addToCart" method="post">
                   <input type="hidden" name="product" value="${item.id}" />
                   <button type="submit">Add to Cart</button>
               </form>
            </div>
        </c:forEach>
    </div>

</main>


<!-- ================= FOOTER ================= -->

<footer>
    © 2026 Techouts E-Commerce | All Rights Reserved
</footer>

</body>
</html>
