# WebTestSelenium
基于pom模式对selenium webui自动化测试

###类图、数据库表设计
http://www.uml.org.cn/oobject/Oobject-class.asp

设计思路：
大概PO的意思了，就是一般的函数封装，基于页面上的控件元素，把元素查找定位与数据操作等封装成对应的函数，然后用例脚本直接调用，从维护的用例文件读取对应的属性值，测试数据，以达到测试场景脚本与测试变动数据的分离，便于维护

针对页面的控件元素类，集元素定位查找+操作、数据输入封装成对应的函数，直接在用例脚本调用，把测试数据，元素属性抽离到到表格或json存储管理；
后期元素属性、测试数据变化只维护json或excel文件即可。
SearchPageElementSub 页面控件封装类（简写SP）

不同页面控件封装类代码生成器---生成控件封装类代码
首先用龙测定位器遍历页面上的元素属性存储到json文件中，然后根据元素名称key去获取对应的属性值，输入值，断言预期值，最后操作的实际结果进行对比，对断言结果分析后输出简易的测试报告。

LoginPageCase 测试用例类，直接控件类调用函数即可完成元素查找及数据操作

Jsonk结构体或excel表关系设计
环境url、定位元素名称，元素属性值，（定位方式、操作动作指令）输入数据、
断言期望结果、mockp配置数据、实际返回结果，测试结果，耗时，报错截图地址url
