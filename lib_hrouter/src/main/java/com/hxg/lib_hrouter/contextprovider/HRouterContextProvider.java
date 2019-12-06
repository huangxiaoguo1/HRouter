package com.hxg.lib_hrouter.contextprovider;

import android.app.Application;
import android.content.Context;

public class HRouterContextProvider {
    private static volatile HRouterContextProvider instance;
    private Context mContext;

    private HRouterContextProvider(Context context) {
        mContext = context;
    }

    /**
     * 获取实例
     */
    public static HRouterContextProvider get() {
        if (instance == null) {
            synchronized (HRouterContextProvider.class) {
                if (instance == null) {
                    Context context = HRouterAppContentProvider.mContext;
                    if (context == null) {
                        throw new IllegalStateException("context为null");
                    }
                    instance = new HRouterContextProvider(context);
                }
            }
        }
        return instance;
    }

    public Context getContext() {
        return mContext;
    }

    public Application getApplication() {
        return (Application) mContext.getApplicationContext();
    }

}
