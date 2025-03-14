package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// окно авторизации
public class AuthPage {

    private WebDriver driver;

    // локатор кнопки авторизации с названием "Войти" на странице авторизации
    private static final By ENTER_BUTTON = By.xpath(".//button[text() = 'Войти']");
    // локатор поля ввода "Email" по XPATH
    public static final By EMAIL = By.xpath(".//div[label[text() = 'Email']]/input");
    // локатор поля ввода "Пароль" по XPATH
    public static final By PASSWORD = By.xpath(".//div[label[text() = 'Пароль']]/input");

    public AuthPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполнение полей аунтетификации")
    // заполнение окна аутентификации пользователя
    public void authentication(String email, String password) {
        driver.findElement(EMAIL).sendKeys(email);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(ENTER_BUTTON).click();
    }

    @Step("Проверка отображение кнопки 'Войти'")
    public String getEnterButtonText() {
        return driver.findElement(ENTER_BUTTON).getText();
    }
}
