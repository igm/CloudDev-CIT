package ie.cit.cad.web;

import ie.cit.cad.Todo;
import ie.cit.cad.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("todo")
@Controller
public class TodoController {
	@Autowired
	private TodoRepository repo;

	@RequestMapping(method = RequestMethod.GET)
	public String todoList(Model model) {
		model.addAttribute("todos", repo.getTodos());
		return "todos";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@RequestParam String text, Model model) {
		Todo todo = new Todo();
		todo.setText(text);
		repo.getTodos().add(todo);
		model.addAttribute("todos", repo.getTodos());
		return "todos";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@RequestParam("todoId") int id, Model model) {
		Todo todo = repo.getTodos().get(id - 1);
		todo.setDone(!todo.isDone());
		model.addAttribute("todos", repo.getTodos());
		return "todos";
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public String delete(@RequestParam("todoId") int id, Model model) {
		repo.getTodos().remove(id - 1);
		model.addAttribute("todos", repo.getTodos());
		return "todo";
	}
}