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
import pages.DeleteUser;
import pages.RegistrationPage;

import java.util.concurrent.TimeUnit;

import static constants.Constants.URL_REGISTRATION;
import static org.junit.Assert.assertEquals;

public class TestEnter extends BaseTest {
    private WebDriver driver;
    DeleteUser deleteUser = new DeleteUser();
    private String name;
    private String email;
    private String password;
    private String accessToken;
//
//    public TestEnter(String name, String email, String password) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//    }

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
    @DisplayName("Регистрация пользователя")
    @Description("Проверка регистрации пользователя с позитивным и негативным сценарием")
    public void enterTest() {
        // регистрация пользователя
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.setRegistrationData(name, email, password);
        // ожидание отображения необходимого элемента
        registrationPage.waitAuthPage();
        // проверяем, что регистраци прошла успешно
        String actualText = registrationPage.getWindowAuth();
        assertEquals("Не удачная регистрация, не достаточно символов", "Войти", actualText);
    }

    @After
    public void tearDown() {
        // закрытие браузера
        this.driver.quit();
        // достаём accessToken пользователя и удаляем пользователя из БД
        Response responseUser = deleteUser.loginUser(new UserJSON(name, password, email));
        accessToken = responseUser.path("accessToken");
        if (accessToken != null) {
            deleteUser.deleteUser(accessToken);
        } else {
            System.out.println("User is null !");
        }
    }
}
