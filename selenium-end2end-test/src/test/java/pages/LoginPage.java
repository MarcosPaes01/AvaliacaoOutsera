package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AppUrls;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    // Todos os elementos são privados
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//button[contains(.,'Login')]");
    private By loginTitle = By.xpath("//h5[text()='Login']");
    private By errorMessage = By.xpath("//div[@class='oxd-alert oxd-alert--error']"); 

    public static final String LOGIN_URL = AppUrls.LOGIN_URL;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isErrorVisible() {
        try {
            Thread.sleep(2000); // Espera 2 segundos para garantir que a mensagem de erro seja exibida
            return driver.findElement(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void login(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginTitle));
    }
}