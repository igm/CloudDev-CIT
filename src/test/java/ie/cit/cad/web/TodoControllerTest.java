package ie.cit.cad.web;

import java.util.ArrayList;
import java.util.List;

import ie.cit.cad.Todo;
import ie.cit.cad.data.dao.TodoRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.springframework.ui.Model;

public class TodoControllerTest {

	private TodoRepository todoRepository;
	private Model model;
	private TodoController tested;

	@Before
	public void setUp() {
		todoRepository = Mockito.mock(TodoRepository.class);
		tested = new TodoController();
		tested.repo = todoRepository;
		model = Mockito.mock(Model.class);

	}

	@Test
	public void testCreate() {
		List<Todo> todoList = Mockito.mock(List.class);
		Mockito.when(todoRepository.getAll()).thenReturn(todoList);
		tested.create("Todo Item", model);
		Mockito.verify(tested.repo).add(
				Mockito.argThat(new TodoMatcher("Todo Item")));
		Mockito.verify(model).addAttribute("todos", todoList);
	}

	@Test
	public void testDelete() {
		tested.delete("10", model);
		Mockito.verify(todoRepository).delete("10");

	}

	static class TodoMatcher extends ArgumentMatcher<Todo> {
		private String expected;

		public TodoMatcher(String expected) {
			this.expected = expected;
		}

		@Override
		public boolean matches(Object argument) {
			return expected.equals(((Todo) argument).getText());
		}
	}

}
