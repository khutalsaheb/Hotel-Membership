package com.ivitesse.epicure.helper;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

import androidx.annotation.Nullable;

class Utils {

    public static int dpToPx(float dp, @Nullable Context context) {
        return dpToPx(dp, context.getResources());
    }

    private static int dpToPx(float dp, @Nullable Resources resources) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
        return (int) px;
    }
}