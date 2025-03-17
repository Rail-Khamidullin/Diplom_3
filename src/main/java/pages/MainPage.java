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
    // локатор кнопки "Конструктор"
    private static final By CONSTRUCTOR = By.xpath(".//p[text()= 'Конструктор']");
    private static final By STELLAR_BURGERS = By.xpath(".//div[@class = 'AppHeader_header__logo__2D0X2']");

    /// Раздел конструктор__________
    // локатор кнопки "Булки"
    private static final By BURGERS = By.xpath(".//span[@class = 'text text_type_main-default' and text() = 'Булки']");
    // локатор текста "Булки" при скролле раздела "Собери бургер"
    private static final By SWITCH_TO_BURGERS = By.xpath(".//h2[text() = 'Булки']");
    // локатор кнопки "Соусы"
    private static final By SAUCES = By.xpath(".//span[@class = 'text text_type_main-default' and text() = 'Соусы']");
    // локатор текста "Соусы" при скролле раздела "Собери бургер"
    private static final By SWITCH_TO_SAUCES = By.xpath(".//h2[text() = 'Соусы']");
    // локатор кнопки "Начинки"
    private static final By TOPPINGS = By.xpath(".//span[@class = 'text text_type_main-default' and text() = 'Начинки']");
    // локатор текста "Начинки" при скролле раздела "Собери бургер"
    private static final By SWITCH_TO_TOPPINGS = By.xpath(".//h2[text() = 'Начинки']");

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

    @Step("Переход в раздел 'Конструктор' выбором кнопки 'Конструктор'")
    public void tapConstructor() {
        driver.findElement(CONSTRUCTOR).click();
    }

    @Step("Переход в раздел 'Конструктор' выбором кнопки 'Stellar Burgers'")
    public void tapStellar() {
        driver.findElement(STELLAR_BURGERS).click();
    }

    /// Раздел конструктор__________
    @Step("Переход к разделу 'Булки'")
    public String switchBurgers() {
        driver.findElement(BURGERS).click();
        return driver.findElement(SWITCH_TO_BURGERS).getText();
    }

    @Step("Переход к разделу 'Соусы'")
    public String switchSauces() {
        driver.findElement(SAUCES).click();
        return driver.findElement(SWITCH_TO_SAUCES).getText();
    }

    @Step("Скролл до раздела 'Начинки'")
    public void clickToppings() {
        driver.findElement(TOPPINGS).click();
    }

    @Step("Переход к разделу 'Начинки'")
    public String switchToppings() {
        driver.findElement(TOPPINGS).click();
        return driver.findElement(SWITCH_TO_TOPPINGS).getText();
    }
}
