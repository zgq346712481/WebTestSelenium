package model;

import pageclasses.Elements;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.*;

/**
 * 简述: 优路教育提供的查找控件类
 */
public class SearchPageElementBase {
    private final static String TAG = "YouluTesting";
    private WebDriver driver;
    //首先声明一个WebElement类型的变量
    public static WebElement element = null ;
    public static WebElement element01 = null ;
    public static WebElement element02 = null ;
    public static WebElement element03 = null ;
    public static WebElement element04 = null ;

    //    public SearchPageElementBase sp;
    private boolean acceptNextAlert = true;
    public int waitTime = 5;
    public static Logger logger = Logger.getLogger(SearchPageElementBase.class);//记录日志

    // 数据库连接对象
    private static Connection Conn;
    // 数据库连接地址
    private static String URL = getKey("mysqladdress");
    // 数据库的用户名
    private static String UserName = getKey("mysqlusername");
    // 数据库的密码
    private static String Password = getKey("mysqlpassword");
    // 连接对象
    private Connection conn;
    // 传递sql语句
    private Statement stt;
    // 结果集
    private ResultSet set;


    //    读取配置文件
    public static String getKey(String key){
ClassLoader classLoader = pageclasses.SearchPageElementSub.class.getClassLoader();
String fileName = "config.properties";
InputStream in = classLoader.getResourceAsStream(fileName);
Properties prop = new Properties();
try {
    //读取属性文件.properties
//    InputStream in = new BufferedInputStream(new FileInputStream("config.properties"));
    prop.load(in);     //加载属性列表
    String returnkKey=prop.getProperty(key);
    in.close();
    return returnkKey;
}catch(Exception e){
    System.out.println(e);
}
return null;
    }


    /**
     * 方法的简述: 把数字变为元素数组
     *
     * @param n 数字
     * @return 元素数组
     */
    private ArrayList<Integer> getDigitList(long n) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        // Get each digit
        while (n > 0) {
            list.add((int) (n % 10));
            n = n / 10;
        }

