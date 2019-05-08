package com.nullroutine.loadlib;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * 代理插件app生命周期
 * Created by tang.wangqiang on 2019/5/7.
 */

public class ProxyActivity extends Activity {
    private String mClassName;
    private PlugStandard mPlugStandard = null;
    private PlugApkModel mPlugApkModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClassName = getIntent().getStringExtra("className");
        mPlugApkModel = PluginManager.getInstance().getmPlugApkModel();
        if (mPlugApkModel == null) {
            throw new RuntimeException("no Apk Load");
        }
        try {
            Class<?> tClass = mPlugApkModel.mDexClassLoader.loadClass(mClassName);
            Object object = tClass.newInstance();
            if (object instanceof PlugStandard) {
                mPlugStandard = (PlugStandard) object;
                mPlugStandard.attach(this);
                Bundle bundle = new Bundle();
                mPlugStandard.onCreate(bundle);
            }
        } catch (Exception e) {

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPlugStandard.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mPlugStandard.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlugStandard.onDestroy();
    }

    @Override
    public ClassLoader getClassLoader() {
        //不用系统的ClassLoader，用dexClassLoader加载
        return mPlugApkModel != null ? mPlugApkModel.mDexClassLoader : super.getClassLoader();
    }

    @Override
    public Resources getResources() {
        //不用系统的resources，自己实现一个resources
        return mPlugApkModel != null ? mPlugApkModel.mResources : super.getResources();
    }

    @Override
    public AssetManager getAssets() {
        return mPlugApkModel != null ? mPlugApkModel.mAssetManager : super.getAssets();
    }
}
