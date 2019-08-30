package Listener;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import core.BaseTest;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;


public class TestListener extends BaseTest implements ITestListener {
    ATUTestRecorder recorder;

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    private static String getTestClassName(ITestResult iTestResult) {
        return iTestResult.getTestClass().getName();
    }

    @Attachment(value = "recording", type = "video/webm")
    public static String encryptToBase64(String filePath) {
        if (filePath == null) {
            return null;
        }
        try {
            //fixme mov 格式需要选转换成mp4
            byte[] b = Files.readAllBytes(Paths.get(filePath));
            return Base64.getEncoder().encodeToString(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Attachment(value = "recording", type = "video/mp4")
    public static File encryptToBase641(String filePath) throws URISyntaxException {
        if (filePath == null) {
            return null;
        }
        //fixme mov 格式需要选转换成mp4
//            byte[] b = Files.readAllBytes(Paths.get(filePath));
        URL refImgUrl = TestListener.class.getClassLoader().getResource(filePath);
        File refImgFile = Paths.get(refImgUrl.toURI()).toFile();
        return refImgFile;
    }

    //Text attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG (WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog (String message) {
        return message;
    }

    //HTML attachments for Allure
    @Attachment(value = "vide_link", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }


    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", this.driver);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("I am in onFinish method " + iTestContext.getName());
        //Do tier down operations for extentreports reporting!
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("I am in onTestStart method " +  getTestMethodName(iTestResult) + " start");

        try {
            String systemName = System.getProperty("os.name").toLowerCase();
            System.out.println(systemName);
            if(systemName.contains("window")||systemName.contains("mac os x")){
                try {
                    String relativelyPath=System.getProperty("user.dir");
                    recorder = new ATUTestRecorder(relativelyPath,getTestMethodName(iTestResult), false);
                } catch (ATUTestRecorderException e) {
                    e.printStackTrace();
                }
                try {
                    recorder.start();
                } catch (ATUTestRecorderException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("跳过录屏");
            }
        }
        catch (Exception e){
            throw e;
        }
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("I am in onTestSuccess method " +  getTestMethodName(iTestResult) + " succeed");

        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();

        //Allure ScreenShotRobot and SaveTestLog
        if (driver instanceof WebDriver) {
            System.out.println("Screenshot captured for test_draw case:" + getTestMethodName(iTestResult));
            saveScreenshotPNG(driver);
        }
        //Save a log on allure.
        saveTextLog(getTestMethodName(iTestResult) + " succeed and screenshot taken!");

        //Take base64Screenshot screenshot for extent reports
        String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)driver).
                getScreenshotAs(OutputType.BASE64);

        try {
            String systemName = System.getProperty("os.name").toLowerCase();
            System.out.println(systemName);
            if(systemName.contains("window")||systemName.contains("mac os x")){
                try {
                    recorder.stop();
                    String testname = getTestMethodName(iTestResult)+".mov";
                    String localurl = "data/attachments/";
                    String fullurl = "<html>\n" +
                            "<head>\n" +
                            "</head>\n" +
                            "<body><a href="+"\""+testname+"\">Click Download</a></body>\n" +
                            "</html>";
//            <a href="网址、链接地址" target="目标" title="说明">被链接内容</a>
                    attachHtml(fullurl);
                } catch (ATUTestRecorderException e) {
                    e.printStackTrace();
                }

            }
        }catch (Exception e){
            throw e;
        }

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("I am in onTestFailure method " +  getTestMethodName(iTestResult) + " failed");
        //Get driver from BaseTest and assign to local webdriver variable.
        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();

        //Allure ScreenShotRobot and SaveTestLog
        if (driver instanceof WebDriver) {
            System.out.println("Screenshot captured for testcase:" + getTestMethodName(iTestResult));
            saveScreenshotPNG(driver);
        }
        //Save a log on allure.
        saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
        try {
            String systemName = System.getProperty("os.name").toLowerCase();
            System.out.println(systemName);
            if(systemName.contains("window")||systemName.contains("mac os x")){
                try {
                    recorder.stop();
                    String testname = getTestMethodName(iTestResult)+".mov";
                    String localurl = "data/attachments/";
                    String fullurl = "<html>\n" +
                            "<head>\n" +
                            "</head>\n" +
                            "<body><a href="+"\""+testname+"\">recording</a></body>\n" +
                            "</html>";
//            <a href="网址、链接地址" target="目标" title="说明">被链接内容</a>
                    attachHtml(fullurl);
                } catch (ATUTestRecorderException e) {
                    e.printStackTrace();
                }

            }
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("I am in onTestSkipped method "+  getTestMethodName(iTestResult) + " skipped");
        //Extentreports log operation for skipped tests.
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }



}
