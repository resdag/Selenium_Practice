package day11_faker_file;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.TestBaseJUnit;

import java.nio.file.Files;
import java.nio.file.Paths;

public class C01_Faker_File extends TestBaseJUnit {
    /*
    - Faker kütüphanesini pom.xml'e ekledik.

    - Faker faker = new Faker(); //yeni obje olusturup bunun uzerinden islem yapariz
        String fakeMail = faker.internet().emailAddress();
        actions.click(isimKutusu)
                .sendKeys(faker.name().firstName()) // fake isim yazar
                .sendKeys(faker.internet(). //fake parola yazar
                .sendKeys(Keys.ENTER)
                .perform(); //en sonda performu unutmamaiyiz

    - File Exist / File DownLoad / File Upload
        - System.getProperty("user.dir") --> icinde olunan dosyanin yolunu verir
        - System.getProperty("user.home") --> dosya yolunun her bildisayar icin farkli olabilecek kismini verir
                String dosyaYolu = System.getProperty("user.home") + "\\Desktop\\text.txt"; --> DINAMIK dosya yolu
        - File.exists(Paths.get("filePath)) --> belirtilen klasorde dosyanin olup olmadigini kontrol eder

        - Dosya yuklemek icin dosya yukleme butonuna dosya adresi yollanir
            -dosyaSecButonu.sendKeys(yuklenecekDosya);
     */

    @Test
    public void fileDownLoad() throws InterruptedException {
        //1. https://the-internet.herokuapp.com/download adresine gidin
        driver.get("https://the-internet.herokuapp.com/download");

        //2. dummy.txt dosyasını indirin
        WebElement dummyLinki = driver.findElement(By.xpath("//a[text()='dummy.txt']"));
        dummyLinki.click();
        Thread.sleep(5000); //indirme suresini bekledik

        //3. dosyanın başarıyla indirilip indirilmediğini test edin
        String farkliKisim = System.getProperty("user.home");
        String ortakKisim = "\\Downloads\\dummy.txt";
        String arananDosyaYolu = farkliKisim + ortakKisim;
        Assert.assertTrue(Files.exists(Paths.get(arananDosyaYolu)));
    }

    @Test
    public void fileExist() {
        driver.close(); // driver'a ihtiyac olmadigindan direkt kapattik
        String farkliKisim = System.getProperty("user.home"); // herkesin bilgisayarinda ortak olan kisim ise
        String ortakKisim = "\\Downloads\\dummy.txt"; // // indirilenlerdeki dummy.txt icin ortak adres
        String masaustuDosyaYolu = farkliKisim + ortakKisim;
        Assert.assertTrue(Files.exists(Paths.get(masaustuDosyaYolu))); // indirilenlerdeki dummy.txt yoksa false/failed olur
    }

    @Test
    public void fileUpload() throws InterruptedException {
        //1. https://the-internet.herokuapp.com/upload adresine gidelim
        driver.get("https://the-internet.herokuapp.com/upload");

        //2. chooseFile butonuna basin ve bir dosya secin
        WebElement dosyaSecButonu = driver.findElement(By.id("file-upload")); //dosya sec butonunu locate ettik
        String farkliKisim = System.getProperty("user.home");
        String ortakKisim = "\\Downloads\\dummy.txt";
        String yuklenecekDosya = farkliKisim + ortakKisim; //yuklenecek doyanin dosya yolu
        dosyaSecButonu.sendKeys(yuklenecekDosya); // sendKeys ile dosya yolunu, secme butonuna yolladik

        //3. secilen dosyayi yukleyin
        driver.findElement(By.xpath("//input[@id=\"file-submit\"]")).click(); //upload butonuna bastik

        //4. “File Uploaded!” textinin goruntulendigini test edin.
        WebElement yaziElementi = driver.findElement(By.tagName("h3"));
        Assert.assertTrue(yaziElementi.isDisplayed());
        Thread.sleep(5000);
    }
}
