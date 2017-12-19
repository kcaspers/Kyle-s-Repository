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
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author kylecaaspers
 */
public class CheckPageTitleSteps {

    WebDriver driver;
    
    @Given("^I am on RSP site$")
    public void i_am_on_RSP_site() throws Throwable {
//        System.setProperty("webdriver.chrome.driver", "/Users/kylecaaspers/chromedriver");
//        driver = new ChromeDriver();
//        
//        WebDriverWait driverWait = new WebDriverWait(driver, 5);
//        driverWait.until(d -> d.findElement(By.id("robertStreetName")));
//        
//        driver.get("http://localhost:8080/RobertStreetProjects/");
//        System.out.println("we made it here");
    }

    @When("^I navigate to Sign In$")
    public void i_navigate_to_Sign_In() throws Throwable {
        //driver.findElement(By.id("loginBtn")).click();
    }

    @Then("^I check page title is Login$")
    public void i_check_page_title_is_Login() throws Throwable {
       //Assert.assertTrue(driver.getTitle().equals("Log In"));
    }

    @Then("^I close the browser$")
    public void i_close_the_browser() throws Throwable {
        //driver.quit();
    }

    @When("^I search for nothing$")
    public void i_search_for_nothing() throws Throwable {
        //driver.findElement(By.name("srch-term")).sendKeys("");
        //driver.findElement(By.id("searchSubmit")).click();
    }

    @Then("^I check page title is blank search result$")
    public void i_check_page_title_is_blank_search_result() throws Throwable {
        throw new PendingException();
    }

    @When("^I search for art$")
    public void i_search_for_art() throws Throwable {
        //driver.findElement(By.name("srch-term")).sendKeys("art");
        //driver.findElement(By.id("searchSubmit")).click();
    }

    @Then("^I check page title is search art$")
    public void i_check_page_title_is_search_art() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I click on LinkedIn icon$")
    public void i_click_on_LinkedIn_icon() throws Throwable {
        //driver.findElement(By.id("linkedinLink")).click();
    }

    @Then("^I check page title is LinkedIn$")
    public void i_check_page_title_is_LinkedIn() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^the client clicks Sign In tab$")
    public void the_client_clicks_Sign_In_tab() throws Throwable {
        //driver.findElement(By.id("loginBtn")).click();
    }

    @Then("^the client is redirected to the sign in page$")
    public void the_client_is_redirected_to_the_sign_in_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
