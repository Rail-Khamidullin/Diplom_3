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
import pages.AuthPage;
import pages.MainPage;
import support.SupportUser;
import java.util.concurrent.TimeUnit;
import static constants.Constants.URL_AUTH_WINDOW;
import static org.junit.Assert.assertEquals;

public class TestSwitchConstructor extends BaseTest  {

    // зависимости
    private SupportUser supportUser = new SupportUser();
    private WebDriver driver;
    private AuthPage authPage;
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
        driver.get(URL_AUTH_WINDOW);
        authPage = new AuthPage(driver);
        authPage.authentication(email, password);
    }

    @Test
    @DisplayName("Переход из «Личный кабинет» в Конструктор")
    @Description("Переход по клику на «Конструктор» и отображение поля 'Соберите бургер'")
    public void byMainWindowTest() {
        mainPage = new MainPage(driver);
        // переход в личный кабинет
        mainPage.tapPersonalAccount();
        // переход по клику на «Конструктор» из Личного кабинета
        mainPage.openConstructor();
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
