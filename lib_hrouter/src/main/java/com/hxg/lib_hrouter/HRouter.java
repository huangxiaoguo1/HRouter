package com.hxg.lib_hrouter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.hxg.lib_hrouter.contextprovider.HAppUtils;
import com.hxg.lib_hrouter.entity.ElementType;
import com.hxg.lib_hrouter.facade.Postcard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dalvik.system.DexFile;

/**
 * 桥梁
 */
public class HRouter {
    private static Handler mHandler;
    private Context context;
    //装在所有Activity类对象的容器
    private Map<String, ElementType> map;


    private HRouter() {
        map = new HashMap<>();
    }

    public void init(Context context) {
        this.context = context;
        mHandler = new Handler(Looper.getMainLooper());
        //根据包名获取到这个包下面所有的类名
        List<String> classList = getClassName("com.hxg.android.hrouter.routes");
        //遍历
        for (String s : classList) {
            try {
                Class<?> aClass = Class.forName(s);
                //判断这个类是不是IRouter这个接口的子类
                if (IRouter.class.isAssignableFrom(aClass)) {
                    //通过接口的引用  指向子类的实例
                    IRouter iRouter = (IRouter) aClass.newInstance();
                    iRouter.putClazz();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        if (HAppUtils.isApkInDebug()) {
            List<String> classLogList = getClassName("com.hxg.android.hrouter.log");
            //遍历
            for (String s : classLogList) {
                try {
                    Class<?> aClass = Class.forName(s);
                    //判断这个类是不是ILog这个接口的子类
                    if (ILog.class.isAssignableFrom(aClass)) {
                        //通过接口的引用  指向子类的实例
                        ILog iLog = (ILog) aClass.newInstance();
                        iLog.logPrint();
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static HRouter getInstance() {
        return HrouterHolder.sInstance;
    }

    private static class HrouterHolder {

        private static final HRouter sInstance = new HRouter();
    }

    public Map<String, ElementType> getMap() {
        return map;
    }

    /**
     * 将Activity,Fragment加入到map中的方法
     *
     * @param key
     * @param clazz
     */
    public void addClazz(String key, String clazzType, Class clazz) {
        if (key != null && clazz != null) {
            ElementType elementType = new ElementType();
            elementType.setType(clazzType);
            elementType.setClazz(clazz);
            map.put(key, elementType);
        }
    }

    public Postcard build(String path) {
        if (TextUtils.isEmpty(path)) {
            throw new RuntimeException("HRouter 传入地址不能为空!");
        }
        return new Postcard(context, path);
    }

    /**
     * 跳转Activity的方法
     *
     * @param currentContext
     * @param intent
     * @param requestCode
     */
    public void navigation(final Context currentContext, final Intent intent, final int requestCode) {
        if (!(currentContext instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        runInMainThread(new Runnable() {
            @Override
            public void run() {
                startActivity(currentContext, intent, requestCode);
            }
        });
    }

    /**
     * 跳转Activity的方法
     *
     * @param currentContext
     * @param intent
     * @param requestCode
     */
    private void startActivity(Context currentContext, Intent intent, int requestCode) {
        if (requestCode >= 0) {
            if (currentContext instanceof Activity) {
                ((Activity) currentContext).startActivityForResult(intent, requestCode);
            } else {
                currentContext.startActivity(intent);
            }
        } else {
            currentContext.startActivity(intent);
        }

    }

    /**
     * 通过包名获取这个包下面的所有类名
     *
     * @param packageName
     * @return
     */
    private List<String> getClassName(String packageName) {
        //创建一个class对象集合
        List<String> classList = new ArrayList<>();
        String path = null;
        try {
            //通过包管理器   获取到应用信息类然后获取到APK的完整路径
            path = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir;
            //根据APK的完整路径获取到编译后的dex文件目录
            DexFile dexfile = new DexFile(path);
            //获得编译后的dex文件中的所有class
            Enumeration<String> entries = dexfile.entries();
            //然后进行遍历
            while (entries.hasMoreElements()) {
                //通过遍历所有的class的包名
                String name = entries.nextElement();
                //判断类的包名是否符合我们起的包名（com.hxg.util）
                if (name.contains(packageName)) {
                    //如果符合，就添加到集合中
                    classList.add(name);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classList;
    }

    private void runInMainThread(Runnable runnable) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            mHandler.post(runnable);
        } else {
            runnable.run();
        }
    }
}
