package yuan.jxau.cn.pansearch.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 编程只服JAVA on 2017.03.12.
 */

public class ToastUtils {
    public static void showShort(Context context , String message){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context , String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
