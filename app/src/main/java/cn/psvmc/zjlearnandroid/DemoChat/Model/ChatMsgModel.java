package cn.psvmc.zjlearnandroid.DemoChat.Model;


public class ChatMsgModel {
    private int type;//1:文字 2:图片
    private int fromId;
    private int toId;
    private String fromName;
    private String fromImg;
    private String toName;
    private String toImg;

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromImg() {
        return fromImg;
    }

    public void setFromImg(String fromImg) {
        this.fromImg = fromImg;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToImg() {
        return toImg;
    }

    public void setToImg(String toImg) {
        this.toImg = toImg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ChatMsgModel() {
    }

    public ChatMsgModel(int type, int fromId, int toId, String fromName, String fromImg, String toName, String toImg) {
        this.type = type;
        this.fromId = fromId;
        this.toId = toId;
        this.fromName = fromName;
        this.fromImg = fromImg;
        this.toName = toName;
        this.toImg = toImg;
    }
}
