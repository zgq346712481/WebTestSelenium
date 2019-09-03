package model.casejson;

import com.alibaba.fastjson.JSON;
import testcase.testdata.jsonMapTest;

public class testCaseJson {

    public static void main(String[] args) throws  Exception{
        String path ="E:\\IntelliJ_IDEA_workspace\\WebTestSelenium\\src\\test\\java\\testcase\\testdata\\commonCaseTemp.json";
        String s = jsonMapTest.readJsonFile(path);//读取json文件输出字符串
        CommonCase commonCaseTemp = JSON.parseObject(s,CommonCase.class);//反射类解析数据

        for (CaseSuit suit : commonCaseTemp.getCaseSuitList()) {//遍历集合
            System.out.println(" = " + suit.getTestCaseID());//get获取集合元素
        }
        System.out.println("commonCase = " + commonCaseTemp);
    }
}
