package day00_exercise;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class D08_C02_IframePractice {
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
        Thread.sleep(2000);
        //driver.close();
    }

    @Test
    public void test01() {
        driver.get("http://demo.guru99.com/test/guru99home/");
        driver.findElement(By.xpath("//p[text()='Consent']")).click();
        List<WebElement> iFrameelementleri = driver.findElements(By.xpath("//iframe"));
        System.out.println("iFrameelementleri = " + iFrameelementleri.size());


    }
}
