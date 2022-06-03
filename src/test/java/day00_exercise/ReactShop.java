package day00_exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ReactShopPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.ArrayList;
import java.util.List;

public class ReactShop {

    private static ReactShopPage deneme02Page;

    @Test
    public static void test01() throws InterruptedException {

        ReactShopPage reactShopPage = new ReactShopPage();

        // 1."https://react-shopping-cart-67954.firebaseapp.com/" adresine gidin
        Driver.getDriver().get(ConfigReader.getProperty("reactShopUrl"));
        // 2.Web sitesindeki tüm öğeleri listeleyin ve yazdirin
        // (sitede 16 urun var, 1.urun : 2.urun :.....seklinde yazdirin )

        for (int i = 0; i < reactShopPage.urunlerListesi.size(); i++) {
            System.out.println((i+1)+". urun : " + reactShopPage.urunlerListesi.get(i).getText());
        }
        // 3.Stringlerden olusan bir ArrayList oluşturun ve Ürün Adlarını ArrayList'e yerleştirin
        List<String> urunIsimleri = new ArrayList<>();
        for (WebElement each : reactShopPage.urunlerListesi){
            urunIsimleri.add(each.getText());
        }
        // 4.Siteden Rastgele 5 öğe seçin, sepete ekleyin ve sectiginiz öğelerin adlarını yazdırın
        // (Her ürün 1 defadan fazla eklenemez!)
        for (int i = 0; i < 5; i++) {
            reactShopPage.addToCartButonlari.get(i).click();
            Thread.sleep(2000);
            reactShopPage.carpiIsareti.click();
            Thread.sleep(2000);
            System.out.println(reactShopPage.urunlerListesi.get(i).getText());
        }

        //Driver.getDriver().findElement(By.xpath("//div[@class='sc-1h98xa9-2 fGgnoG']")).click();

        // 5.Her bir öğenin fiyatını toplayın ve sonucunuzu web sitesiyle karşılaştırın
        int toplam=0;
        for (WebElement each: reactShopPage.sepetUrunleriFiyatListesi) {
            //toplam+=Integer.parseInt(each.getText());
            System.out.println(each.getText());
        }
        //System.out.println(toplam);
        //System.out.println(reactShopPage.toplamTutarElementi.getText());

        // 6.Sonuçlar eşleşiyorsa  konsola test pass yazirin

        // 7.Checkout'a tıklayın
        reactShopPage.checkOutButonu.click();

        //8- Driver' kapatin
        Driver.closeDriver();


    }
}
