package com.zxin.basemodule.overlay;

import android.text.TextUtils;

import com.zxin.basemodule.util.CheckUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Render map item.
 * 1. First add to overlay or already add to overlay group.
 * 2. request update.
 * 3.
 *
 * @author Alex.Liu
 */
public class MapItemGroup extends MapItem {
    public MapItemGroup() {}
    public MapItemGroup(String tag, float layerZ) {
        super(tag, layerZ);
    }

    private List<MapItem> mChildren = new ArrayList<>();

    /**
     * Add a child map item.
     * <p>
     * if child map item is {@link MapItemGroup} will fix child's tag.
     * example: child's tag is "child", parent's tag is "parent", after fix, child's tag is
     * "parent_child", parent's tag as child's tag's prefix. if child's tag already prefixed with
     * parent's tag, don't fix again.
     *
     * @param item
     */
    public void addMapItem(MapItem item) {
        item.setParent(this);
        if (item instanceof MapItemGroup) {
            fixChildTag((MapItemGroup) item);
        }
        CheckUtil.require(TextUtils.equals(item.getTag(), getTag()),
                "if child is not a map item group, the child's tag must equals parent's tag."
                        + "but now child tag is (" + item.getTag()
                        + ") and parent tag is (" + getTag() + ")");

        mChildren.add(item);
        sort();
    }

    private void sort() {
        Collections.sort(mChildren, new Comparator<MapItem>() {
            @Override
            public int compare(MapItem o1, MapItem o2) {
                return o1.getLayerZ() - o2.getLayerZ() > 0 ? 1 : -1;
            }
        });
    }

    public void addMapItems(List<MapItem> items) {
        for (MapItem item : items) {
            if (item instanceof MapItemGroup) {
                fixChildTag((MapItemGroup) item);
            }
        }
        mChildren.addAll(items);
        sort();
    }

    /**
     * If child map item is {@link MapItemGroup} will fix child's tag.
     * example: child's tag is "child", parent's tag is "parent", after fix, child's tag is
     * "parent_child", parent's tag as child's tag's prefix. if child's tag already prefixed with
     * parent's tag, don't fix again.
     *
     * @param group
     */
    private void fixChildTag(MapItemGroup group) {
        if (group.getTag().startsWith(getTag())) {
            group.setTag(getTag() + "_" + group.getTag());
        }
    }

    public void removeMapItem(MapItem item) {
        mChildren.remove(item);
    }

    public final Set<String> getTags() {
        Set<String> tags = new HashSet<>();
        for (MapItem item : mChildren) {
            if (item instanceof MapItemGroup) {
                tags.addAll(((MapItemGroup) item).getTags());
            }
        }
        tags.add(getTag());
        return tags;
    }

    public final List<MapItem> getAllChildren() {
        List<MapItem> mapItems = new ArrayList<>(getCount());
        for (MapItem item : mChildren) {
            if (item instanceof MapItemGroup) {
                mapItems.addAll(((MapItemGroup) item).getAllChildren());
            } else {
                mapItems.add(item);
            }
        }
        return mapItems;
    }

    /**
     * Contain self
     *
     * @return
     */
    public final List<MapItemGroup> getGroupChildren() {
        List<MapItemGroup> mapItems = new ArrayList<>(getCount());
        for (MapItem item : mChildren) {
            if (item instanceof MapItemGroup) {
                mapItems.addAll(((MapItemGroup) item).getGroupChildren());
            }
        }
        mapItems.add(this);
        return mapItems;
    }

    public List<MapItem> getChildren() {
        return mChildren;
    }

    public MapItem getFirstChild() {
        if (mChildren.size() >= 1) {
            return mChildren.get(0);
        }
        return null;
    }

    public final int getCount() {
        return mChildren.size();
    }

    public final MapItem getSelectMapItem() {
        for (MapItem item : mChildren) {
            if (item instanceof MapItemGroup) {
                MapItem select = ((MapItemGroup) item).getSelectMapItem();
                if (select != null) {
                    return select;
                }
            } else {
                if (item.isHighlight()) {
                    return item;
                }
            }
        }
        return null;
    }



    public MapItemGroup findMapItemGroupByTag(String tag) {
        if (TextUtils.equals(getTag(), tag)) {
            return this;
        }
        for (MapItem child : mChildren) {
            if (child instanceof MapItemGroup) {
                MapItemGroup result = ((MapItemGroup) child).findMapItemGroupByTag(tag);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    public void clear() {
        mChildren.clear();
    }
}
