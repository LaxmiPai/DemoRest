package stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
public class stepDefinition {
	
	@Given("^Enter the URL$")
    public void enter_the_url() throws Throwable {
        
    }

    @When("^User logs in with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void user_logs_in_with_username_something_and_password_something(String strArg1, String strArg2) throws Throwable {
       System.out.println(strArg1);
       System.out.println(strArg2);
    }

    @Then("^User enters into the facebook page$")
    public void user_enters_into_the_facebook_page() throws Throwable {
       
    }

    @And("^Facebook image are displayed$")
    public void facebook_image_are_displayed() throws Throwable {
       
    }
    
}

