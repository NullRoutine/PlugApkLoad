package com.nullroutine.plugapkload;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nullroutine.loadlib.PluginManager;
import com.nullroutine.loadlib.ProxyActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PluginManager.getInstance().init(this);
        final PrinterShareUtil printerShareUtil = new PrinterShareUtil(this);
        findViewById(R.id.btn_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String apk = printerShareUtil.copyApkFile(MainActivity.this, "plugapk.apk", printerShareUtil.getFilePath(MainActivity.this));
                Log.e("TAG", printerShareUtil.getFilePath(MainActivity.this));
//                Log.e("TAG", apk);
                PluginManager.getInstance().loadApk(apk);
            }
        });
        findViewById(R.id.btn_plug).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 点击跳往插件app的activity，一律跳转到PRoxyActivity
                 */
                Intent intent = new Intent(MainActivity.this, ProxyActivity.class);
                intent.putExtra("className", "com.nullroutine.plugapk.PlugApkActivity");
                startActivity(intent);
            }
        });
    }
}
