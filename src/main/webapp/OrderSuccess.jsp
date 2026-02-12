<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Placed</title>
    <link href="css/checkout.css" rel="stylesheet"> <!-- optional CSS -->
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f8f8;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .success-card {
            background-color: #fff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0,0,0,0.1);
            text-align: center;
        }
        .success-card h1 {
            color: #28a745;
            margin-bottom: 20px;
        }
        .success-card p {
            font-size: 18px;
            margin-bottom: 30px;
        }
        .success-card a {
            text-decoration: none;
            background-color: #28a745;
            color: #fff;
            padding: 12px 25px;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .success-card a:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

<div class="success-card">
    <h1>✅ Order Placed Successfully!</h1>
    <p>Thank you for your purchase. Your order has been received and is being processed.</p>
    <a href="/products">Continue Shopping</a>
</div>

</body>
</html>
