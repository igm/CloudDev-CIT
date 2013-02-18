package ie.cit.cad;

import java.util.ArrayList;
import java.util.List;

public class TodoRepository {

	private List<Todo> todos = new ArrayList<Todo>();

	public TodoRepository() {
		Todo todo1 = new Todo();
		todo1.setText("Remember the Milk!");
		todos.add(todo1);
	}

	public void addTodo(Todo todo) {
		todos.add(todo);
	}

	public List<Todo> getTodos() {
		return todos;
	}
}
