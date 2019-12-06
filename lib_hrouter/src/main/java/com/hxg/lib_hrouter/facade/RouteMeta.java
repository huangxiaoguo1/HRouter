package com.hxg.lib_hrouter.facade;

import android.app.Activity;

import com.hxg.lib_hrouter.HRouter;
import com.hxg.lib_hrouter.entity.ElementType;
import com.hxg.lib_hrouter.utils.HToastUtil;

public class RouteMeta {
    protected String mPath;

    public RouteMeta() {
    }

    protected Class<? extends Activity> getDestination() {
        ElementType elementType = HRouter.getInstance().getMap().get(mPath);
        if (elementType == null) {
            HToastUtil.showToast("未找到" + mPath + "配置的类名");
            return null;
        }
        if (elementType.getType().equals("activity")) {
            Class<? extends Activity> clazz = elementType.getClazz();
            return clazz;
        }
        return null;
    }

    protected Class getFragment() {
        ElementType elementType = HRouter.getInstance().getMap().get(mPath);
        if (elementType == null) {
            HToastUtil.showToast("未找到" + mPath + "配置的类名");
            return null;
        }
        if (elementType.getType().equals("fragment")) {
            Class<? extends Activity> clazz = elementType.getClazz();
            return clazz;
        }
        return null;
    }

}
