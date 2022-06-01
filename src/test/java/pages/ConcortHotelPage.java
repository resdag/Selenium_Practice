package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class ConcortHotelPage {

    public ConcortHotelPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy (xpath="//*[text()='Log in']")
    public WebElement firstLoginButonu;

    @FindBy (id="UserName")
    public  WebElement userNameBox;

    @FindBy (id="Password")
    public  WebElement passwordBox;

    @FindBy (xpath="//input[@type='submit']")
    public WebElement secondLoginButonu;

    @FindBy (xpath = "//*[text()='Hotel Management']")
    public WebElement hotelManagementMenu;

    @FindBy (xpath = "//*[text()='User Management']")
    public WebElement userManagementMenu;

    @FindBy (xpath = "//a[@href='/admin/HotelAdmin']")
    public WebElement hotelListMenu;

    @FindBy (xpath = "//a[@href='/admin/HotelRoomAdmin']")
    public WebElement hotelRoomMenu;

    @FindBy (xpath = "//a[@href='/admin/RoomReservationAdmin']")
    public WebElement roomReservationMenu;

    @FindBy(xpath = "//*[text()='Add Hotel ']")
    public WebElement addHotelButton;

    @FindBy(id = "Code")
    public WebElement addHotelCodeElementi;

    @FindBy(xpath = "//*[text()='Save']")
    public WebElement addHotelSaveButton;

    @FindBy(id = "IDGroup")
    public WebElement idGroupDropDownElement;

    @FindBy(xpath = "//div[@class='bootbox-body']")
    public WebElement successfullyAddedTextBox;

    @FindBy(xpath = "//*[text()='OK']")
    public WebElement successfullyAddedOkButton;

    @FindBy(xpath = "//span[text()='List Of Hotels']")
    public WebElement listOfHotelsElement;

    @FindBy(xpath = "//tbody//td[3]")
    public List<WebElement> hotelNameList;





}
