package cucumberframework.steps;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

	WebDriver driver;

	@Before
	public void setUp() throws IOException {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe ");
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	@Given("User navigates to the {string} website")
	public void user_navigates_to_the_website(String string) {
		driver.get(string);
	}

	@Given("User clicks on Login portal")
	public void user_clicks_on_login_portal() {
		driver.findElement(By.xpath("//*[@id=\"login-portal\"]")).click();
		//	or	
		//driver.findElement(By.id("login-portal")).click();
		//		xPath //*[@id="login-portal"]
	}

	@Given("User enters valid username {string}")
	public void user_enters_valid_username(String string) {
		for(String windowHandler : driver.getWindowHandles()) {
			driver.switchTo().window(windowHandler);
		}
		driver.findElement(By.xpath("//*[@id=\"text\"]")).sendKeys(string);
		//		xPath //*[@id="text"]
	}

	@Given("User enters valid password {string}")
	public void user_enters_valid_password(String string) {
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(string);
		//		xPath //*[@id="password"]
	}

	@When("User clicks on the Login button")
	public void user_clicks_on_the_login_button() {
		driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
		//		xPath //*[@id="login-button"]
	}

	@Then("An alert is displayed to the user with {string}")
	public void an_alert_is_displayed_to_the_user_with(String string) throws Throwable {
		try {
		}
		catch(Exception e){
			if(e.toString().contains("org.openqa.selenium.UnhandledAlertException")) {
				Alert a = driver.switchTo().alert();
				a.accept(); // this statement clicks on OK in the pop-up
				assertEquals(a.getText(), string);
				driver.close();
			}
		}
	}	
}
