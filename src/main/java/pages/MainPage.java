package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    private WebDriver driver;
    // локатор кнопки "Войти в аккаунт" на главной странице
    public static final By ENTER_IN_ACCOUNT = By.xpath(".//button[text() = 'Войти в аккаунт']");
    // локатор заголовка "Соберите бургер" по XPATH
    public static final By CONSTRUCTOR_NAME = By.xpath(".//h1[text() = 'Соберите бургер']");
    // локатор заголовка "Личный Кабинет" по XPATH
    public static final By PERSONAL_ACCOUNT = By.xpath(".//p[text() = 'Личный Кабинет']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Вход через главную страницу")
    public void tapEnterButtonOnMainWindow() {
        driver.findElement(ENTER_IN_ACCOUNT).click();
    }

    @Step("Получение текста заголовка 'Соберите бургер'")
    public String getConstructorName() {
        return driver.findElement(CONSTRUCTOR_NAME).getText();
    }

    @Step("Вход через главную страницу")
    public void tapPersonalAccount() {
        driver.findElement(PERSONAL_ACCOUNT).click();
    }
}
