package com.example.secureapp;

import android.content.Context;
import android.util.AttributeSet;

import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;

public class SquareMapView extends MapView {
    public SquareMapView(Context context) {
        super(context);
    }

    public SquareMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SquareMapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public SquareMapView(Context context, GoogleMapOptions googleMapOptions) {
        super(context, googleMapOptions);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        // Optimization so we don't measure twice unless we need to
        if (width != height) {
            setMeasuredDimension(width, width);
        }
    }
}
