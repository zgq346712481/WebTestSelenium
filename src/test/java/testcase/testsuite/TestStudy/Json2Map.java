package testcase.testsuite.TestStudy;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.util.*;

public class Json2Map {
    /**依赖为参考：net.sf.json.JSONObject maven依赖
     * java实体,MAP,json之间的转换
     * 将json字符串转为Map结构
     * 如果json复杂，结果可能是map嵌套map
     * @param jsonStr 入参，json格式字符串
     * @return 返回一个map
     */
    public static Map<String, Object> json2Map(String jsonStr) {
        Map<String, Object> map = new HashMap<>();
        if(jsonStr != null && !"".equals(jsonStr)){
            //最外层解析
            JSONObject json = JSONObject.fromObject(jsonStr);
            for (Object k : json.keySet()) {
                Object v = json.get(k);
                //如果内层还是数组的话，继续解析
                if (v instanceof JSONArray) {
                    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                    Iterator<JSONObject> it = ((JSONArray) v).iterator();
                    while (it.hasNext()) {
                        JSONObject json2 = it.next();
                        list.add(json2Map(json2.toString()));
                    }
                    map.put(k.toString(), list);
                } else {
                    map.put(k.toString(), v);
                }
            }
            return map;
        }else{
            return null;
        }
    }


    public static void main(String[] args) {
        String jsonString = "{\n" +
                "\"data\": [\n" +
                "           {\n" +
                "            \"IR_SRCNAME\": \"车主之家\",\n" +
                "                \"IR_SITENAME\": \"车主之家\",\n" +
                "                \"IR_AUTHORS\": null,\n" +
                "                \"IR_URLTITLE\": \"2017年4月份高尔夫销量11798台, 同比下降20.24%\",\n" +
                "                \"IR_URLNAME\": \"http://news.16888.com/a/2017/0521/8172148.html\",\n" +
                "                \"IR_ABSTRACT\": \" 2017年4月份高尔夫销量11798台 \",\n" +
                "                \"IR_URLTIME\": \"2017/05/21 23:35:00\",\n" +
                "                \"IR_HKEYBBSNUM\": \"18093721078864168420-0\",\n" +
                "                \"IR_CHANNEL\": \"新闻_汽车新闻\", \n" +
                "                \"COMPANY_RISK_CONN\": \"汽车之家股份有限公司_财务风险\\偿债能力\\营运资本_18;\",\n" +
                "                \"ZFM\": \"负面\"\n" +
                "           }\n" +
                "    \t],\n" +
                "        \"path\": \"/cloud/wsu/queryByKeyword!get_by_fullname.action\",\n" +
                "                \"rstcode\": \"0000\",\n" +
                "                \"rstcount\": 19476,\n" +
                "                \"cmpname\": \"汽车之家股份有限公司\",\n" +
                "                \"shortname\": \"汽车之家\",\n" +
                "                \"rstmsg\": \"查询成功\"\n" +
                "}";

        Map jsonMap =Json2Map.json2Map(jsonString);

        System.out.println("jsonmap:"+jsonMap.get("IR_SRCNAME"));


    }

}

