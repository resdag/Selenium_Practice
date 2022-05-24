package day13_cookies_webTables;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.TestBase;

import java.util.List;
import java.util.Set;

public class C01_Cookies_WebTables extends TestBase {
    /*
    .manage().getCookies() --> cookiesleri set'e atar
                .addCookie(cookie); --> Cookie cookie = new Cookie("yeni", "seker"); ile olusturulani sete ekler
                .deleteCookieNamed("skin") --> skin coockies'i siler
                .deleteAllCookies() --> tum cookies'i siler

    locate: //tr[5]//td[4] --> 5.satirin 4. sutunundaki elementine gideriz.
            //tbody//tr -->body'deki tum satirlari bulur
            By.xpath("//tbody//tr[" + satir + "]//td[" + sutun + "]") --> bu yazi sekliyle kodu dinamik yapabiliriz
     */

    @Test
    public void webTable() {
        //1- girisYap( ) metodun oluşturup https://www.hotelmycamp.com admin/HotelRoomAdmin adresine gidin ve
        //2- Username : manager, Password : Manager1! ile oturum açın.
        girisYap();
        //3- Tüm table body’sinin boyutunu(sutun sayisi) bulun. /tbody
        List<WebElement> sutunBasliklariListesi = driver.findElements(By.xpath("//thead//tr[1]//th"));
        System.out.println("Sutun Sayisi : " + sutunBasliklariListesi.size());
        //4- Table’daki tum body’i ve basliklari(headers) konsolda yazdırın.
        WebElement tumBody = driver.findElement(By.xpath("//tbody"));
        System.out.println(tumBody.getText());
        //5- table body’sinde bulunan toplam satir(row) sayısını bulun.
        List<WebElement> satirlarListesi = driver.findElements(By.xpath("//tbody//tr"));
        System.out.println("Satir sayisi : " + satirlarListesi.size());
        //6- Table body’sinde bulunan satirlari(rows) konsolda yazdırın.
        for (WebElement each : satirlarListesi
        ) {
            System.out.println(each.getText());
        }
        //7- 4.satirdaki(row) elementleri konsolda yazdırın.
        List<WebElement> cellList = driver.findElements(By.xpath("//tbody//tr[4]//td"));

        for (WebElement each : cellList
        ) {
            System.out.println(each.getText());
        }

        //8- Email basligindaki tum elementleri(sutun) konsolda yazdırın.
        List<WebElement> basliklarListesi = driver.findElements(By.xpath("//thead//tr[1]//th"));

        int emailSutunNo = 0;
        for (int i = 0; i < basliklarListesi.size(); i++) {
            if (basliklarListesi.get(i).getText().equals("Email")) {
                emailSutunNo = i;
            }
        }

        List<WebElement> emailSutunListesi =
                driver.findElements(By.xpath("//tbody//td[" + (emailSutunNo + 1) + "]"));
        for (WebElement each : emailSutunListesi) {
            System.out.println(each.getText());
        }

    }

    @Test
    public void dinamikYazici() {
        // input olarak verilen satir ve sutun sayisi'na sahip cell'deki text'i yazdirin
        int satir = 3;
        int sutun = 3;
        girisYap();
        WebElement arananCell =
                driver.findElement(By.xpath("//tbody//tr[" + satir + "]//td[" + sutun + "]"));
        System.out.println(arananCell.getText());
    }

    public void girisYap() {
        driver.get("https://www.hotelmycamp.com");
        driver.findElement(By.linkText("Log in")).click();
        Actions actions = new Actions(driver);
        WebElement username = driver.findElement(By.id("UserName"));
        actions.click(username).
                sendKeys("manager").
                sendKeys(Keys.TAB).
                sendKeys("Manager1!").
                sendKeys(Keys.ENTER).
                perform();
    }
}