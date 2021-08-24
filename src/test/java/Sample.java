import io.percy.selenium.Percy;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import java.io.*;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Sample {
    public WebDriver driver;
    public Percy percy;
    public static int count=0;


    @Test
    public void test() throws Exception {
        ChromeOptions options = new ChromeOptions();

        //options.addArguments("--headless");
        //options.addArguments("--start-maximized");

        System.setProperty("webdriver.chrome.driver", "/Users/nithyamani/Desktop/chromedrivers/chromedriver89");
        driver = new ChromeDriver(options);
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //Get basic auth value
        //System.out.println("Authorization: "+basicAuthHeaderGeneration("admin","admin"));
        //Authorization: Basic YWRtaW46YWRtaW4=
        percy = new Percy(driver);

        //driver.get("https://the-internet.herokuapp.com/basic_auth");
        driver.get("https://0bik9ag28kxjqaj.ap.qlikcloud.com/sense/app/0f8ed451-620a-4e6b-9080-05fc2b1e0c98/sheet/46ec0784-7290-426c-8d1a-bcb1e5744fd9/state/analysis/hubUrl/%2Fitem%2F60745f4a70ffa3dff5cdf0f0%2Fdetails");

        driver.findElement(By.xpath("//*[@id=\"lock-container\"]/div/div/form/div/div/div[3]/span/div/div/div/div/div/div/div/div/div/div[1]/div[2]/input")).sendKeys("nithya@browserstack.com");
        driver.findElement(By.xpath("//*[@id=\"lock-container\"]/div/div/form/div/div/div[3]/span/div/div/div/div/div/div/div/div/div/div[2]/div/div[2]/input")).sendKeys("Reset@me15");
        percy.snapshot("Qliksense Login page");
        Thread.sleep(3000);
        //takeSnapShot(driver,"src/test/upload_file/loginPage.png");
        driver.findElement(By.xpath("//*[@id=\"lock-container\"]/div/div/form/div/div/div[4]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"sheet-title\"]/span")).getText();
        Thread.sleep(6000);
        //takeSnapShot(driver,"src/test/upload_file/chartsPage.png");
        percy.snapshot("Qliksense data charts");

        //Thread.sleep(5000);
        //percy.snapshot("Basic Auth sample page");

        Thread.sleep(5000);
        driver.quit();
    }

    public String basicAuthHeaderGeneration(String authUsername, String authPassword){
        String authCreds = "admin:admin";
        System.out.println("Basic " + Base64.getEncoder().encodeToString(authCreds.getBytes()));
        return "Basic " + Base64.getEncoder().encodeToString(authCreds.getBytes());
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

    /*

     Random random = new Random();
        int upperBound = 100;
        int generateNum = random.nextInt(upperBound);


    public void startPercyServer() throws Exception {
        String[] command = new String[] {
                "npx","percy","exec:start"
        };
        String inputLine = "Y";

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        Process p = pb.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

        writer.write(inputLine);
        writer.newLine();
        writer.close();

        String line;

        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }*/
}
