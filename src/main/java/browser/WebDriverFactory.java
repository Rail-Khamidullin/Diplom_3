package browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    public enum BrowserName {
        CHROME,
        YANDEX
    }

    // выбор браузера для тестирования
    public static WebDriver createDriver(BrowserName browserName) {

        switch (browserName) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case YANDEX:
                System.setProperty("webdraiver.chrome.driver", "/Users/a1111/chromedriver-mac-x64/chromedriver");
                ChromeOptions options = new ChromeOptions();
                options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
                return new ChromeDriver(options);
            default:
                throw new IllegalArgumentException("Введите значение BrowserName: CHROME или YANDEX");
        }
    }
}