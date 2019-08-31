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


public class DataProviderWithExcelLogin {
    private String sTestCaseName;
    private int iTestCaseRow;
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe"); //Chrome Driver
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://member.niceloo.com");
    }

    @Test(dataProvider = "Authentication")
    public void Login(String sUserName, String sPassword) throws InterruptedException {
        driver.findElement(By.id("txtUserName")).click();
        driver.findElement(By.id("txtUserName")).sendKeys(sUserName);
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
//        driver.quit();
        driver.close();
    }

    @DataProvider
    public Object[][] Authentication() throws Exception {
        // Setting up the Test Data Excel file
        ExcelCaseUtils.setExcelFile("E:\\IntelliJ_IDEA_workspace\\WebTestSelenium\\src\\test\\java\\testcase\\testdata\\casedata.xlsx", "commonCasetest");
        sTestCaseName = this.toString();
        // From above method we get long test case name including package and class name etc.
        // The below method will refine your test case name, exactly the name use have used
        sTestCaseName = ExcelCaseUtils.getTestCaseName(this.toString());
        // Fetching the Test Case row number from the Test Data Sheet
        // Getting the Test Case name to get the TestCase row from the Test Data Excel sheet
        iTestCaseRow = ExcelCaseUtils.getRowContains(sTestCaseName, 0);
        Object[][] testObjArray = ExcelCaseUtils.getTableArray("E:\\IntelliJ_IDEA_workspace\\WebTestSelenium\\src\\test\\java\\testcase\\testdata\\casedata.xlsx", "commonCasetest", iTestCaseRow);
        return (testObjArray);
    }
}
