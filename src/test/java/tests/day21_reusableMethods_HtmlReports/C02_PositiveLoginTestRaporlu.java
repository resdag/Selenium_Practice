package tests.day21_reusableMethods_HtmlReports;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BrcPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.TestBaseRapor;

public class C02_PositiveLoginTestRaporlu extends TestBaseRapor {
    /*
    1- TestNG'de HTML rapor hazirlamak icin aventstack(ExtentReports) dependency eklenmelidir
    2- Extent Reports raporlama aracidir vve kullanmak icin 3 obje olusturulur
            - ExtentReports extendReports: Raporlamayi baslatmak icin kullanilir.
                    Bitince flush() methodu kullanilir.
            - ExtendHtmlReporter extendHtmlReporter: Raporlari yapilandirir.
                    Raporun olusturulacagi yolu ayarlamada kullanilir.
            - ExtendTest extendTest: Bilgi, test adimlari, günlük(logs) eklemek icin kullaniriz.
                    extendTest.info("url acildi"); -- sadecce adim eklemek icindir
    3- TestBaseRapor dosyasinda istedigimz degerleri degistir
    4- Istenen class'i TestBaseRapor'a child yap
    5- Test'in ilk satirinda extentTest'e deger atanir.
            extentTest=extentReports.createTest(testName, description);
    6- Bilgi verilmesini istediginiz her satirda
            extentTest.info("istenen url'e gidildi"); //String olarak details yazilir
    7- Son test'ten sonra pass olup olmama durumu kontrol et.
            extentTest.pass("basarili giris yapildi");
    8- Target altindaki html rapor istenen browser'da acilarak incelenebilir.
     */
    BrcPage brcPage;

    @Test
    public void positiveLoginTest() {
        brcPage=new BrcPage();
        extentTest=extentReports.createTest("Pozitif Login","Gecerli username ve sifre ile giris yapabilmeli");
        // Bir test method olustur positiveLoginTest()
        //		 https://www.bluerentalcars.com/ adresine git
        Driver.getDriver().get(ConfigReader.getProperty("brcUrl"));
        extentTest.info("Brc anasayfaya gidildi");
        //		login butonuna bas
        brcPage.ilkLoginButonu.click();
        extentTest.info("login butonuna tiklandi");
        // test data user email: customer@bluerentalcars.com ,
        brcPage.emailTextBox.sendKeys(ConfigReader.getProperty("brcValidEmail"));
        extentTest.info("Gecerli email yazildi");
        // test data password : 12345
        brcPage.passwordTextBox.sendKeys(ConfigReader.getProperty("brcValidPassword"));
        extentTest.info("Gecerli password yazildi");
        // login butonuna tiklayin
        brcPage.ikinciLoginButonu.click();
        extentTest.info("login butonuna basildi");
        // Degerleri girildiginde sayfaya basarili sekilde girilebildigini test et

        String actualUsername= brcPage.kullaniciProfilIsmi.getText();
        String expectedUsername= ConfigReader.getProperty("brcValidUsername");
        Assert.assertEquals(actualUsername,expectedUsername);
        extentTest.pass("kullanici basarili sekilde giris yapti");
        Driver.closeDriver();
    }
}