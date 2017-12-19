/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cucumber;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author kylecaaspers
 */
public class AdminSignInSteps {

    WebDriver driver;
    
    @Given("^User is on to RSP page$")
    public void user_is_on_to_RSP_page() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "/Users/kylecaaspers/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/RobertStreetProjects/");
    }

    @When("^User navigates to sign in page$")
    public void user_navigates_to_sign_in_page() throws Throwable {
        driver.findElement(By.id("loginBtn")).click();
        
    }

    @When("^User enters correct admin name and password$")
    public void user_enters_correct_admin_name_and_password() throws Throwable {
        driver.findElement(By.name("j_username")).sendKeys("admin");
        driver.findElement(By.name("j_password")).sendKeys("admin");
        //driver.findElement(By.id("search-button")).click();
    }

    @Then("^User is re-routed to main page with privleges$")
    public void user_is_re_routed_to_main_page_with_privleges() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
