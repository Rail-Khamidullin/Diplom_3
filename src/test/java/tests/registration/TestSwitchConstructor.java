package tests.registration;

import api.UserJSON;
import browser.WebDriverFactory;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.AuthPage;
import pages.MainPage;
import support.SupportUser;
import java.util.concurrent.TimeUnit;
import static browser.WebDriverFactory.BrowserName.CHROME;
import static constants.Constants.URL_AUTH_WINDOW;
import static org.junit.Assert.assertEquals;

public class TestSwitchConstructor extends BaseTest {

    // зависимости
    private SupportUser supportUser = new SupportUser();
    private WebDriver driver;
    private AuthPage authPage;
    private MainPage mainPage;

    // креды пользователя
    private String name = "Frodo";
    private String email = "frodo.begens.test@yandex.ru";
    private String password = "123456";
    private String accessToken;

    @Before
    public void startUp() {
        setUp();
        accessToken = supportUser.createUser(new UserJSON(name, password, email));
        driver = WebDriverFactory.createDriver(CHROME);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(URL_AUTH_WINDOW);
        authPage = new AuthPage(driver);
        authPage.authentication(email, password);
    }

    @Test
    @DisplayName("Переход из «Личный кабинет» в Конструктор по нажатию на кнопку 'Конструктор'")
    @Description("Переход по клику на «Конструктор» и отображение поля 'Соберите бургер'")
    public void switchConstructorTest() {
        mainPage = new MainPage(driver);
        // переход в личный кабинет
        mainPage.tapPersonalAccount();
        // переход по клику на «Конструктор» из Личного кабинета
        mainPage.tapConstructor();
        // проверяем, что переход совершён правильно
        String actualText = mainPage.getConstructorName();
        assertEquals("Текст заголовка должен быть 'Соберите бургер'", "Соберите бургер", actualText);
    }

    @Test
    @DisplayName("Переход из «Личный кабинет» в Конструктор по нажатию на кнопку 'Stellar Burgers'")
    @Description("Переход по клику на «Конструктор» и отображение поля 'Соберите бургер'")
    public void switchStellarTest() {
        mainPage = new MainPage(driver);
        // переход в личный кабинет
        mainPage.tapPersonalAccount();
        // переход по клику на «Конструктор» из Личного кабинета
        mainPage.tapStellar();
        // проверяем, что переход совершён правильно
        String actualText = mainPage.getConstructorName();
        assertEquals("Текст заголовка должен быть 'Соберите бургер'", "Соберите бургер", actualText);
    }

    @After
    public void tearDown() {
        // закрытие браузера
        this.driver.quit();
        // удаление пользователя
        if (accessToken != null) {
            supportUser.deleteUser(accessToken);
        } else {
            System.out.println("User is null !");
        }
    }
}
