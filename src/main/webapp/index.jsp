
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
        <a href="login">Home</a>
        <div class="dropdown">
             <span>Categories</span>
            <div class="dropdown-content">
                <a href="products?category=Mobiles">Mobiles</a>
                <a href="products?category=Footwear">Foot Wear</a>
                <a href="products?category=Books">Books</a>
                <a href="products?category=Accessory">Accessories</a>
            </div>
        </div>
    </nav>

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
                <!-- CART -->
                <div class="right-section">
                  <a href="displayCart">
                    <button class="btn logout-btn">Cart</button>
                  </a>
                </div>
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
