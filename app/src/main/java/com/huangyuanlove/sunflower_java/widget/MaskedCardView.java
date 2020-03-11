package com.huangyuanlove.sunflower_java.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import com.huangyuanlove.sunflower_java.R;

public class MaskedCardView extends MaterialCardView {

    private ShapeAppearancePathProvider pathProvider;
    private Path path;
    private ShapeAppearanceModel shapeAppearanceModel;
    private RectF rectF = new RectF(0f, 0f, 0f, 0f);

    public MaskedCardView(Context context, AttributeSet attrs,int defStyleAttr) {
        super(context, attrs, R.attr.materialCardViewStyle);
        path = new Path();
        pathProvider = new ShapeAppearancePathProvider();
        shapeAppearanceModel = new ShapeAppearanceModel(
                context,
                attrs,
                defStyleAttr,
                R.style.Widget_MaterialComponents_CardView
        );
    }


    @Override
    public void onDraw(Canvas canvas) {
        canvas.clipPath(path);
        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        rectF.right = w;
        rectF.bottom = h;
        pathProvider.calculatePath(shapeAppearanceModel, 1f, rectF, path);
        super.onSizeChanged(w, h, oldw, oldh);
    }


}
