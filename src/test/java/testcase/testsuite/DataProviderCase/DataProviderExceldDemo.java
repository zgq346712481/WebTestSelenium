/**
 * TestNG使用excel进行数据驱动代码
 */
package testcase.testsuite.DataProviderCase;

import Listener.ExtentTestNGIReporterListener;
import Listener.SendEmailListener;
import Listener.TestListener;
import core.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class,ExtentTestNGIReporterListener.class})//java多个listener监听方法
public class DataProviderExceldDemo extends BaseTest {
    @BeforeMethod
    @Override
    public void setUp() throws Exception {
// 初始化前置条件：1. 登录 --- click ---> login----->跳转到学习首页
        super.setUp();
//        SearchPageElementSub.pageLogin(driver, UtilProperties.getProperties("usernameValue"), UtilProperties.getProperties("passwordValue"));
    }

    @AfterMethod(alwaysRun = true)
    public void end() throws Exception {
        SendEmailListener sendTestReport = new SendEmailListener();
        sendTestReport.sendMailTestReport();//测试执行完毕发送测试报告到QQ邮箱，后续支持钉钉企业微信等机器人发消息
        driver.quit();
    }

}
