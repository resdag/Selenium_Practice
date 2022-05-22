package day12_synchronization_waits;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.TestBase;

import java.time.Duration;

public class C01_ImplicitlyWait_ExplicitlyWait extends TestBase {
    /*
    Java tabanli wait --> Thread.sleep(), sure dolana kadar bekler

    Selenium tabanli wait -->
        ImplicitlyWait -->  tum ogeler icin genel bir maksimum bekleme suresidir
        ExplicitlyWait --> tek bir komut icin istenen kosul saglanana kadar bekler

        WebElement birElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("dosyaYolu")));
        - beklenen sart henuz saglanmadigi icin kosul ve locate etme islemi ayni satirda yapilmalidir.
        - kosul olarak biir cok ssecenek vardir: elementToBeSelected, titleContains, visibilityOfAllElement...

        ExplicitlyWait kullanimi her method'da 3 adimdadir;
        - WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); //obje olustur
        - WebElement birElement = wait.until(ExpectedConditions.titleContains(By.xpath("dosyaYolu"))); //wait ve dosya yolu
        - Assert.assertTrue(birElement.isDisplayed()); //istenen kontrol
     */

    @Test
    public void implicitlyWaitTesti() {
        //3. https://the-internet.herokuapp.com/dynamic_controls adresine gidin.
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        //4. Remove butonuna basin.
        driver.findElement(By.xpath("//button[@onclick='swapCheckbox()']")).click();
        //5. “It’s gone!” mesajinin goruntulendigini dogrulayin.
        // implicitlyWait testBase'de ACIK ve bekleme suresi icinde oldugundan test passed
        WebElement itsGoneYaziElementi = driver.findElement(By.xpath("//p[text()=\"It's gone!\"]"));
        Assert.assertTrue(itsGoneYaziElementi.isDisplayed());
        //6. Add buttonuna basin
        driver.findElement(By.xpath("//button[text()=\"Add\"]")).click();
        //7. It’s back mesajinin gorundugunu test edin
        WebElement itsBackElementi = driver.findElement(By.xpath("//p[text()=\"It's back!\"]"));
        Assert.assertTrue(itsBackElementi.isDisplayed());
    }

    @Test
    public void explicitlyWaitTesti() {
        // Calistirirken TestBase'den implicitlyWait'i kapat

        //3. https://the-internet.herokuapp.com/dynamic_controls adresine gidin.
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        //4. Remove butonuna basin.
        driver.findElement(By.xpath("//button[@onclick='swapCheckbox()']")).click();

        //5. “It’s gone!” mesajinin goruntulendigini dogrulayin.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement itsGoneYaziElementi = wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//p[text()=\"It's gone!\"]")));

        Assert.assertTrue(itsGoneYaziElementi.isDisplayed());
        //6. Add buttonuna basin
        driver.findElement(By.xpath("//button[text()=\"Add\"]")).click();
        //7. It’s back mesajinin gorundugunu test edin
        WebElement itsBackElementi = wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//p[text()=\"It's back!\"]")));
        Assert.assertTrue(itsBackElementi.isDisplayed());
    }
}
