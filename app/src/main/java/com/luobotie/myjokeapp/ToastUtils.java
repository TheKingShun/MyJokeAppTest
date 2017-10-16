package com.luobotie.myjokeapp;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import javax.xml.datatype.Duration;

/**
 * TheKing_Shun 欢迎来到我的 ->MyJokeApp<- 项目
 * 该项目创建于2017/10/15.
 * 努力 加油 我是最棒的！2017
 */

public class ToastUtils {
    private static Toast mToast;

    public static void setShortToast(Context context,String text){
        if (mToast!=null){
            mToast.cancel();
            mToast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
            mToast.show();

        }else{
            mToast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
            mToast.show();

        }
    }

}
