package com.zxin.basemodule.util;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import java.util.List;

public class ImageUtils {
    private static final String TAG = "ImageUtils";

    /**
     * 由于导航信息中使用的图标，在日间/夜间这两种模式下显示不同的颜色，但只有一套适用于日间模式的图片，
     * 因此需要在夜间模式下改变图标的颜色
     *
     * @param imageView route guidance icon view
     * @param color expected color
     */
    public static void setImageColor(final ImageView imageView, final int color) {
        if (imageView != null) {
            ColorFilter cf = new ColorMatrixColorFilter(generateColorMatrixForSpecificColor(color));
            imageView.setColorFilter(cf);
        }
    }

    /**
     * 若需要对多个ImageView施加同一种变色处理，可以生成一个ColorFilter对象，
     * 然后对多个ImageView进行同样的设置
     *
     * @param imageViews multiple route guidance icon view
     * @param color expected color
     */
    public static void setImageColor(final List<ImageView> imageViews, final int color) {
        if (imageViews != null) {
            ColorFilter cf = generateColorFilterForSpecificColor(color);
            for (ImageView eachIv : imageViews) {
                eachIv.setColorFilter(cf);
            }
        }
    }

    public static void setDrawableColor(final Drawable d, final int color) {
        if (d != null) {
            ColorFilter cf = generateColorFilterForSpecificColor(color);
            d.setColorFilter(cf);
        }
    }

    private static void printRgb(float[] rgba) {
        Log.d(TAG, String.format("printRgb: (%.0f, %.0f, %.0f)", rgba[0], rgba[1], rgba[2]));
    }

    private static float[] splitColorToRGB(final int color) {
        final int unit = 0xff;

        final float a = (color >> 24) & unit;
        final float b =  color & unit;
        final float g = (color >> 8) & unit;
        final float r = (color >> 16) & unit;

        return new float[]{ r, g, b, a };
    }

    private static ColorMatrix generateColorMatrixForSpecificColor(final int targetColor) {
        float[] rgba = splitColorToRGB(targetColor);
        float[] m  = {
                0, 0, 0, 0, rgba[0],
                0, 0, 0, 0, rgba[1],
                0, 0, 0, 0, rgba[2],
                0, 0, 0, rgba[3] / 255, 0,
        };

        return new ColorMatrix(m);
    }

    private static ColorFilter generateColorFilterForSpecificColor(final int targetColor) {
        ColorMatrix cm = generateColorMatrixForSpecificColor(targetColor);
        return new ColorMatrixColorFilter(cm);
    }

    public static final int CROP_LEFT_TOP_CORNER = 0x1;
    public static final int CROP_RIGHT_TOP_CORNER = 0x1 << 1;
    public static final int CROP_LEFT_BOTTOM_CORNER = 0x1 << 2;
    public static final int CROP_RIGHT_BOTTOM_CORNER = 0x1 << 3;

    public static Bitmap cornerCrop(final Bitmap srcBm, final int cornerRadius, final int cropArgs) {
        if (srcBm == null || cornerRadius <= 0) {
            return srcBm;
        }

        final int w = srcBm.getWidth();
        final int h = srcBm.getHeight();
        Bitmap result = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        Paint paint = new Paint();
        paint.setShader(new BitmapShader(srcBm, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);

        RectF drawArea = new RectF();
        Canvas canvas = new Canvas(result);
        final float r = cornerRadius;

        drawArea.set(0, 0, w, h);
        canvas.drawRoundRect(drawArea, r, r, paint);

        if ((cropArgs & CROP_LEFT_TOP_CORNER) == CROP_LEFT_TOP_CORNER) {
            drawArea.set(0, 0, r, r);
            canvas.drawRect(drawArea, paint);
        }
        if ((cropArgs & CROP_RIGHT_TOP_CORNER) == CROP_RIGHT_TOP_CORNER) {
            drawArea.set(w - r, 0, w, r);
            canvas.drawRect(drawArea, paint);
        }
        if ((cropArgs & CROP_LEFT_BOTTOM_CORNER) == CROP_LEFT_BOTTOM_CORNER) {
            drawArea.set(0, h - r, r, h);
            canvas.drawRect(drawArea, paint);
        }
        if ((cropArgs & CROP_RIGHT_BOTTOM_CORNER) == CROP_RIGHT_BOTTOM_CORNER) {
            drawArea.set(w - r, h - r, w, h);
            canvas.drawRect(drawArea, paint);
        }

        return result;
    }

    public static Bitmap cropTopCorners(final Bitmap srcBm, final int cornerRadius) {
        int cropArgs = CROP_LEFT_BOTTOM_CORNER | CROP_RIGHT_BOTTOM_CORNER;
        return cornerCrop(srcBm, cornerRadius, cropArgs);
    }

    public static Bitmap cropBottomCorners(final Bitmap srcBm, final int cornerRadius) {
        int cropArgs = CROP_LEFT_TOP_CORNER | CROP_RIGHT_TOP_CORNER;
        return cornerCrop(srcBm, cornerRadius, cropArgs);
    }

    public static Bitmap cropAllCourners(final Bitmap srcBm, final int cornerRadius) {
        if (srcBm == null || cornerRadius <= 0) {
            return srcBm;
        }

        final int w = srcBm.getWidth();
        final int h = srcBm.getHeight();
        Bitmap result = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        Paint paint = new Paint();
        paint.setShader(new BitmapShader(srcBm, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);

        new Canvas(result).drawRoundRect(
                new RectF(0, 0, w, h),
                cornerRadius, cornerRadius, paint);

        return result;
    }
}
