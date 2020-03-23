package com.zxin.basemodule.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StatFs;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class SDCardUtils {

    private static final double MIN_FREE_SPACE = 10;

    public static final int SDCARD_NORMAL = 0;
    public static final int SDCARD_FULL = 1;
    public static final int SDCARD_ERROR = 2;
    public static final int SDCARD_NOTFOUND = 3;

    public static final int MIN_FREE_SIZE = 1024 * 1024 * 15;

    // SD卡可用空间小于此值时,提示用户
    public static final int MIN_CACHE_FREE_SIZE = 1024 * 1024 * 20;


    public static boolean writeTestFileToSdcard(String path) {
        boolean flag = false;
        try {
            File testFile = new File(path + "/test.0");
            if (testFile.exists()) {
                testFile.delete();
            }
            flag = testFile.createNewFile();
            if (testFile.exists()) {
                testFile.delete();
            }
        } catch (IOException e) {
//            LogUtil.e("", e.toString());
        }
        return flag;
    }

    public static int handleOfflinePathError(long sizeInByte, boolean bBuffer) {
        int state = checkOfflinePathAvailable();
        if (state == SDCARD_NORMAL) {
            StatFs sfs = getOfflinePathSize();
            long freeSize = sfs.getBlockSize();
            long freeBlock = sfs.getFreeBlocks();
            freeSize = freeSize * freeBlock;
            if (freeSize < (bBuffer ? MIN_FREE_SIZE : 0) + sizeInByte) {
                // Log.e(TAG, "SDCARD_FULL");
                state = SDCARD_FULL;
            }
        }
        return state;
    }

    private static int checkOfflinePathAvailable() {
        File file = new File(ZxinUtil.getWorkDirectory());

        if (file.exists() && file.canRead() && file.canWrite()) {
            return SDCARD_NORMAL;
        } else {
            return SDCARD_NOTFOUND;
        }
    }

    public static int getSdcardState() {
        // Log.e(TAG, "getSdcardState");
        String status = Environment.getExternalStorageState();

        if (status == null || Environment.MEDIA_BAD_REMOVAL.equals(status)) {
            // Log.e(TAG, "MEDIA_BAD_REMOVAL");
            return SDCARD_ERROR;
        } else if (Environment.MEDIA_CHECKING.equals(status)) {
            // Log.e(TAG, "MEDIA_CHECKING");
        } else if (Environment.MEDIA_MOUNTED.equals(status)) {
            // OK
            // Windows Media Sync
            // Motorola Phone Portal
            // Log.e(TAG, "MEDIA_MOUNTED");
            return SDCARD_NORMAL;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(status)) {
            // Log.e(TAG, "MEDIA_MOUNTED_READ_ONLY");
            return SDCARD_ERROR;
        } else if (Environment.MEDIA_NOFS.equals(status)) {
            // Log.e(TAG, "MEDIA_NOFS");
            return SDCARD_ERROR;
        } else if (Environment.MEDIA_REMOVED.equals(status)) {
            // Remove Sdcard
            // Log.e(TAG, "MEDIA_REMOVED");
            return SDCARD_NOTFOUND;
        } else if (Environment.MEDIA_SHARED.equals(status)) {
            // USB Mass Storage
            // Log.e(TAG, "MEDIA_SHARED");
            return SDCARD_NOTFOUND;
        } else if (Environment.MEDIA_UNMOUNTABLE.equals(status)) {
            // Log.e(TAG, "MEDIA_UNMOUNTABLE");
            return SDCARD_ERROR;
        } else if (Environment.MEDIA_UNMOUNTED.equals(status)) {
            // Log.e(TAG, "MEDIA_UNMOUNTED");
            return SDCARD_NOTFOUND;
        }
        return SDCARD_NORMAL;
    }

    private static StatFs getOfflinePathSize() {
        return new StatFs(ZxinUtil.getWorkDirectory());
    }

    /**
     * 保存Bitmap到指定文件
     *
     * @param bitmap   图片
     * @param dir      目录
     * @param fileName 文件名
     * @return 是否保存成功
     */
    public static boolean saveBitmapToFile(@NonNull Bitmap bitmap, @NonNull String dir, @NonNull String fileName) {
        if (bitmap == null || dir == null || fileName == null) {
            return false;
        }
        File path = new File(dir);
        if (!path.exists()) {
            boolean b = path.mkdirs();
            if (!b) {
                return false;
            }
        }
        File bmFile = new File(dir, fileName);
        if (bmFile.exists()) {
            bmFile.delete();
            bmFile = new File(dir, fileName);
        }
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(bmFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream);
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*****
     * 获取文件信息
     * @param filePath 文件路径
     * @return
     */
    public static Bitmap getFileToBitmap(@NonNull String filePath) {
        if (isNull(filePath)) {
            return null;
        }
        Bitmap bitmap = null;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            if (fis == null) {
                return null;
            }
            bitmap = BitmapFactory.decodeStream(fis);
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /****
     * 转换InputStream 为Bitmap
     * @param is
     * @return
     */
    public static Bitmap getBitmapByInputStream(InputStream is) throws IOException {
        if (is == null) {
            return null;
        }
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        is.close();
        return bitmap;
    }

    /*****
     * 删除文件
     * @param filePath 文件路径
     * @return
     */
    public static boolean removeFile(String filePath, String fileName) {
        if (isNull(filePath) || isNull(fileName)) {
            return false;
        }
        //删除本地文件
        File file = new File(filePath, fileName);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    /****
     * 是否为空
     * @param str
     * @return
     */
    private static boolean isNull(String str) {
        return str == null || str.trim().equals("") || str.trim().equals("null");
    }
}