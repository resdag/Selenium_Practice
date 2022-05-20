package day02_driverMethodlari;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C01_DriverMethodlari {
    /*  get(). -- getTitle,getCurrentUrl,getPageSource,getWindowHandle
        navigate(), -- to,back,forward,refresh
        manage().window(). --  maximize,fullscreen,minimize,getSize,getPosition,setSize,setPosition,
                .timeouts(). -- implicitlyWait,
        close() sadece son calisan browser'i kapatir
        quit() acik olan tum browser'lari kapatir
     */

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize(); //tam ekran yapar

        driver.get("https://www.amazon.com");

        System.out.println("Actual title = " + driver.getTitle()); // sayfa basligini getirir
        System.out.println("Actual Url  = " + driver.getCurrentUrl()); // sayfa url'ini getirir
        // System.out.println("Page Source = " + driver.getPageSource()); // kaynak kodlarini getirir (sayfada ctrl+u ile gorulebilir)
        System.out.println(" = " + driver.getWindowHandle()); // her calistirmada benzersiz acilan pencereye ait hash kodu getirir
        // windowlar arasi geciste bu kod gerekir

        driver.navigate().to("https://www.facebook.com"); // adrese goturur
        driver.navigate().back(); // onceki sayfaya gide
        Thread.sleep(1000); // gormek icin 1 sn beklettik
        driver.navigate().forward(); // sonraki sayfaya gider
        Thread.sleep(1000);
        driver.navigate().refresh(); // sayfayi yeniler

        System.out.println("Pencere olculeri = " + driver.manage().window().getSize()); // (1552, 840) //cozunurlugu verir
        System.out.println("Pencere konumu = " + driver.manage().window().getPosition()); // (-8, -8)
        driver.manage().window().setPosition(new Point(15, 15)); // konumu degistirir
        driver.manage().window().setSize(new Dimension(900, 600)); // olculeri degistirir
        System.out.println("Yeni Pencere olculeri = " + driver.manage().window().getSize()); // (902, 602)
        System.out.println("Yeni Pencere konumu = " + driver.manage().window().getPosition()); // (13, 13)

        // driver.manage().window().fullscreen(); //ekranin kenarindaki boslulari da doldurur
        // driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); // sayfa acilmasi icin maximum bekleme suresini belirler


        driver.close(); //browser'i kapatir
    }
}
