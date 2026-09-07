<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>orderlist.jsp</title>
</head>
<body>
	<table style="text-align: left; width: 100%;" border="1"
		cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td>訂單編號</td>
				<td>會員名稱</td>
				<td>Shipping address</td>
				<td>Product total</td>
				<td>email</td>
				<td>phone number</td>
			</tr>
			<c:forEach var="orders" items="${orderList}">
				<tr>
					<td>${orders.orderId}</td>
					<td><c:out value="${orders.memberId.memberName}" /></td>
					<td><c:out value="${orders.shippingAddress}" /></td>
					<td>${orders.productTotal}</td>
					<td><c:out value="${orders.email}" /></td>
					<td><c:out value="${orders.phoneNumber}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
</body>
</html>
