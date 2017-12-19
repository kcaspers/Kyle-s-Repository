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
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author kylecaaspers
 */
public class AdminSignInSteps {

    WebDriver driver;

    @Given("^User is on RSP page$")
    public void user_is_on_RSP_page() throws Throwable {
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
        driver.findElement(By.id("search-button")).click();
    }

    @Then("^User is re-routed to main page with privleges$")
    public void user_is_re_routed_to_main_page_with_privleges() throws Throwable {
        Assert.assertTrue(driver.findElement(By.id("adminNavButton")).isDisplayed());
        driver.quit();
    }

    @When("^User enters incorrect sign in info$")
    public void user_enters_incorrect_sign_in_info() throws Throwable {
        driver.findElement(By.name("j_username")).sendKeys("admin");
        driver.findElement(By.name("j_password")).sendKeys("admi");
        driver.findElement(By.id("search-button")).click();
    }

    @Then("^The login page is redisplayed$")
    public void the_login_page_is_redisplayed() throws Throwable {
        Assert.assertTrue(driver.getTitle().equals("Log In"));
        driver.quit();
    }
    
    @When("^I navigate to \"([^\"]*)\"$")
    public void i_navigate_to(String link) throws Throwable {
        //new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id(link)));
        driver.findElement(By.id(link)).click();
    }

    @Then("^I check page title is \"([^\"]*)\"$")
    public void i_check_page_title_is(String arg1) throws Throwable {
        //new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id(arg1)));
        Assert.assertTrue(driver.getTitle().equals(arg1));
    }

    @Then("^I close the browser$")
    public void i_close_the_browser() throws Throwable {
        driver.close();
    }
    
    @When("^I click the about dropdown$")
    public void i_click_the_about_dropdown() throws Throwable {
        driver.findElement(By.id("dropdownMenu2")).click();
    }
}
