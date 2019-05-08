package com.nullroutine.loadlib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * 插件管理类
 * Created by tang.wangqiang on 2019/5/7.
 */

public class PluginManager {

    @SuppressLint("StaticFieldLeak")
    private static volatile PluginManager instance = new PluginManager();

    public static PluginManager getInstance() {
        return instance;
    }

    private Context mContext;
    private PlugApkModel mPlugApkModel;

    public PlugApkModel getmPlugApkModel() {
        return mPlugApkModel;
    }

    public void init(Context context) {
        this.mContext = context.getApplicationContext();//防止内存泄漏
        Log.e("TAG", mContext.toString());
    }

    public void loadApk(String apkPath) {
        PackageInfo packageInfo = mContext.getPackageManager().getPackageArchiveInfo(apkPath,
                PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES);
//        Log.e("TAG", packageInfo.toString());
        if (packageInfo == null) {
            return;
        }

        DexClassLoader dexClassLoader = createDexClassLoader(apkPath);
        AssetManager am = createAssetManager(apkPath);
        Resources resources = createResource(am);
        mPlugApkModel = new PlugApkModel(packageInfo, resources, dexClassLoader);
    }

    private Resources createResource(AssetManager assetManager) {
        return new Resources(assetManager, mContext.getResources().getDisplayMetrics(), mContext.getResources().getConfiguration());
    }

    private AssetManager createAssetManager(String apkPath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            @SuppressLint("PrivateApi")
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            method.invoke(assetManager, apkPath);
            return assetManager;
        } catch (Exception e) {
        }
        return null;
    }

    private DexClassLoader createDexClassLoader(String apkPath) {
        File file = mContext.getDir("dex", Context.MODE_PRIVATE);
        return new DexClassLoader(apkPath, file.getAbsolutePath(), null, mContext.getClassLoader());
    }
}
