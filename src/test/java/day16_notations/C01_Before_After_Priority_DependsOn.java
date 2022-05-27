package day16_notations;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C01_Before_After_Priority_DependsOn extends TestBase {
/*
    - @BeforeMethod ve @AfterMethod notasyonlari
        JUnit'teki @Before ve @After gibidir.
        her test method'undan once ve sonra calisirlar

    -   JUnit'te @BeforeClass ve @AfterClass notasyonunlu methodlar statikti,
        TestNG bu zorunlu deggil. TestNG daha fazla before ve after notasyonlari sunar.
        Digerleri tanimlayacagimiz grup, test ve ya suit'ten once ve sonra calisirlar.
        ileride xml dosyalariyla birlikte bunu gorecegiz.

    -   TestNG test methodlarini isim sirasina gore calistirir.
        Farkli siralama icin test methodlarina PRIORITY tanimlanabilir.
        Priority kucukten buyuge gore calisir.(negatif olabilir)
        Prioroty atanmazsa, default olarak priority=0 kabul edilir
        @Test(priority = -2)

    -   DependsOnMethods test method'larinin calisma siralamasina karismaz.
        Sadece bagli olan test, baglandigi testin sonucuna bakar.
        Baglandigi test PASSED olmazsa, baglanan test hic calismaz(ignore).
        @Test(dependsOnMethods = "amazonTesti")
     */

    @BeforeClass
    public void beforeClassMethod() {
        System.out.println("Before Class");
    }

    @AfterClass
    public void afterClassMethod() {
        System.out.println("After Class");
    }

    @Test(priority = 5)
    public void amazonTesti() {
        driver.get("https://www.amazon.com");
    }

    @Test(priority = -2)
    public void techproedTesti() {
        driver.get("https://www.techproeducation.com");
    }

    @Test(dependsOnMethods = "amazonTesti")
    public void test02() {
        driver.get("https://www.bestbuy.com");
    }
}
