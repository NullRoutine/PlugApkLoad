package com.nullroutine.loadlib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * 管理插件生命周期
 * Created by tang.wangqiang on 2019/5/7.
 */

public interface PlugStandard {

    int FROM_EXTERNAL = 0;//从宿主apk过来
    int FROM_INTERNAL = 1;//自己独立

    void setContentView(int layoutResID);

    <T extends View> T findViewById(int id);

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onDestroy();

    void onPause();

    void onSaveInstanceState(Bundle outState);

    /**
     * 需要宿主app注入给插件app上下文
     */
    void attach(Activity activity);

    void onActivityResult(int requestCode, int resultCode, Intent data);
}
