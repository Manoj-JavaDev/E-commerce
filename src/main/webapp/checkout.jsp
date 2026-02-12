<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/checkout.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <!-- LEFT SIDE -->
    <div class="left card">

        <!-- Customer Info -->
        <div class="user-box">
            <h3>Customer Details</h3>
            <p><b>Name:</b> ${user.name}</p>
            <p><b>Email:</b> ${user.email}</p>
            <p><b>Phone:</b> ${user.phone}</p>
        </div>

<form action="orders" method="post">

    <h2>Shipping Information</h2>

   Postal Code:
   <input type="text" name="postalCode" required>

   City:
   <input type="text" name="city" required>

   Country:
   <input type="text" name="country" required>

   Street Address:
   <textarea name="address" placeholder="Shipping Address" required></textarea>
            <h2>Payment Method</h2>
            <select name="paymentMethod">
                <option>Cash on Delivery</option>
                <option>Credit Card</option>
                <option>UPI</option>
                <option>Net Banking</option>
            </select>
            <button type="submit" class="btn">Place Order</button>
    </form>
    </div>
    <div class="right card">
     <c:choose>
            <c:when test="${not empty cartProducts}">

                <c:forEach var="cartProduct" items="${cartProducts}">
                    <div class="order-item">
                        <div>
                            <strong>${cartProduct.product.name}</strong><br>
                            Qty: ${cartProduct.quantity} × ₹${cartProduct.product.price}
                        </div>
                        <div>
                            ₹${cartProduct.quantity * cartProduct.product.price}
                        </div>
                    </div>
                </c:forEach>
                <hr>
                <div class="total">
                    Grand Total: ₹${total}
                </div>
            </c:when>

            <c:otherwise>
                <p>Your cart is empty.</p>
            </c:otherwise>
        </c:choose>

</div>
</body>

</html>