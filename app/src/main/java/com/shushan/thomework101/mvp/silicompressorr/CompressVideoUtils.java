package com.shushan.thomework101.mvp.silicompressorr;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.net.URISyntaxException;

/**
 * created by  sqj  on 2018/12/27 11:24
 * 视频压缩工具类
 */
public class CompressVideoUtils {


    static int mTargetMaxSize = 450;// 目标最大控制size 超过就按照这个比例压缩
    static int mTargetMinSize = 20;// 目标最小控制size 超过这个值才进行压缩
    static int mMaxBit = 1000; //目标最小控制size的压缩率
    static int mMinBit = 400;//目标最大控制size的压缩率

    static Context mContext;
    private static String filePath;
    private static RefreshVideoDialog handler;
    private static boolean isCompressed;//是否有压缩


    public static CompressVideoTask getCompressVideoTask(Context context, int targetMaxSize
            , int targetMinSize, int maxBit, int minBit, OnCompressListener compressListener) {
        mTargetMaxSize = 450;
        mTargetMinSize = 20;
        mMaxBit = 1000;
        mMinBit = 400;
        getHandler();
        return new CompressVideoTask(context, targetMaxSize
                , targetMinSize, maxBit, minBit, compressListener);
    }

    private static void getHandler() {
        if (handler == null) {
            handler = new RefreshVideoDialog();
        }
    }

    private static ProgressDialog mVideoCompressProgressDialog;

    public static class RefreshVideoDialog extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mVideoCompressProgressDialog = ProgressDialog.show(mContext, "", "视频压缩中...", true);
                    break;
                case 1:
                    if (mVideoCompressProgressDialog != null) {
                        mVideoCompressProgressDialog.dismiss();
                        mVideoCompressProgressDialog = null;
                    }
                    break;
                case 2:
                    if (mVideoCompressProgressDialog != null) {
                        mVideoCompressProgressDialog.dismiss();
                        mVideoCompressProgressDialog = null;
                    }
                    break;
            }
        }
    }


    public static class CompressVideoTask extends AsyncTask<String, Float, String> {
        public CompressVideoTask(Context context) {
            mContext = context;
        }

        public CompressVideoTask(Context context, int targetMaxSize
                , int targetMinSize, int maxBit, int minBit, OnCompressListener compressListener) {
            mOnCompressListener = compressListener;
            mTargetMaxSize = targetMaxSize == 0 ? mTargetMaxSize : targetMaxSize;
            mTargetMinSize = targetMinSize == 0 ? mTargetMinSize : targetMinSize;
            mMaxBit = maxBit == 0 ? mMaxBit : maxBit;
            mMinBit = minBit == 0 ? mMinBit : minBit;
            mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... paths) {
            File imageFile = new File(paths[0]);
            float length = imageFile.length() / 1024f / 1024f; // kb
            filePath = null;
            if (length > mTargetMinSize) {
                isCompressed = true;
                publishProgress();
                handler.sendEmptyMessage(0);
                // 大于限制大小才压缩
                try {
                    // 450m 最高使用400*1000 比特率
                    // 0m使用1000 *1000 比特率
                    // 1000 - (600  / 450)*size
                    int bitrate = (int) (mMaxBit - ((mMaxBit - mMinBit) / mTargetMaxSize) *
                            (new File(paths[0]).length() / 1024 / 1024));
                    if (bitrate < mMinBit) {
                        bitrate = mMinBit;
                    }
                    filePath = SiliCompressor.with(mContext)
                            .compressVideo(paths[0], paths[1], 0, 0, bitrate * 1000);

                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            } else {
                filePath = paths[0];
                isCompressed = false;
            }

            return filePath;

        }

//        @Override
//        protected void onProgressUpdate(Float... percent) {
//            super.onProgressUpdate(percent);
//            if (mOnCompressListener != null) {
//                Log.e("UploadVideoActivity", "percent:" + percent);
//                mOnCompressListener.onProgress(percent[0]);
//            }
//        }


        @Override
        protected void onPostExecute(String compressedFilePath) {
            super.onPostExecute(compressedFilePath);
            if (mOnCompressListener != null) {
                if (!TextUtils.isEmpty(compressedFilePath)) {
                    File imageFile = new File(compressedFilePath);
                    float length = imageFile.length() / 1024f; // Size in KB
                    String value;
                    if (length >= 1024)
                        value = length / 1024f + " MB";
                    else
                        value = length + " KB";
                    Log.e("UploadVideoActivity", "value:" + value);
                    handler.sendEmptyMessage(1);
                    mOnCompressListener.onFinishCompress(compressedFilePath, isCompressed);
                } else {
                    handler.sendEmptyMessage(2);
                }
            }
        }

        private OnCompressListener mOnCompressListener;


    }

    public interface OnCompressListener {
//        void onStart();
//
//        void onFail();
//
//        void onProgress();

        void onFinishCompress(String path, boolean isCompressed);
    }

}

