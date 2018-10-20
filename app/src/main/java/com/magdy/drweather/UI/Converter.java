package com.magdy.drweather.UI;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.magdy.drweather.R;

import java.util.Locale;

public class Converter {
    public static Drawable convertLayoutToImage (Context context,int count , int drawableId)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.menu_number_item,null);
        ((ImageView)view.findViewById(R.id.icon)).setImageResource(drawableId);
        TextView textView = view.findViewById(R.id.count) ;
        if (count==0)
            textView.setVisibility(View.GONE);
        else
            textView.setText(String.format(Locale.getDefault(),"%d",count));
        view.measure(View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED));
        view.layout(0,0,view.getMeasuredWidth(),view.getMeasuredHeight());
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return new BitmapDrawable(context.getResources(),bitmap);
    }
}
