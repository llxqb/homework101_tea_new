package com.shushan.thomework101.mvp.utils;

import android.app.Activity;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;

/**
 * 文件操作
 */
public class FileUtils {
    /**
     * 转换文件大小,指定转换的类型
     *
     * @param fileS
     * @param sizeType
     * @return
     */
    private static double FormetFileSize(long fileS, int sizeType) {
        DecimalFormat df = new DecimalFormat("#.00");
        double fileSizeLong = 0;
        switch (sizeType) {
            case 1:
                fileSizeLong = Double.valueOf(df.format((double) fileS));
                break;
            case 2:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1024));
                break;
            case 3:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1048576));
                break;
            case 4:
                fileSizeLong = Double.valueOf(df
                        .format((double) fileS / 1073741824));
                break;
            default:
                break;
        }
        return fileSizeLong;
    }

    /**
     * 获取指定文件大小
     *
     * @param
     * @return
     */
    private static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
            Log.e("获取文件大小", "文件不存在!");
        }
        return size;
    }

    /**
     * 获取指定文件夹
     *
     * @param f
     * @return
     */
    private static long getFileSizes(File f) throws Exception {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSizes(flist[i]);
            } else {
                size = size + getFileSize(flist[i]);
            }
        }
        return size;
    }

    /**
     * 获取文件指定文件的指定单位的大小
     *
     * @param filePath 文件路径
     * @param sizeType 获取大小的类型1为B、2为KB、3为MB、4为GB
     * @return double值的大小
     */
    public static double getFileOrFilesSize(String filePath, int sizeType) {
        File file = new File(filePath);
        long blockSize = 0;
        try {
            if (file.isDirectory()) {
                blockSize = getFileSizes(file);
            } else {
                blockSize = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("获取文件大小", "获取失败!");
        }
        return FormetFileSize(blockSize, sizeType);
    }

    public static void upLoadVideo(Activity activity, String filePath, String newFilePath) {
//        VideoCompress task = VideoCompress.compressVideo(filePath, newFilePath, new VideoCompress.CompressListener() {
//            @Override
//            public void onStart() {
//                //Start Compress
//            }
//
//            @Override
//            public void onSuccess() {
//                //Finish successfully
//
//            }
//
//            @Override
//            public void onFail() {
//                //Failed
//            }
//
//            @Override
//            public void onProgress(float percent) {
//                //Progress
//            }
//        });
    }

    /**
     * 获取文件的后缀名
     *
     * @param filePath 文件路径
     * @return
     */
    public static String getNameSuffix(String filePath) {

        String fileSuffix = filePath.substring(filePath.lastIndexOf(".") + 1);
        return fileSuffix;

    }

}
