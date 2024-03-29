package com.example.listview.reflash.horizontal;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by mac on 2019/4/23.
 */
public class DragState {

    public static final int STATE_IDLE = 1 << 0;

    public static final int STATE_START_DRAG = 1 << 1;//开始左滑

    public static final int STATE_DRAGING = 1 << 2;//左滑中

    public static final int STATE_ON_LOADING = 1 << 3;//loading状态（松手）

    @IntDef({STATE_IDLE, STATE_START_DRAG, STATE_DRAGING, STATE_ON_LOADING})
    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.FIELD)
    public @interface EnumDragState {
    }

}
