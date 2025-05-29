package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.FormPage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;
    FormPage formPage;

    @Given("que o usuário está na página de login")
    public void que_o_usuário_está_na_página_de_login() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage(driver);
    }

    @When("ele preenche as credenciais válidas e entra")
    public void ele_preenche_as_credenciais_válidas_e_entra() {
        loginPage.login("Admin", "admin123");
        formPage = new FormPage(driver);
    }

    @Then("ele deve ser redirecionado para a página de formulário")
    public void ele_deve_ser_redirecionado_para_a_página_de_formulário() {
        assertTrue(formPage.isAt());
        driver.quit();
    }
}
