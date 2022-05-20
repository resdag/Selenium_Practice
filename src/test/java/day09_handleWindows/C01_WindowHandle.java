package day09_handleWindows;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

public class C01_WindowHandle {
    /*
    driver.getWindowHandle() - bulunulan window'un hash kodunu dondurur
          .getWindowHandles() -- set ile tum window'larin  hash kodunu dondurur

    driver.switchTo().window("Handle Degeri") -- bu handle degeri olan sayfaya gider
                     (CDwindow-12168F63FA40495174B45A2348A68CAC)
                     .newWindow(WindowType.WINDOW); -- yeni pencere acar
                     .newWindow(WindowType.TAB); -- yeni sayfa acar

       * kontrolsuz olarak yeni sekme/pencereye gidersek bu sayfanin handle degerine ulasmak gerekir
     */

    static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void test01() {
        // 1-amazon'a gidin
        driver.get("https://amazon.com");
        String ilkSayfaWindowHandleDegeri = driver.getWindowHandle();
        System.out.println(ilkSayfaWindowHandleDegeri);
        // 2-URL'in amazon icerdigini test edin
        String istenenKelime = "amazon";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(istenenKelime));
        // 3-yeni pencere acip bestbuy ana sayfaya gidin
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://www.bestbuy.com");
        String ikinciSayfaWindowHandleDegeri = driver.getWindowHandle();
        // 4-title Best Buy icerdigini test edin
        String actualTitle = driver.getTitle();
        String arananKelime = "Best Buy";
        Assert.assertTrue(actualTitle.contains(arananKelime));
        // 5-ilk sayfaya donup sayfada java aratin
        driver.switchTo().window(ilkSayfaWindowHandleDegeri);
        // 6-arama sonuclarinin java icerdigini test edin
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Java" + Keys.ENTER);
        WebElement sonucYaziElementi = driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']"));
        String sonucYazisiStr = sonucYaziElementi.getText();
        String aradigimizKelime = "Java";
        Assert.assertTrue(sonucYazisiStr.contains(aradigimizKelime));
        // 7-yeniden bestbuy sayfasina gidin
        driver.switchTo().window(ikinciSayfaWindowHandleDegeri);
        // 8-logonun gorundugunu test edin
        WebElement logoElementi = driver.findElement(By.xpath("(//img[@class='logo'])[1]"));
        Assert.assertTrue(logoElementi.isDisplayed());
    }

    @Test
    public void test02() {
        //● https://the-internet.herokuapp.com/windows adresine gidin.
        driver.get("https://the-internet.herokuapp.com/windows");
        //● Sayfadaki textin “Opening a new window” olduğunu doğrulayın.
        WebElement sayfadakiYaziElementi = driver.findElement(By.xpath("//h3"));
        String expectedYazi = "Opening a new window";
        String actualYazi = sayfadakiYaziElementi.getText();
        Assert.assertEquals(expectedYazi, actualYazi);

        //● Sayfa başlığının(title) “The Internet” olduğunu doğrulayın.
        String expectedTitle = "The Internet";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
        String ilkSayfaWindowHandleDegeri = driver.getWindowHandle();
        System.out.println(ilkSayfaWindowHandleDegeri);

        //● Click Here butonuna basın.
        driver.findElement(By.linkText("Click Here")).click();
        Set<String> windowHandleseti = driver.getWindowHandles();
        System.out.println(windowHandleseti);
        String ikinciSayfaWindowHandleDegeri = "";
        for (String each : windowHandleseti
        ) {
            if (!each.equals(ilkSayfaWindowHandleDegeri)) {
                ikinciSayfaWindowHandleDegeri = each;
            }
        }
        driver.switchTo().window(ikinciSayfaWindowHandleDegeri);

        //● Acilan yeni pencerenin sayfa başlığının (title) “New Window” oldugunu dogrulayin.
        String expectedIkinciTitle = "New Window";
        String actualIkinciTitle = driver.getTitle();
        Assert.assertEquals(expectedIkinciTitle, actualIkinciTitle);
        //● Sayfadaki textin “New Window” olduğunu doğrulayın.
        WebElement ikinciSayfaYaziElementi = driver.findElement(By.xpath("//h3"));
        String expectedIkinciYazi = "New Window";
        String actualIkinciYazi = ikinciSayfaYaziElementi.getText();
        Assert.assertEquals(expectedIkinciYazi, actualIkinciYazi);
        //● Bir önceki pencereye geri döndükten sonra sayfa başlığının
        // “The Internet” olduğunu doğrulayın.
        driver.switchTo().window(ilkSayfaWindowHandleDegeri);
        expectedTitle = "The Internet";
        actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }
}
