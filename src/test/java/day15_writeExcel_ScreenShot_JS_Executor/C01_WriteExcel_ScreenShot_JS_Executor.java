package day15_writeExcel_ScreenShot_JS_Executor;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.openqa.selenium.*;
import utilities.TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class C01_WriteExcel_ScreenShot_JS_Executor extends TestBase {
    /*
    - 5. hucreye cell olusturup icine Nufus yazdirma;
        String dosyaYolu = "src/resources/ulkeler.xlsx";
        FileInputStream fis = new FileInputStream(dosyaYolu);
        Workbook workbook = WorkbookFactory.create(fis);
            workbook.getSheet("Sayfa1")
                    .getRow(0).createCell(4)
                    .setCellValue("Nufus");

    - bu degisiklikler workbook'ta olusur onu dosyaya yollamaliyiz:
        FileOutputStream fos = new FileOutputStream(dosyaYolu);
            workbook.write(fos);
    - Dosyayi kapatalim
        workbook.close();
        fis.close();
        fos.close();

    * Tum sayfanin ss'ini almak icin;
        TakesScreenshot objesi olusturmaliyiz
            - TakesScreenshot ts = (TakesScreenshot) driver;
        Kaydedecegimiz yeri path ile gostermeliyiz
            - File tumSayfaResim = new File("target/ekranGoruntuleri/tumSayfa.jpeg");
        Dosyayi gecici klasore kaydedelim
            - File geciciDosya = ts.getScreenshotAs(OutputType.FILE);
        Kaydedilen dosyayi istenen yere kopyalayin
            - FileUtils.copyFile(geciciDosya, tumSayfaResim);

    * Sadece tek elementin ss'ini almak icin;
        WebElement istenenElement = driver.findElement(By.xpath("ss'i alinacak element"));
        File istenenElementSS = new File("target/ekranGoruntuleri/istenenElementSS.jpeg");
        File temp = istenenElement.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(temp, istenenElementSS);

    *JS-Executor kullanmak icin;
        - yeni bir JS objesi olustururuz,
            JavascriptExecutor jse = (JavascriptExecutor) driver;
        - islem yapilacak element locate edilir
            WebElement istenenElement = driver.findElement(By.xpath("locate"));
        - istenen js islemi yapilir
             jse.executeScript("arguments[0].click();", istenenElement);
     */

    @Test
    public void writeExcel() throws IOException {
        String dosyaYolu = "src/resources/ulkeler.xlsx";
        FileInputStream fis = new FileInputStream(dosyaYolu);
        Workbook workbook = WorkbookFactory.create(fis);
        //4) 5.hucreye yeni bir cell olusturalim
        //5) Olusturdugumuz hucreye “Nufus” yazdiralim
        workbook
                .getSheet("Sayfa1")
                .getRow(0)
                .createCell(4)
                .setCellValue("Nufus");
        //6) 2.satir nufus kolonuna 1500000 yazdiralim
        workbook
                .getSheet("Sayfa1")
                .getRow(1)
                .createCell(4)
                .setCellValue("1.500.000");
        //7) Dosyayi kaydedelim
        FileOutputStream fos = new FileOutputStream(dosyaYolu);
        workbook.write(fos);
        //8)Dosyayi kapatalim
        workbook.close();
        fis.close();
        fos.close();
    }

    @Test
    public void tumSayfaScreenShot() throws IOException {

        //amazon sayfasina gidip tum sayfanin fotografini cekelim

        driver.get("https://www.amazon.com");
        TakesScreenshot ts = (TakesScreenshot) driver;

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYMMddHHmmss");
        String tarih = date.format(dtf);

        File tumSayfaResim = new File("target/ekranGoruntuleri/tumSayfa" + tarih + ".jpeg");
        File geciciDosya = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(geciciDosya, tumSayfaResim);
    }

    @Test
    public void JSExecutorTest() throws InterruptedException {
        // amazon'a gidelim
        driver.get("https://www.amazon.com");
        // asagidaki sign in butonu gorununceye kadar js ile scrool yapalim
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement signInButonu = driver.findElement(By.xpath("(//span[text()='Sign in'])[2]"));
        Thread.sleep(3000);
        jse.executeScript("arguments[0].scrollIntoView(true);", signInButonu);
        // sign in butonuna js ile click yapalim
        jse.executeScript("arguments[0].click();", signInButonu);
        Thread.sleep(3000);
    }
}
