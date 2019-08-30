package pageclasses;

import model.SearchPageElementBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.UtilProperties;

import java.io.IOException;

public class SearchPageElementSub extends SearchPageElementBase {
    /**
     * 简述: 构造函数
     *
     * @param driver 关联测试用例脚本初始化浏览器对象实例
     */
    public SearchPageElementSub(WebDriver driver) {
        super(driver);
    }


    /**
     * 基于pom,以页面为单位，查找元素对象的封装，查找元素与元素操作动作的封装
     * LoginPageCase页面控件函数封装：元素定位+操作/数据
     * 返回"文本输入框"元素
     * @param driver
     * @return
     */
    //页面元素控件查找封装方法
    public static WebElement problemDescText(WebDriver driver) throws IOException {
        element = driver.findElement(By.xpath(UtilProperties.getProperties("problemDescTextXpath")));
        return element;
    }
    //元素操作数据封装成方法
    public static void sendkeyText(WebDriver driver,String sendTextValue) throws IOException, InterruptedException {
        element = problemDescText(driver);
        element.sendKeys(sendTextValue);
        Thread.sleep(1000);
    }

//针对页面的控件元素查找及操作数据封装成一个函数
    public static void problemDescTextSend(WebDriver driver,String sendTextValue) throws IOException, InterruptedException {
        element = driver.findElement(By.xpath(UtilProperties.getProperties("problemDescTextXpath")));
        element.sendKeys(sendTextValue);
        Thread.sleep(1000);
    }


    /**
     * 返回"上传按钮"元素
     * @param driver
     * @return
     */
    public static WebElement uploadButton(WebDriver driver) throws IOException {
        element = driver.findElement(By.xpath(UtilProperties.getProperties("uploadButtonXpath")));
        return element;
    }
    //元素与操作动作封装成控件函数 String actionName
    public static void uploadButtonClick(WebDriver driver) throws IOException, InterruptedException {
        element = uploadButton(driver);
        element.click();
    }

}
