package tests.day17_pom;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AmazonPage;
import utilities.Driver;

public class C01_PageClassKullanimi {
    /*
    --------- Page Object Model(TestNG Framework Olusturma) ---------
    1- java altina pages, tests, utilities package'i olustur.
    2- utilities altina Driver class'i olustur.
    Driver icinde getDriver() ve closeDriver() methodlarini olustur.
    3- tests package altina calisacagimiz test class'ini olustur.
    4- utilities/Driver'da if/else ile her defasinda yeni driver olusumunu onle.
            if (driver == null) {driver ac} --- if (driver != null) {driver kapat}
    5- pages package altina AmazonPage clas'i olustur.
    6- AmazonPage icinde parametresiz constructor olustur. Constructor icinde
    PageFactory class'indan initElements() kullaniip, Driver class'inin
    getDriver()getirecegi driver this ile bu class'takine esitlenir.
    AmazonPage'ten amazonPage objesi olustururken bu AmazonPage constructor calisir.
            public AmazonPage() {
            PageFactory.initElements(Driver.getDriver(), this)
            };
    7- AmazonPage'de ilgili yerleri locate et.
            @FindBy(id = "twotabsearchtextbox") //  @FindBy(xpath = "//div[@class='a little']")
            public WebElement aramaKutusu;
    8- Calisacagimiz her class ya da method'un basinda page class'a ulasmak icin
    AmazonPage objesi olustur.
            AmazonPage amazonPage = new AmazonPage();
    9- Bu amazonPage uzerinden istenen locate'e istenen islemi yap
            amazonPage.aramaKutusu.sendKeys("nutella" + Keys.ENTER);
    10- class sonunda Driver'i kapat
            Driver.closeDriver();
            -----day18----
    11- Project seviyesinde configuration.properties isminde yeni bir file olustur.
    Kullanacagimiz degerleri buraya key/value seklinde tanimlayabiliriz
            browser=chrome
            amazonUrl=https://www.amazon.com
     12- utilities altinda ConfigReader class olustur.
     13- ConfigReader class'i basinda Properties objesi ve getProperty() methodu olustur.
            public static Properties properties;

            public static String getProperty(String key) {
                return properties.getProperty(key);
                // Key ile configuration.properties class'indan getProperty( ) method'unu
                kullanarak bu key'e ait value'u bize getirdi
            }
      14-   ConfigReader icinde static block olusturulur ve herseyden once calisarak properties'e deger atar
            Dosyayai bulmada sorun yasamamasi icin try-catch ile cevreleriz.
            static {
                String dosyaYolu = "configuration.properties";
                try{
                    FileInputStream fis = new FileInputStream(dosyaYolu); //configuration.properties dosyasini okudu
                    properties = new Properties();
                    properties.load(fis); // fis'in okudugu bilgileri properties'e yukledi
                }catch ...
            }
       15-  test sayfasindan Driver.getDriver().get(ConfigReader.getProperty("facebookUrl")); ile istenen
     adrese ulasilabillir
       16-  Driver class'inda switch-case ile istenen browser'lari ekle.
     */

    @Test
    public void test01() {
        Driver.getDriver().get("https://www.amazon.com");

        Driver.closeDriver();
        Driver.getDriver().get("https://www.bestbuy.com");
        Driver.getDriver().get("https://www.facebook.com");
        Driver.closeDriver();
    }

    @Test
    public void test02() {
        AmazonPage amazonPage = new AmazonPage();
        // amazona gidelim
        Driver.getDriver().get("https://www.amazon.com");
        // nutella aratalim
        amazonPage.aramaKutusu.sendKeys("nutella" + Keys.ENTER);
        // sonuc yazisinin nutella icerdigini test edelim
        String actualSonuc = amazonPage.aramaSonucElementi.getText();
        String arananKelime = "nutella";
        Assert.assertTrue(actualSonuc.contains(arananKelime));
        Driver.closeDriver();
    }
}
