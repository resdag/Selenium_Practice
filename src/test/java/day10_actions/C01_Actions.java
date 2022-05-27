package day10_actions;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.TestBaseJUnit;

import java.util.Set;

public class C01_Actions extends TestBaseJUnit {
    /*
    src/test/java/utilities altinda olusturdugumuz TestBase class'ini extend ederek
    oradaki Before/After methodlarini kullanabiliriz.

    Actions 3 adimda yapilir.
        -Actions actions = new Actions(driver); --> obje olusturulup paremetre olarak driver kullanilir
        -WebElement accountLinki = driver.findElement(By.xpath("..")); --> istenen element locate edilir
        -actions.moveToElement(accountLinki).perform(); --> istenen islem yapilir

    ---Mause
    .click() --> tek tiklar
    .doubleClick() --> cift tiklar
    .moveToElement(element).perform() --> fareyi istenen konuma goturur
    .contextClick(element).perform() --> farede sag tiklar
    .dragAndDrop(tasinacakElement, hedefElement).perform(); --> elementi istenen yere surukleyip birakir
    .clickAndHold() --> click yapip bizden komut bekler

    ---Klavye
    .sendKeys() --> basilan tusu yollar
    .keyDown() --> tusa basili tutar
    .keyUp() --> tusa basmayi birakir
     */

    @Test
    public void test01() throws InterruptedException {
        //amazon'da account menusunden create a list'e tiklayin
        driver.get("https://www.amazon.com");
        Actions actions = new Actions(driver);
        WebElement accountLinki = driver.findElement(By.xpath("//*[text()='Account & Lists']"));
        actions.moveToElement(accountLinki).perform();
        driver.findElement(By.xpath("//*[text()='Create a List']")).click();
        Thread.sleep(5000);
    }


    @Test
    public void test02() {
        //2- https://the-internet.herokuapp.com/context_menu sitesine gidin
        driver.get("https://the-internet.herokuapp.com/context_menu");
        //3- Cizili alan uzerinde sag click yapin
        Actions actions = new Actions(driver);
        WebElement cizgiliAlanElementi = driver.findElement(By.id("hot-spot"));
        actions.contextClick(cizgiliAlanElementi).perform();
        //4- Alert’te cikan yazinin “You selected a context menu” oldugunu test edin
        String expectedYazi = "You selected a context menu";
        String actualYazi = driver.switchTo().alert().getText();
        Assert.assertEquals(expectedYazi, actualYazi);
        //5- Tamam diyerek alert’i kapatin
        driver.switchTo().alert().accept();
        //6- Elemental Selenium linkine tiklayin
        String ilkSayfaWHDegeri = driver.getWindowHandle();
        driver.findElement(By.xpath("//*[text()='Elemental Selenium']")).click();
        Set<String> handleSeti = driver.getWindowHandles();
        String ikinciSayfaWHDegeri = "";
        System.out.println(handleSeti);
        for (String each : handleSeti
        ) {
            if (!each.equals(ilkSayfaWHDegeri)) {
                ikinciSayfaWHDegeri = each;
            }
        }
        //7- Acilan sayfada h1 taginda “Elemental Selenium” yazdigini test edin
        driver.switchTo().window(ikinciSayfaWHDegeri);
        WebElement yaziElementi = driver.findElement(By.tagName("h1"));
        String expectedIkinciYazi = "Elemental Selenium";
        String actualIkinciYazi = yaziElementi.getText();
        Assert.assertEquals(expectedIkinciYazi, actualIkinciYazi);
    }

    @Test
    public void test03() throws InterruptedException {
        // amazon'a harf harf Bal yazip Enter'a basin
        driver.get("https://www.amazon.com");
        WebElement aramaKutusu = driver.findElement(By.id("twotabsearchtextbox"));
        Actions actions = new Actions(driver);

        actions.click(aramaKutusu)
                .keyDown(Keys.SHIFT)
                .sendKeys("b")
                .keyUp(Keys.SHIFT)
                .sendKeys("a")
                .sendKeys(Keys.ENTER)
                .perform();

        Thread.sleep(3000);
    }


}
