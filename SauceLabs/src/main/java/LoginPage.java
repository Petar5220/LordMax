import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage {

    @FindBy(css = "input[data-test='username']")
    public WebElement usernameInput;
    @FindBy(css= "input[data-test= 'password' ] ")

    public WebElement passwordInput;

    @FindBy(css="input[id= 'login-button' ] " )

    public WebElement loginButton;

    @FindBy(css= "input [data-test  = 'error' ] " )

    public WebElement errorMessageLabel;
    public class LoginTests {



    @Test
    public void successfullLoginTest() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickOnLoginButton();


        wait.until(ExpectedConditions.visible)


    }

    }


