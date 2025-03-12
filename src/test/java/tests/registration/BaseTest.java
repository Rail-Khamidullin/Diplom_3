package tests.registration;

import io.restassured.RestAssured;
import org.junit.Before;

public class BaseTest {

    public static final String BURGERS_URL = "https://stellarburgers.nomoreparties.site";

    // Повторяющуюся для разных ручек часть URL лучше записать в переменную в методе Before
    @Before
    public void setUp() { RestAssured.baseURI = BURGERS_URL; }
}
