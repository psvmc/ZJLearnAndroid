package cn.psvmc.zjlearnandroid.Model;

/**
 * Created by PSVMC on 16/5/27.
 */
public class ListItemModel {
    private String name;
    private String tag;
    private String tip;

    public ListItemModel( String tag,String name,String tip) {
        this.name = name;
        this.tag = tag;
        this.tip = tip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
