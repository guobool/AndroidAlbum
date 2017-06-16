package com.clock.album.model;

import android.os.Environment;

import java.io.File;

/**
 * 目录管理器
 * <p/>
 * Created by Clock on 2016/5/28.
 */
public class FolderManager {

    /**
     * 应用程序在SD卡上的主目录名称
     */
    private final static String APP_FOLDER_NAME = "album";
    /**
     * 存放闪退日志目录名
     */
    private final static String CRASH_LOG_FOLDER_NAME = "crash";

    private FolderManager() {
    }

    /**
     * 获取app在sd卡上的主目录
     *
     * @return 成功则返回目录，失败则返回null
     */
    public static File getAppFolder() {
        // 首先判断SD卡是否被挂载
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            // 在应用的目录下创建一个album目录
            File appFolder = new File(Environment.getExternalStorageDirectory(), APP_FOLDER_NAME);
            return createOnNotFound(appFolder);//

        } else {
            return null;
        }
    }

    /**
     * 获取闪退日志存放目录
     *
     * @return
     */
    public static File getCrashLogFolder() {
        File appFolder = getAppFolder();
        if (appFolder != null) {

            File crashLogFolder = new File(appFolder, CRASH_LOG_FOLDER_NAME);
            return createOnNotFound(crashLogFolder);
        } else {
            return null;
        }
    }

    /**
     * 创建目录，存在则直接返回。
     *
     * @param folder
     * @return 创建成功则返回目录，失败则返回null
     */
    private static File createOnNotFound(File folder) {
        if (folder == null) { // 没有要创建的目录，则直接返回
            return null;
        }

        if (!folder.exists()) { // 不存在则先创建目录
            folder.mkdirs();
        }

        if (folder.exists()) { // 创建成功则返回
            return folder;
        } else {
            return null; // 失败返回空。
        }
    }
}
