package pages;

import api.UserJSON;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteUser {

    // Создаём экземпляр класса с телом запроса
    private UserJSON userJSON;

    // Endpoint авторизации пользователя
    public static final String LOGIN_USER_API = "/api/auth/login";
    // Endpoint обновления и удаления данных пользователя
    public static final String DATE_API = "/api/auth/user";

    @Step("Авторизация пользователя")
    public Response loginUser(UserJSON userJSON) {

        Response response = given()
                .header("Content-type", "application/json")
                .log().all()
                .body(userJSON)
                .when()
                .post(LOGIN_USER_API);
        return response;
    }

    public void deleteUser(String accessToken) {
        Response response = given()
                .header("authorization", accessToken)
                .log().all()
                .when()
                .delete(DATE_API);
        response.then().statusCode(202); // Проверяем статус код
    }
}
