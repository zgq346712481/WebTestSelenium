package testcase.testsuite;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestDataByExcelFile {
    private static WebDriver driver;
    @DataProvider(name="searchData")
    public static Object[][] data() throws IOException
    {
        return getSearchData("E:\\AutoData\\","testData.xlsx","testData");//获取Excel文件的测试数据
    }
    @Test(dataProvider="searchData")
    public void testSearch(String searchdata1,String searchdata2,String searchResult) {
        //打开sogou首页
        driver.get("http://www.sogou.com/");
        //输入搜索条件
        //从Excel文件中读取每行中前2个单元格内容作为搜索框中输入的搜索关键词,在两个搜索词中间增加一个空格
        driver.findElement(By.id("query")).sendKeys(searchdata1+" "+searchdata2);
        //单击搜索按钮
        driver.findElement(By.id("stb")).click();

        //使用显式等待方式，确认页面已经加载完成，页面底部的关键字"搜索帮助"已经显示在页面上
        (new WebDriverWait(driver,3)).until(new ExpectedCondition<Boolean>(){

            @Override
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id("sogou_webhelp")).getText().contains("搜索帮助");
            }});

        //断言搜索结果页面是否包含Excel文件中每行的最后一个单元格内容的关键字
        Assert.assertTrue(driver.getPageSource().contains(searchResult));
    }
    @BeforeMethod
    public void beforeMethod() {
        //若无法打开Firefox浏览器，可设定Firefox浏览器的安装路径
        System.setProperty("webdriver.firefox.bin", "D:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
        //打开Firefox浏览器
        driver=new FirefoxDriver();
        //设定等待时间为5秒
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void afterMethod() {
        //关闭打开的浏览器
        driver.quit();
    }
    //从Excel文件获取测试数据的静态方法
    public static Object[][] getSearchData(String filePath,String FileName,String sheetName) throws IOException{
        //根据参数传入的数据文件路径和文件名称，组合出Excel数据文件的绝对路径，声明一个File文件对象
        File file = new File(filePath + "\\" + FileName);
        //创建FileInputStream对象用于读取Excel文件
        FileInputStream inputStream = new FileInputStream(file);
        Workbook Workbook = null;
        //获取文件名参数的扩展名，判断是.xlsx文件还是.xls文件
        String fileExtensionName = FileName.substring(FileName
                .indexOf("."));
        if (fileExtensionName.equals(".xlsx")) {
            Workbook = new XSSFWorkbook(inputStream);
        } else if (fileExtensionName.equals(".xls")) {
            Workbook = new HSSFWorkbook(inputStream);
        }
        //通过sheetName参数，声称Sheet对象
        Sheet Sheet = Workbook.getSheet(sheetName);
        //获取Excel数据文件Sheet1中数据的行数，getLastRowNum()方法获取数据的最后一行行号
        //getFirstRowNum()方法获取数据的第一行行号，相减之后得出数据的行数，Excel文件的行号和列号都是从0开始
        int rowCount = Sheet.getLastRowNum() - Sheet.getFirstRowNum();
        //创建list对象存储从Excel数据文件读取的数据
        List<Object[]> records = new ArrayList<Object[]>();
        //循环遍历Excel数据文件的所有数据，除了第一行，第一行是数据列名称
        for (int i = 1; i < rowCount + 1; i++) {
            //使用getShow方法获取行对象
            Row row = Sheet.getRow(i);
            //声明一个数组，存储Excel数据文件每行中的3个数据，数组的大小用getLastCellNum()方法进行动态声明，实现测试数据个数和数组大小一致
            String fields[] = new String[row.getLastCellNum()];
            for (int j = 0; j < row.getLastCellNum(); j++) {
                //使用getCell()和getStringCellValue()方法获取Excel文件中的单元格数据
                fields[j] =row.getCell(j).getStringCellValue();
            }
            //将fields的数据对象存入records的list中
            records.add(fields);
        }
        // 将存储测试数据的List转换为一个Object的二维数组
        Object[][] results = new Object[records.size()][];
        // 设置二位数组每行的值，每行是一个Object对象
        for (int i = 0; i < records.size(); i++) {
            results[i] = records.get(i);
        }
        return results;
    }
}