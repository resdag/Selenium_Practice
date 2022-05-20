package day09_handleWindows;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

public class C02_exercise {
    static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @AfterClass
    public static void hearDown() throws InterruptedException {
        driver.quit();
    }

    @Test
    public void test01() {
        driver.get("https://www.amazon.com");
        WebElement aramaKutusuElementi = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        aramaKutusuElementi.sendKeys("Nutella" + Keys.ENTER);
        driver.findElement(By.xpath("(//img[@class='s-image'])[1]")).click();
        String ilkDeger= handleDegeriBul();
        System.out.println("ilkDeger = " + ilkDeger);

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://www.facebook.com");
        String ikinciDeger= handleDegeriBul();
        System.out.println("ikinciDeger = " + ikinciDeger);

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://www.instagram.com");
        String ucuncuDeger= handleDegeriBul();
        System.out.println("ucuncuDeger = " + ucuncuDeger);

    }

    private String handleDegeriBul() {
        Set<String> windowHandleSeti = driver.getWindowHandles();
        String windowHandleDegeri = "";

        for (String each : windowHandleSeti) {
            if (!windowHandleDegeri.equals(each)) {
                windowHandleDegeri = each;
            }
        }
        return windowHandleDegeri;
    }
}
