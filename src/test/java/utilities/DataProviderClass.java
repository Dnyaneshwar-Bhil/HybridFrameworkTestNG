package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviderClass {

    // DataProvider 1
    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {
        // taking Exel file form testData folder
        String path = ".\\testData\\Open-cart-LoginTestData.xlsx";
        ExcelUtility excelUtility = new ExcelUtility(path);

        int totalRow = excelUtility.getRowCount("Sheet2");
        int totalColumn = excelUtility.getColumnCount("Sheet2", 1);

        // Created two-dimensional array which can store total rows and column
        String[][] loginData = new String[totalRow][totalColumn];

        for (int i = 1; i <= totalRow; i++) {
            for (int j = 0; j < totalColumn; j++) {
                loginData[i - 1][j] = excelUtility.getCellData("Sheet2", i, j);
            }
        }
        return loginData;  // return two-dimensional array
    }

    // DataProvider 2

    // DataProvider 3

    // DataProvider 4


}
