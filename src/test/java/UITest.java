import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class UITest {
    private WebDriver driver;

    @Test
    public void testPlayground() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        driver = new ChromeDriver();
        driver.get("https://playground.learnqa.ru/puzzle/triangle");

        WebElement button = driver.findElement(By.id("show_answ"));
        button.click();

        driver.findElement(By.className("track_log")).isDisplayed();
        driver.findElement(By.id("hide_answ")).isDisplayed();
    }

    @AfterMethod
    public void PlaygroundQuit() {
        driver.quit();
    }
}

