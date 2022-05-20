package day08_alerts;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class C02_Practice {

    static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @AfterClass
    public static void tearDown() throws InterruptedException {
        // driver.findElement(By.xpath("(//img[@data-testid='user-avatar'])[1]")).click();
        // driver.findElement(By.xpath("//*[@aria-label='Options']")).click();
        // driver.findElement(By.xpath("//button[text()='Log Out']")).click();
        // Thread.sleep(2000);
        // driver.close();
    }

    @Test
    public void instagramTest01() {
        driver.get("https://www.instagram.com/");
        driver.findElement(By.xpath("//button[text()='Allow essential and optional cookies']")).click();
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("+4915218135559");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("qwert12345"+ Keys.ENTER);
        //driver.findElement(By.xpath("//button[text()='Log In']")).click();
        driver.findElement(By.xpath("//button[text()='Not Now']")).click();
        driver.findElement(By.xpath("//button[text()='Not Now']")).click();

        driver.findElement(By.xpath("(//*[@aria-label='More options'])[1]")).click();


    }

}
