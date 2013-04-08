package ie.cit.cad.data.dao;

import ie.cit.cad.Todo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JdbcTodoRepository implements TodoRepository {

	private JdbcTemplate jdbcTemplate;

	public JdbcTodoRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Todo> getAll() {
		return jdbcTemplate.query("SELECT * FROM TODO", new TodoRowMapper());
	}

	public Todo findById(String id) {
		return null;
	}

	public void add(Todo todo) {

	}

	public void delete(String id) {

	}

	public void update(Todo todo) {

	}
}

class TodoRowMapper implements RowMapper<Todo> {

	public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Todo todo = new Todo();
		todo.setId(rs.getString("ID"));
		todo.setText(rs.getString("TEXT"));
		todo.setDone(rs.getBoolean("DONE"));
		return todo;
	}

}
