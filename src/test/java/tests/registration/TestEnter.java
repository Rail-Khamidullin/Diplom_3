package tests.registration;

import api.UserJSON;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import java.util.concurrent.TimeUnit;
import static constants.Constants.*;
import static org.junit.Assert.assertEquals;

public class TestEnter extends BaseTest {
    // зависимости
    private SupportUser supportUser = new SupportUser();
    private RegistrationPage registrationPage;
    private AuthPage authPage;
    private WebDriver driver;
    private RecoverPasswordPage recoverPasswordPage;
    private MainPage mainPage;

    // креды пользователя
    private String name = "Frodo";
    private String email = "frodo.begens1.test@yandex.ru";
    private String password = "123456";
    private String accessToken;

    @Before
    public void startUp() {
        setUp();
        accessToken = supportUser.createUser(new UserJSON(name, password, email));
        WebDriverManager.chromedriver().setup();
        // Создаём драйвер для браузера Chrome и устанавливаем размер окна
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1160, 800));
    }

    @Test
    @DisplayName("Через кнопку «Войти в аккаунт» на главной")
    @Description("Авторизация пользователя через кнопку «Войти в аккаунт» на главной")
    public void byMainWindowTest() {
        driver.get(URL_MAIN_WINDOW);
        authPage = new AuthPage(driver);
        mainPage = new MainPage(driver);
        mainPage.tapEnterButtonOnMainWindow();
        authPage.authentication(email, password);
        // вход по кнопке «Войти в аккаунт» на главной
        String actualText = mainPage.getConstructorName();
        assertEquals("Текст заголовка должен быть 'Соберите бургер'", "Соберите бургер", actualText);
    }

    @Test
    @DisplayName("Через кнопку «Личный кабинет»")
    @Description("Авторизация пользователя через кнопку «Личный кабинет»")
    public void byPersonalAccountTest() {
        driver.get(URL_MAIN_WINDOW);
        authPage = new AuthPage(driver);
        mainPage = new MainPage(driver);
        mainPage.tapPersonalAccount();
        authPage.authentication(email, password);
        // вход по кнопке «Войти в аккаунт» на главной
        String actualText = mainPage.getConstructorName();
        assertEquals("Текст заголовка должен быть 'Соберите бургер'", "Соберите бургер", actualText);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Авторизация пользователя через кнопку в форме регистрации")
    public void byRegistrationWindowTest() {
        driver.get(URL_REGISTRATION);
        registrationPage = new RegistrationPage(driver);
        authPage = new AuthPage(driver);
        mainPage = new MainPage(driver);
        registrationPage.tapToEnterOnRegistration();
        authPage.authentication(email, password);
        // вход через кнопку «Личный кабинет»
        String actualText = mainPage.getConstructorName();
        assertEquals("Текст заголовка должен быть 'Соберите бургер'", "Соберите бургер", actualText);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Авторизация пользователя через окно «Восстановить пароль»")
    public void byRecoverPasswordWindowTest() {
        driver.get(URL_RECOVER_PASSWORD);
        recoverPasswordPage = new RecoverPasswordPage(driver);
        authPage = new AuthPage(driver);
        mainPage = new MainPage(driver);
        recoverPasswordPage.tapEnterButtonOnRecoverWindow();
        authPage.authentication(email, password);
        // вход через окно «Восстановить пароль»
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
