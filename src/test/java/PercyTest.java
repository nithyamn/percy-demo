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
    //@Parameters({"test_name","url"})
    //@Test
    public void test(String test_name, String url) throws Exception {
        //System.setProperty("webdriver.chrome.driver", "/Users/nithyamani/Desktop/Tools/chromedrivers/chromedriver100");
        ArrayList<Integer> widths = new ArrayList();
        //widths.add(200);
//        widths.add("300");
//        widths.add("400");

        //WebDriver driver = new ChromeDriver();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browser","chrome");
        capabilities.setCapability("build","JLP percy");
        capabilities.setCapability("name","test");
        WebDriver driver = new RemoteWebDriver(new URL("https://nithyamani3:PsurqC6o6MphBNxyphUy@hub.browserstack.com/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Percy percySnapshots = new Percy(driver);
        driver.get(url);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"root\"]/div/header/nav/ul/li[1]/a"))).perform();
        percySnapshots.snapshot("Main Page "+test_name);
//        driver.findElement(By.id("postcode")).sendKeys("SE1 2LL");
//        driver.findElement(By.id("search-address-btn")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.cssSelector("button .c-eWMPLj")).click();
//        driver.findElement(By.id("downshift-0-item-1")).click();
//
//        Thread.sleep(3000);
//        percySnapshots.snapshot("Show button and map", widths, 1024, true);

//        driver.findElement(By.linkText("Contact Us!")).click();
//        percySnapshots.snapshot("Contact Us Page "+test_name);
//        percySnapshots.snapshot("contact_us.png");
        //percySnapshots.snapshot("contact_us.png", widths, 1048,true);
        Thread.sleep(2000);
        driver.quit();
    }

    @Parameters({"test_name","url"})
    @Test
    public void onpremTest(String test_name, String url) throws Exception {
        System.setProperty("webdriver.chrome.driver", "/Users/nithyamani/Desktop/Tools/chromedrivers/chromedriver100");
        ArrayList<Integer> widths = new ArrayList();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Percy percySnapshots = new Percy(driver);
        driver.get(url);
        percySnapshots.snapshot("Main Page "+test_name);

        driver.findElement(By.linkText("Contact Us!")).click();
        percySnapshots.snapshot("Contact Us Page "+test_name);

        Thread.sleep(2000);
        driver.quit();
    }

}
