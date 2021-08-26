import io.percy.selenium.Percy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PercyTest {
    @Parameters("test_name")
    @Test
    public void test(String test_name) throws Exception {
        System.setProperty("webdriver.chrome.driver", "/Users/nithyamani/Desktop/chromedrivers/chromedriver91");
        WebDriver driver = new ChromeDriver();
        Percy percySnapshots = new Percy(driver);
        driver.get("http://localhost:8000/");
        percySnapshots.snapshot("Main Page "+test_name);

        driver.findElement(By.linkText("Contact Us!")).click();
        percySnapshots.snapshot("Contact Us Page "+test_name);

        Thread.sleep(2000);
        driver.quit();
    }
}
