package cn.psvmc.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by PSVMC on 16/5/31.
 */
public class ZJFileUtils {

    private static final String DOWNLOAD_DIR = "download";


    /**
     * 获取下载文件夹
     * @return
     */
    public static final File getDownloadDir() {
        return new File(Environment.getExternalStorageDirectory(), "Download");
    }

    /**
     * 获取文件大小
     * @param size
     * @return
     */
    public static String getFileSizeStr(long size) {
        long kb = 1024;
        long mb = 1024 * kb;
        long gb = 1024 * mb;
        long tb = 1024 * gb;
        double tempSize = size;
        if (tempSize < 1024) {
            return tempSize + "B";
        } else if (size < 1024 * 1024) {
            return decimal2(tempSize/kb) + "KB";
        } else if (size < 1024 * 1024 * 1024) {
            return decimal2(tempSize/mb) + "MB";
        } else if (size < 1024 * 1024 * 1024 * 1024) {
            return decimal2(tempSize/gb) + "GB";
        } else {
            return decimal2(tempSize/tb) + "TB";
        }
    }

    /**
     * 保留两位小数
     * @param num
     * @return
     */
    private static double decimal2(double num) {
        BigDecimal b = new BigDecimal(num);
        double df = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return df;
    }

    /**
     * 获取文件名
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath) {
        String fileName = "";
        if (filePath != null) {
            String[] arr = filePath.split("/");
            fileName = arr[arr.length - 1];
        }
        return fileName;
    }

    /**
     * 获取文件的md5
     *
     * @param filePath
     * @return
     */
    public static String getFileMD5(String filePath) {
        File file = new File(filePath);
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

    /**
     * 获取文件大小
     *
     * @param filePath
     * @return
     */
    public static long getFileSize(String filePath) {
        long size = 0;
        File file = new File(filePath);
        size = file.length();
        return size;
    }

    /**
     * 获取文件的总片数
     *
     * @param filePath
     * @return
     */
    public static int getFilePieces(String filePath) {
        File file = new File(filePath);
        long size = file.length();

        long pieceSize = 1024 * 1024 * 2;
        if (size % pieceSize == 0) {
            return (int) (size / pieceSize);
        } else {
            return (int) (size / pieceSize + 1);
        }
    }

    /**
     * 获取当前时间的字符串
     * @return
     */
    public static String getNowStr() {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(now);

    }

    /**
     * 根据后缀获取图片
     * @param context
     * @param suffix
     * @return
     */
    public static Drawable getDrawableBySuffix(Context context, String suffix) {
        Resources res = context.getResources();
        suffix = suffix.toLowerCase();
        String name = "ic_parttern_icon_" + suffix;
        int id = res.getIdentifier(name, "drawable", context.getPackageName());
        if(id == 0){
            return null;
        }else{
            return res.getDrawable(id);
        }
    }

    /**
     * 获取文件前缀
     * @param fileName
     * @return
     */
    public static final String getPrefix(@NonNull String fileName) {
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    /**
     * 获取文件后缀
     * @param fileName
     * @return
     */
    public static final String getSuffix(@NonNull String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 根据原文件删除临时分片文件
     * @param originFile 原文件
     * @param chunk 第几片
     */
    public static void deleteFileByOriginFile(File originFile,int chunk){
        String partFilePath = originFile.getParentFile().getAbsolutePath()+ File.separator+"temp"+File.separator+chunk+File.separator+getFileName(originFile.getAbsolutePath());
        File partFile = new File(partFilePath);
        if(partFile.exists()){
            partFile.delete();
        }
    }

    /**
     * 2M一个分片
     * @param originFile
     * @param chunk
     * @return
     */
    public static File splitFileBy2M(File originFile,int chunk){
        long byteSize = 1024 * 1024 * 2;
        return splitFile(originFile,byteSize,chunk);
    }

    /**
     * 分割文件
     * @param originFile 原文件
     * @param byteSize 分片大小
     * @param chunk 第几片 从0开始
     * @return 分割后的新文件
     */
    public static File splitFile(File originFile,long byteSize,int chunk){
        long startPos = byteSize * chunk;
        RandomAccessFile rFile;
        OutputStream os = null;
        try {
            String partFilePath = originFile.getParentFile().getAbsolutePath()+ File.separator+"temp"+File.separator+chunk+File.separator+getFileName(originFile.getAbsolutePath());
            File partFile = new File(partFilePath);
            if(partFile.exists()){
                partFile.delete();
            }
            partFile.getParentFile().mkdirs();
            rFile = new RandomAccessFile(originFile, "r");
            byte[] b = new byte[(int)byteSize];
            rFile.seek(startPos);// 移动指针到每“段”开头
            int s = rFile.read(b);
            os = new FileOutputStream(partFilePath);
            os.write(b, 0, s);
            os.flush();
            os.close();
            return partFile;
        } catch (IOException e) {
            e.printStackTrace();
            if(null != os){
                try {
                    os.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            return null;
        }
    }

    /**
     * 分割处理Runnable
     *
     * @author yjmyzz@126.com
     *
     */
    private class SplitRunnable implements Runnable {
        int byteSize;
        String partFileName;
        File originFile;
        int startPos;

        public SplitRunnable(int byteSize, int startPos, String partFileName,
                             File originFile) {
            this.startPos = startPos;
            this.byteSize = byteSize;
            this.partFileName = partFileName;
            this.originFile = originFile;
        }

        public void run() {
            RandomAccessFile rFile;
            OutputStream os;
            try {
                rFile = new RandomAccessFile(originFile, "r");
                byte[] b = new byte[byteSize];
                rFile.seek(startPos);// 移动指针到每“段”开头
                int s = rFile.read(b);
                os = new FileOutputStream(partFileName);
                os.write(b, 0, s);
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
