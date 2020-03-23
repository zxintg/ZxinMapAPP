package com.zxin.basemodule.overlay;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.zxin.basemodule.model.LonLat;
import com.zxin.basemodule.util.CheckUtil;
import com.zxin.basemodule.util.DisplayUtils;

/**
 * Render a item to map view
 *
 * Notice:
 * After render, user click the render map item,
 * user can receive a map item via
 * {@link com.nio.map.view.IMapView.IMapTouchListener#onMapClick(MotionEvent, MapItem)}
 *
 * In bai du engine, use can only get {@link MapItem#uid}
 * Other data will be lost, so if you want get the same data, you need set this object's uid as
 * your data id, after the receive map item, get data id and query your data, that you can get same
 * data, like favorite icon.
 *
 *
 */
public class MapItem implements IMapItem{
    MapItemGroup parent;
    int id;
    String uid;
    String name;
    LonLat position;

    private String tag;

    private Bitmap bitmap;

    private View view;

    public MapItem() {

    }

    public MapItem(String tag, float layerZ) {
        this.tag = tag;
        this.layerZ = layerZ;
    }

    /**
     * Scale the map item bitmap.
     * because the bai bu render will scale origin bitmap.
     */
    private float scale = -1f;

    public final String getTag() {
        return tag;
    }

    public View getView() {
        return view;
    }

    /**
     *  public static final int UNKNOWN = 0;
     *  public static final int BASE_MAP = 1;
     *  public static final int BASE_POI = 2;
     *  public static final int POI_MARKER = 3;
     *  public static final int POI_BKG_MARKER = 4;
     *  public static final int ROUTE = 5;
     *  public static final int CUSTOM_MARKER = 6;
     *  public static final int OPERATING_POI = 7;
     *  public static final int TRAFFIC_UGC_POI = 8;
     *  public static final int ROUTE_START = 9;
     *  public static final int ROUTE_DESTINATION = 10;
     *  public static final int ROUTE_WAYPOINT = 11;
     */
    int itemType = 6;
    int index;
    float layerZ;

    Object ref;

    boolean highlight;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LonLat getPosition() {
        return position;
    }

    public void setPosition(LonLat position) {
        ref = null;
        this.position = position;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public float getLayerZ() {
        return layerZ;
    }

    public void setLayerZ(float layerZ) {
        this.layerZ = layerZ;
    }

    public Object getRef() {
        return ref;
    }

    public void setRef(Object ref) {
        this.ref = ref;
    }

    public MapItemGroup getParent() {
        return parent;
    }

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }

    public boolean isHighlight() {
        return highlight;
    }

    public void setParent(MapItemGroup parent) {
        if (this.parent != null) {
            CheckUtil.throwException("Has set a parent. can't set again");
        }
        this.parent = parent;
    }

    @Override
    public String toString() {
        return getName() + ":id=" + getId() + ", uid=" + getUid() + ", index=" + getIndex()
                + ", layZ=" + getLayerZ() + ", type=" + getItemType();
    }

    public Bitmap getBitmap() {
        return bitmap;
    }


    /**
     * This method will adjust bitmap.
     * after adjust:
     * bitmap's height will be height * 2
     * width not change:
     * <p>
     * Because map view draw bitmap, the default align point is bitmap center.
     * but we need align point is bitmap bottom.
     *
     * @param bitmap
     */
    public void setBitmap(Bitmap bitmap) {
        setBitmap(bitmap, 0);
    }


    public void setBitmap(Bitmap bitmap, boolean autoScale) {
        if (autoScale) {
            setBitmap(bitmap, 0);
        } else {
            this.bitmap = bitmap;
        }
    }


    public static final int ALIGN_POINT_CENTER = 0;
    public static final int ALIGN_POINT_LEFT = 1;
    public static final int ALIGN_POINT_TOP = 2;
    public static final int ALIGN_POINT_RIGHT = 3;
    public static final int ALIGN_POINT_BOTTOM = 4;


    public void setView(View view, int alignPoint) {
        this.view = null;
        setBitmap(DisplayUtils.createBitmapFromView(view), alignPoint);
        this.view = view;
    }

    public void setView(View view, boolean produce) {
        if (!produce) {
            this.view = view;
        } else {
            setView(view, ALIGN_POINT_CENTER);
        }
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    /**
     * Adjust bitmap.
     *
     * @param bitmap
     * @param alignPoint 0: center, 1: left, 2: top, 3:right, 4: bottom
     * @return adjust bitmap.
     */
    public void setBitmap(Bitmap bitmap, final int alignPoint) {
        CheckUtil.require(view == null,
                "Has bean setBitmap via setView, can't set again");
        int bmWidth = bitmap.getWidth();
        int bmHeight = bitmap.getHeight();
        if (scale == -1f) {
            Context context = null;
            if (context != null) {
                Resources res = context.getResources();
                if (res != null) {
                    DisplayMetrics metrics = res.getDisplayMetrics();
                    scale = metrics.density > 1.4f ? 1.4f : metrics.density;
                }
            }
            if (scale == -1f) {
                scale = 1f;
            }
        }
        bmWidth = Math.round(bmWidth / scale);
        bmHeight = Math.round(bmHeight / scale);

        bitmap = Bitmap.createScaledBitmap(bitmap, bmWidth, bmHeight, true);
        if (alignPoint == ALIGN_POINT_CENTER) {
            this.bitmap = bitmap;
            return;
        }
        int width = bmWidth;
        int height = bmHeight;
        int offsetX = 0, offsetY = 0;
        Bitmap reBitmap;
        Canvas canvas;
        switch (alignPoint) {
            case ALIGN_POINT_LEFT:
                width <<= 1;
                offsetX = bmWidth;
                offsetY = 0;
                break;
            case ALIGN_POINT_TOP:
                height <<= 1;
                offsetX = 0;
                offsetY = bmHeight;
                break;
            case ALIGN_POINT_RIGHT:
                width <<= 1;
                offsetX = offsetY = 0;
                break;
            case ALIGN_POINT_BOTTOM:
                height <<= 1;
                offsetX = offsetY = 0;
                break;
            default:
                CheckUtil.throwException("align point must in 0 ~ 4");
        }
        // Notice this bitmap format must ARGB_8888, otherwise the baidu map engine may be crash.
        reBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(reBitmap);
        canvas.drawColor(Color.parseColor("#70FF0000"));
        canvas.drawBitmap(bitmap, offsetX, offsetY, new Paint());
        this.bitmap = reBitmap;
        this.ref = null;
    }
}
