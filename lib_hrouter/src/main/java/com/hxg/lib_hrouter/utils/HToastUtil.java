package com.hxg.lib_hrouter.utils;

import android.content.Context;
import android.widget.Toast;

import com.hxg.lib_hrouter.contextprovider.HRouterContextProvider;

public class HToastUtil {
    private static Toast toast;

    /**
     * 静态吐司
     *
     * @param context
     * @param text
     */
    public static void showToast(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        }
        toast.setText(text);
        toast.show();
    }

    /**
     * 不需要上下文对象的  静态toast
     */
    public static void showToast(String text) {
        showToast(HRouterContextProvider.get().getApplication(), text);
    }
}
