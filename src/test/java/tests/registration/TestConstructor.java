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

public class TestConstructor extends BaseTest {

    // зависимости
    private SupportUser supportUser = new SupportUser();
    private AuthPage authPage;
    private WebDriver driver;
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
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("Проверка работы перехода к разделу «Булки» на главной")
    public void switchBurgersTest() {
        authPage = new AuthPage(driver);
        mainPage = new MainPage(driver);
        authPage.authentication(email, password);
        mainPage.clickToppings();
        String actualText = mainPage.switchBurgers();
        assertEquals("Текст заголовка должен быть 'Булки'", "Булки", actualText);
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Проверка работы перехода к разделу «Соусы» на главной")
    public void switchSaucesTest() {
        authPage = new AuthPage(driver);
        mainPage = new MainPage(driver);
        authPage.authentication(email, password);
        mainPage.clickToppings();
        String actualText = mainPage.switchSauces();
        assertEquals("Текст заголовка должен быть 'Соусы'", "Соусы", actualText);
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Проверка работы перехода к разделу «Начинки» на главной")
    public void switchToppingsTest() {
        authPage = new AuthPage(driver);
        mainPage = new MainPage(driver);
        authPage.authentication(email, password);
        String actualText = mainPage.switchToppings();
        assertEquals("Текст заголовка должен быть 'Начинки'", "Начинки", actualText);
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
