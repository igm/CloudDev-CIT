package ie.cit.cad.data.dao;

import ie.cit.cad.Todo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;

@Secured("ROLE_USER")
public class JdbcTodoRepository implements TodoRepository {

	private JdbcTemplate jdbcTemplate;

	public JdbcTodoRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Todo> getAll() {
		return jdbcTemplate.query("SELECT * FROM TODO WHERE OWNER=?", new TodoRowMapper(), getCurrentUser());
	}

	private String getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	public Todo findById(String id) {
		return jdbcTemplate.queryForObject(
				"SELECT ID, TEXT, DONE FROM TODO WHERE ID=?",
				new TodoRowMapper(), id);
	}

	public void add(Todo todo) {
		jdbcTemplate.update("INSERT INTO TODO VALUES(?,?,?,?)", todo.getId(),
				todo.getText(), getCurrentUser(), todo.isDone());
	}

	public void delete(String id) {
		jdbcTemplate.update("DELETE FROM TODO WHERE ID=? AND OWNER=?", id, getCurrentUser());
	}

	public void update(Todo todo) {
		jdbcTemplate.update("UPDATE TODO SET TEXT=?, DONE=? WHERE ID=?",
				todo.getText(), todo.isDone(), todo.getId());
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
