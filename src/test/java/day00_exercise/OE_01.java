package day00_exercise;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.TestBase;

public class OE_01 extends TestBase {
    @Test
    public void test01() {
        //1. Launch browser
        //2. Navigate to url 'https://automationexercise.com/'
        driver.get("http://automationexercise.com/");
        //3. Verify that home page is visible successfully
        String expectedUrl = "https://automationexercise.com/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
        //4. Click on 'Signup / Login' button
        driver.findElement(By.xpath("//a[text()=' Signup / Login']")).click();
        //5. Verify 'New User Signup!' is visible
        WebElement newUser = driver.findElement(By.xpath("//h2[text()='New User Signup!']"));
        Assert.assertTrue(newUser.isDisplayed());
        //6. Enter name and email address
        Faker faker = new Faker();
        Actions actions = new Actions(driver);
        WebElement nameBoxElement = driver.findElement(By.xpath("//input[@name='name']"));
        actions.click(nameBoxElement).
                sendKeys(faker.name().firstName()).
                sendKeys(Keys.TAB).
                sendKeys(faker.internet().emailAddress()).perform();

        //7. Click 'Signup' button
        driver.findElement(By.xpath("//button[text()='Signup']")).click();
        //8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
        String infoText= driver.findElement(By.xpath("//*[text()='Enter Account Information']")).getText();
        Assert.assertTrue(infoText.contains("ENTER ACCOUNT INFORMATION"));
        //9. Fill details: Title, Name, Email, Password, Date of birth
        WebElement gerderElement= driver.findElement(By.xpath("//input[@value='Mr']"));
        actions.click(gerderElement).
                sendKeys(faker.name().fullName()).
                sendKeys(Keys.TAB).
                sendKeys(faker.internet().emailAddress()).
                sendKeys(Keys.TAB).
                sendKeys(faker.internet().password()).perform();
        //10. Select checkbox 'Sign up for our newsletter!'
        //11. Select checkbox 'Receive special offers from our partners!'
        //12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
        //13. Click 'Create Account button'
        //14. Verify that 'ACCOUNT CREATED!' is visible
        //15. Click 'Continue' button
        //16. Verify that 'Logged in as username' is visible
        //17. Click 'Delete Account' button
        //18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button

    }
}
