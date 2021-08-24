import com.browserstack.local.Local;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

public class BStackHeadfulTest {

    public WebDriver driver;
    public  Local bsLocal;
    public DesiredCapabilities capabilities;
    public String username = System.getenv("BROWSERSTACK_USERNAME");
    public String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    public String HUB_URL = "https://"+username+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub";
    public String TEST_URL = "http://localhost:45691/check";
    public String FILE_PATH = "src/test/upload_file/";

    @BeforeMethod
    public void setUpDriver() throws Exception{

        capabilities = new DesiredCapabilities();
        capabilities.setCapability("browser","Chrome");
        capabilities.setCapability("browser_version","87.0");
        capabilities.setCapability("os","Windows");
        capabilities.setCapability("os_version","10");
        capabilities.setCapability("build","Snapshots");
        capabilities.setCapability("name","mainPageSnapshotHeadful");
        capabilities.setCapability("browserstack.local","true");

        startLocal();
        driver = new RemoteWebDriver(new URL(HUB_URL),capabilities);

    }

    @Test
    public void testCase() throws Exception{
        driver.get(TEST_URL);
        /**Add any additional test steps here needs to reach to the main page which needs to be captured in the snapshot**/

        takeSnapShot(driver,FILE_PATH+"bsSnapshotHeadful.png");

        //Wating for the snapshot to be captured successfully
        Thread.sleep(3000);
    }

    public void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
        //Call getScreenshotAs method to create image file
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        File DestFile=new File(fileWithPath);
        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
    }
    public void startLocal() throws Exception{
            bsLocal = new Local();

            HashMap<String, String> bsLocalArgs = new HashMap<String, String>();
            bsLocalArgs.put("key", accessKey);
            bsLocalArgs.put("localIdentifier", "headful");
            //bsLocalArgs.put("forceLocal", "true");


            bsLocal.start(bsLocalArgs);
            System.out.println(bsLocal.isRunning());
    }

    @AfterMethod
    public void teardown() throws Exception {
        if( bsLocal != null)
            bsLocal.stop();
        driver.quit();
    }
}
