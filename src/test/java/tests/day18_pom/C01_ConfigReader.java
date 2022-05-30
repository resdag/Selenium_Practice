package tests.day18_pom;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FacebookPage;
import utilities.ConfigReader;
import utilities.Driver;

public class C01_ConfigReader {

    @Test
    public void test01() {

        Driver.getDriver().get(ConfigReader.getProperty("amazonUrl"));
        Driver.getDriver().get(ConfigReader.getProperty("facebookUrl"));
        Driver.closeDriver();
    }

    @Test
    public void test02() {
        FacebookPage facebookPage = new FacebookPage();
        // facebook anasayfaya gidin
        Driver.getDriver().get(ConfigReader.getProperty("facebookUrl"));
        // cikarsa cookies kabul edin
        facebookPage.cookieButonu.click();
        // kullanici mail kutusuna yanlis kullanici ismi yazdirin
        facebookPage.mailKutusu.sendKeys(ConfigReader.getProperty("fbWrongUsername"));
        // kullanici sifre kutusuna yanlis password yazdirin
        facebookPage.sifreKutusu.sendKeys(ConfigReader.getProperty("fbWrongPassword"));
        // login butonuna basin
        facebookPage.loginTusu.click();
        // giris yapilamadigini test edin
        Assert.assertTrue(facebookPage.girilemediYaziElementi.isDisplayed());
        // sayfayi kapatin
        Driver.closeDriver();
    }
}