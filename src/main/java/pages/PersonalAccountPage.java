package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAccountPage {

    private WebDriver driver;
    // локатор отображающий текст ошибки при регистрации
    private static final By PROFILE = By.xpath(".//a[text()= 'Профиль']");
    // локатор отображающий текст ошибки при регистрации
    private static final By EXIT_BUTTON = By.xpath(".//button[text()= 'Выход']");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка отображение поля 'Профиль'")
    public String getProfileText() {
        return driver.findElement(PROFILE).getText();
    }

    @Step("Нажатие на кнопку 'Выход'")
    public void tapExitButton() {
        driver.findElement(EXIT_BUTTON).click();
    }
}
