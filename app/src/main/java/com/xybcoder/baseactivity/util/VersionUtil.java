package com.xybcoder.baseactivity.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dell on 2016/5/9.
 * 版本更新工具类
 */
public class VersionUtil {
    /**
     * 弹出对话框通知用户更新程序
     * @param context
     * @param url
     */
    public void showUpdataDialog(final Context context,final String url) {
        AlertDialog.Builder builer = new AlertDialog.Builder(context) ;
        builer.setTitle("版本升级");
        builer.setMessage("亲，可以选择在wifi条件下更新");
        //当点确定按钮时从服务器上下载 新的apk 然后安装
        builer.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                downLoadApk(context,url);
            }
        });
        //当点取消按钮时进行登录
        builer.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builer.create();
        dialog.show();
    }


    /*
     * 从服务器中下载APK
     */
    public  void downLoadApk(final Context context, final String url) {
        final ProgressDialog pd;    //进度条对话框
        pd = new  ProgressDialog(context);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setTitle("正在下载");
        pd.setMessage("请稍后....");
        pd.setProgress(0);
        pd.show();
        new Thread(){
            @Override
            public void run() {
                try {
                    File file =getFileFromServer(url, pd);
                    sleep(3000);
                    installApk(context,file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                   //todo 下载失败
                    e.printStackTrace();
                    pd.dismiss();
                }
            }}.start();
    }


    /**
     * 从服务器获取文件
     * @param path
     * @param pd
     * @return
     * @throws Exception
     */
    public  File getFileFromServer(String path,ProgressDialog pd) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            URL url = new URL(path);
            HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            pd.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();
            if (is != null) {
                File file = new File(Environment.getExternalStorageDirectory(), "updata.apk");
                FileOutputStream fos = new FileOutputStream(file);
                BufferedInputStream bis = new BufferedInputStream(is);
                byte[] buffer = new byte[1024];
                int len;
                int total = 0;
                while ((len = bis.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                    total += len;
                    //获取当前下载量,这里就是关键的实时更新进度
                    pd.setProgress(total);
                }
                fos.close();
                bis.close();
                is.close();
                return file;
            }else{
                return null;
            }
        }
        else{
            return null;
        }
    }

    /**
     * 安装apk
     * @param file
     */
    public void installApk(Context context,File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

}
