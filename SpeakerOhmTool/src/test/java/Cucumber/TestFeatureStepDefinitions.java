package Cucumber;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestFeatureStepDefinitions {

    WebDriver driver;

    @Given("^we open the speaker app$")
    public void we_open_the_speaker_app() throws Throwable {
        System.out.println("This is the 'Given' step.");

        System.setProperty("webdriver.chrome.driver", "/Users/kylecaaspers/chromedriver");
        driver = new ChromeDriver();
        //driver.navigate().to("http://localhost:8080/SpeakerOhmTool/");
        driver.get("http://localhost:8080/SpeakerOhmTool/");
    }

    @When("^we open the add speaker module$")
    public void we_open_the_add_speaker_module() throws Throwable {
        System.out.println("This is the 'When' step.");

        driver.findElement(By.id("cabinetButton")).click();
        
    }

    @When("^we click the add speaker button$")
    public void we_click_the_add_speaker_button() throws Throwable {
        //driver.findElement(By.id("addCabinetSubmit")).click();
    }

    @Then("^the speaker is made to our specifications$")
    public void the_speaker_is_made_to_our_specifications() throws Throwable {
        System.out.println("This is the 'Then' step.");
        //Assert.assertTrue()

    }
}
