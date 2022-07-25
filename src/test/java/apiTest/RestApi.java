package apiTest;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.Test;
import org.hamcrest.Matchers;
import static io.restassured.RestAssured.given;


public class RestApi {
	//AOI root
	public String API_ROOT = "https://reqres.in";
	public User user = new User("eve.holt@reqres.in" , "pistol");

	@Test//post User Object request, status code 200, and response body check
	public void postRegisterSuccessful() {
		Response response = given()
				.header("Content-Type", "application/json")
				.body(user)
				.post(API_ROOT + "/api/register")
				.then()
				.statusCode(HttpStatus.SC_OK)
				.body("id", Matchers.equalTo(4))
				.body("token", Matchers.equalTo("QpwL5tke4Pnpja7X4"))
				.extract()
				.response();

		user.setId(response.path("id"));
		user.setToken(response.path("token"));
	}

	@Test//post User Object request without password, status code 400, and response body check
	public void postRegisterUnsuccessful() {
		User user2 = new User("sydney@fife");

		given()
				.header("Content-Type", "application/json")
				.body(user2)
				.post(API_ROOT + "/api/register")
				.then()
				.statusCode(HttpStatus.SC_BAD_REQUEST)
				.body("error", Matchers.equalTo("Missing password"));
	}


	@Test//post User Object request, status code 200, and response body check
	public void postLoginSuccessful() {
		postRegisterSuccessful();

		given()
				.header("Content-Type", "application/json")
				.body(user)
				.post(API_ROOT + "/api/login")
				.then()
				.statusCode(HttpStatus.SC_OK)
				.body("token", Matchers.equalTo(user.getToken()));

	}

	@Test//post User Object request without password, status code 400, and response body check
	public void postLoginUnsuccessful() {
		User user2 = new User("peter@klaven");

		given()
				.header("Content-Type", "application/json")
				.body(user2)
				.post(API_ROOT + "/api/login")
				.then()
				.statusCode(HttpStatus.SC_BAD_REQUEST)
				.body("error", Matchers.equalTo("Missing password"));

	}

	@Test//post empty Object request, status code 400, and response body check
	public void postLoginUnsuccessful2() {
		given()
				.header("Content-Type", "application/json")
				.post(API_ROOT + "/api/login")
				.then()
				.statusCode(HttpStatus.SC_BAD_REQUEST)
				.body("error", Matchers.equalTo("Missing email or username"));

	}

	@Test//get User, status code 200, and response body check
	public void getSingleUser() {
		postRegisterSuccessful();

		given()
				.header("Content-Type", "application/json")
				.get(API_ROOT + "/api/users/" + user.getId())
				.then()
				.statusCode(HttpStatus.SC_OK)
				.body("data.id", Matchers.equalTo(user.getId()))
				.body("data.email", Matchers.equalTo(user.getEmail()));
	}

	@Test//get non-exist User, status code 404
	public void getSingleUserNotFound() {
		given()
				.header("Content-Type", "application/json")
				.get(API_ROOT + "/api/users/23")
				.then()
				.statusCode(HttpStatus.SC_NOT_FOUND);
	}

}

