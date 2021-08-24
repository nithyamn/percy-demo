import io.percy.selenium.Percy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class iFrameSelTest {
    public WebDriver driver;
    public Percy percy;
    @Test
    public void test() throws Exception {

        System.setProperty("webdriver.chrome.driver", "/Users/nithyamani/Desktop/chromedrivers/chromedriver91");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        percy = new Percy(driver);

        driver.get("https://www.browserstack.com");
        percy.snapshot("Bstack demo");
//        driver.get("http://localhost/PercyWebsite/iFrameTest.html");
//        Thread.sleep(2000);
//        percy.snapshot("Main page");
//        driver.switchTo().frame(0);
//        Thread.sleep(2000);
//        percy.snapshot("charts");
//        Thread.sleep(5000);
        driver.quit();
    }
}
