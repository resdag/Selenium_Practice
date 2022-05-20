package day05_JUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C02_JUnit {
    /*
    - JUnit bir framework'tur
    - Bu framework'u kullanmak icin, pom.xml'e
        https://mvnrepository.com/artifact/junit/junit/4.13.2
        <dependencies> taglari arasina ekledik ve maven'dan yeniledik.

    -  @ isareti annotation'dir ve bununla yorumlayiciya talimat veririz.
    Bu(@Test) Notasyonuyla isaretle main method olmadan testleri calistirabiliriz.

    - @Before/After - sadece bir web sayfasinda calisirken kullanilir.
        Classtaki her methodun basinda ve sonunda birer defa calisir.
        3 tane test olan bir class calistirilinca test methodlariyla birlikte 9(3+6) method calisir.
        Bu class'ta @BeforeClass ve @AfterClass'da varsa 11 tane calisir.

    - @BeforeClass/AfterClass - birden fazla sayfada calisirken kullanilir.
        Sadece static methodlarla ve class'in basinda ve sonunda sadece bir defa calisir.
        3 tane test olan bir class calistirilinca test methodlariyla birlikte 5(3+2) method calisir.
     */

    static WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        System.out.println("Before calisti");
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.close();
        System.out.println("After calisti");
    }

   // @BeforeClass
   // public static void setUp2() {
   //     System.out.println("BeforeClass calisti");
   // }

   // @AfterClass
   // public static void tearDown2() {
   //     System.out.println("AfterClass calisti");
   // }

    @Test
    public void test01() {
        // Before method call
        driver.get("https://www.amazon.com");
        // After method call
    }

    @Test
    public void test02() {
        // Before method call
        driver.get("https://www.instagram.com");
        // After method call
    }

    @Test
    public void test03() {
        // Before method call
        driver.get("https://www.facebook.com");
        // After method call
    }
}
