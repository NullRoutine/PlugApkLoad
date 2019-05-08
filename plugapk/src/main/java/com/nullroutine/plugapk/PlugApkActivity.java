package com.nullroutine.plugapk;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by tang.wangqiang on 2019/5/7.
 */

public class PlugApkActivity extends BaseActivity {

    @BindView(R.id.tv_notice)
    TextView tvNotice;
    @BindView(R.id.btn)
    Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plug);
//        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mProxyActivity, "我被点击了", Toast.LENGTH_SHORT).show();
//            }
//        });
        tvNotice.setText("我被替换了");
    }

    @OnClick({R.id.tv_notice, R.id.btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_notice:
                break;
            case R.id.btn:
                Toast.makeText(mProxyActivity, "我被点击了", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
