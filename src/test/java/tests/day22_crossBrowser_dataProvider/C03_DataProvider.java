package tests.day22_crossBrowser_dataProvider;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AmazonPage;
import utilities.ConfigReader;
import utilities.Driver;

public class C03_DataProvider {
    /*
    -- Ayni test'i farkli giris datalariyla calistirmaktir
    1- @Test notasyonu yanina dataProvider ifadesi ekle
            @Test(dataProvider = "AranacakKelimeler")
    2- Method'a paremetre yolla
            public void dataProviderTesti(String arananKelime) {...}
    3- AranacakKelimeler'e yaklasinca @DataProvider olusturur ve iki katli bir array olusturmamizi ister.
            @DataProvider
            public static Object[][] AranacakKelimeler() {
                Object[][] arananKelimeArrayi = {{"Nutella"}, {"Java"}, {"Elma"}};
                return arananKelimeArrayi;
            }
    4- Array'in her elemani icin browser'i calistirir.

     */

    @DataProvider
    public static Object[][] AranacakKelimeler() {
        Object[][] arananKelimeArrayi = {{"Nutella"}, {"Java"}, {"cigdem"}, {"Netherlands"}};
        return arananKelimeArrayi;
    }

    @Test(dataProvider = "AranacakKelimeler")
    // arayacagimiz kelimeleri bir liste gibi tutup
    // bana yollayacak bir veri saglayicisi olusturacagiz
    public void dataProviderTesti(String arananKelime) {
        AmazonPage amazonPage = new AmazonPage();
        //amazon anasayfaya gidelim
        Driver.getDriver().get(ConfigReader.getProperty("amazonUrl"));
        //Nutella, Java, cigdem ve Netherlands icin arama yapalim
        amazonPage.aramaKutusu.sendKeys(arananKelime + Keys.ENTER);
        //sonuclarin aradigimiz kelime icerdigini test edelim
        String expectedKelime = arananKelime;
        String actualSonucYazisi = amazonPage.aramaSonucElementi.getText();
        Assert.assertTrue(actualSonucYazisi.contains(expectedKelime));
        // sayfayi kapatalim
        Driver.closeDriver();
    }
}
