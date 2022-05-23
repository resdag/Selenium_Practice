package day00_exercise;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.TestBase;

import java.time.Duration;

public class D08_C02_Iframe extends TestBase {

    @Test
    public void iFrameTesti() throws InterruptedException {
        // 1- http://webdriveruniversity.com/IFrame/index.htmlsayfasina gidin
        driver.get("http://webdriveruniversity.com/IFrame/index.html");
        // 2 - our products butonuna basin
        String ilkSayfaWHDegeri = driver.getWindowHandle();
        WebElement iFrameElementi = driver.findElement(By.xpath("//iframe[@id='frame']"));
        driver.switchTo().frame(iFrameElementi);
        driver.findElement(By.xpath("//*[text()='Our Products']")).click();
        // 3 - cameras product'i tiklayin
        driver.findElement(By.xpath("//div[@id='container-product1']")).click();
        // 4 - popup mesajini yazin
        Thread.sleep(5000);
        System.out.println(driver.findElement(By.xpath("//div[@class='modal-body']")).getText());

        // WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(15));
        // WebElement metinElementi= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-body']")));
        // System.out.println(metinElementi.getText());

        // 5 - close butonuna basin
        driver.findElement(By.xpath("//*[text()='Close']")).click();
        // 6 - webdriveruniversity.com (IFrame) linkine tiklayin
        driver.switchTo().window(ilkSayfaWHDegeri);
        driver.findElement(By.xpath("//a[text()='WebdriverUniversity.com (IFrame)']")).click();
        // 7 - http://webdriveruniversity.com/index.html sayfasina gittigini kontrol edin
        String beklenenUrl = "http://webdriveruniversity.com/index.html";
        Assert.assertEquals(beklenenUrl, driver.getCurrentUrl());
    }
}
