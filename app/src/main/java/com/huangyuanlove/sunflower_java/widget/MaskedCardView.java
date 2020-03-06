package com.huangyuanlove.sunflower_java.widget;


import android.content.Context;
import android.graphics.Path;
import android.util.AttributeSet;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import com.huangyuanlove.sunflower_java.R;

public class MaskedCardView extends MaterialCardView {

    private ShapeAppearancePathProvider pathProvider;
    private Path path;
    private ShapeAppearanceModel shapeAppearanceModel;

    public MaskedCardView(Context context, AttributeSet attrs) {
        super(context, attrs, R.attr.materialCardViewStyle);
        path = new Path();
    }
}
