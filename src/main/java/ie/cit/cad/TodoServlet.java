package ie.cit.cad;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class TodoServlet extends HttpServlet {

	private TodoRepository repo;

	@Override
	public void init(ServletConfig config) throws ServletException {
		repo = new TodoRepository();
		super.init(config);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (req.getParameter("_method") != null) {
			req = new HttpServletRequestWrapper(req) {
				@Override
				public String getMethod() {
					return getParameter("_method").toUpperCase();
				}
			};
		}
		super.service(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String strId = req.getParameter("todoId");
		int todoId = Integer.parseInt(strId);
		repo.getTodos().remove(todoId - 1);
		forward(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String strId = req.getParameter("todoId");
		int todoId = Integer.parseInt(strId);
		Todo todo = repo.getTodos().get(todoId - 1);
		todo.setDone(!todo.isDone());
		forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String text = req.getParameter("text");
		Todo todo = new Todo();
		todo.setText(text);
		repo.getTodos().add(todo);
		forward(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		forward(req, resp);
	}

	private void forward(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Todo> todos = repo.getTodos();
		req.setAttribute("todos", todos);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/todos.jsp");
		dispatcher.forward(req, resp);
	}

}
