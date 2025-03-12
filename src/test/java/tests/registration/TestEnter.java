package tests.registration;

import api.UserJSON;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.SupportUser;
import pages.EnterPage;
import pages.RegistrationPage;

import java.util.concurrent.TimeUnit;

import static constants.Constants.URL_MAIN_WINDOW;
import static org.junit.Assert.assertEquals;

public class TestEnter extends BaseTest {

    private SupportUser supportUser = new SupportUser();
    private RegistrationPage registrationPage;
    private EnterPage enterPage;
    private WebDriver driver;

    // креды пользователя
    private String name = "Frodo";
    private String email = "frodo.begens1.test@yandex.ru";
    private String password = "123456";
    private String accessToken;

    @Before
    public void startUp() {
        setUp();
        Response response = supportUser.createUser(new UserJSON(name, password, email));
        accessToken = response.path("accessToken");
        WebDriverManager.chromedriver().setup();
        // Создаём драйвер для браузера Chrome
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get(URL_MAIN_WINDOW);
    }

    @Test
    @DisplayName("Через кнопку «Войти в аккаунт» на главной")
    @Description("Авторизация пользователя через кнопку «Войти в аккаунт» на главной")
    public void byMainWindowTest() {
        enterPage = new EnterPage(driver);
        enterPage.tapEnterButtonOnMainWindow();
        enterPage.tapToEnterButton(email, password);
        // вход по кнопке «Войти в аккаунт» на главной
        String actualText = enterPage.getConstructorName();
        assertEquals("Текст заголовка должен быть 'Конструктор'", "Конструктор", actualText);
    }

    @Test
    @DisplayName("Через кнопку «Личный кабинет»")
    @Description("Авторизация пользователя через кнопку «Личный кабинет»")
    public void byPersonalAccountTest() {
        enterPage = new EnterPage(driver);
        enterPage.tapPersonalAccount();
        enterPage.tapToEnterButton(email, password);
        // вход по кнопке «Войти в аккаунт» на главной
        String actualText = enterPage.getConstructorName();
        assertEquals("Текст заголовка должен быть 'Конструктор'", "Конструктор", actualText);
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
