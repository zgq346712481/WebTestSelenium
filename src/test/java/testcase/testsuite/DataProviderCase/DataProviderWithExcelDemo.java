package testcase.testsuite.DataProviderCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.ExcelCaseUtils;

import java.util.concurrent.TimeUnit;


public class DataProviderWithExcelDemo {
    private String sTestCaseName;
    private int iTestCaseRow;
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe"); //Chrome Driver
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://member.niceloo.com");
        Thread.sleep(2000);
    }

    @Test(dataProvider = "Authentication")//数据提供者程序函数,dataProvider提供传递测测试数据，从excel表格中按行列一词读取对应单元格测试数据
    public void Login(String sUserName, String sPassword) throws InterruptedException {
        driver.findElement(By.id("txtUserName")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("txtUserName")).sendKeys(sUserName);//要跟用例表格中对应列名一致，按行列单元格取值
        System.out.println("sUserName:"+sUserName);
        Thread.sleep(1000);
        driver.findElement(By.id("txtUserPwd")).sendKeys(sPassword);
        System.out.println("sPassword:"+sPassword);
        driver.findElement(By.id("login")).click();
        System.out.println(" Login Successfully, now it is the time to study.");
        Thread.sleep(10000);

    }

    @AfterMethod
    public void afterMethod() {
    //driver.quit();
    driver.close();
    }

    @DataProvider
    public Object[][] Authentication() throws Exception {
    // Setting up the Test Data Excel file
        String excelPath ="E:\\IntelliJ_IDEA_workspace\\WebTestSelenium\\src\\test\\java\\testcase\\testdata\\casedata.xlsx";
//        String excelContent = ExcelCaseUtils.setExcelFile(excelPath, "commonCasetest01","usernameText");
        String sUserName = ExcelCaseUtils.getExcelNextCell(excelPath, "commonCasetest01","usernameText");
        System.out.println("NextCellContent:"+sUserName);
        String sPassword = ExcelCaseUtils.getExcelNextCell(excelPath, "commonCasetest01","passwdText");
        System.out.println("NextCellContent:"+sPassword);

        sTestCaseName = this.toString();
        // From above method we get long test case name including package and class name etc.
        // The below method will refine your test case name, exactly the name use have used
        sTestCaseName = ExcelCaseUtils.getTestCaseName(this.toString());
        // Fetching the Test Case row number from the Test Data Sheet
        // Getting the Test Case name to get the TestCase row from the Test Data Excel sheet
        iTestCaseRow = ExcelCaseUtils.getRowContains(sTestCaseName, 0);//获取用例名称
        Object[][] testObjArray = ExcelCaseUtils.getTableArray(excelPath, "commonCasetest01", iTestCaseRow);
        return (testObjArray);//Object[][]
//        String[][] ObjectArry = new String[][]{{sUserName},{sPassword}};
//        System.out.println("ObjectArry:"+ObjectArry);
//        return (ObjectArry);

    }


}
