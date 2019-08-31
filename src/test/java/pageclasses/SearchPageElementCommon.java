package pageclasses;

import model.SearchPageElementBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.UtilProperties;
import java.io.IOException;

public class SearchPageElementCommon extends SearchPageElementBase  {
    /**
     * 简述: 构造函数
     *
     * @param driver 关联测试用例脚本初始化浏览器对象实例
     */
    public SearchPageElementCommon(WebDriver driver) {
        super(driver);
    }

    //针对页面的控件元素查找及操作数据封装成一个函数
    public static void pageLogin(WebDriver driver, String usernameValue,String passwordValue) throws IOException, InterruptedException {
        driver.get("http://member.niceloo.com");
        element01  =  driver.findElement(By.id(UtilProperties.getProperties("username")));
        element01.sendKeys(usernameValue);
        element02  =  driver.findElement(By.id(UtilProperties.getProperties("password")));
        element02.sendKeys(passwordValue);
        element03  =  driver.findElement(By.id(UtilProperties.getProperties("loginButton")));
        element03.click();
//        sp.waitForVisible(Elements.id_by, Elements.user);
//        sp.isElementExist(Elements.xpath_by, Elements.account_information);
//        sp.assertDtContain(Elements.id_by, Elements.user, "yl18103835542"); //对个人账号信息断言验证
        System.out.println("欢迎你，登录成功到优路大课堂，尽情学习吧！！！");
        Thread.sleep(1000);
    }
}

