<%@page import="ie.cit.cad.Todo" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<h1>Todo Application (Spring MVC)</h1>
	<h2>Todo list:</h2>
	<c:forEach items="${todos}" var="todo" varStatus="row">
	${todo.id} - ${todo.text} - ${todo.done}
		<form method="post">
			<input name="_method" value="delete" type="hidden">
			<input name="todoId" value="${todo.id}" type="hidden">
			<input type="submit" value="Delete">
		</form>
		<form method="post">
			<input name="_method" value="put" type="hidden">
			<input name="todoId" value="${todo.id}" type="hidden">
			<input type="submit" value="Update">
		</form>
	</c:forEach>

	<form method="post">
		<input name="text"><input type="submit" title="Create">
	</form>
</body>
</html>