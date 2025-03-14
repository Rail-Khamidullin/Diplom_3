package tests.registration;

import api.UserJSON;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import support.SupportUser;
import pages.RegistrationPage;
import java.util.concurrent.TimeUnit;
import static constants.Constants.URL_REGISTRATION;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestRegistration extends BaseTest {

    private WebDriver driver;
    private SupportUser supportUser = new SupportUser();
    private String name;
    private String email;
    private String password;
    private String accessToken;
    private RegistrationPage registrationPage;

    public TestRegistration(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][] registration() {
        return new Object[][]{
                {"Frodo", "frodo.begens1.test@yandex.ru", "123456"},
//                {"Gendalf", "gendalf.test@yandex.ru", "1234567"},
//                {"Aragorn", "aragorn.test@yandex.ru", "123456789"},
//                {"Legolas", "legolas.test@yandex.ru", "12345"},
//                {"Gimlie", "gimlie.test@yandex.ru", "1234"}
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        setUp();
        // Создаём драйвер для браузера Chrome
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get(URL_REGISTRATION);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка входа пользователя через регистрацию")
    public void registrationTest() {
        // регистрация пользователя
        registrationPage = new RegistrationPage(driver);
        registrationPage.waitRegistrationPage();
        registrationPage.setRegistrationData(name, email, password);
        // если пароль валидный
        if (password.length() > 5) {
            // ожидание отображения необхадимого элемента
            registrationPage.waitAuthPage();
            // проверяем, что регистраци прошла успешно
            String actualText = registrationPage.getWindowAuth();
            assertEquals("Текст кнопки должен быть 'Войти'", "Войти", actualText);
        } else {
            String actual = registrationPage.getErrorTextRegistration();
            assertEquals("Текст ошибки должен быть 'Некорректный пароль'", "Некорректный пароль", actual);
        }
    }

    @After
    public void tearDown() {
        // закрытие браузера
        this.driver.quit();
        // достаём accessToken пользователя и удаляем пользователя из БД
        Response responseUser = supportUser.loginUser(new UserJSON(name, password, email));
        accessToken = responseUser.path("accessToken");
        if (accessToken != null) {
            supportUser.deleteUser(accessToken);
        } else {
            System.out.println("User is null !");
        }
    }
}
