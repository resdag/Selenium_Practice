package day06_checkBox_RadioButton;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C01_CheckBox_RadioButton {
    /*
    CheckBox'larda birden cok eleman secilebilir.
    .isSelected() -- istenilen element'e secili mi diye bakar
        .click() -- ile tik atar/varsa kaldiririz

    RadioButton sadece bir secenegi secmemize izin verir.
     */
    WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void test01() throws InterruptedException {
        //a. https://the-internet.herokuapp.com/checkboxes sayfasina gidin
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        //b. Checkbox1 ve checkbox2 elementlerini locate edin
        WebElement checkBox1 = driver.findElement(By.xpath("(//input[@type='checkbox'])[1]"));
        WebElement checkBox2 = driver.findElement(By.xpath("(//input[@type='checkbox'])[2]"));
        //c. Checkbox1 seçili değilse onay kutusunu tıklayın
        Thread.sleep(2000);
        if (!checkBox1.isSelected()) {
            checkBox1.click();
        }
        //d. Checkbox2 seçili değilse onay kutusunu tıklayın
        Thread.sleep(2000);
        if (!checkBox2.isSelected()) {
            checkBox2.click();
        }
        Thread.sleep(2000);
    }

    @Test
    public void test02() throws InterruptedException {
        //https://www.facebook.com adresine gidin
        driver.get("https://www.facebook.com");
        //Cookies’i kabul edin
        driver.findElement(By.xpath("//*[text()='Allow essential and optional cookies']")).click();
        //“Create an Account” button’una basin
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        //“radio buttons” elementlerini locate edin
        WebElement femaleButton = driver.findElement(By.xpath("//input[@value='1']"));
        WebElement maleButton = driver.findElement(By.xpath("//input[@value='2']"));
        WebElement customButton = driver.findElement(By.xpath("//input[@value='-1']"));
        //Secili degilse cinsiyet butonundan size uygun olani secin
        Thread.sleep(3000);
        if (!maleButton.isSelected()) {
            maleButton.click();
        }
        Thread.sleep(3000);
    }
}
