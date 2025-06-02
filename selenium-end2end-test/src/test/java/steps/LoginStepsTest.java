package steps;

import io.cucumber.java.en.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import pages.LoggedAreaPage;
import pages.LoginPage;

import static org.junit.Assert.assertTrue;

public class LoginStepsTest {

    WebDriver driver;
    LoginPage loginPage;
    LoggedAreaPage loggedAreaPage;

    @Given("que o usuário está na página de login")
    public void que_o_usuário_está_na_página_de_login() {
        Hooks   .get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage(Hooks.driver);

        //Garante que a pagina esta carregada
        loginPage.waitForPageToLoad();
    }

    @When("ele preenche as credenciais válidas e entra")
    public void ele_preenche_as_credenciais_válidas_e_entra() {
        loginPage.login("Admin", "admin123");
        loggedAreaPage = new LoggedAreaPage(driver);
    }

    @Then("ele deve ser redirecionado para a página de formulário")
    public void ele_deve_ser_redirecionado_para_a_página_de_formulário() {
        assertTrue(loggedAreaPage.isAt());
        Hooks.driver.quit();
    }
}
