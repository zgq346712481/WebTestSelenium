package model;

public class LocationElement {

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
