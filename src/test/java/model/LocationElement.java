package model;

public class LocationElement {
    //添加八种元素定位方式： SearchPageElementBase.java定义
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

    private String ui;
    private String text;
    private String uiclass;
    private String classIndex;
    private String css;

    public LocationElement(String ui, String text, String uiclass, String classIndex, String css) {
        this.ui = ui;
        this.text = text;
        this.uiclass = uiclass;
        this.classIndex = classIndex;
        this.css = css;
    }

    public String getUi() {
        return ui;
    }

    public void setUi(String ui) {
        this.ui = ui;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUiclass() {
        return uiclass;
    }

    public void setUiclass(String uiclass) {
        this.uiclass = uiclass;
    }

    public String getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(String classIndex) {
        this.classIndex = classIndex;
    }

    public String getCss() {
        return css;
    }

    public void setCss(String classIndex) {
        this.classIndex = classIndex;
    }
}
