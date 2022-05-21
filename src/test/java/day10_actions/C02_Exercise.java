package day10_actions;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import utilities.TestBase;

import java.util.Set;

public class C02_Exercise extends TestBase {

    @Test
    public void test01() {
        driver.get("https://demoqa.com/droppable");
        WebElement tasinacakElement = driver.findElement(By.xpath("//div[text()='Drag me']"));
        WebElement tasinacakYerElementi = driver.findElement(By.xpath("(//div[@id='droppable'])[1]"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(tasinacakElement, tasinacakYerElementi).perform();
        String beklenenSonuc = "Dropped!";
        WebElement sonucElementi = driver.findElement(By.xpath("//*[text()='Dropped!']"));
        Assert.assertEquals(beklenenSonuc, sonucElementi.getText());
    }

    @Test
    public void test02() {
        driver.get("https://demoqa.com/links");
        //WebElement homeLinkElementi = driver.findElement(By.linkText("Home"));
        String ilkSayfaWHDegeri = driver.getWindowHandle();
        WebElement homeLinkElementi = driver.findElement(By.xpath("(//a[text()='Home'])[1]"));
        homeLinkElementi.click();

        Set<String> tumWHDegerleri = driver.getWindowHandles();
        String ikinciSayfaWHDegeri = "";
        for (String each : tumWHDegerleri) {
            if (!each.equals(ilkSayfaWHDegeri)) {
                ikinciSayfaWHDegeri = each;
            }
        }
        System.out.println(driver.getWindowHandle());
        driver.switchTo().window(ikinciSayfaWHDegeri);
        System.out.println(driver.getWindowHandle());
        //driver.get("https://demoqa.com/elements");
        String baslik= driver.findElement(By.xpath("//img[@alt='Selenium Online Training']")).getText();
        String beklenenUyariMetni = "Selenium"; //fotograf oldugu icin false olmali
        //String actualUyarimetni = driver.findElement(By.xpath("//*[text()='Please select an item from left to start practice.']")).getText();

        Assert.assertFalse(baslik.contains(beklenenUyariMetni));


    }

}
