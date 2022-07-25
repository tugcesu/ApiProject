package apiTest;

public class User {

	private int id;
	private String token;
	private String email;
	private String password;

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public User(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
