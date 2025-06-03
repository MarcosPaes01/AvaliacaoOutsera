package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoggedAreaPage {

    WebDriver driver;

    // Locators dos elementos do formulário
    private By submitButton = By.xpath("//div[contains(@class, 'orangehrm-attendance-card') or .//p[contains(text(),'Punched In')]]");

    public LoggedAreaPage(WebDriver driver) {
        this.driver = driver;
    }

    // Método para validar se está na página dos dashboards
    public boolean isAt() {
                try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton));
            return button.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
