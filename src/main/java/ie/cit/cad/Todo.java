package ie.cit.cad;

import java.util.UUID;

public class Todo {
	private String id;
	private String text;
	private boolean done;

	public Todo() {
		id = UUID.randomUUID().toString();
	}

	public String getText() {
		return text;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
