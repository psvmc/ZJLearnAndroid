package cn.psvmc.zjlearnandroid.TabbarDemo.Lib;

import android.os.Bundle;

/**
 * Created by PSVMC on 15/12/11.
 */
public final class ZJTabInfo {
    public final String tag;
    public final Class<?> clss;
    public final Bundle args;
    public Integer id;
    public String text ="";

    public ZJTabInfo(String tag, Class<?> clss, Bundle args, int id, String text) {
        this.tag = tag;
        this.clss = clss;
        this.args = args;
        this.id = id;
        this.text = text;
    }
}