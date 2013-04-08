package ie.cit.cad.data.dao;

import ie.cit.cad.Todo;

import java.util.List;

public interface TodoRepository {
	List<Todo> getAll();

	Todo findById(String id);

	void add(Todo todo);

	void delete(String id);

	void update(Todo todo);

}
