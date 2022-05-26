package day07_assertions_dropdown;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class C01_Assertions {
    /*
    Assert.(method),
    @BeforeClass, @AfterClass, @Before, @After,
    .click(),
    sendKeys("Ali" + Keys.ENTER)
    select - getOptions(), getFirstSelectedOption(), getText()


    - Assert.(method) ile if-else kullanimina son verdik.
        Kullanmadan once beklenen sonucun true-false oldugunu belirlemeliyiz
        Beklenen sonuc ile gerceklesen ayni ise test PASSED, degilse test FAILED olur.
        a=20, b=40  Assert.assertEqual(a,b) -- failed
            Assert.assertFalse(a>b) -- passed (sonuc false, beklenen false)

     - @BeforeClass - ayni classda birden cok test yapacaksak
        her defasinda sayfayi kapatip acmamak icin kullandik.
        Tek bir test varsa @Before kullanilabilir

     - .click(); --> locate edilen yere tiklar
     - .sendKeys("Ali" + Keys.ENTER); --> locate edilen yere Ali yazip Enter'a basar

     - dropDown - acilir menu <select> tag'iyla kullanilir.
        kullanmak icin 3 adim izlemeliyiz.
        - DropDownMenu locate edilir. (WebElement ddm = driver.findElement(By.xpath("//.."));)
        - Select objesi olusturup locate edilen DropDownMenu'yu element yap (Select select = new Select(ddm);)
        - 3 yontemden birisi ile istenen ddm elemanini sec
            - select.selectByVisibleText("Books");
            - select.selectByIndex(5);
            - select.selectByValue("search-alias...");

        - select.getFirstSelectedOption() ile son secilen optiona'a gideriz, bu WebElement dondurur,
            getText() ile uzerindeki yaziyi yazdirilir
     */

    static WebDriver driver;

    @BeforeClass // ayni gorevde birden cok test yapacaksa her defasinda calismasi icin @Before yapmadik
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @AfterClass
    public static void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
    }

    @Test
    public void test01() {
        driver.get("https://www.amazon.com");
        // 1- URLin amazon icerdigini test edin
        String arananKelime = "amazon";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(arananKelime)); //beklenen true - sonuc true --> PASSED
    }

    @Test
    public void test02() {
        driver.get("https://www.amazon.com");
        // 2- title'in facebook icermedigini test edin
        String istenmeyenKelime = "facebook";
        String actualUrl = driver.getTitle();
        Assert.assertFalse(actualUrl.contains(istenmeyenKelime)); //beklenen FALSE - sonuc FALSE --> PASSED
    }

    @Test
    public void test03() {
        // https://www.bestbuy.com/ sayfasinda 'BestBuy' logosunun görüntülendigini test edin
        driver.get("https://www.bestbuy.com/");
        WebElement logoElementi = driver.findElement(By.xpath("(//img[@class='logo'])[1]"));
        Assert.assertTrue(logoElementi.isDisplayed()); //beklenen gorunmesi(TRUE), sonuc gorunmesi(TRUE) --> PASSSED
    }

    @Test
    public void test04() {
        // http://automationpractice.com/index.php sayfasina gidin,
        // Sign in butonuna basin,
        // Email'e @isareti olmayan bir mail yazip ENTER’a basinca
        // “Invalid email address” uyarisi ciktigini test edin
        driver.get("http://automationpractice.com/index.php");
        driver.findElement(By.xpath("//a[@class='login']")).click();
        WebElement mailKutusu = driver.findElement(By.xpath("//input[@id='email_create']"));
        mailKutusu.sendKeys("mehmetgmail.com" + Keys.ENTER);
        WebElement uyariYazisiElementi = driver.findElement(By.xpath("//*[text()='Invalid email address.']"));
        Assert.assertTrue(uyariYazisiElementi.isDisplayed());
    }

    @Test
    public void test05() {
        // amazon'da, dropdown'dan books secip, Java aratip, sonuclarinin Java icerdigini test et
        driver.get("https://www.amazon.com");
        WebElement dropDownMenu = driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
        Select select = new Select(dropDownMenu);
        select.selectByVisibleText("Books");
        WebElement aramaKutusu = driver.findElement(By.id("twotabsearchtextbox"));
        aramaKutusu.sendKeys("Java" + Keys.ENTER);
        WebElement sonucYazisiElementi = driver.findElement(By.xpath("(//div[@class='a-section a-spacing-small a-spacing-top-small'])[1]"));
        String sonucYazisiStr = sonucYazisiElementi.getText();
        String arananKelime = "Java";
        Assert.assertTrue(sonucYazisiStr.contains(arananKelime));
    }

    @Test
    public void test06() {
        // https://the-internet.herokuapp.com/dropdown adresine gidin.
        driver.get("https://the-internet.herokuapp.com/dropdown");
        //  1.Index kullanarak Secenek 1’i (Option 1) secin ve yazdirin
        WebElement ddm = driver.findElement(By.xpath("//select[@id='dropdown']"));
        Select select = new Select(ddm);
        select.selectByIndex(1);
        System.out.println("ByIndex ile Option 1: " + select.getFirstSelectedOption().getText());
        //  2.Value kullanarak Secenek 2'yi (Option 2) secin ve yazdirin
        select.selectByValue("2");
        System.out.println("ByValue ile Option 2: " + select.getFirstSelectedOption().getText());
        //  3.Visible Text(Gorunen metin) kullanarak Secenek 1’i (Option 1) secin ve yazdirin
        select.selectByVisibleText("Option 1");
        System.out.println("VisibleText ile Option 1: " + select.getFirstSelectedOption().getText());
        //  4.Tum dropdown options'i yazdirin
        List<WebElement> tumOpsiyonlar = select.getOptions();
        System.out.print("Tum opsiyonlar: ");
        for (WebElement each : tumOpsiyonlar
        ) {
            System.out.print(each.getText() + ", ");
        }
        System.out.println();
        //  5. Dropdown’un boyutunu bulun, boyut 4 ise True, degilse False yazdırın.
        System.out.print("Dropdown boyutu 4 mu?: ");
        int dropdownBoyut = tumOpsiyonlar.size();
        if (dropdownBoyut == 4) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}

