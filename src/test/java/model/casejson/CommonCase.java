package model.casejson;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * idea需要安装Lombok插件
 * 使用alibaba.com.fastjson解析json数据
 */
@Getter
@Setter
public class CommonCase implements Serializable {

    private String browserName;

    private String caseDes;

    private List<CaseSuit> caseSuitList;//CaseSuit列表写解析类
}
