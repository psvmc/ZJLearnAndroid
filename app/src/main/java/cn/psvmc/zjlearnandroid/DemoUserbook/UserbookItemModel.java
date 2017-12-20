package cn.psvmc.zjlearnandroid.DemoUserbook;

import android.support.annotation.NonNull;

public class UserbookItemModel implements Comparable<UserbookItemModel> {
    private String name;
    private String image;
    private String firstChar;

    public UserbookItemModel() {
    }

    public UserbookItemModel(String name, String image, String firstChar) {
        this.name = name;
        this.image = image;
        this.firstChar = firstChar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFirstChar() {
        return firstChar;
    }

    public void setFirstChar(String firstChar) {
        this.firstChar = firstChar;
    }


    @Override
    public int compareTo(@NonNull UserbookItemModel another) {
        return this.firstChar.compareToIgnoreCase(another.getFirstChar());
    }
}