        return list;
    }

    /**
     * 方法的简述: 打开网页
     * @param website 网址
     */
    public void get(String website) {
        this.driver.get(website);
        return;
    }

    /**
     * 获取手机屏幕的宽度
     * @return
     */
    public int getScreenWidth()
    {
        return driver.manage().window().getSize().width;
    }

    /**
     * 获取手机屏幕的高度
     * @return
     */
    public int getScreenHeight()
    {
        return driver.manage().window().getSize().height;
    }



    /**
     * 简述: 构造函数
     *
     * @param driver 关联测试用例脚本初始化浏览器对象实例
     */
    public SearchPageElementBase(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * 方法的简述: 定位View
     *
     * @param locationElement  定位元素封装类
     * @return null 或者 找到的view
     */
    public WebElement findView(LocationElement locationElement) {
        return this.findView(locationElement.getUi(), locationElement.getText(), locationElement.getUiclass(), locationElement.getClassIndex(),locationElement.getCss());
    }

    /**
     * 方法的简述: 定位View
     *
     * @param id        根据ID定位View
     * @param text      根据Text定位View
     * @param viewclass 根据Class:Index定位View
     * @param index     配合class
     * @param css       配合css定位View
     * @return null 或者 找到的view
     */
    public WebElement findView(String id, String text, String viewclass, String index,String css) {
        WebElement v = null;

        if (!id.isEmpty()) {
            try {
                v = (WebElement)driver.findElement(By.id(id));
                return v;
            } catch (Exception error) {
                System.out.println("找不到 id=  " + text);
            }
        }

        if (!text.isEmpty()) {
            try {
                v = (WebElement)driver.findElement(By.name(text));
                return v;
            } catch (Exception error) {
                System.out.println("找不到 text=  " + text);
            }
        }

        if (!viewclass.isEmpty() && !index.isEmpty()) {
            int num = -1;
            try {
                num = Integer.parseInt(index);
            } catch (Exception e) {
                System.out.println("找不到 index=  " + index);
                return v;
            }

            List<?> viewList = driver.findElements(By.className(viewclass));
            if (viewList == null || (viewList.size() <= num)) {
                System.out.println("index 非法  " + index);
                return v;
            }
            return (WebElement) viewList.get(num);
        }
        if (!css.isEmpty()) {
            try {
                v = (WebElement)driver.findElement(By.cssSelector(css));
                return v;
            } catch (Exception error) {
                System.out.println("找不到 css=  " + css);
            }
        }
        return v;
    }
    public WebElement findView(String id, String text, String viewclass, String index) {
        WebElement v = null;

        if (!id.isEmpty()) {
            try {
                v = (WebElement)driver.findElement(By.id(id));
                return v;
            } catch (Exception error) {
                System.out.println("找不到 id=  " + text);
            }
        }

        if (!text.isEmpty()) {
            try {
                v = (WebElement)driver.findElement(By.name(text));
                return v;
            } catch (Exception error) {
                System.out.println("找不到 text=  " + text);
            }
        }

        if (!viewclass.isEmpty() && !index.isEmpty()) {
            int num = -1;
            try {
                num = Integer.parseInt(index);
            } catch (Exception e) {
                System.out.println("找不到 index=  " + index);
                return v;
            }

            List<?> viewList = driver.findElements(By.className(viewclass));
            if (viewList == null || (viewList.size() <= num)) {
                System.out.println("index 非法  " + index);
                return v;
            }
            return (WebElement) viewList.get(num);
        }
        return v;
    }


    /**
     * 方法的简述: 使用xpath直接定位，其他输入报错
     * @param webview 中文文字或
     * @return null:没找到； By: 找到
     */
    public WebElement findWebView(String webview) {
        if (!webview.isEmpty()) {
            return driver.findElement(By.xpath(webview));
        }
        return null;
    }


    /**
     * 查找单个控件:8种定位方式
     *
     * @param locateWay
     * @param locateValue
     * @return
     */
    public WebElement findView(String locateWay, String locateValue) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        WebElement element = null;
        try {
            if (locateWay.equalsIgnoreCase("ID")) {
                element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locateValue)));
            } else if (locateWay.equalsIgnoreCase("CLASS_NAME")) {
                String[] value1 = locateValue.split(":");
                List<?> viewList = driver.findElements(By.className(value1[0]));
                int num = Integer.valueOf(value1[1]);
                WebElement element1 = (WebElement) viewList.get(num);
                element = element1;

            } else if (locateWay.equalsIgnoreCase("CSS_SELECTOR")) {
                element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locateValue)));

            } else if (locateWay.equalsIgnoreCase("LINK_TEXT")) {
                element = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locateValue)));

            } else if (locateWay.equalsIgnoreCase("NAME")) {
                element = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(locateValue)));

            } else if (locateWay.equalsIgnoreCase("PARTIAL_LINK_TEXT")) {
                element = wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locateValue)));

            } else if (locateWay.equalsIgnoreCase("TAGNAME")) {
                //                element = driver.findElement(By.tagName(locateValue));
                element = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locateValue)));

            } else if (locateWay.equals("XPATH")) {
                //                element = driver.findElement(By.xpath(locateValue));
                element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locateValue)));
            } else {
                logger.error("定位方式：" + locateWay + "不被支持");
                Assert.fail("定位方式：" + locateWay + "不被支持");
                element = null;
            }
        }
        catch (NoSuchElementException e){
            Assert.fail("未找到元素信息：定位方式："+locateWay+" 定位值："+locateValue,e);
        }
        return element;

    }


    /**
     * 查找一组控件
     *
     * @param locateWay
     * @param locateValue
     * @return
     */
    public List<WebElement> findViews(String locateWay, String locateValue) {
        WebElement element = null;
        try {
            Thread.sleep(1000);
            if (locateWay.equalsIgnoreCase("ID")) {
                return driver.findElements(By.id(locateValue));
            } else if (locateWay.equalsIgnoreCase("CLASS_NAME")) {
                return  driver.findElements(By.className(locateValue));
            } else if (locateWay.equalsIgnoreCase("CSS_SELECTOR")) {
                return driver.findElements(By.cssSelector(locateValue));
            } else if (locateWay.equalsIgnoreCase("LINK_TEXT")) {
//                element = driver.findElement(By.linkText(locateValue));
                return driver.findElements(By.linkText(locateValue));
            } else if (locateWay.equalsIgnoreCase("NAME")) {
//                element = driver.findElement(By.name(locateValue));
                return driver.findElements(By.name(locateValue));
            } else if (locateWay.equalsIgnoreCase("PARTIAL_LINK_TEXT")) {
//                element = driver.findElement(By.partialLinkText(locateValue));
                return driver.findElements(By.partialLinkText(locateValue));
            } else if (locateWay.equalsIgnoreCase("TAGNAME")) {
//                element = driver.findElement(By.tagName(locateValue));
                return driver.findElements(By.tagName(locateValue));
            } else if (locateWay.equals("XPATH")) {
//                element = driver.findElement(By.xpath(locateValue));
                return  driver.findElements(By.xpath(locateValue));
            }  else {
                logger.error("定位方式：" + locateWay + "不被支持");
                Assert.fail("定位方式：" + locateWay + "不被支持");
                element = null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            throw e;
        }
        System.out.println("element" + element);
        return (List) element;
    }


    public String getValue(String locateWay, String locateValue){
        return findView(locateWay,locateValue).getText();
    }

    /**
     * 基础action动作封装
     * @param locateWay
     * @param locateValue
     */

    public void click(String locateWay, String locateValue) {
        try {
            findView(locateWay, locateValue).click();
            logger.info("通过" + locateWay + "方式定位到元素：" + locateValue+ "点击：");
        } catch (Exception e) {
            Assert.fail("未通过" + locateWay + "方式定位到元素：" + locateValue,e);
            logger.error("未通过" + locateWay + "方式定位到元素：" + locateValue);
        }
    }

    public void inputText(String locateWay, String locateValue,String text){
        try {
            WebElement target = findView(locateWay, locateValue);
            target.clear();
            target.sendKeys(text);
            logger.info("通过" + locateWay + "方式定位到元素：" + locateValue + "输入值："+text);
        } catch (Exception e) {
            Assert.fail("未通过" + locateWay + "方式定位到元素：" + locateValue,e);
            logger.error("未通过" + locateWay + "方式定位到元素：" + locateValue);
        }
    }


    public void dbClick(String locateWay,String locateValue) throws Exception{
        Actions actiondb = new Actions(driver);
        WebElement test1item = findView(locateWay,locateValue);
        actiondb.doubleClick(test1item).perform();
    }

    /**
     * 键盘回车
     *
     */
    public void carriageReturn(){
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).perform();
    }

    /**
     * 键盘事件
     *
     */
    public void executeKeyBoard(String keys){
        Actions action = new Actions(driver);
        action.sendKeys(keys).perform();
    }

    /**
     * 键盘事件
     *
     */
    public void executeKeyBoard(String keys,String actions){
        Actions action = new Actions(driver);
        action.sendKeys(keys,actions).perform();
    }

    /**
     * 鼠标右击
     *
     * @param locateWay 定位方式
     * @param locateValue 定位值
     */
    public void rightClick(String locateWay,String locateValue) throws Exception{
        Actions actiondb = new Actions(driver);
        WebElement test1item = findView(locateWay,locateValue);
        actiondb.contextClick(test1item).perform();
    }
    public void clickRight(String locateWay,String locateValue) throws Exception{
        Actions actiondb = new Actions(driver);
        WebElement test1item = findView(locateWay,locateValue);
        actiondb.contextClick(test1item).perform();
    }

    /**
     * 鼠标长按
     *
     * @param locateWay 定位方式
     * @param locateValue 定位值
     */
    public void longPress(String locateWay,String locateValue) throws Exception{
        Actions actiondb = new Actions(driver);
        WebElement test1item = findView(locateWay,locateValue);
        actiondb.clickAndHold(test1item).perform();

    }

    //fixme 需要左滑动和右滑动的js代码
    public void swipe(String way){
        try {
            if (way == "UP"){
                String js="window.scrollTo(0,document.body.scrollHeight)";
                ((JavascriptExecutor) driver).executeScript(js);
            }
            else if (way == "DWON"){
                String js = "window.scrollTo(0,0)";
                ((JavascriptExecutor) driver).executeScript(js);
            }

        }
        catch (Exception e){
        }
    }

    public void drag_and_drop(String locateWay,String locateValue,String locateWay1,String locateValue1){
        WebElement web_element_source = findView(locateWay, locateValue);
        WebElement web_element_target = findView(locateWay1, locateValue1);
        Actions actiondb = new Actions(driver);
        actiondb.dragAndDrop(web_element_source,web_element_target).perform();

    }

    public void sleep(int second) throws InterruptedException {
        Thread.sleep(second);
    }

    /**
     * 切换至frame,
     *@param name frame id name
     */
    public void frame(String name) {
        driver.switchTo().frame(name);
    }

    /**
     * 切换至frame,
     *@param index frame的 index
     */
    public void frame(int index) {
        driver.switchTo().frame(index);
    }

    /**
     * 切换至frame,
     *@param locateWay locateValue
     */
    public void frame(String locateWay,String locateValue) {
        WebElement frame = findView(locateWay,locateValue);
        driver.switchTo().frame(frame);
    }

    /**
     * 切换至frame,再切换回主文档
     *
     */
    public void frame2Default() {
        driver.switchTo().defaultContent();
    }

    /**
     * 切换至最新的窗口句柄
     */
    public void window() throws InterruptedException {
        sleep(3000);
        Set<String> handles = driver.getWindowHandles();
//        for (String str : handles) {
//            System.out.println("===="+str);
//            driver.switchTo().window(str);
//        }
        System.out.println(handles);
        Iterator<String> i = handles.iterator();
        while (i.hasNext()) {
            driver.switchTo().window(i.next());
        }
    }

    /**
     * 通过Title来切换窗口
     */
    public void window(String windowTitle){
        boolean flag = false;
        String currentHandle = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String s : handles) {
//            if (s.equals(currentHandle))
//                continue;
//            else {
            driver.switchTo().window(s);
            if (driver.getTitle().contains(windowTitle)) {
                flag = true;
                System.out.println("Switch to window: "
                        + windowTitle + " successfully!");
                break;
            } else
                continue;
//            }
        }
        if (flag == false){
            System.out.printf("Window: " + windowTitle  + " cound not found!");
        }
    }

    /**
     * 获取alert值
     */
    public String switch2AlertAndGetText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

    /**
     * accept  alert
     */
    public void switch2Alert() {
        try {
            Alert alert = driver.switchTo().alert();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
        } finally {
            acceptNextAlert = true;
        }
    }

    /**
     * alert输入值，并接受
     */
    public void switch2AlertSendkeys(String value) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.sendKeys(value);
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
        } finally {
            acceptNextAlert = true;
        }
    }

    public void mouseOver(String way ,String value) throws InterruptedException{
        WebElement target = findView(way,value);
        Actions actiondb = new Actions(driver);
        actiondb.clickAndHold(target).perform();
    }

    public void jsexecut(String js,Object...args) {
        if(js == null) {
            System.out.println("js为空");
        } else {
            ((JavascriptExecutor) driver).executeScript(js,args);
        }
    }

    /**
     * click并截图
     */
    public void clickWithscreenshot(String way,String value) throws Exception{
        WebElement click = findView(way,value);
        click.click();
        saveScreenshotPNG(driver);
    }
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG (WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }


    /**
     * 截图
     */
    public void screenShotwithoutname() throws IOException, InterruptedException {
        //生成时间戳
        String pathStr = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
        String dateString = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_").format(new Date());
        String dir_name = System.getProperty("user.dir") + "/Screenshot" + "/" + pathStr;
        System.out.println("图片目录" + dir_name);
        //由于可能存在异常图片的且当被删除的可能，所以这边先判断目录是否存在
        if (!(new File(dir_name).isDirectory())) ;
        {
            //判断是否存在该目录
            new File(dir_name).mkdir();
        }
        //调用方法捕捉画面
        Thread.sleep(3000);
        File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //复制文件到指定目录
        //图片最后存放的路径由 目录：dir_name +时间戳+测试套件+测试用例+测试步骤组合生成
        System.out.println("图片名称" + dir_name + "/" + dateString  + ".jpg");
        FileUtils.copyFile(screen, new File(dir_name + "/" + dateString  + ".jpg"));
    }

    /**
     * 复选框单选
     */
    public void selectOneCheckBox(String way,String value){
        findView(way,value).click();
    }

    /**
     * 复选框全选
     */
    public void selectAllCheckBox(String way,String value){
        List<WebElement> li = findViews(way,value);
        for(int i=0;i<li.size();i++){
            li.get(i).click();
        }
    }

    /**
     * 按下标选择下拉选项
     */
    public void selectSelectByIndex(String way,String value,int index){
        WebElement el = findView(way,value);
        new Select(el).selectByIndex(index);
    }

    /**
     * 按可见文本选择下拉选项
     */
    public void selectSelectByVisibleText(String way,String value,String text){
        WebElement el = findView(way,value);
        new Select(el).selectByVisibleText(text);
    }

    /**
     * 按value选择下拉选项
     */
    public void selectSelectByValue(String way,String value,String value1){
        WebElement el = findView(way,value);
        new Select(el).selectByValue(value1);
    }

    /**
     * 文件上传：非input类型上传文件需要用AutoIT插件方式或 robot方式
     * @param locateWay
     * @param locateValue
     * @param exepath
     */
    public void inputByAutoIT(String locateWay, String locateValue, String exepath) {
        try {
            click(locateWay,locateValue);
            sleep(1000);
            Runtime.getRuntime().exec(exepath);
            logger.info("执行上传文件通过");
        } catch (Exception e) {
            logger.error("执行上传文件失败");
        }
    }

    /**
     * 上传文件robot方式
     * @param filepath
     */
    public  void UploadFileByRobot(String filepath) {	//定义上传文件方法，传入文件路径
        System.out.print(filepath);
        try {
            Thread.sleep(1000);
            // 指定图片的路径 ：filepath
            StringSelection sel = new StringSelection(filepath);
            // 把图片文件路径复制到剪贴板
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel,null);
            System.out.println("selection" +sel);
            // 新建一个Robot类的对象
            Robot robot = new Robot();
            Thread.sleep(1000);
            // 按下回车
            robot.keyPress(KeyEvent.VK_ENTER);
            // 释放回车
            robot.keyRelease(KeyEvent.VK_ENTER);
            // 按下 CTRL+V
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            // 释放 CTRL+V
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            Thread.sleep(1000);
            // 点击回车 Enter
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            System.out.println("执行上传文件通过");
        } catch (Exception e) {
            System.out.println("执行上传文件失败");
        }
    }

    /**
     * 遍历元素：遍历列表中属性值，并触发事件(action操作也可再封装一下)
     * @param list_elements
     */

    public  void elementForEach(ArrayList list_elements) throws InterruptedException {
        for (int i = 0; i < list_elements.size(); i++) {
            String elements_temp = (list_elements.get(i)).toString();
            Thread.sleep(20000);
            this.click(Elements.xpath_by, elements_temp);
            System.out.println("列表中第"+i+"个的元素:"+list_elements.get(i).toString());
            System.out.println("列表中所有元素遍历操作完成");
        }
    }



    public WebElement getWhenVisible(By locator, int timeOutInSeconds) {
        WebElement element = null;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element;
    }

    /**
     * * @Description:寻找控件信息
     * @param locateWay 定位方式
     * @param locateValue 定位值
     * @author xujun
     */
    public WebElement waitForVisible(String locateWay, String locateValue) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        WebElement displayElement = findView(locateWay, locateValue);
        try {
            wait.until(ExpectedConditions.visibilityOf(displayElement));
        }
        catch (Exception e){
            return displayElement;
        }
        return displayElement;
    }





    /**
     * 基础断言封装
     * @param locateWay
     * @param locateValue
     * @param expect
     * @throws Exception
     */

    public void assertDtEquals(String locateWay,String locateValue,String expect) throws Exception {
        String actual = findView(locateWay,locateValue).getText();
//        assert actual.equals(expect);
        try {
            assert actual.equals(expect);
        }
        catch (AssertionError e){
            logger.error("实际值："+actual+" ; 期望值："+expect);
            Assert.fail("实际值："+actual+" ; 期望值："+expect);
            throw e;
        }

    }

    /**
     * 断言值不相等
     * @param locateWay
     * @param locateValue
     * @param expect
     * @throws Exception
     */
    public void assertDtNotEquals(String locateWay,String locateValue,String expect) throws Exception {
        String actual = findView(locateWay,locateValue).getText();
        try {
            Assert.assertNotEquals(actual,expect);
        }
        catch (AssertionError e){
            logger.error("实际值："+actual+" ; 期望值："+expect);
            Assert.fail("实际值："+actual+" ; 期望值："+expect);
            throw e;
        }
    }

    /**
     * 断言包含
     * @param locateWay
     * @param locateValue
     * @param expect
     * @throws Exception
     */
    public void assertDtContain(String locateWay,String locateValue,String expect) throws Exception{
        String actual = findView(locateWay,locateValue).getText();
        try {
            assert (actual.contains(expect));
        }
        catch (AssertionError e){
            logger.error("实际值："+actual+" ; 期望值："+expect);
            Assert.fail("实际值："+actual+" ; 期望值："+expect);
            throw e;
        }

    }

    /**
     * 断言alert的值是否与期望相等
     * @param expect
     * @throws Exception
     */
    public void assertAlertMessage(String expect) throws Exception{
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        try {
            assert alertText.equals(expect);
        }
        catch (AssertionError e){
            logger.error("实际值："+alertText+" ; 期望值："+expect);
            Assert.fail("实际值："+alertText+" ; 期望值："+expect);
            throw e;
        }
    }

    /**
     * 断言元素是否被选中
     * @param way
     * @param value
     * @throws Exception
     */
    public void assertIsSelected(String way,String value) throws Exception{
        try {
            assert findView(way,value).isSelected();
        } catch (AssertionError e){
            logger.error("断言元素未被选中");
            Assert.fail("断言元素未被选中");
            throw e;
        }
    }

    /**
     * 断言元素未被选中
     * @param way
     * @param value
     * @throws Exception
     */
    public void assertIsNotSelected(String way,String value) throws Exception{
        try {
            assert !findView(way, value).isSelected();
        } catch (AssertionError e){
            logger.error("断言元素被选中了");
            Assert.fail("断言元素被选中了");
            throw e;
        }
    }

    public void assertIsDisplayed(String way,String value) throws Exception{
        try {
            assert findView(way,value).isDisplayed();
        } catch (AssertionError e){
            logger.error("断言元素未显示");
            Assert.fail("断言元素未显示");
            throw e;
        }
    }

    public void assertIsNotDisplayed(String way,String value) throws Exception{
        try {
            assert isElementExist(way,value) == false;
        } catch (AssertionError e){
            logger.error("断言元素显示了");
            Assert.fail("断言元素显示了");
            throw e;
        }
    }


    /**
     * 判断元素是否存在
     */
    public boolean isElementExist(String locateWay,String locateValue) {
        try {
            findView(locateWay,locateValue);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
    public void assertIsEnabled(String way,String value) throws Exception{
        try {
            assert findView(way,value).isEnabled();
        } catch (AssertionError e){
            logger.error("断言元素不可编辑");
            Assert.fail("断言元素不可编辑");
            throw e;
        }
    }

    public void assertIsNotEnabled(String way,String value) throws Exception{
        try {
            assert !findView(way, value).isEnabled();
        } catch (AssertionError e){
            logger.error("断言元素可编辑");
            Assert.fail("断言元素可编辑");
            throw e;
        }
    }

    public void assertIsTitle(String title) throws Exception{
        String actual = driver.getTitle();
        try {
            assert actual.equals(title);
        } catch (AssertionError e){
            logger.error("实际值："+actual+" ; 期望值："+title);
            Assert.fail("实际值："+actual+" ; 期望值："+title);
            throw e;
        }
    }

    public void assertIsTitleContains(String title) throws Exception{
        String actual = driver.getTitle();
        try {
            assert actual.contains(title);
        } catch (AssertionError e){
            logger.error("实际值："+actual+" ; 期望值："+title);
            Assert.fail("实际值："+actual+" ; 期望值："+title);
            throw e;
        }
    }

    public void assertIsAlertPresent() throws Exception{
        try {
            assert isAlertPresent() == true;
        } catch (AssertionError e){
            logger.error("没有出现alert");
            Assert.fail("没有出现alert");
            throw e;
        }
    }

    public boolean isAlertPresent() throws Exception{
        try {
            Alert alert = driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e){
            return false;
        }
    }

    public void assertAttribute(String way,String value,String attribute,String except) throws Exception{
        String actual = findView(way,value).getAttribute(attribute);
        try {
            assert actual.equals(except);
        } catch (AssertionError e){
            logger.error("断言元素属性值不正确");
            Assert.fail("断言元素属性值不正确");
            throw e;
        }
    }


    /**
     * 查询数据库进行断言
     * * @Description: 连接mysql数据库
     * @return Connection 连接数据的对象
     * @author xujun
     */
    public static Connection getConnection() {

        try {

            Class.forName("com.mysql.jdbc.Driver"); // 加载驱动

            System.out.println("加载驱动成功!!!");
        } catch (ClassNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        try {

            //通过DriverManager类的getConenction方法指定三个参数,连接数据库
            Conn = DriverManager.getConnection(URL, UserName, Password);
            System.out.println("连接数据库成功!!!");

            //返回连接对象
            return Conn;

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

    /**
     * * @Description:执行 sql 获取执行结果
     * @param sql sql语句
     * @return String 执行结果
     * @author xujun
     */
    public String mysql_search(String sql) {
        String result = null;
        try {
            // 获取连接
            conn = getConnection();
            if (conn == null)
                return result;
            // 定义sql语句
            String Sql = sql;
            // 执行sql语句
            stt = conn.createStatement();
            // 返回结果集
            set = stt.executeQuery(Sql);
            // 获取数据
            while (set.next()) {
                result = set.getString(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // 释放资源
            try {
                set.close();
                conn.close();
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }
        return result;
    }

    /**
     * * @Description: 获取sql的执行结果和元素的实际值进行相等的比较
     * @param locateWay 定位方式
     * @param locateValue 定位值
     * @param sql sql语句
     * @author xujun
     */
    public void assertDtEqualsDB(String locateWay,String locateValue,String sql) throws Exception {
        String actual = findView(locateWay,locateValue).getText();
        String expect = mysql_search(sql);
        try {
            Assert.assertEquals(actual,expect);
            logger.error("实际值："+actual+" ; DB查询值："+expect);
        }
        catch (AssertionError e){
            logger.error("实际值："+actual+" ; DB查询值："+expect);
            Assert.fail("实际值："+actual+" ; DB查询值："+expect);
            throw e;
        }

    }

    /**
     * * @Description: 获取sql的执行结果和元素的实际值进行不等于的断言
     * @param locateWay 定位方式
     * @param locateValue 定位值
     * @param sql sql语句
     * @author xujun
     */
    public void assertDtNotEqualsDB(String locateWay,String locateValue,String sql) throws Exception {
        String actual = findView(locateWay,locateValue).getText();
        String expect = mysql_search(sql);
        try {
            Assert.assertNotEquals(actual,expect);
            logger.error("实际值："+actual+" ; 期望值："+expect);
        }
        catch (AssertionError e){
            logger.error("实际值："+actual+" ; 期望值："+expect);
            Assert.fail("实际值："+actual+" ; 期望值："+expect);
            throw e;
        }

    }

    /**
     * * @Description: 获取sql的执行结果和元素的实际值进行包含的断言
     * @param locateWay 定位方式
     * @param locateValue 定位值
     * @param sql sql语句
     * @author xujun
     */
    public void assertDtContainDB(String locateWay,String locateValue,String sql) throws Exception{
        String actual = findView(locateWay,locateValue).getText();
        String expect = mysql_search(sql);
        try {
            assert (actual.contains(expect));
            logger.error("实际值："+actual+" ; 期望值："+expect);
        }
        catch (AssertionError e){
            logger.error("实际值："+actual+" ; 期望值："+expect);
            Assert.fail("实际值："+actual+" ; 期望值："+expect);
            throw e;
        }

    }

}
