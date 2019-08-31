/**
 * 基于pom,以页面为单位，查找元素对象的封装，查找元素与元素操作动作的封装
 * LoginPageCase页面控件函数封装：元素定位+操作/数据
 * 返回"文本输入框"元素
 * @param driver
 * @return
 */

package pageclasses;

import model.SearchPageElementBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.UtilProperties;
import java.io.IOException;

    public class SearchPageElementCommon extends SearchPageElementBase {
        /**
         * 简述: 构造函数
         *
         * @param driver 关联测试用例脚本初始化浏览器对象实例
         */
        public SearchPageElementCommon(WebDriver driver) {
            super(driver);
        }


        //针对页面的登录操作公共对象封装成函数
        public static void pageLogin(WebDriver driver, String usernameValue,String passwordValue) throws IOException, InterruptedException {
            driver.get(UtilProperties.getProperties("webSiteURL"));
//      System.out.println("element_now:"+UtilProperties.getProperties("username"));
            element01  =  driver.findElement(By.id(UtilProperties.getProperties("username")));
            element01.sendKeys(usernameValue);
            element02  =  driver.findElement(By.id(UtilProperties.getProperties("passWord")));
            element02.sendKeys(passwordValue);
            element03  =  driver.findElement(By.id(UtilProperties.getProperties("loginButton")));
            element03.click();
//        sp.waitForVisible(Elements.id_by, Elements.user);
//        sp.isElementExist(Elements.xpath_by, Elements.account_information);
//        sp.assertDtContain(Elements.id_by, Elements.user, "yl18103835542"); //对个人账号信息断言验证
            System.out.println("欢迎你，登录成功到优路大课堂，尽情学习吧！！！");
            Thread.sleep(1000);
        }


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

