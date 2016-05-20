package com.library.common.util;

import com.library.common.entity.PatchResult;

/**
 * Created by dell on 2016/5/20.
 */
public class PatchUtil {

    /**
     * patch old apk and patch file to new apk
     *
     * @param oldApkPath
     * @param patchPath
     * @param newApkPath
     * @return
     */
    public static native PatchResult patch(String oldApkPath, String patchPath, String newApkPath);
}
