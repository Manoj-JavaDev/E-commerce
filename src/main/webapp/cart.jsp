<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
<link rel="stylesheet" href="css/index.css" />
</head>

<main>
    <h2>Cart Products</h2>
    <br>
<c:choose>
    <c:when test="${empty cartProducts}" >
        <h3> Your Cart Is Empty </h3>
    </c:when>
    <c:otherwise>
    <div class="products">
        <c:forEach var="item" items="${cartProducts}">
            <div class="product-card">
                <img src="${item.product.imageUrl}" alt="Product">
                <h3>${item.product.name}</h3>
                <p>${item.product.description}</p>
                <p><b>${item.product.price}</b></p>
                <p><b>Quantity = ${item.quantity}</b></p>
               <form action="removeFromCart" method ="Post">
                   <input type="hidden" name="productId" value="${item.product.id}" />
                   <button type="submit">Decrease Quantity</button>
               </form>
                  <form action="addToCart">
                      <input type="hidden" name="productId" value="${item.product.id}" />
                      <button type="submit">Increase Quantity</button>
                  </form>
                  <form action="deleteFromCart">
                     <input type="hidden" name="productId" value="${item.product.id}" />
                     <button type="submit">Delete From Cart</button>
                  </form>
            </div>
        </c:forEach>
    </div>
    </c:otherwise>
</c:choose>
</main>

       <a href="checkout">
       <button class="btn logout-btn" > Checkout </button></a>

</html>