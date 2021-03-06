package com.wsg.xsybbs.application;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;

import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;

import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;
import com.wsg.xsybbs.util.StaticClass;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.update.BmobUpdateAgent;

/**
 * Created by wsg
 * on         2018/6/29.
 * function:   全局application的封装
 */
public class MyApplication extends Application {
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化bugly
        CrashReport.initCrashReport(getApplicationContext(), StaticClass.BUGLY_APP_ID, true);
        //初始化Bmob
        Bmob.initialize(this, StaticClass.BMOB_APP_ID);

        //自动创建APP更新表，注意只能使用一次
        //BmobUpdateAgent.initAppVersion();

        //wifi/3g/3g/4g均可更新
        BmobUpdateAgent.setUpdateOnlyWifi(false);
        //开启更新
        BmobUpdateAgent.update(this);


        EMOptions options = new EMOptions();
       // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(true);
        EaseUI.getInstance().init(this, options);




        //7.0+
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();





       // 2019/1/5 集成leakcanary

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        LeakCanary.install(this);






    }
}
