package com.hyn.baselibrary.avc.core;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;


/**
 * author：admin on 2016/1/10
 * mail：hyn_0525@sina.com
 */
public class BaseApplication extends Application {

    protected static Context _context;  //上下文
    protected static Resources _resource; //资源
    private static boolean sIsAtLeastGB;
    /**
     * 调整信息（全局跳转参数定义）
     */
    public static final String JUMP_INFO = "JUMP_INFO";
    /**
     * 判断是否正常运行，如果为0，则为被回收过
     */
    public static int isRunning = 0;

    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            sIsAtLeastGB = true;
        }
    }

    /**
     * 初始化应用，父类什么也不干
     */
    public boolean init() {
        return true;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (_context == null) {  //判断是否已经被初始化过
            _context = getApplicationContext();
            _resource = _context.getResources();
        }
//        LeakCanary.install(this);//内存泄露展现工具
        init();
        registerUncaughtExceptionHandler();
    }

    /**
     * 返回应用的主Scheme，继承应用可重写,这个是为了统一URI使用
     *
     * @return
     */
    protected String getPrimaryScheme() {
        return "qytxapp";
    }

    /**
     * 根据应用的data来处理,目前data要符合XXX://intent
     * 注册的intent要符合隐式调用的规范，例如
     * <activity
     * android:name="com.example.app016.SecondActivity">
     * <intent-filter>
     * <action android:name="com.example.app016.SEND_EMAI"/>
     * <category android:name="android.intent.category.DEFAULT"/>
     * </intent-filter>
     * </activity>
     *
     * @param
     * @return
     */
//    public Intent mapURI(Intent intent) {
//        // already specify a class, no need to map url
//        if (intent.getComponent() != null)
//            return intent;
//            //else if(intent.getAction()!=null)
//            //    return intent;
//        else {
//            Uri uri = intent.getData();
//            if (uri == null)
//                return intent;
//            else if (uri.getScheme() == null)
//                return intent;
//            else if (!(getPrimaryScheme().equalsIgnoreCase(uri.getScheme())))
//                return intent;
//            else {
//                intent.setData(null);   //这里是先不考虑外部URL拦截的情况
//                String actionName = (uri.getHost() + uri.getPath()).replace("/", ".");  //解决action android:name 字符问题
//                intent.setAction(actionName);
//                return intent;
//            }
//        }
//    }

    public static synchronized BaseApplication context() {
        return (BaseApplication) _context;
    }


    public static Resources resources() {
        return _resource;
    }

    // 注册App异常崩溃处理器
    private void registerUncaughtExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler());
    }
}
