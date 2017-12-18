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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author kylecaaspers
 */
public class TestLowImpedanceWarningSteps {

    WebDriver driver;
    
    @Given("^I open the speaker impedance app$")
    public void i_open_the_speaker_impedance_app() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "/Users/kylecaaspers/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/SpeakerOhmTool/");
        
    }

    @When("^I add two (\\d+) ohm cabinets$")
    public void i_add_two_4_ohm_cabinets(int arg1) throws Throwable {
        driver.findElement(By.id("cabinetButton")).click();
        
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id("addCabinetSubmit")));
        driver.findElement(By.id("addCabinetSubmit")).click();
        
        driver.findElement(By.id("cabinetButton")).click();
        
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id("addCabinetSubmit")));
        driver.findElement(By.id("addCabinetSubmit")).click();
    }

    @Then("^the impedance is low enough to display the warning messgage$")
    public void the_impedance_is_low_enough_to_display_the_warning_messgage() throws Throwable {
        Assert.assertTrue(driver.findElement(By.id("lowImpedanceWarning")).isDisplayed());
        
        while(driver.findElements(By.name("speakerToDelete")).size() != 0){
            driver.findElement(By.name("speakerToDelete")).click();
        }
        
        driver.quit();
    }

}
