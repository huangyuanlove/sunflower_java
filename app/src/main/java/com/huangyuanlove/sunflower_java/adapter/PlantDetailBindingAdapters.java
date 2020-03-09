package com.huangyuanlove.sunflower_java.adapter;

import android.content.res.Resources;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.huangyuanlove.sunflower_java.R;


public class PlantDetailBindingAdapters {

    @BindingAdapter("imageFromUrl")
  public static  void bindImageFromUrl(ImageView imageView,String imageUrl){
        if(!TextUtils.isEmpty(imageUrl)){
            Glide.with(imageView.getContext())
                    .load(imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);
        }
    }
    @BindingAdapter("isGone")
    public static   void bindIsGone(FloatingActionButton view , boolean isGone) {
        if (isGone) {
            view.hide();
        } else {
            view.show();
        }
    }

    @BindingAdapter("renderHtml")
    public static   void bindRenderHtml(TextView view , String description) {
        if (description != null) {
            view.setText(HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT));
            view.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            view.setText("");
        }
    }

    @BindingAdapter("wateringText")
    public static   void bindWateringText(TextView textView ,int wateringInterval) {
        Resources resources = textView.getContext().getResources();
        String quantityString = resources.getQuantityString(R.plurals.watering_needs_suffix,
                wateringInterval, wateringInterval);

        textView.setText(quantityString);
    }

}
