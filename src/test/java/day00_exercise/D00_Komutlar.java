package day00_exercise;

public class D00_Komutlar {
    /*
        --------------------------------------------------------------------------------------
        get(). -- getTitle,getCurrentUrl,getPageSource,getWindowHandle
        navigate(), -- to,back,forward,refresh
        manage().window(). --  maximize,fullscreen,minimize,getSize,getPosition,setSize,setPosition,
                .timeouts(). -- implicitlyWait,
        close() sadece son calisan browser'i kapatir
        quit() acik olan tum browser'lari kapatir
        --------------------------------------------------------------------------------------
         .findElement(..) -- Tek bir WebElement dondurur. 8 yontem ile locate edilir
                        By.id(), By.name(), By.className(), By.tagName(),
                        By.linkText()(link'teki(a tagi) text), By.partialLinkText() (text'in parcasi)
                        By.xpath(),  By.cssSelector()

    .findElements(..) --  WebElement'lerden olusan list dondurur.

    .sendKeys(".." + Keys.ENTER) -- icine yazilan degeri webelement'e yollar

    .isDisplayed()(gorunur mu), isEnabled()(ulasilabilir mi), isSelected()(secili mi)

    .getTagName() -- locate ettigimiz bir WebElementin tagName'ni dondurur
    .getAttribute("name") -- WebElementin istedigim attribute'unun degerini dondurur (name tag'indaki degeri getirir)

    .getLocation() -- WebElementin'in konumunu dondurur
    .aramaKutusu.getSize().height -- WebElement'in yuksekligi dondurur
        --------------------------------------------------------------------------------------
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
        --------------------------------------------------------------------------------------
        - Maven framework olusturmamizi kolaylastiran tool'dur. JUnit,TestNG, Cucumber ise framework'tur.
    - https://mvnrepository.com/ adresinden;
        - en guncel, stabil, cok kullanilan methodlardan,

            https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager
            https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java

        kutuphanelerini ilgili kod satirini projemizdeki pom.xml dosyasinda
         <dependencies> taglari arasina ekleyerek yukledik.

        - Versiyon ve yuklenme durumunu ide'nin sagindaki maven kismindan yenileyerek guncelledik.
        --------------------------------------------------------------------------------------
                - JUnit bir framework'tur
            - Bu framework'u kullanmak icin, pom.xml'e
    https://mvnrepository.com/artifact/junit/junit/4.13.2
    <dependencies> taglari arasina ekledik ve maven'dan yeniledik.

            -  @ isareti annotation'dir ve bununla yorumlayiciya talimat veririz.
    Bu(@Test) Notasyonuyla isaretle main method olmadan testleri calistirabiliriz.

            - @Before/After - sadece bir web sayfasinda calisirken kullanilir.
    Classtaki her methodun basinda ve sonunda birer defa calisir.
        3 tane test olan bir class calistirilinca test methodlariyla birlikte 9(3+6) method calisir.
    Bu class'ta @BeforeClass ve @AfterClass'da varsa 11 tane calisir.

            - @BeforeClass/AfterClass - birden fazla sayfada calisirken kullanilir.
            Sadece static methodlarla ve class'in basinda ve sonunda sadece bir defa calisir.
            3 tane test olan bir class calistirilinca test methodlariyla birlikte 5(3+2) method calisir.
        --------------------------------------------------------------------------------------
        CheckBox'larda birden cok eleman secilebilir.
        .isSelected() -- istenilen element'e secili mi diye bakar
        .click() -- ile tik atar/varsa kaldiririz

        RadioButton sadece bir secenegi secmemize izin verir.
        --------------------------------------------------------------------------------------
       Assert.(method),
    @BeforeClass, @AfterClass, @Before, @After,
    .click(),
    sendKeys("Ali" + Keys.ENTER)
    select - getOptions(), getFirstSelectedOption(), getText()

    - Assert.(method) ile if-else kullanimina son verdik.
        Kullanmadan once beklenen sonucun true-false oldugunu belirlemeliyiz
        Beklenen sonuc ile gerceklesen ayni ise test PASSED, degilse test FAILED olur.
        a=20, b=40  Assert.assertEqual(a,b) -- failed
            Assert.assertFalse(a>b) -- passed (sonuc false, beklenen false)

     - @BeforeClass - ayni classda birden cok test yapacaksak
        her defasinda sayfayi kapatip acmamak icin kullandik.
        Tek bir test varsa @Before kullanilabilir

     - .click(); --> locate edilen yere tiklar
     - .sendKeys("Ali" + Keys.ENTER); --> locate edilen yere Ali yazip Enter'a basar

     - dropDown - acilir menu <select> tag'iyla kullanilir.
        kullanmak icin 3 adim izlemeliyiz.
        - DropDownMenu locate edilir. (WebElement ddm = driver.findElement(By.xpath("//.."));)
        - Select objesi olusturup locate edilen DropDownMenu'yu element yap (Select select = new Select(ddm);)
        - 3 yontemden birisi ile istenen ddm elemanini sec
            - select.selectByVisibleText("Books");
            - select.selectByIndex(5);
            - select.selectByValue("search-alias...");

        - select.getFirstSelectedOption() ile son secilen optiona'a gideriz, bu WebElement dondurur,
            getText() ile uzerindeki yaziyi yazdirilir
        --------------------------------------------------------------------------------------
        .alert().switchTo().alert() / iframe() / window()  -- ilgili yere gider
               .switchTo().alert().accept() -- alert'i kabul eder
                            .alert().dismiss() -- alert'i reddeder
                            .alert().sendKeys("Ali Bal"); -- alert'e deger girer

     .alert().switchTo().frame(iFrameElementi / index / nameOrId); -- iframeElementinin oldugu web sayfasina gider
               .switchTo().defaultContent(); -- en ust frame doner
               .switchTo().parentFrame(); -- bir ust frame gecer

    .clear() -- yaziyi siler

    .alert() -- uyaridir.    HTML alert'lere islem gerekmez.
                            JS alertler ise ciktiginda locate edilemez.


    Basic Authentication -- siteye girince dogrulama istiyorsa verileri aldigimiz
    API nasil girmemiz gerektigini de soylemelidir. Orn: Normal URL yerine, 'https://username:password@URL' gibi

    iframe: Web stesindeki HTML tagleri arasinda olmalidir.
    bu varsa ilgili bolum ayri bir HTML tag'i yani ayri web sayfasi icerir.
    buraya gecmek icin switchTo kullanilmalidir.
    .switchTo() ile iframe'e gecersek eski sayfaya geri donmemiz gerekir
        --------------------------------------------------------------------------------------
        driver.getWindowHandle() - bulunulan window'un hash kodunu dondurur
          .getWindowHandles() -- set ile tum window'larin  hash kodunu dondurur

    driver.switchTo().window("Handle Degeri") -- bu handle degeri olan sayfaya gider
                     (CDwindow-12168F63FA40495174B45A2348A68CAC)
                     .newWindow(WindowType.WINDOW); -- yeni pencere acar
                     .newWindow(WindowType.TAB); -- yeni sayfa acar

       * kontrolsuz olarak yeni sekme/pencereye gidersek bu sayfanin handle degerine ulasmak gerekir
        --------------------------------------------------------------------------------------
        src/test/java/utilities altinda olusturdugumuz TestBase class'ini extend ederek
    oradaki Before/After methodlarini kullanabiliriz.

    Actions 3 adimda yapilir.
        -Actions actions = new Actions(driver); --> obje olusturulup paremetre olarak driver kullanilir
        -WebElement accountLinki = driver.findElement(By.xpath("..")); --> istenen element locate edilir
        -actions.moveToElement(accountLinki).perform(); --> istenen islem yapilir

    ---Mause
    .click() --> tek tiklar
    .doubleClick() --> cift tiklar
    .moveToElement(element).perform() --> fareyi istenen konuma goturur
    .contextClick(element).perform() --> farede sag tiklar
    .dragAndDrop(tasinacakElement, hedefElement).perform(); --> elementi istenen yere surukleyip birakir
    .clickAndHold() --> click yapip bizden komut bekler

    ---Klavye
    .sendKeys() --> basilan tusu yollar
    .keyDown() --> tusa basili tutar
    .keyUp() --> tusa basmayi birakir
        --------------------------------------------------------------------------------------
        - Faker kütüphanesini pom.xml'e ekledik.

    - Faker faker = new Faker(); //yeni obje olusturup bunun uzerinden islem yapariz
        String fakeMail = faker.internet().emailAddress();
        actions.click(isimKutusu)
                .sendKeys(faker.name().firstName()) // fake isim yazar
                .sendKeys(faker.internet(). //fake parola yazar
                .sendKeys(Keys.ENTER)
                .perform(); //en sonda performu unutmamaiyiz

    - File Exist / File DownLoad / File Upload
        - System.getProperty("user.dir") --> icinde olunan dosyanin yolunu verir
        - System.getProperty("user.home") --> dosya yolunun her bildisayar icin farkli olabilecek kismini verir
                String dosyaYolu = System.getProperty("user.home") + "\\Desktop\\text.txt"; --> DINAMIK dosya yolu
        - File.exists(Paths.get("filePath)) --> belirtilen klasorde dosyanin olup olmadigini kontrol eder

        - Dosya yuklemek icin dosya yukleme butonuna dosya adresi yollanir
            -dosyaSecButonu.sendKeys(yuklenecekDosya);
        --------------------------------------------------------------------------------------
        Java tabanli wait --> Thread.sleep(), sure dolana kadar bekler

    Selenium tabanli wait -->
        ImplicitlyWait -->  tum ogeler icin genel bir maksimum bekleme suresidir
        ExplicitlyWait --> tek bir komut icin istenen kosul saglanana kadar bekler

        WebElement birElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("dosyaYolu")));
        - beklenen sart henuz saglanmadigi icin kosul ve locate etme islemi ayni satirda yapilmalidir.
        - kosul olarak biir cok ssecenek vardir: elementToBeSelected, titleContains, visibilityOfAllElement...

        ExplicitlyWait kullanimi her method'da 3 adimdadir;
        - WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); //obje olustur
        - WebElement birElement = wait.until(ExpectedConditions.titleContains(By.xpath("dosyaYolu"))); //wait ve dosya yolu
        - Assert.assertTrue(birElement.isDisplayed()); //istenen kontrol
        --------------------------------------------------------------------------------------
        .manage().getCookies() --> cookiesleri set'e atar
                .addCookie(cookie); --> Cookie cookie = new Cookie("yeni", "seker"); ile olusturulani sete ekler
                .deleteCookieNamed("skin") --> skin coockies'i siler
                .deleteAllCookies() --> tum cookies'i siler

    locate: //tr[5]//td[4] --> 5.satirin 4. sutunundaki elementine gideriz.
            //tbody//tr -->body'deki tum satirlari bulur
            By.xpath("//tbody//tr[" + satir + "]//td[" + sutun + "]") --> bu yazi sekliyle kodu dinamik yapabiliriz
        --------------------------------------------------------------------------------------
        Bir excel'i okumak icin;
    - Dosya yolunu bir String degiskene atayalim
    - FileInputStream objesi olusturup,parametre olarak dosya yolunu girelim
    - WorkbookFactory.create(fileInputStream) ile Workbook objesi olusturalim,
            parameter olarak fileInputStream objesini girelim
    - Worksheet objesi olusturun workbook.getSheetAt(index)
    - Row objesi olusturun sheet.getRow(index)
    - Cell objesi olusturun row.getCell(index)

    - cell.toString()--> cell'deki data string'e donusturur.

    - Hucredeki veriye daha kolay ulasmak icin;
        String dosyaYolu = "src/resources/ulkeler.xlsx";
        FileInputStream fis = new FileInputStream(dosyaYolu);
        Workbook workbook = WorkbookFactory.create(fis);
        String cellData = workbook
                            .getSheet("SayfaNo")
                            .getRow(satirNo)
                            .getCell(sutunNo)
                            .toString();
    - workbook.getSheet("Sayfa1").getLastRowNum() --> kullanilan son satira kadar olan satir sayisini getirir
                                .getPhysicalNumberOfRows() --> sadece icinde veri olan satirlari getirir
                                .getRow(1).getCell(1).toString() -->1. satir, 1.sutun'nun String'ini verir

    - bir excel datasini Map'e atmak icin unique olan 1 degeri key'e, diger degerleri ise value'ya atariz
        --------------------------------------------------------------------------------------

     */
}
