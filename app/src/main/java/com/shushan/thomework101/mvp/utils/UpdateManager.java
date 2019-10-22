package com.shushan.thomework101.mvp.utils;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shushan.thomework101.R;
import com.shushan.thomework101.entity.response.VersionUpdateResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by 佳乐 on 2015/1/21.
 */
public class UpdateManager {
    private Context mContext;
    //提示语
    private String updateMsg = "有最新的软件包哦，快下载吧~";

    //返回的安装包url
    private String apkUrl = "";

    private AlertDialog noticeDialog;

    private Dialog downloadDialog;
    /* 下载包安装路径 */
    private static final String savePath = "/sdcard/homework101_teacher/";

    private static final String saveFileName = savePath + "zuoye101_teacher.apk";

    /* 进度条与通知ui刷新的handler和msg常量 */
    public ProgressBar mProgress;


    private static final int DOWN_UPDATE = 1;

    private static final int DOWN_OVER = 2;

    private int progress;

    private Thread downLoadThread;

    private boolean interceptFlag = false;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWN_UPDATE:
                    mProgress.setProgress(progress);
                    break;
                case DOWN_OVER:
                    installApk();
                    break;
                default:
                    break;
            }
        }
    };

    private VersionUpdateResponse mVersionUpdateResponse;

    public UpdateManager(Context context, VersionUpdateResponse versionUpdateResponse) {
        this.mContext = context;
        this.mVersionUpdateResponse = versionUpdateResponse;
        apkUrl = versionUpdateResponse.getUpdate_url();
    }

    //外部接口让主Activity调用
    public void checkUpdateInfo() {
//        showNoticeDialog();
        showUpdateDialog();
    }


    private void showNoticeDialog() {
//        if (noticeDialog == null) {
//            Builder builder = new Builder(mContext);
//            builder.setTitle("软件有更新");
//            builder.setMessage(updateMsg);
//            builder.setPositiveButton("下载", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                    showDownloadDialog();
//                }
//            });
//            builder.setNegativeButton("以后再说", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            });
//            noticeDialog = builder.create();
//        } else {
//            if (noticeDialog.isShowing()) {
//                return;
//            }
//
//        }
//        noticeDialog.show();
//        noticeDialog.setCancelable(false);//按返回键和空白处不消失
//        showDownloadDialog();

    }

    private void showDownloadDialog() {
        Builder builder = new Builder(mContext);
        builder.setTitle("软件版本更新");

        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.update_no_psw_dialog, null);
        mProgress = v.findViewById(R.id.progress);
        TextView versionText = v.findViewById(R.id.update_dialog_version_tv);
        TextView descText = v.findViewById(R.id.update_dialog_context_tv);
        versionText.setText(mVersionUpdateResponse.getApp_version());
        descText.setMovementMethod(ScrollingMovementMethod.getInstance());
        descText.setText(getVersionDes(mVersionUpdateResponse));

        builder.setView(v);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                interceptFlag = true;
            }
        });
        downloadDialog = builder.create();
        downloadDialog.setCancelable(false);
        downloadDialog.show();

        downloadApk();
    }


    private void showUpdateDialog() {
        final Dialog updateDialog = new Dialog(mContext, R.style.Dialog_Translucent_Background);
        updateDialog.setContentView(R.layout.update_no_psw_dialog);
        updateDialog.setCancelable(false);

        mProgress = updateDialog.findViewById(R.id.progress);
        TextView versionText = updateDialog.findViewById(R.id.update_dialog_version_tv);
        versionText.setText(mVersionUpdateResponse.getApp_version());
        TextView descText = updateDialog.findViewById(R.id.update_dialog_context_tv);
        descText.setMovementMethod(ScrollingMovementMethod.getInstance());
        descText.setText(getVersionDes(mVersionUpdateResponse));

        Button btnCancel = updateDialog.findViewById(R.id.update_dialog_cancel_btn);
        btnCancel.setOnClickListener(v -> updateDialog.dismiss());

        Button btnUpdate = updateDialog.findViewById(R.id.update_dialog_update_btn);
        btnUpdate.setOnClickListener(v -> {
            mProgress.setVisibility(View.VISIBLE);
            downloadApk();
        });

        updateDialog.show();
    }


    private Runnable mdownApkRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                URL url = new URL(apkUrl);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();
                int length = conn.getContentLength();
                InputStream is = conn.getInputStream();

                File file = new File(savePath);
                if (!file.exists()) {
                    file.mkdir();
                }
                String apkFile = saveFileName;
                File ApkFile = new File(apkFile);
                FileOutputStream fos = new FileOutputStream(ApkFile);

                int count = 0;
                byte buf[] = new byte[1024];

                do {
                    int numread = is.read(buf);
                    count += numread;
                    progress = (int) (((float) count / length) * 100);
                    //更新进度
                    mHandler.sendEmptyMessage(DOWN_UPDATE);
                    if (numread <= 0) {
                        //下载完成通知安装
                        mHandler.sendEmptyMessage(DOWN_OVER);
                        break;
                    }
                    fos.write(buf, 0, numread);
                } while (!interceptFlag);//点击取消就停止下载.

                fos.close();
                is.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                if (downloadDialog != null) {
                    downloadDialog.dismiss();
                }
                Looper.prepare();
                Toast.makeText(mContext, "资源文件不存在,请稍后重试", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }
    };

    /**
     * 下载apk
     */
    private void downloadApk() {
        downLoadThread = new Thread(mdownApkRunnable);
        downLoadThread.start();
    }


    /**
     * 安装apk
     */
    private void installApk() {
        File apkfile = new File(saveFileName);
        if (!apkfile.exists()) {
            return;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//i.setDataAndType(Uri.parse("file://"+apkfile.toString()),"application/vnd.android.package-archive");
//mContext.startAcitivity(i);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(mContext, "com.shushan.thomework101.FileProvider", apkfile);
            i.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            i.setDataAndType(Uri.fromFile(apkfile), "application/vnd.android.package-archive");
        }
        mContext.startActivity(i);

    }

    private String getVersionDes(VersionUpdateResponse versionUpdateResponse) {
        List<String> versionContentList = versionUpdateResponse.getApp_des();
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < versionContentList.size(); i++) {
            stringBuffer.append(i + 1).append(":");
            stringBuffer.append(versionContentList.get(i));
            if (i < (versionContentList.size() - 1)) {
                stringBuffer.append("\n");
            }
        }
        return stringBuffer.toString();
    }

}
