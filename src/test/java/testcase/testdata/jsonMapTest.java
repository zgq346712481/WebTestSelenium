package testcase.testdata;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;


public class jsonMapTest {

    /**
     * 读取json文件，返回json串
     * @param fileName
     * @return
     */
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //遍历map，然后把map的value放到数组里
//    public List getMapValue(Map<String,Object> map){
//        List<Object> list = new ArrayList<>();
//        for (Map.Entry<String, Object> entry : map.entrySet()) {
//
//            list.add(entry.getValue());
//        }
//        return list;
//    }

    public static void main(String[] args) throws  Exception{
//        String path = jsonMapTest.class.getClassLoader().getResource("test.json").getPath();
        String path ="E:\\IntelliJ_IDEA_workspace\\WebTestSelenium\\src\\test\\java\\testcase\\testdata\\commonCaseTemp.json";

        String s = jsonMapTest.readJsonFile(path);//读取json文件输出字符串
        JSONObject jobj = JSON.parseObject(s);//json字符串解析转化为json对象
        System.out.println("browserName"+jobj.get("browserName"));
        JSONObject caseDes1 = jobj.getJSONObject("caseDes");//getjson对象
        String webSiteURL = (String) caseDes1.get("webSiteURL");
        String version = (String) caseDes1.get("version");
        System.out.println("webSiteURL :" + webSiteURL);
        System.out.println("version :" + version);

        JSONArray CaseSuit = jobj.getJSONArray("CaseSuit");//获取json数组对象，CaseSuit用例集名称,封装为函数
        for (int i = 0 ; i < CaseSuit.size();i++){//遍历数组对象输出其元素
            JSONObject keyArray = (JSONObject)CaseSuit.get(i);
            String TestCaseNameValue = (String)keyArray.get("TestCaseName");
            String TestCaseIDValue = (String)keyArray.get("TestCaseID");
            System.out.println("TestCaseNameValue:"+TestCaseNameValue);
            System.out.println("TestCaseIDValue:"+TestCaseIDValue);
        }


    }

}
