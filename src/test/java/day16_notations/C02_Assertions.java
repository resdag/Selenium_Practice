package day16_notations;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.TestBase;

public class C02_Assertions extends TestBase {

    @Test
    public void test01() {
        /*
        *   TestNG'de JUnit'teki gibi Assert kullanilabilir.

        *   FAILED olan testin diger testleri etkilememesi icin SoftAssert kullanilir
            Softassert'un hata bulsa bile assertAll() method'una kadar calisir ve
            bize buldugu hatalari raporlar. Assertion'larda FAILED olan varsa assertAll() sonrasi calismaz.

        *   Softassert 3 adimda olusturulur;
            - softassert baslangici obje olusturmaktir
                 SoftAssert softAssert = new SoftAssert();
            - ilgili kosulu kontrol et
                softAssert.assertTrue(beklene, guncel);
            - softassert sonuclarini raporla
                softAssert.assertAll();
                * Assertion'larda FAILED olan varsa buradan sonrasi calismaz.
         *  softAssert.assertTrue(kosul, "aciklama"); ile aciklama eklenebilir.

         */

        SoftAssert softAssert = new SoftAssert();
        // 1- amazon anasayfaya gidin
        driver.get("https://www.amazon.com");
        // 2- title'in Amazon icerdigini test edin
        String expectedTitle = "Amazon";
        String actualTitle = driver.getTitle();
        softAssert.assertTrue(actualTitle.contains(expectedTitle), "title amazon icermiyor");
        // 3- Arama kutusunun erisilebilir oldugunu test edin
        WebElement aramaKutusu = driver.findElement(By.id("twotabsearchtextbox"));
        softAssert.assertTrue(aramaKutusu.isEnabled(), "arama kutusuna erisilemiyor");
        // 4- arama kutusuna nutella yazip aratin
        aramaKutusu.sendKeys("Nutella" + Keys.ENTER);
        // 5- arama yapildigini test edin
        WebElement sonucYaziElementi = driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']"));
        softAssert.assertTrue(sonucYaziElementi.isDisplayed(), "arama yapilamadi");
        // 6- arama sonucunun Nutella icerdigini test edin
        softAssert.assertTrue(sonucYaziElementi.getText().contains("Nutella"), "sonuc yazisi Kutella icermiyor");
        softAssert.assertAll();
        System.out.println("assertion'lardan fail olan olursa, burasi calismaz");
    }
}
