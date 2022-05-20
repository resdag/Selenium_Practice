package day03_webElements_locators;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class C01_WebElement_Locators {
    /*
    .findElement(..) -- Tek bir WebElement dondurur. 8 yontem ile locate edilir
                        By.id(), By.name(), By.className(), By.tagName(),
                        By.linkText()(link'teki(a tagi) text), By.partialLinkText() (text'in parcasi)
                        By.xpath(),  By.cssSelector()

    .findElements(..) --  WebElement'lerden olusan list dondurur.

    .sendKeys(".." + Keys.ENTER) -- icine yazilan degeri webelement'e yollar

    .isDisplayed()(gorunur mu), isEnabled()(ulasilabilir mi), isSelected()(secili mi)

    .getTagName() -- locate ettigimiz bir WebElementin tagName'ni dondurur
    .getAttribute("name") -- WebElementin istedigim attribute'unun degerini dondurur (name tag'indaki degeri getirir)

    .getLocation() -- WebElementin'in konumunu dondurur
    .aramaKutusu.getSize().height -- WebElement'in yuksekligi dondurur
     */

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        driver.get("https://www.amazon.com");
        //WebElement aramaKutusu = driver.findElement(By.id("twotabsearchtextbox"));
        WebElement aramaKutusu = driver.findElement(By.name("field-keywords"));
        aramaKutusu.sendKeys("Nutella" + Keys.ENTER); // WebElement'ine yazi yollariz

        WebElement todaysDeals = driver.findElement(By.linkText("Today's Deals"));
        System.out.println("Bugune ozel : " + todaysDeals.getText());

        List<WebElement> linkListesi = driver.findElements(By.tagName("a"));
        System.out.println("Sayfadaki link sayisi : " + linkListesi.size());

        // web elementteki degerleri yazdirin
        // for (WebElement each: linkListesi) {
        //     System.out.println(each.getText());
        // }

        WebElement aramaKutusu2= driver.findElement(By.id("twotabsearchtextbox"));
        String expectedTagName="input";
        String actualTagName= aramaKutusu.getTagName();

        if (expectedTagName.equals(actualTagName)){
            System.out.println("TagName testi PASSED");
        } else {
            System.out.println("TagName testi FAILED");
        }

        String expectedNameDegeri= "field-keywords";
        String actualNameDegeri= aramaKutusu.getAttribute("name");

        if (expectedNameDegeri.equals(actualNameDegeri)){
            System.out.println("name attribute testi PASSED");
        } else {
            System.out.println("name attribute testi FAILED");
        }

        System.out.println("konum : " + aramaKutusu.getLocation());
        System.out.println("webelement yuksekligi : " + aramaKutusu.getSize().height);


        Thread.sleep(2000);
        driver.close();

    }

}
