package com.hxg.lib_hrouter.contextprovider;

import android.content.pm.ApplicationInfo;

public class HAppUtils {
    /**
     * 判断当前应用是否是debug状态
     */

    public static boolean isApkInDebug() {
        try {
            ApplicationInfo info = HRouterContextProvider.get().getApplication().getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

}
