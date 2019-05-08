package com.nullroutine.plugapk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nullroutine.loadlib.PlugStandard;

import butterknife.ButterKnife;

/**
 * Created by tang.wangqiang on 2019/5/7.
 */

public abstract class BaseActivity extends Activity implements PlugStandard {
    protected Activity mProxyActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (mProxyActivity != null) {
            ButterKnife.bind(mProxyActivity);
        } else {
//            ButterKnife.bind(this);
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        if (mProxyActivity == null) {
            super.setContentView(layoutResID);
        } else {
            mProxyActivity.setContentView(layoutResID);
        }
    }

    @Override
    public void onStart() {
        if (mProxyActivity != null) {

        } else {
            super.onStart();
        }
    }

    @Override
    public void onResume() {
        if (mProxyActivity != null) {

        } else {
            super.onResume();
        }
    }

    @Override
    public void onDestroy() {
        if (mProxyActivity != null) {

        } else {
            super.onDestroy();
        }
    }

    @Override
    public void onPause() {
        if (mProxyActivity != null) {

        } else {
            super.onPause();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public <T extends View> T findViewById(int id) {
        if (mProxyActivity == null) {
            return super.findViewById(id);
        } else {
            return mProxyActivity.findViewById(id);
        }

    }

    /**
     * 注入
     *
     * @param activity
     */
    @Override
    public void attach(Activity activity) {
        this.mProxyActivity = activity;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
