package day08_alerts;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C01_Alerts_Authentication_Iframe {
    /*
    .alert().switchTo().alert() / iframe() / window()  -- ilgili yere gider
               .switchTo().alert().accept() -- alert'i kabul eder
                            .alert().dismiss() -- alert'i reddeder
                            .alert().sendKeys("Ali Bal"); -- alert'e deger girer

     .alert().switchTo().frame(iFrameElementi / index / nameOrId); -- iframeElementinin oldugu web sayfasina gider
               .switchTo().defaultContent(); -- en ust frame doner
               .switchTo().parentFrame(); -- bir ust frame gecer

    .clear() -- yaziyi siler

    .alert() -- uyaridir.    HTML alert'lere islem gerekmez.
                            JS alertler ise ciktiginda locate edilemez.


    Basic Authentication -- siteye girince dogrulama istiyorsa verileri aldigimiz
    API nasil girmemiz gerektigini de soylemelidir. Orn: Normal URL yerine, 'https://username:password@URL' gibi

    iframe: Web stesindeki HTML tagleri arasinda olmalidir.
    bu varsa ilgili bolum ayri bir HTML tag'i yani ayri web sayfasi icerir.
    buraya gecmek icin switchTo kullanilmalidir.
    .switchTo() ile iframe'e gecersek eski sayfaya geri donmemiz gerekir
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
    public static void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void alertTest() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElement(By.xpath("//*[text()='Click for JS Alert']")).click();
        driver.switchTo().alert().accept();
        Thread.sleep(2000);

        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElement(By.xpath("//*[text()='Click for JS Confirm']")).click();
        driver.switchTo().alert().dismiss();
        Thread.sleep(2000);

        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElement(By.xpath("//*[text()='Click for JS Prompt']")).click();
        driver.switchTo().alert().sendKeys("Ali Bal");
        driver.switchTo().alert().accept();
        Thread.sleep(2000);

        WebElement sonucYazisiElementi = driver.findElement(By.xpath("//p[@id='result']"));
        String sonucYazisiStr = sonucYazisiElementi.getText();
        String girilenIsim = "Ali Bal";
        Assert.assertTrue(sonucYazisiStr.contains(girilenIsim));
    }

    @Test
    public void basicAuthenticationTest() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/basic_auth"); // boyle yazinca authentication ister ve giremeyiz
        Thread.sleep(2000);
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        Thread.sleep(2000);
    }

    @Test
    public void iFrameTest() throws InterruptedException {
        // https://the-internet.herokuapp.com/iframe adresine gidin.
        driver.get("https://the-internet.herokuapp.com/iframe");
        // “An IFrame containing….” textinin erisilebilir oldugunu test edin ve  yazdirin.
        WebElement baslikElementi = driver.findElement(By.xpath("//h3"));
        Assert.assertTrue(baslikElementi.isEnabled());
        System.out.println(baslikElementi.getText());

        // Text Box’a “Merhaba Dunya!” yazin.
        WebElement iFrameElementi = driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame(iFrameElementi);
        WebElement textkutusu = driver.findElement(By.xpath("//body[@id='tinymce']"));
        textkutusu.clear();
        textkutusu.sendKeys("Mehaba Dunya");

        // TextBox’in altinda bulunan “Elemental Selenium” linkinin textinin gorunur oldugunu  dogrulayin ve  yazdirin.
        driver.switchTo().defaultContent();  // iframe'den eski sayfaya geri donduk
        WebElement linkYaziElementi = driver.findElement(By.linkText("Elemental Selenium"));
        Assert.assertTrue(linkYaziElementi.isDisplayed());
        System.out.println(linkYaziElementi.getText());
        Thread.sleep(3000);
    }


}
