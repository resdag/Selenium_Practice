package day04_xpath_CssSelector;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.time.Duration;

public class C01_Xpath_CssSelector {
    /*
    .findElement(By.xpath("locate"))
        Absolute Xpath() = By.xpath(//div/table/tbody/tr/td[3]/a[2]/span)
        Relative Xpath() = By.xpath(//span[@class='navFooterDescText'])
                            By.xpath((//tagName[@attributeIsmi='attributeValue'])[siraNo])
                            By.xpath(//*[text()='metindeki ifade']) -- yazi olan elementler icin

        Css selectorh() = By.cssSelector(input[name='session[password]'])
                          By.cssSelector(tagName[attributeIsmi='attributeValue'])
                          (tagName#idValue ve #idValue sadece id value ile (cssSelector("input#session_password"))
                          tagName.classValue ve .classValue ise sadece class value ile calisir.)
                          (cssSelector(".form-control"))

        driver.findElement(RelativeLocator.with(By.tagName("img")).below(bostonWE).toLeftOf(sailorWE));
                                      --near, above, below, toLeftOf, toRighrOf--
     */
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        System.out.println("Test 1 Xpath--------------------------------------------");
        //1- https://the-internet.herokuapp.com/add_remove_elements/ adresine gidin
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        //2- Add Element butonuna basin
        driver.findElement(By.xpath("//*[text()='Add Element']")).click();
        //3- Delete butonu’nun gorunur oldugunu test edin
        WebElement deleteButonu = driver.findElement(By.xpath("//button[@class='added-manually']"));
        if (deleteButonu.isDisplayed()) {
            System.out.println("delete butonu testi PASSED");
        } else {
            System.out.println("delete butonu testi FAILED");
        }
        //4- Delete tusuna basin
        Thread.sleep(3000);
        deleteButonu.click();

        //5- “Add/Remove Elements” yazisinin gorunur oldugunu test edin
        WebElement addRemoveYaziElementi = driver.findElement(By.xpath("//h3"));
        if (addRemoveYaziElementi.isDisplayed()) {
            System.out.println("addRemoveYazi testi PASSED");
        } else {
            System.out.println("addRemoveYazi testi FAILED");
        }
        Thread.sleep(2000);

        System.out.println("Test 2 CSS Selector-------------------------------------");
        //  a. http://a.testaddressbook.com/sign_in sayfasina gidin
        driver.get("http://a.testaddressbook.com/sign_in");
        //  b. Locate email textbox
        WebElement mailTextBox = driver.findElement(By.cssSelector("#session_email"));
        //  c. Locate password textbox ve
        WebElement passwordTextBox = driver.findElement(By.cssSelector("#session_password"));
        //  d. Locate signin button
        WebElement signInButonu = driver.findElement(By.cssSelector("input[value='Sign in']"));
        //  e. Asagidaki kullanıcı adını ve şifreyi girin ve sign in düğmesini tıklayın
        //      i. Username : testtechproed@gmail.com
        //      ii. Password : Test1234!
        mailTextBox.sendKeys("testtechproed@gmail.com");
        passwordTextBox.sendKeys("Test1234!");
        signInButonu.click();
        System.out.println("Test 3 Relative Locator-----------------------------------");

        driver.get("https://www.diemol.com/selenium-4-demo/relative-locators-demo.html");
        WebElement bostonWE = driver.findElement(By.id("pid6_thumb"));
        WebElement sailorWE = driver.findElement(By.id("pid11_thumb"));
        WebElement mountie = driver.findElement(RelativeLocator.with(By.tagName("img"))
                .below(bostonWE).toLeftOf(sailorWE));
        System.out.println(mountie.getAttribute("id")); // pid10_thumb
        System.out.println("----------------------------------------------------------");

        Thread.sleep(2000);
        driver.close();
    }
}
