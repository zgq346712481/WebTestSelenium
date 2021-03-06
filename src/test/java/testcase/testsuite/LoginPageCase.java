/**
 * po模式封装:页面类主要是元素定位和页面操作写成函数，供测试类调用
 * 页面类.元素.（操作方法：输入值）；读取用例数据
 *  sp.problemDescText(driver).sendKeys(UtilProperties.getProperties("problemDescTextValue"));
 * 页面类.控件函数（driver,动作或输入值等参数）
 * sp.sendkeyText(driver,UtilProperties.getProperties("problemDescTextValue"));
 */

package testcase.testsuite;

import Listener.ExtentTestNGIReporterListener;
import Listener.SendEmailListener;
import Listener.TestListener;
import core.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageclasses.Elements;
import pageclasses.SearchPageElementSub;
import utilities.UtilProperties;

@Listeners({TestListener.class,ExtentTestNGIReporterListener.class})//java多个listener监听方法
public class LoginPageCase extends BaseTest {
    @BeforeMethod
    @Override
    public void setUp() throws Exception {
        // 初始化前置条件：1. 登录 --- click ---> login----->跳转到学习首页
        super.setUp();
        SearchPageElementSub.pageLogin(driver,UtilProperties.getProperties("usernameValue"),UtilProperties.getProperties("passwordValue"));
    }

    @AfterMethod(alwaysRun = true)
    public void end() throws Exception {
        SendEmailListener sendTestReport= new SendEmailListener();
        sendTestReport.sendMailTestReport();//测试执行完毕发送测试报告到QQ邮箱，后续支持钉钉企业微信等机器人发消息
        driver.quit();
    }

    @Story("流程1_网站用户答疑模块")
    @Feature("用户答疑问题提交")
    @Description("填写答疑问卷上传图片")
    @Severity(SeverityLevel.BLOCKER)
    @Test(priority = 1)
    public void QuestionAnswerCase() throws Exception {
        // 3. 登录--->课程中心--->学习答疑表单提交
        //切换到学习答疑
        sp.waitForVisible(Elements.link_text_by, Elements.text_QuestionAnswer);
        sp.click(Elements.link_text_by, Elements.text_QuestionAnswer);
        Thread.sleep(4000);
        //跳转到我的答疑表单页面  是否存在待解答问题
        String title_answer_first = sp.getValue(Elements.xpath_by,Elements.title_answer_his);
        System.out.println("dayi:"+title_answer_first);
//      sp.assertIsDisplayed(Elements.link_text_by,Elements.wait_answer);
        if (title_answer_first.indexOf("测试")!=-1){
            System.out.println("答疑已提交成功");
        }else{
            System.out.println("不存在历史答疑");
        }
        sp.isElementExist(Elements.xpath_by, Elements.answer_his);
        sp.click(Elements.xpath_by, Elements.answer_his);
        Thread.sleep(1000);
//       选择器定位--所属项目--下拉框--执业西药师:选择器要先点击操作，然后选择内容元素
        sp.click(Elements.xpath_by, Elements.Subject_Professional);
        sp.selectSelectByIndex(Elements.xpath_by,Elements.Subordinate_projects,2);
//        "西药法规"选择器：依赖第一个选择器选项的关联内容
        Thread.sleep(2000);

        sp.click(Elements.xpath_by, Elements.Subjects);
        sp.selectSelectByIndex(Elements.xpath_by,Elements.Subjects,2);
        sp.problemDescTextSend(driver,UtilProperties.getProperties("problemDescTextValue"));
        sp.uploadButtonClick(driver);

//图片上传：非input类型上传文件需要用AutoIT插件或robot方式
        String imagepath ="D:\\webui_saveScreen\\selenium.jpg";
        sp.UploadFileByRobot(imagepath);
//等待图片上传完成
        Thread.sleep(10000);
        sp.click(Elements.xpath_by, Elements.submit_form);
        Thread.sleep(10000);
        System.out.println("学习答疑提问表单完成请期待回答");
    }



}
