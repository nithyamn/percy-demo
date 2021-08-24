import io.percy.selenium.Percy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class PercyTest {
    @Test
    public void test() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/Users/nithyamani/Desktop/chromedrivers/chromedriver91");
        WebDriver driver = new ChromeDriver();
        Percy percySnapshots = new Percy(driver);
        driver.get("http://localhost:8000/");
        percySnapshots.snapshot("Main Page");

        driver.findElement(By.linkText("Contact Us!")).click();
        percySnapshots.snapshot("Contact Us Page");

        Thread.sleep(2000);
        driver.quit();
    }
}
