package Cucumber;

import cucumber.api.PendingException;
import cucumber.api.java.After;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id("addCabinetSubmit")));
        driver.findElement(By.id("addCabinetSubmit")).click();
    }

    @Then("^the speaker is made to our specifications$")
    public void the_speaker_is_made_to_our_specifications() throws Throwable {
        System.out.println(driver.getTitle());
        Assert.assertTrue(driver.findElement(By.id("cab1impedance")).getText().equals("4.0 ohm"));
        
        while(driver.findElements(By.name("speakerToDelete")).size() != 0){
            driver.findElement(By.name("speakerToDelete")).click();
        }
        driver.quit();
    }
    
//    @After
//    public void deleteSpeakers(){
//        
//    }
}
