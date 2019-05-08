package com.nullroutine.plugapkload;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 拷贝文件
 * Created by tang.wangqiang on 2018/9/26.
 */

public class FileUtil {
    private static final String FILE_NAME = "plugapk.apk";
    private Context mContext;
    public static final int REQUEST_CODE_UNKNOWN_APP = 10018;

    public FileUtil(Context context) {
        this.mContext = context;
    }

    /**
     * 获取文件路径
     *
     * @param context
     * @return
     */
    public String getFilePath(Context context) {
        File file = context.getExternalFilesDir(null);
        if (file != null && file.exists()) {
            return file.getAbsolutePath() + File.separator + FILE_NAME;
        } else {
            file = context.getFilesDir();
            return file.getAbsolutePath() + File.separator + FILE_NAME;
        }
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1://拷贝成功（安装）
                    Toast.makeText(mContext, "拷贝成功", Toast.LENGTH_SHORT).show();
                    break;
                case 2://拷贝失败
                    Toast.makeText(mContext, "拷贝失败", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }
    });

    /**
     * 安装新版本应用
     */
    private void installApp(String filePath) {
        File appFile = new File(filePath);
        if (!appFile.exists()) {
            return;
        }
        // 跳转到新版本应用安装页面
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0
            Uri apkUri = FileProvider.getUriForFile(mContext, BuildConfig.APPLICATION_ID, appFile);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(appFile), "application/vnd.android.package-archive");
        }
        mContext.startActivity(intent);
    }

    /**
     * 拷贝文件
     *
     * @param context
     * @param filePath
     * @param copyFilePath
     * @return
     */
    public String copyApkFile(final Context context, final String filePath, final String copyFilePath) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
        try {
            InputStream inputStream = context.getAssets().open(filePath);
            File file = new File(copyFilePath);
            boolean isDelete = false;
            if (file.exists()) { //删除老文件
                isDelete = file.delete();
            }
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i;
            while ((i = inputStream.read(temp)) != -1) {
                fileOutputStream.write(temp, 0, i);
            }
            inputStream.close();
            return file.getAbsolutePath();
//            handler.sendEmptyMessage(1);
        } catch (IOException e) {
            e.printStackTrace();
//            handler.sendEmptyMessage(2);
        }
//            }
//        }).start();
        return "";
    }

}
