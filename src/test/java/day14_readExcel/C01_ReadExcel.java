package day14_readExcel;

import org.apache.poi.ss.usermodel.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class C01_ReadExcel {
    /*
    Bir excel'i okumak icin;
    - Apache POI ve Apache POI API Based on OPC class'larini pom.xml'e ekledik
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
     */

    @Test
    public void readEcxelTesti01() throws IOException {
        //1. Dosya yolunu bir String degiskene atayalim
        String dosyaYolu = "src/resources/ulkeler.xlsx";
        //2. FileInputStream objesi olusturup,parametre olarak dosya yolunu girelim
        FileInputStream fis = new FileInputStream(dosyaYolu);
        //3. Workbook objesi olusturalim,parameter olarak fileInputStream objesini girelim
        //4. WorkbookFactory.create(fileInputStream)
        Workbook workbook = WorkbookFactory.create(fis);
        //5. Worksheet objesi olusturun workbook.getSheetAt(index)
        Sheet sheet = workbook.getSheet("Sayfa1");
        //6. Row objesi olusturun sheet.getRow(index)
        Row row = sheet.getRow(3);
        //7. Cell objesi olusturun row.getCell(index)
        Cell cell = row.getCell(3);

        System.out.println(cell); // 3. satir, 3. indexteki datayi yazdirir
        //8. 3'uncu indexdeki satirin 3'uncu index"indeki datanin Cezayir oldugunuz test edin
        String expectedData = "Cezayir";
        String actualData = cell.toString();

        Assert.assertEquals(expectedData, actualData);
        Assert.assertEquals(expectedData, cell.getStringCellValue());

        String actualData2 = workbook
                .getSheet("Sayfa1")
                .getRow(3)
                .getCell(3)
                .toString();
        Assert.assertEquals(expectedData, actualData2);
        System.out.println("------------------------End of the test------------------------");
    }

    @Test
    public void readExcelTesti02() throws IOException {
        // istenen satir ve sutundaki degeri bulan bir method olusturun
        int satirNo = 12;
        int sutunNo = 2;

        String expectedData = "Baku";
        String actualData = banaDataGetir(satirNo - 1, sutunNo - 1);
        Assert.assertEquals(expectedData, actualData);
        System.out.println("------------------------End of the test------------------------");
    }

    public static String banaDataGetir(int satirIndex, int sutunIndex) throws IOException {
        String istenenData = "";
        String dosyaYolu = "src/resources/ulkeler.xlsx";
        FileInputStream fis = new FileInputStream(dosyaYolu);
        Workbook workbook = WorkbookFactory.create(fis);
        istenenData = workbook
                .getSheet("Sayfa1")
                .getRow(satirIndex)
                .getCell(sutunIndex)
                .toString();
        return istenenData;
    }

    @Test
    public void readExcelTest03() throws IOException {
        String dosyaYolu = "src/resources/ulkeler.xlsx";
        FileInputStream fis = new FileInputStream(dosyaYolu);
        Workbook workbook = WorkbookFactory.create(fis);

        // ingilizce baskentler sutununu yazdiralim,
        int sonSatir = workbook.getSheet("Sayfa1").getLastRowNum();
        System.out.println(sonSatir);
        String satirdakiData = "";
        for (int i = 0; i <= 5; i++) { //sadece 5 saytiri yazmasi icin sonSatir yazmadim
            satirdakiData = banaDataGetir(i, 1);
            System.out.println(satirdakiData);
        }
        System.out.println("------------------------End of the test------------------------");
    }

    @Test
    public void readExcelTest04() throws IOException {
        Map<String, String> ulkelerMap = new HashMap<>();
        String dosyaYolu = "src/resources/ulkeler.xlsx";
        FileInputStream fis = new FileInputStream(dosyaYolu);
        Workbook workbook = WorkbookFactory.create(fis);

        int sonSatirIndex = workbook.getSheet("Sayfa1").getLastRowNum();
        for (int i = 0; i <= sonSatirIndex; i++) {
            // key i. satirdaki 0 indexindeki data olacak
            String key = workbook.getSheet("Sayfa1").getRow(i).getCell(0).toString();
            // value ise i. satirdaki 1,2 ve 3. indexdeki datalarin birlesimi olacak
            String value = workbook.getSheet("Sayfa1").getRow(i).getCell(1).toString()
                    + ", "
                    + workbook.getSheet("Sayfa1").getRow(i).getCell(2).toString()
                    + ", "
                    + workbook.getSheet("Sayfa1").getRow(i).getCell(3).toString();
            ulkelerMap.put(key, value);
        }
        System.out.println(ulkelerMap);
        // Listede Ghana oldugunu test edelim
        Assert.assertTrue(ulkelerMap.keySet().contains("Ghana"));
    }
}
