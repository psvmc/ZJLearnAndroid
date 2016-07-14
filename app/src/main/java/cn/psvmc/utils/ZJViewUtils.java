package cn.psvmc.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by PSVMC on 16/6/24.
 */
public class ZJViewUtils {

    public static void showDialog(Context mContext,String title, String message, DialogInterface.OnClickListener listener){
        AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认", listener);
        alertDialog.show();

    }
}
