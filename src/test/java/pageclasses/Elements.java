package pageclasses;

public class Elements {
    //添加八种元素定位方式： DtTestCase.java定义
    public static final String btnLogin_by = "ID";
    public static final String btnLogin = "su";
    // 通过ID定位元素
    public static final String id_by = "ID";
    // 通过xpath定位元素
    public static final String xpath_by = "XPATH";
    public static final String class_name_by = "CLASS_NAME";
    public static final String css_selector_by = "CSS_SELECTOR";
    public static final String link_text_by = "LINK_TEXT";
    public static final String name_by = "NAME";
    public static final String partial_link_text_by = "PARTIAL_LINK_TEXT";
    public static final String tagname_by = "TAGNAME";


    // 定位账号输入框
    public static final String username = "txtUserName";

    // 定位密码输入框
    public static final String password = "txtUserPwd";

    // 定位登录按钮
    public static final String id = "login";

    // 定位登录成功后账号信息
    public static final String user = "lblUserName";
//    判断新页面元素是否存在
    public static final String path_by ="XPATH";
    public static final String path = "//*[@id=\"form1\"]/div[1]/div[1]/div[3]/a[1]";

    // 定位登录成功后账号信息:登入后的账号显示信息
    public static final String account_information = "//*[@id=\"lblUserName\"]";
    //我的公开课页面:课程中心列表菜单点击页面链接 link_text_by
    public static final String my_course_link = "mlc_cs";

    //我的课程：2019执业西药师黄金执考班--进入学习 link_text_by
    public static final String study_now_link = "进入学习";
//    public static final String study_now_xpath = "/html/body/form/div[4]/div/div[3]/div[2]/ul/li[1]/a[2]";

    //课程列表：药事管理与法规课程焦老师（高清版） span块中元素的绝对路径定位xpath
    public static final String view_online_history="/html/body/form/div[4]/div/div[3]/div[2]/div[3]/a";
    //点击第一个展开按钮列表:用firefox浏览器定位绝对路径--OK
    public static final String list_open_first ="/html/body/form/div[4]/div/div[3]/div[3]/div[1]/div[1]/span";

    //2019执业西药师黄金执考班--展开药事管理与法规课程焦老师（高清版），按照顺序依次播放 xpath
    public static final String view_online_second_course ="/html/body/form/div[4]/div/div[3]/div[3]/div[1]/div[2]/div[2]/a[1]";
    public static final String view_online_first_course ="/html/body/form/div[4]/div/div[3]/div[3]/div[1]/div[2]/div[1]/a[1]";
    public static final String view_online_three_course ="/html/body/form/div[4]/div/div[3]/div[3]/div[1]/div[2]/div[3]/a[1]";
    public static final String view_online_course_pdf_down ="/html/body/form/div[4]/div/div[3]/div[3]/div[1]/div[2]/div[14]/a";

    //药学专业知识二第01次课程张老师（高清版）课程列表，按照顺序依次播放 xpath
    public static final String list_open_second ="/html/body/form/div[4]/div/div[3]/div[3]/div[2]/div[1]/span";
    public static final String view_online_second_course_2 ="/html/body/form/div[4]/div/div[3]/div[3]/div[2]/div[2]/div[1]/a[1]";
    public static final String view_online_first_course_2 ="/html/body/form/div[4]/div/div[3]/div[3]/div[2]/div[2]/div[2]/a[1]";
    public static final String view_online_three_course_2 ="/html/body/form/div[4]/div/div[3]/div[3]/div[2]/div[2]/div[3]/a[1]";
    public static final String view_online_course_pdf_down_2 ="/html/body/form/div[4]/div/div[3]/div[3]/div[2]/div[2]/div[14]/a";


//    课程中心--学习答疑表单
    public static final String text_QuestionAnswer ="学习答疑";
    public static final String view_QuestionAnswer ="/html/body/form/div[4]/div/div[1]/div[1]/ul/li[3]/a/span";
    public static final String Submit_questions ="/html/body/form/div[4]/div/div[3]/div[2]/a";
    public static final String click_upload ="//*[@id=\"btnBrowser\"]";
    public static final String Subject_Professional ="//*[@id=\"DropQuestionClass_0\"]";
    public static final String Subordinate_projects_text ="所属项目";
    public static final String Subordinate_projects ="//*[@id=\"DropProject\"]";
    public static final String Subjects ="//*[@id=\"DropQuestionSubject\"]";
    public static final String Subjects_css ="#DropQuestionSubject";
    public static final String Subjects_text ="所属科目";
    public static final String Problem_Desc ="//*[@id=\"TxtQuestionContent\"]";
    public static final String Problem_Desc_id="TxtQuestionContent";
    public static final String submit_form ="//*[@id=\"send\"]";
    public static final String wait_answer ="等待解答";
    public static final String title_answer_his ="/html/body/form/div[4]/div/div[3]/div[2]/div[1]/div[1]/a";
    public static final String answer_his="/html/body/form/div[4]/div/div[3]/div[1]/a[2]";
    public static final String images_upload_his="/html/body/form/div[4]/div/div[3]/div[2]/div[1]/div[1]/span";

//    订单管理--我的订单
    public static final String my_order ="我的订单";
    public static final String buycourse_online ="/html/body/form/div[4]/div/div[3]/div[2]/div[1]/a";
    public static final String my_order_div ="//*[@id=\"divNoRecord\"]";
//    href页面超链接跳转
    public static final String select_first="//*[@id=\"form1\"]/div[4]/div[2]/ul/li[1]/a";
    public static final String now_buy ="/html/body/form/div[4]/div[3]/div[2]/div[1]/span[3]/a";
    public static final String select_subject_all ="/html/body/form/div[4]/div[2]/div[1]/div/a[1]";
    public static final String select_type_all ="/html/body/form/div[4]/div[2]/div[2]/div/a[1]";
//    id定位
    public static final String real_name ="realyname";
    public static final String my_tel ="tel";
    public static final String my_address ="address";
    public static final String order_submit ="/html/body/div[2]/div[4]/div[3]/div[2]/input";
//    立即支付页面
    public static final String pay_now ="/html/body/form/div[4]/div/div[2]/div[1]/div/input[2]";
//    支付结果确认页面
    public static final String submit_pay_ok ="/html/body/div[3]/div[2]/p[3]/span[1]";

//    切换到充值开课流程
    public static final String my_recharge ="充值充课";
    public static final String value_recharge ="txtZfbPrice";
    public static final String pay_confir ="//*[@id=\"form1\"]/div[4]/div/div[3]/div[2]/div[1]/div/div[2]/input[2]";
    public static final String card_num ="cardNumber";
    public static final String next_step ="btnNext";
    public static final String card_pwd ="_ocx_password";
    public static final String pre_tel ="cellPhoneNumber";
    public static final String code_sms_select ="btnGetCode";
    public static final String code_sms ="smsCode";
    public static final String open_pay ="btnCardPay";
    public static final String code_sms_erro ="/html/body/div[2]/div[5]/div[1]/div[3]/div[4]/div[2]/ul/li/span";


