import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTests {
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeEach
    public void loginTestsSetup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/v1/");

    }

    @Test
    public void successfulLoginTest() {


        WebElement usernameInput = driver.findElement(By.cssSelector("[data-test='username']"));
        WebElement passInput = driver.findElement(By.cssSelector("#password"));
        WebElement loginButton = driver.findElement(By.cssSelector("#login-button"));

        usernameInput.sendKeys("standard_user");
        passInput.sendKeys("secret_sauce");
        loginButton.click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("img.inventory_item_img")));
        Assertions.assertTrue(driver.getPageSource().contains("Products"), "Couldn't find Products text");

    }

    @Test
    public void userCannotLoginWithInvalidCredentialsTest() {

        WebElement usernameInput = driver.findElement(By.cssSelector("[data-test='username']"));
        WebElement passInput = driver.findElement(By.cssSelector("#password"));
        WebElement loginButton = driver.findElement(By.cssSelector("#login-button"));

        usernameInput.sendKeys("standard_user");
        passInput.sendKeys("incorrect_pass");
        loginButton.click();
        Assertions.assertFalse(driver.getPageSource().contains("Products"), "Found Products even though I shouldn't have!");
        WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));

        String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        Assertions.assertEquals(expectedErrorMessage, errorMessage.getText());
    }

    @AfterEach
    public void loginTestsTearDown() {
        driver.quit();
    }

}
