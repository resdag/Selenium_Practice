package day00_exercise;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.TestBaseJUnit;

import java.util.List;

public class D13_C01 extends TestBaseJUnit {

    @Test
    public void test01() {
        // 1- https://www.concorthotel.com//admin/HotelRoomAdminadresine gidin
        driver.get("https://www.concorthotel.com//admin/HotelRoomAdmin");
        Actions actions = new Actions(driver);
        WebElement userNameEkementi = driver.findElement(By.xpath("//input[@name='UserName']"));
        actions.click(userNameEkementi)
                .sendKeys("manager").sendKeys(Keys.TAB)
                .sendKeys("Manager1!").sendKeys(Keys.ENTER)
                .perform();
        // 2- Username : manager ve Password : Manager1! ile giris yapin
        // 3- Tüm table body'sinin boyutunu(sutun sayisi) bulun. /tbody
        List<WebElement> headSatirElementleri = driver.findElements(By.xpath("//thead/tr[1]/th"));
        System.out.println("Head Sutun Sayisi : " + headSatirElementleri.size());

        // 4- Table'daki tum body'i ve başlıkları(headers) konsolda yazdırın.
        WebElement bodyElementi = driver.findElement(By.xpath("//tbody"));
        System.out.println(bodyElementi.getText());

        WebElement headElementi = driver.findElement(By.xpath("//thead/tr[1]"));
        System.out.println(headElementi.getText());
        // 5- table body'sinde bulunan toplam satir(row) sayısını bulun.
        List<WebElement> bodySatirElementleri = driver.findElements(By.xpath("//tbody/tr"));
        System.out.println("Body Satir Sayisi : " + bodySatirElementleri.size());
        // 6- Table body'sinde bulunan satirlari(rows) konsolda yazdırın.
        for (WebElement each : bodySatirElementleri) {
            System.out.println(each.getText());
        }
        // 7- 4.satirdaki(row) elementleri konsolda yazdırın.
        System.out.println("4.satir : " + driver.findElement(By.xpath("//tbody/tr[4]")).getText());
        // 8- table body'sinde bulunan toplam hücre(cell) sayısını bulun.
        List<WebElement> bodyCellElementleri = driver.findElements(By.xpath("//tbody//td"));
        System.out.println("Toplam hucre sayisi: " + bodyCellElementleri.size());
        // 9- Table body'sinde bulunan hücreleri(cells) konsolda yazdırın.
        for (WebElement each : bodyCellElementleri) {
            System.out.print(each.getText() + ", ");
        }
        System.out.println("");
        // 10- table body'sinde bulunan toplam sutun(column) sayısını bulun.
        List<WebElement> bodyIlkSatirElementleri = driver.findElements(By.xpath("//tbody/tr[1]/td"));
        System.out.println("Body Sutun Sayisi : " + bodyIlkSatirElementleri.size());

        // 11- Table body'sinde bulunan sutunlari(column) konsolda yazdırın.
        for (WebElement each : bodyIlkSatirElementleri) {
            System.out.println(each.getText() + ", ");
        }
        // 12- 5.column daki elementleri konsolda yazdırın.
        System.out.println("5.sutun : " + driver.findElement(By.xpath("//tbody//td[5]")).getText());
    }
}
