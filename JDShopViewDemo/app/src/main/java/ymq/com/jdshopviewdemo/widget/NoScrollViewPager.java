package ymq.com.jdshopviewdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import ymq.com.jdshopviewdemo.R;

/**
 * Created by iyunshu on 2019/7/4.
 */

public class NoScrollViewPager extends ViewPager {
    private boolean scrollable = false;

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub

        TypedArray a  = context.obtainStyledAttributes(attrs, R.styleable.PagerSlidingTabStrip);

        scrollable = a.getBoolean(R.styleable.ViewPager_scrollable, false);
    }

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        /* return false;//super.onTouchEvent(arg0); */
        if (!scrollable)
            return false;
        else
            return super.onTouchEvent(arg0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (!scrollable)
            return false;
        else
            return super.onInterceptTouchEvent(arg0);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }


    public void setOnPageChangeListener() {
    }
}
