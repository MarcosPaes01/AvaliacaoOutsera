package steps;

import io.cucumber.java.en.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.LoggedAreaPage;
import pages.LoginPage;

import static org.junit.Assert.assertTrue;

public class LoginStepsTest {
    static WebDriver driver;
    LoginPage loginPage;
    LoggedAreaPage loggedAreaPage;
    public static final String LOGIN_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    @Given("que o usuário está na página de login")
    public void que_o_usuário_está_na_página_de_login() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(LOGIN_URL);
        loginPage = new LoginPage(driver);

        //Garante que a pagina esta carregada
        loginPage.waitForPageToLoad();
    }

    @When("ele preenche as credenciais válidas e entra")
    public void ele_preenche_as_credenciais_válidas_e_entra() {
        loginPage.login("Admin", "admin123");
        loggedAreaPage = new LoggedAreaPage(driver);
    }

    @When("ele preenche o usuário correto e senha incorreta")
    public void ele_preenche_o_usuário_correto_e_senha_incorreta() {
        loginPage.login("Admin", "senha_incorreta");
    }

    @Then("ele deve ver uma mensagem de erro")
    public void ele_deve_ver_uma_mensagem_de_erro() {
        //Verifica se existe um elemento de erro na tela
        boolean erroVisivel = loginPage.isErrorVisible();
        assertTrue("Mensagem de erro não visível!", erroVisivel);
    }

    @Then("ele deve ser redirecionado para a página de formulário")
    public void ele_deve_ser_redirecionado_para_a_página_de_formulário() {
        assertTrue(loggedAreaPage.isAt());
    }
}
