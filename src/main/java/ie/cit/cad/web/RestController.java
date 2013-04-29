package ie.cit.cad.web;

import ie.cit.cad.Todo;
import ie.cit.cad.data.dao.TodoRepository;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

@Controller
@RequestMapping("api")
public class RestController {
	@Autowired
	private TodoRepository repo;

	@RequestMapping(value = "todo", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Todo> listTodo() {
		return repo.getAll();
	}

	@RequestMapping(value = "todo/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Todo todo(@PathVariable String id) {
		return repo.findById(id);
	}

	@RequestMapping(value = "todo", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void createTodo(HttpServletRequest req, HttpServletResponse res,
			@RequestParam String text) {
		Todo todo = new Todo();
		todo.setText(text);
		repo.add(todo);

		StringBuffer url = req.getRequestURL().append("/{id}");
		UriTemplate uriTemplate = new UriTemplate(url.toString());
		URI locationURL = uriTemplate.expand(todo.getId());
		res.addHeader("Location", locationURL.toASCIIString());
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public void exceptionHandler() {
		// no code here
	}
}
