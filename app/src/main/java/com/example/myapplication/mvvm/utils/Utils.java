package com.example.myapplication.mvvm.utils;

import androidx.databinding.BindingConversion;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Author wuyuhang
 * @Date 2023/11/27 16:47
 * @Describe
 */
public class Utils {

    @BindingConversion
    public static String convertToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return simpleDateFormat.format(date);
    }

}
