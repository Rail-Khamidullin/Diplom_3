package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoverPasswordPage {

    private WebDriver driver;

    // локатор кнопки "Войти"
    public static final By RECOVER_PASSWORD = By.xpath(".//a[text() = 'Войти']");


    public RecoverPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Вход через окно 'Восстановление пароля'")
    public void tapEnterButtonOnRecoverWindow() {
        driver.findElement(RECOVER_PASSWORD).click();
    }
}
