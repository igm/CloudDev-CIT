package ie.cit.cad.web;

import ie.cit.cad.Todo;
import ie.cit.cad.data.dao.TodoRepository;

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
	TodoRepository repo;

	@RequestMapping(method = RequestMethod.GET)
	public String todoList(Model model) {
		model.addAttribute("todos", repo.getAll());
		return "todos";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@RequestParam String text, Model model) {
		Todo todo = new Todo();
		todo.setText(text);
		repo.add(todo);
		model.addAttribute("todos", repo.getAll());
		return "todos";
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@RequestParam("todoId") String id, Model model) {
		Todo todo = repo.findById(id);
		todo.setDone(!todo.isDone());
		repo.update(todo);
		model.addAttribute("todos", repo.getAll());
		return "todos";
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public String delete(@RequestParam("todoId") String id, Model model) {
		repo.delete(id);
		model.addAttribute("todos", repo.getAll());
		return "todos";
	}
}