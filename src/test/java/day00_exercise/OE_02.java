package day00_exercise;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.TestBase;

import java.util.ArrayList;
import java.util.List;

public class OE_02 extends TestBase {

    @Test
    public void test01() throws InterruptedException {
        List<String> userInfoList = signUp();
        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com/'
        //driver.get("https://automationexercise.com/");
        //3. Verify that home page is visible successfully
        // String expectedUrl = "https://automationexercise.com/";
        // String actualUrl = driver.getCurrentUrl();
        // Assert.assertEquals(expectedUrl, actualUrl);
        //4. Click on 'Signup / Login' button
        driver.findElement(By.xpath("//*[text()=' Signup / Login']")).click();
        //5. Verify 'Login to your account' is visible
        WebElement loginElement = driver.findElement(By.xpath("//*[text()='Login to your account']"));
        Assert.assertTrue(loginElement.isDisplayed());
        //6. Enter correct email address and password
        WebElement enterBox = driver.findElement(By.xpath("//input[@data-qa='login-email']"));
        Actions actions = new Actions(driver);

        Thread.sleep(2000);
        actions.click(enterBox)
                .sendKeys(userInfoList.get(1))
                .sendKeys(Keys.TAB)
                .sendKeys(userInfoList.get(2))
                .sendKeys(Keys.ENTER).perform();
        Thread.sleep(2000);
        //7. Click 'login' button
        //8. Verify that 'Logged in as username' is visible
        //9. Click 'Delete Account' button
        //10. Verify that 'ACCOUNT DELETED!' is visible

    }

    private List signUp() throws InterruptedException {
        driver.get("http://automationexercise.com/");
        driver.findElement(By.xpath("//a[text()=' Signup / Login']")).click();
        Faker faker = new Faker();
        Actions actions = new Actions(driver);
        WebElement nameBoxElement = driver.findElement(By.xpath("//input[@name='name']"));

        String fakerName = faker.name().fullName();
        String fakerEmail = faker.internet().emailAddress();
        String fakerPassword = faker.internet().password();

        actions.click(nameBoxElement).
                sendKeys(fakerName).
                sendKeys(Keys.TAB).
                sendKeys(fakerEmail).perform();
        driver.findElement(By.xpath("//button[text()='Signup']")).click();
        WebElement genderElement = driver.findElement(By.xpath("//input[@value='Mr']"));
        actions.click(genderElement)
                .sendKeys(Keys.TAB).sendKeys(fakerName)
                .sendKeys(Keys.TAB).sendKeys(fakerEmail)
                .sendKeys(Keys.TAB).sendKeys(fakerPassword)
                .sendKeys(Keys.TAB).sendKeys("20")
                .sendKeys(Keys.TAB).sendKeys("July")
                .sendKeys(Keys.TAB).sendKeys("1991")
                .sendKeys(Keys.TAB).click()
                .sendKeys(Keys.TAB).click()
                .sendKeys(Keys.TAB).sendKeys(faker.name().firstName())
                .sendKeys(Keys.TAB).sendKeys(faker.name().lastName())
                .sendKeys(Keys.TAB).sendKeys(faker.company().name())
                .sendKeys(Keys.TAB).sendKeys(faker.address().fullAddress())
                .sendKeys(Keys.TAB).sendKeys(faker.address().fullAddress())
                .sendKeys(Keys.TAB).sendKeys(faker.country().name())
                .sendKeys(Keys.TAB).sendKeys(faker.address().state())
                .sendKeys(Keys.TAB).sendKeys(faker.address().city())
                .sendKeys(Keys.TAB).sendKeys(faker.address().zipCode())
                .sendKeys(Keys.TAB).sendKeys(faker.phoneNumber().cellPhone())
                .sendKeys(Keys.TAB).sendKeys(Keys.ENTER)
                .perform();
        //15. Click 'Continue' button
        driver.findElement(By.xpath("//a[text()='Continue']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[text()=' Logout']")).click();
        //driver.findElement(By.xpath("//a[text()=' Home']")).click();
        List<String> userDataList = new ArrayList<>();
        userDataList.add(fakerName);
        userDataList.add(fakerEmail);
        userDataList.add(fakerPassword);

        return userDataList;
    }
}
