package com.xybcoder.baseactivity.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2015/7/28.
 */
public class SidlerBar extends View {
    private OnIndexTouchListener listener;
    private float rawTextSize;
    public SidlerBar(Context context) {
        this(context, null);
    }

    public SidlerBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SidlerBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSider();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SidlerBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initSider();
    }

    private String[] indexstrs={};
    Paint paint=new Paint();
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height=getHeight();
        int width=getWidth();
        if (indexstrs.length!=0) {
            int itemheight = height / indexstrs.length;
            for (int i = 0; i < indexstrs.length; i++) {
                paint.setAntiAlias(true);
                if (currindex == i) {
                    paint.setColor(0Xff005fad);
                } else {
                    paint.setColor(0xff808080);
                }

                paint.setTypeface(Typeface.DEFAULT);
                paint.setTextSize(rawTextSize);
                float pox = width / 2 - paint.measureText(indexstrs[i]) / 2;
                float poy = itemheight * i + itemheight;
                canvas.drawText(indexstrs[i], pox, poy, paint);
                paint.reset();
            }
        }
    }

    public void setIndexstrs(String[] indexstrs) {
        this.indexstrs = indexstrs;
        invalidate();
    }

    public void setOnIndexTouchListener(OnIndexTouchListener listener){
        this.listener=listener;
    }
    private  int currindex=-1;

    public interface  OnIndexTouchListener{
        public void onTouchDown(String indextxt, int state);
        public void onTouchUp(int state);
    }
    private void initSider() {

        setTextSize(14);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
       float y=event.getY();
       int index= (int)(y/getHeight()*indexstrs.length);
       if (index>=0&&index<indexstrs.length){
           switch (event.getAction()){
               case MotionEvent.ACTION_DOWN:

                       currindex=index;
                      if (listener!=null){
                          listener.onTouchDown(indexstrs[currindex],MotionEvent.ACTION_DOWN);
                      }
                       Log.i("index",indexstrs[currindex]);

                   break;
               case MotionEvent.ACTION_MOVE:
                   if (currindex!=index){
                       currindex=index;
                       if (listener!=null){
                           listener.onTouchDown(indexstrs[currindex],MotionEvent.ACTION_MOVE);
                       }
                       Log.i("index",indexstrs[currindex]);
                   }
                   break;
               case MotionEvent.ACTION_UP:

                   Log.i("index",indexstrs[currindex]);
                   if (listener!=null){
                       listener.onTouchUp(MotionEvent.ACTION_UP);
                   }
                   currindex=-1;
                   break;

           }
           invalidate();
       }else {
           currindex = -1;
           if (listener != null) {
               listener.onTouchUp(MotionEvent.ACTION_UP);
           }
       }
       return  true;
    }

    public void setTextSize(float size){
        Context c = getContext();
        Resources r;

        if (c == null)
            r = Resources.getSystem();
        else
            r = c.getResources();

        setRawTextSize(TypedValue.applyDimension(
                2, size, r.getDisplayMetrics()));
    }

    public void setRawTextSize(float rawTextSize) {
        this.rawTextSize = rawTextSize;
    }
}
