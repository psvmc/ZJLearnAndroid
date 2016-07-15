package cn.psvmc.zjlearnandroid.DemoTabbar.M;

import android.os.Bundle;

/**
 * Created by PSVMC on 15/12/11.
 */
public final class ZJTabInfo {
    public final Class<?> clss;
    public final Bundle args;
    public String title;

    public ZJTabInfo(Class<?> clss, Bundle args) {
        this.clss = clss;
        this.args = args;
    }

    public ZJTabInfo(Class<?> clss, Bundle args,String title) {
        this.clss = clss;
        this.args = args;
        this.title = title;
    }
}