import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;/**/
import org.testng.Assert;
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

        WebElement link = driver.findElement(By.cssSelector("a[data-target='answers_button']"));
        boolean isDisplayed = link.isDisplayed();

        String linkText = link.getText();
        String expectedText = "Ссылка на ответы";

        Assert.assertTrue(isDisplayed);
        Assert.assertEquals(linkText, expectedText);

        boolean hideAnswerIsDisplayed = driver.findElement(By.id("hide_answ")).isDisplayed();
        Assert.assertTrue(hideAnswerIsDisplayed);
    }

    @AfterMethod
    public void PlaygroundQuit() {
        driver.quit();
    }
}

