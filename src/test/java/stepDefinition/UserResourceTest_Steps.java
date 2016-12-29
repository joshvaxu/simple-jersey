package stepDefinition;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberTest.TestRunner;

public class UserResourceTest_Steps {
	
	Response response;
	
	/**
	 * 
	 * 
	 * @throws Throwable
	 */
	@When("^users input user info$")
	public void inputUsers() throws Throwable {
	    // Make a post request body
		
		Form form = new Form();
		form.param("name", "Jony Washton");
		form.param("password", "password1");
		form.param("profession", "Student");
		
	    response = TestRunner.target.path("users").request().post(Entity.form(form));
	}
	
	@Then("^the server should handle it and return a success status$")
	public void confirmUsers() throws Throwable {
		
	    System.out.println(response.readEntity(String.class));
	}
	
	@Given("^there a an user named Jony Washton$")
	public void PreparedUser() throws Throwable {

		Form form = new Form();
		form.param("name", "Jony Washton");
		form.param("password", "password1");
		form.param("profession", "Student");
		
	    TestRunner.target.path("users").request().post(Entity.form(form));
	}
	
	@When("^users search with name Jony Washton$")
	public void users_search_with_name_Jony_Washton() throws Throwable {

	    response = TestRunner.target.path("users/sss").request().get();
	}
	
	@Then("^the server return an user info named Jony Washton$")
	public void the_server_return_an_user_info_named_Jony_Washton() throws Throwable {

	    System.out.println(response.readEntity(String.class));
	}

	
}
