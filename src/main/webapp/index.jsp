<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="repo" class="ie.cit.cad.TodoRepository"
	scope="application" />
<jsp:useBean id="todo" class="ie.cit.cad.Todo" scope="page" />
<html>
<body>
	<h1>Todo Application</h1>
	<h2>Todo list:</h2>
	<jsp:setProperty property="*" name="todo" />

	<c:if test="${todo.text !=null }">
		<%
			repo.addTodo(todo);
		%>
	</c:if>
	<c:forEach items="${repo.todos}" var="todo" varStatus="row">
	${row.count} - ${todo.text} - ${todo.done} <br />
	</c:forEach>

	<form  method="get">
		<input name="text"><input type="submit" title="Create">
	</form>
</body>
</html>