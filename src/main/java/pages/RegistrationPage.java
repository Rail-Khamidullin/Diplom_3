package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    private WebDriver driver;
    // локатор поля ввода "Email" по XPATH
    public static final By EMAIL = By.xpath(".//div[label[text() = 'Email']]/input");
    // локатор поля ввода "Пароль" по XPATH
    public static final By PASSWORD = By.xpath(".//div[label[text() = 'Пароль']]/input");
    // локатор поля ввода "Пароль" по XPATH
    public static final By NAME = By.xpath(".//div[label[text() = 'Имя']]/input");
    // локатор кнопки регистрации с названием "Зарегистрироваться"
    private static final By REGISTRATION_BUTTON = By.xpath(".//button[text() = 'Зарегистрироваться']");
    // локатор кнопки авторизации с названием "Войти" на странице авторизации
    private static final By ENTER_BUTTON = By.xpath(".//button[text() = 'Войти']");
    // локатор кнопки авторизации с названием "Войти" на старнице регистрации
    private static final By ENTER_BUTTON_ON_REGISTRATION = By.xpath(".//a[text() = 'Войти']");
    // локатор отображающий текст ошибки при регистрации
    private static final By ERROR_REGISTRATION = By.xpath(".//p[text()='Некорректный пароль']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполнение окна регистрации пользователя")
    public void setRegistrationData(String name, String email, String password) {
        driver.findElement(NAME).sendKeys(name);
        driver.findElement(EMAIL).sendKeys(email);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(REGISTRATION_BUTTON).click();
    }

    // ожидание отображения элемента
    public void waitRegistrationPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(ENTER_BUTTON_ON_REGISTRATION));
    }

    // ожидание отображения элемента
    public void waitAuthPage() {
        new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOfElementLocated(ENTER_BUTTON));
    }

    // выбор кнопки "Войти" в форме регистрации
    public void tapToEnterOnRegistration() {
        driver.findElement(ENTER_BUTTON_ON_REGISTRATION).click();
    }

    @Step("Проверка отображение кнопки 'Войти' в форме авторизации")
    public String getWindowAuth() {
        waitAuthPage();
        return driver.findElement(ENTER_BUTTON).getText();
    }

    @Step("Получение текста ошибки")
    public String getErrorTextRegistration() {
        return driver.findElement(ERROR_REGISTRATION).getText();
    }
}
