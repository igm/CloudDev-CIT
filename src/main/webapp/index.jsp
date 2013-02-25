<%@page import="ie.cit.cad.Todo" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="repo" class="ie.cit.cad.TodoRepository"
	scope="application" />
<html>
<body>
	<h1>Todo Application</h1>
	<h2>Todo list:</h2>
	
	<c:if test="${ param._method.equals(\"delete\") }">
	<%
		String strId =  request.getParameter("todoId");
		int todoId = Integer.parseInt(strId);
		repo.getTodos().remove(todoId-1);
	%>
	</c:if>

	<c:if test="${ param._method.equals(\"put\") }">
	<%
		String strId =  request.getParameter("todoId");
		int todoId = Integer.parseInt(strId);
		Todo todo = repo.getTodos().get(todoId-1);
		todo.setDone(!todo.isDone());
	%>
	</c:if>

	<c:if test="${! empty param.text }">
		<%
		Todo todo =new Todo();
		todo.setText(request.getParameter("text"));
		repo.addTodo(todo);
		%>
	</c:if>
	<c:forEach items="${repo.todos}" var="todo" varStatus="row">
	${row.count} - ${todo.text} - ${todo.done}
		<form method="post">
			<input name="_method" value="delete" type="hidden">
			<input name="todoId" value="${row.count}" type="hidden">
			<input type="submit" value="Delete">
		</form>
		<form method="post">
			<input name="_method" value="put" type="hidden">
			<input name="todoId" value="${row.count}" type="hidden">
			<input type="submit" value="Update">
		</form>
	</c:forEach>

	<form method="post">
		<input name="text"><input type="submit" title="Create">
	</form>
</body>
</html>