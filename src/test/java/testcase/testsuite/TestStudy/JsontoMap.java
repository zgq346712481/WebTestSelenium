package testcase.testsuite.TestStudy;

import org.apache.solr.client.solrj.SolrServerException;
import org.json.*;
import java.io.IOException;
import java.util.*;

/**
 * @Author：sks
 * @Description：
 * @Date：Created in 16:18 2017/12/18
 * @Modified by：
 **/
public class JsontoMap {

    /**
     * @Author：sks
     * @Description：把json对象数据存储在map以键值对的形式存储，只存储叶节点
     * @Date：
     */
    private static void JsonToMap(Stack<JSONObject> stObj, Map<String, Object> resultMap)  throws SolrServerException, IOException {

        if(stObj == null && stObj.pop() == null){
            return ;
        }
        JSONObject json = stObj.pop();
        Iterator it = json.keys();
        while(it.hasNext()){
            String key = (String) it.next();
            //得到value的值
            Object value = json.get(key);
            //System.out.println(value);
            if(value instanceof JSONObject)
            {
                stObj.push((JSONObject)value);
                //递归遍历
                JsonToMap(stObj,resultMap);
            }
            else {
                resultMap.put(key, value);
            }
        }
    }
    public static void main(String []args) throws SolrServerException, IOException{
        String jsonStr ="{responseHeader:{status:0,QTime:0},spellcheck:{suggestions:{中国:{numFound:9,startOffset:0,endOffset:2," +
                "suggestion:[中国工商银行, 中国人民, 中国国际, 中国农业, 中国市场, 中国经济, 中国人, 中国广播, 中国文化]}}," +
                "collations:{collation:中国工商银行}}}";
        JSONObject obj = new JSONObject(jsonStr);
        Stack<JSONObject> stObj = new Stack<JSONObject>();
        stObj.push(obj);
        Map<String, Object> resultMap =new HashMap<String, Object>();
        JsonToMap(stObj,resultMap);
        Set<String> keys = resultMap.keySet();
        for (String key:keys){
            System.out.println(key+"："+resultMap.get(key));
        }

    }
//    输出：
//    endOffset：2
//    startOffset：0
//    QTime：0
//    numFound：9
//    suggestion：["中国工商银行","中国人民","中国国际","中国农业","中国市场","中国经济","中国人","中国广播","中国文化"]
//    collation：中国工商银行
//    status：0

}