package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConstructorPage {
    private WebDriver driver;
    // локатор кнопки "Конструктор"
    private static final By CONSTRUCTOR = By.xpath(".//p[text()= 'Конструктор']");

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Переход в раздел 'Конструктор'")
    public void openConstructor() {
        driver.findElement(CONSTRUCTOR).click();
    }
}
