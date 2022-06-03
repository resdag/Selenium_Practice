package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class ReactShopPage {

    public ReactShopPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//div[@tabindex='1']")
    public List<WebElement> urunlerListesi;

    @FindBy(xpath = "//span[.='X']")
    public WebElement carpiIsareti;

    @FindBy(xpath = "//button[@tabindex='-1']")
    public List<WebElement> addToCartButonlari;

    @FindBy(xpath = "//p[@class='sc-1h98xa9-9 jzywDV']")
    public WebElement toplamTutarElementi;

    @FindBy(xpath = "//button[@class='sc-1h98xa9-11 gnXVNU']")
    public WebElement checkOutButonu;

    @FindBy(xpath = "//div[@class='sc-11uohgb-4 bnZqjD']")
    public List<WebElement> sepetUrunleriFiyatListesi;


    @FindBy(xpath = "//div//div//div//div//div//div//p")
    public List<WebElement> sepettekiUrunIsimleriListesi;



}
