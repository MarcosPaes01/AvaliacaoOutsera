package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormPage {

    private WebDriver driver;

    // Locators dos elementos do formulário (exemplo)
    private By formTitle = By.tagName("h1"); // Exemplo: título do formulário
    private By inputField = By.id("input-id"); // Exemplo: campo de input
    private By submitButton = By.cssSelector("button[type='submit']");

    // Construtor da página
    public FormPage(WebDriver driver) {
        this.driver = driver;
    }

    // Método para obter o título da página/formulário
    public String getFormTitle() {
        return driver.findElement(formTitle).getText();
    }

    // Método para preencher um campo do formulário (exemplo)
    public void fillInputField(String value) {
        WebElement input = driver.findElement(inputField);
        input.clear();
        input.sendKeys(value);
    }

    // Método para clicar no botão de enviar
    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

    // Método para validar se está na página do formulário
    public boolean isAt() {
        return driver.findElement(submitButton).isDisplayed();
    }
}
