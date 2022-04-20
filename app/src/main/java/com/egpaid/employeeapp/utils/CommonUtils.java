package com.egpaid.employeeapp.utils;

import com.github.mikephil.charting.utils.ColorTemplate;

public class CommonUtils {


    public static int[] getColors() {
        // have as many colors as stack-values per entry
        int[] colors = new int[2];
        System.arraycopy(ColorTemplate.MATERIAL_COLORS, 0, colors, 0, 2);
        return colors;
    }
}
