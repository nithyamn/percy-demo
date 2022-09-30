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
import io.github.bonigarcia.wdm.WebDriverManager;


public class PercyTest {

    //@Parameters({"url"})
    @Test
    public void onpremTest() throws Exception {
        WebDriverManager.chromedriver().setup();
        ArrayList<Integer> widths = new ArrayList();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Percy percySnapshots = new Percy(driver);
        driver.get("https://bstackdemo.com/signin");
        percySnapshots.snapshot("test-1.png");
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
        capabilities.setCapability("browser","Safari");
        capabilities.setCapability("browser_version","14.0");
        capabilities.setCapability("build","Bstak Percy Integration");
        capabilities.setCapability("name","test");
        WebDriver driver = new RemoteWebDriver(new URL("https://"+username+":"+accesskey+"@hub.browserstack.com/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        Percy percySnapshots = new Percy(driver);
        driver.get("https://www.wearlenses.no/online-help");
        Thread.sleep(2000);
        //driver.switchTo().alert().accept();

        percySnapshots.snapshot("Main Page");

        driver.quit();
    }
}