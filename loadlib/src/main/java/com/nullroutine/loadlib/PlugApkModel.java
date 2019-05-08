package com.nullroutine.loadlib;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

/**
 * 插件apk对象
 * Created by tang.wangqiang on 2019/5/7.
 */

public class PlugApkModel {
    public PackageInfo mPackageInfo;
    public Resources mResources;
    public AssetManager mAssetManager;
    public DexClassLoader mDexClassLoader;

    public PlugApkModel(PackageInfo mPackageInfo, Resources mResources, DexClassLoader mDexClassLoader) {
        this.mPackageInfo = mPackageInfo;
        this.mResources = mResources;
        this.mAssetManager = mResources.getAssets();
        this.mDexClassLoader = mDexClassLoader;
    }
}
