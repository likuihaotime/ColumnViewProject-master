package com.lixiaodaoaaa.graphics;

import android.content.Context;

/**
 * @author : 李奎
 * @datetime : 2019/8/8 10:33
 * @describe :
 */
public class UI {

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
