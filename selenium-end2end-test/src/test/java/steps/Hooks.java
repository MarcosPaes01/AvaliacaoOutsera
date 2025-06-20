package steps;

import io.cucumber.java.After;

public class Hooks {
    // Se você usar um DriverManager, chame DriverManager.quitDriver();
    // Aqui, exemplo usando driver estático em LoginStepsTest:
    @After
    public void tearDown() {
        // Fecha o driver se estiver aberto
        if (LoginStepsTest.driver != null) {
            LoginStepsTest.driver.quit();
            LoginStepsTest.driver = null;
        }
    }
}