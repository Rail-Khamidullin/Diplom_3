package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EnterPage {

    private WebDriver driver;

    // локатор кнопки "Войти в аккаунт" на главной странице
    public static final By ENTER_IN_ACCOUNT = By.xpath(".//button[text() = 'Войти в аккаунт']");
    // локатор кнопки авторизации с названием "Войти" на странице авторизации
    private static final By ENTER_BUTTON = By.xpath(".//button[text() = 'Войти']");
    // локатор поля ввода "Email" по XPATH
    public static final By EMAIL = By.xpath(".//div[label[text() = 'Email']]/input");
    // локатор поля ввода "Пароль" по XPATH
    public static final By PASSWORD = By.xpath(".//div[label[text() = 'Пароль']]/input");
    // локатор заголовка "Конструктор" по XPATH
    public static final By CONSTRUCTOR_NAME = By.xpath(".//p[text() = 'Конструктор']");
    // локатор заголовка "Личный Кабинет" по XPATH
    public static final By PERSONAL_ACCOUNT = By.xpath(".//p[text() = 'Личный Кабинет']");


    public EnterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Вход через главную страницу")
    public void tapEnterButtonOnMainWindow() {
        driver.findElement(ENTER_IN_ACCOUNT).click();
    }

    @Step("Вход через главную страницу")
    public void tapPersonalAccount() {
        driver.findElement(PERSONAL_ACCOUNT).click();
    }

    @Step("Заполнение полей аунтетификации")
    // заполнение окна аутентификации пользователя
    public void tapToEnterButton(String email, String password) {
        driver.findElement(EMAIL).sendKeys(email);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(ENTER_BUTTON).click();
    }

    @Step("Получение текста заголовка")
    public String getConstructorName() {
        return driver.findElement(CONSTRUCTOR_NAME).getText();
    }

}
