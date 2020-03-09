package com.huangyuanlove.sunflower_java.adapter;

import android.view.View;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {

    @BindingAdapter("isGone")
    public static void bindIsGone(View view, boolean isGone) {

        view.setVisibility(isGone ? View.GONE : View.VISIBLE);

    }


}
