package cn.psvmc.zjlearnandroid.DemoRecycleViewWithHeaderAndFooter.Model;

/**
 * Created by PSVMC on 16/5/30.
 */
public class ZJFileModel {
    String ID;//ID
    Integer TYPE;//0文件夹 1文件
    String FILE_NAME;//文件名
    String FILE_PATH;//下载路径
    long FILE_SIZE;//文件大小
    String UPDATE_DATE;//更新时间
    int IS_TAR;//是否收藏


    boolean isOpen = false;
    boolean checked = false;

    public ZJFileModel(String ID, Integer TYPE, String FILE_NAME, String FILE_PATH, long FILE_SIZE, String UPDATE_DATE, int IS_TAR) {
        this.ID = ID;
        this.TYPE = TYPE;
        this.FILE_NAME = FILE_NAME;
        this.FILE_PATH = FILE_PATH;
        this.FILE_SIZE = FILE_SIZE;
        this.UPDATE_DATE = UPDATE_DATE;
        this.IS_TAR = IS_TAR;
    }

    public String getFILE_NAME() {
        return FILE_NAME;
    }

    public void setFILE_NAME(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }

    public String getFILE_PATH() {
        return FILE_PATH;
    }

    public void setFILE_PATH(String FILE_PATH) {
        this.FILE_PATH = FILE_PATH;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }


    public Integer getTYPE() {
        return TYPE;
    }

    public void setTYPE(Integer TYPE) {
        this.TYPE = TYPE;
    }

    public String getUPDATE_DATE() {
        return UPDATE_DATE;
    }

    public void setUPDATE_DATE(String UPDATE_DATE) {
        this.UPDATE_DATE = UPDATE_DATE;
    }

    public int getIS_TAR() {
        return IS_TAR;
    }

    public void setIS_TAR(int IS_TAR) {
        this.IS_TAR = IS_TAR;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public long getFILE_SIZE() {
        return FILE_SIZE;
    }

    public void setFILE_SIZE(long FILE_SIZE) {
        this.FILE_SIZE = FILE_SIZE;
    }

}
