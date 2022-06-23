import io.percy.selenium.Percy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PercyTest {

    @Parameters({"url"})
    @Test
    public void onpremTest(String url) throws Exception {
        System.setProperty("webdriver.chrome.driver", "/Users/nithyamani/Desktop/Tools/chromedrivers/chromedriver102");
        ArrayList<Integer> widths = new ArrayList();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Percy percySnapshots = new Percy(driver);
        driver.get(url);
        percySnapshots.snapshot("Main Page");
        //percySnapshots.snapshot("name", widths,1024,false,".container{display: none}");

        driver.findElement(By.linkText("Contact Us!")).click();
        percySnapshots.snapshot("Contact Us Page");

        Thread.sleep(2000);
        driver.quit();
    }


    //@Parameters({"url"})
    //@Test
    public void test(String url) throws Exception {
        String username =  System.getenv("BROWSERSTACK_USERNAME");
        String accesskey =  System.getenv("BROWSERSTACK_ACCESS_KEY");
        ArrayList<Integer> widths = new ArrayList();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browser","chrome");
        capabilities.setCapability("browser_version","latest");
        capabilities.setCapability("build","Bstak Percy Integration");
        capabilities.setCapability("name","test");
        WebDriver driver = new RemoteWebDriver(new URL("https://"+username+":"+accesskey+"@hub.browserstack.com/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Percy percySnapshots = new Percy(driver);
        driver.get(url);
        percySnapshots.snapshot("Main Page");
        Thread.sleep(2000);
        driver.quit();
    }
}