    // 定位退出按钮
    public static final String dropout = "dropout";

    // 定位进入学习
    public static final String study = "进入学习";

    public static final String tuichu = "//*[@id=\"form1\"]/div[1]/div[1]/div[3]/a[2]";
    // 定位展开按钮
    public static final String zhankai = "#form1 > div.le_content > div > div.cu_mainr > div.nwcumr_mulu > div.mulu_item > div > span";
//    首页右上角登陆按钮
    public static final String loginbtn = "#form1 > div.header > div.pageWidth > div.fr.login > a:nth-child(1)";
    // 定位收起按钮
    public static final String shouqi = "#form1 > div.le_content > div > div.cu_mainr > div.nwcumr_mulu > div.mulu_item > div.mulu_tt.zk_fg > span";

//    我的公开课
    public static final String gongkaike = "#form1 > div.le_content > div > div.cun_mainl > div:nth-child(1) > ul > li:nth-child(5) > a > span";
//    公开课内容
    public static final String gongkaikeneirong = "#form1 > div.le_content > div > div.cu_mainr > div.cumr_list > div.kmlb_con > div.zbkmlb_item > div.zbkmlb_img > a > img";
//    开始学习
    public static final String kaishixuexi = "body > div.gk_kcjj > div.kcjj_rt > div.kcjt_bm > a";
//    用户名
    public static final String yonghuming = "#loginInfo > a.user";
//    我的订单
    public static final String dingdanguanli = "#form1 > div.le_content > div > div.cun_mainl > div:nth-child(2) > ul > li:nth-child(1) > a";
//    在线购课
    public static final String zaixiangouke = "#divNoRecord > a";
//    一级建造师
    public static final String yijijianzaoshi = "#form1 > div.pageWidth > div:nth-child(2) > ul > li:nth-child(1) > a";
//    立即购买
    public static final String lijigoumai = "#classUl > div:nth-child(1) > span.ljgm > a";
//    我已阅读并同意
    public static final String xieyi = "#agreementdiv > table > tbody > tr > td:nth-child(1) > p > i";
//    提交订单
    public static final String tijiaodingdan = "body > div.nod_con > div.nod_order > div.nod_rt > div.nod_btnbox > input";
//    在线支付
    public static final String zaixianzhifu = "#form1 > div.py_ordercont > div > div.py_way > div.pywa_fs.active > div > div:nth-child(4)";
//    立即支付
    public static final String lijizhifu = "#form1 > div.py_ordercont > div > div.py_way > div.pywa_fs.active > div > input.pyfs_ljbtn";
//    银行卡号输入框
    public static final String shurukuang = "#cardNumber";
//    下一步
    public static final String xiayibu = "#btnNext";
//    断言交易结果
    public static final String jiaoyijieguo = "body > div.header_wrap > div > div.fr > strong";
//    学习答疑
    public static final String xuexidayi = "#form1 > div.le_content > div > div.cun_mainl > div:nth-child(1) > ul > li:nth-child(3) > a > span";
//    提问
    public static final String tiwen = "#form1 > div.le_content > div > div.right > div.top > a.ask";
//    所属类型
    public static final String suoshuleixing = "#DropQuestionClass_0";
//    所属项目
    public static final String suoshuxiangmu = "#DropProject";
//    所属科目
    public static final String suoshukemu1 = "//*[@id=\"DropQuestionSubject\"]";
    //    问题描述
    public static final String wentimiaoshu = "#TxtQuestionContent";
//    文件上传
    public static final String wenjianshangchuan = "#btnBrowser";
//    提交问题
    public static final String tijiaowenti = "#send";
    public static final String duanyantijiao = "#form1 > div.le_content > div > div.right > div:nth-child(2) > div.ques > div.content > a";
//    js 滑动滚动条到底部
    public static final String scrollHeight = "window.scrollTo(0,document.body.scrollHeight)";
//    js 滑动滚动条到顶部
    public static final String  scrollTop= "window.scrollTo(0,0)";


}
