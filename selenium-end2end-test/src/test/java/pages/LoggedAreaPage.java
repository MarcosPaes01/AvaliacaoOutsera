package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoggedAreaPage {

    WebDriver driver;

    // Locators dos elementos do formulário
    private By submitButton = By.xpath("//h6[text()='Dashboard']");

    public LoggedAreaPage(WebDriver driver) {
        this.driver = driver;
    }

    // Método para validar se está na página do formulário
    public boolean isAt() {
        return driver.findElement(submitButton).isDisplayed();
    }
}
