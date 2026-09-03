<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/main/main.css"> <!-- ${pageContext.request.contextPath} 專案路徑 -->
<title>Hibernate Demo</title>
</head>
<body>
	<h1>這是一位後端人員作的網頁 QQ</h1>
	<h2>員工系統</h2>
	<a href="${pageContext.request.contextPath}/OrdersServLet?action=getAll">查詢所有員工</a>
	<br><br>
	<h3><b>複合查詢 (使用 Criteria Query)：</b></h3>
	<form action="${pageContext.request.contextPath}/OrdersServLet" method="post">
		<p><label>員工名字模糊查詢：</label></p>
		<input type="text" name="ename"><br>
		<p><label>員工職位：</label></p>
		<select name="job">
			<option value="">選取職位</option>
			<option value="PRESIDENT">PRESIDENT</option>
			<option value="MANAGER">MANAGER</option>
			<option value="SALESMAN">SALESMAN</option>
			<option value="CLERK">CLERK</option>
			<option value="ANALYST">ANALYST</option>
		</select>
		<p><label>到職日期間範圍</label></p>
		<input type="date" name="starthiredate"> ～ <input type="date" name="endhiredate"><br>
		<p><label>薪資範圍</label></p>
		<input type="text" name="startsal"> ～ <input type="text" name="endsal"><br>
		<p><input type="submit" value="送出"></p>
		<input type="hidden" name="action" value="compositeQuery">
	</form>
</body>
</html>