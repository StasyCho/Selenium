import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationCardTest {
    private WebDriver driver;
    ChromeOptions options = new ChromeOptions();

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
        //WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestV1()
        throws InterruptedException {
            driver.get("http://localhost:9999/");
            List<WebElement> inputs = driver.findElements(By.tagName("input"));
            inputs.get(0).sendKeys("Петров Иван");
            inputs.get(1).sendKeys("+89632541725");
            driver.findElement(By.className("checkbox")).click();
            driver.findElement(By.tagName("button")).click();
            String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
            String actual = driver.findElement(By.className("paragraph")).getText().trim();
            assertEquals(expected, actual);
        }
}
