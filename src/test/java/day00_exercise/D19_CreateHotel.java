package day00_exercise;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ConcortHotelPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;

public class D19_CreateHotel {

    ConcortHotelPage concortHotelPage = new ConcortHotelPage();

    @Test
    public void createHotel() throws InterruptedException {

        //1. Tests packagenin altına class olusturun: D17_CreateHotel
        //2. Bir metod olusturun: createHotel
        //3. https://www.concorthotel.com/ adresine git.
        Driver.getDriver().get(ConfigReader.getProperty("concortHotelUrl"));
        concortHotelPage.firstLoginButonu.click();
        // 4. Username textbox ve password metin kutularını locate edin ve asagidaki verileri girin.
        //a. Username : manager
        concortHotelPage.userNameBox.sendKeys(ConfigReader.getProperty("concortHotelAdminUsername"));
        //b. Password : Manager1!
        concortHotelPage.passwordBox.sendKeys(ConfigReader.getProperty("concortHotelAdminPassword"));
        //5. Login butonuna tıklayın.
        concortHotelPage.secondLoginButonu.click();
        //6. Hotel Management/Hotel List menusunden ADD HOTEL butonuna tiklayin
        concortHotelPage.hotelManagementMenu.click();
        concortHotelPage.hotelListMenu.click();
        concortHotelPage.addHotelButton.click();
        //7. Açılan sayfadaki tüm metin kutularına istediğiniz verileri girin.
        Faker faker = new Faker();
        String fakeHotelName = faker.company().name();
        Actions actions = new Actions(Driver.getDriver());
        actions.click(concortHotelPage.addHotelCodeElementi)
                .sendKeys(faker.address().zipCode()).sendKeys(Keys.TAB)
                .sendKeys(fakeHotelName).sendKeys(Keys.TAB)
                .sendKeys(faker.address().fullAddress()).sendKeys(Keys.TAB)
                .sendKeys(faker.phoneNumber().phoneNumber()).sendKeys(Keys.TAB)
                .sendKeys(faker.internet().emailAddress()).perform();
        Select select = new Select(concortHotelPage.idGroupDropDownElement);
        select.selectByVisibleText("Hotel Type2");
        //8. Save butonuna tıklayın.
        concortHotelPage.addHotelSaveButton.click();
        //9. "Hotel was inserted successfully" textinin göründüğünü test edin.
        String expectedAddedText = ConfigReader.getProperty("successfullyAddedText");
        //beklemezse hata veriyordu
        //Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
        String actualAddedText = wait.until(ExpectedConditions.visibilityOf(concortHotelPage.successfullyAddedTextBox)).getText();
        Assert.assertEquals(actualAddedText, expectedAddedText);
        //10. OK butonuna tıklayın.
        concortHotelPage.successfullyAddedOkButton.click();
        //11. Hotel list linkine tıklayın.
        concortHotelPage.hotelListMenu.click();
        //12. Istenen hotelin eklendigini dogrulayin..
        select = new Select(concortHotelPage.hotelListDropDownElement);
        select.selectByVisibleText("All");
        concortHotelPage.searchByNameBox.sendKeys(fakeHotelName);
        concortHotelPage.searchButton.click();
        Thread.sleep(1000);
        String expectedHotelName = fakeHotelName;
        String actualHotelName = concortHotelPage.searchResultElement.getText();
        Assert.assertEquals(actualHotelName, expectedHotelName);
        Driver.closeDriver();
    }
}
