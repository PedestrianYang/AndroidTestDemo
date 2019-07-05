package ymq.com.jdshopviewdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import ymq.com.jdshopviewdemo.JDShopActivity;

/**
 * Created by iyunshu on 2019/7/4.
 */

public class JDShopListView extends ListView {

    public float mDownY;

    public int lastYa;
    private JDShopActivity activity;

    public void setJDShopListViewTouchLinstener(JDShopListViewTouchLinstener test2LitViewTouchLinstener) {
        this.shopListViewTouchLinstener = test2LitViewTouchLinstener;
        activity = (JDShopActivity)test2LitViewTouchLinstener;
    }

    private JDShopListViewTouchLinstener shopListViewTouchLinstener;

    public JDShopListView(Context context) {
        super(context);
    }

    public JDShopListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public JDShopListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getRawY();
                lastYa = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (activity.getState() != 2){
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getRawY();
                lastYa = (int) ev.getRawY();

                break;
            case MotionEvent.ACTION_MOVE:

                int dy = (int) ev.getRawY() - lastYa;
                lastYa = (int) ev.getRawY();
                Log.e("mDownY",String.valueOf(mDownY));
                Log.e("isListViewTop()",String.valueOf(isListViewTop()));
                Log.e("ev.getY() - mDownY",String.valueOf(ev.getRawY() - mDownY));
                Log.e("activity.getState()",String.valueOf(activity.getState()));

                if (ev.getRawY() - mDownY < 0 && activity.getState() != 2) {
                    shopListViewTouchLinstener.moveView(0, dy);
                    return true;
                } else if (ev.getRawY() - mDownY > 0 && isListViewTop()  && activity.getState() != 0) {
                    shopListViewTouchLinstener.moveView(0, dy);
                    return true;
                } else if (activity.getState() == 1){
                    shopListViewTouchLinstener.moveView(0, dy);
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    public boolean isListViewTop() {
        boolean result=false;
        //代表是ListView的第一个item   即ListView的顶部
        if(getFirstVisiblePosition()==0){
            //第一个item
            View topChildView = getChildAt(0);
            if (topChildView != null) {
                //listview的顶部   即Y坐标==0
                result=topChildView.getTop()==0;
                Log.e("isListViewTop", String.valueOf(topChildView.getTop()));
            }
        }
        return result ;
    }

    public boolean isListViewBottom() {
        boolean result=false;
        //listview最后可见的一个item
        if (getLastVisiblePosition() == (getCount() - 1)) {
            //getLastVisiblePosition() - getFirstVisiblePosition() 可见item的总数   即一个屏幕中的所有item
            View bottomChildView = getChildAt(getLastVisiblePosition() - getFirstVisiblePosition());
            if (bottomChildView != null) {
                //getHeight() listview的高度      最后一个item
                result= (getHeight() >= bottomChildView.getBottom());
            }
        }
        return  result;
    }

    public interface JDShopListViewTouchLinstener{
        void moveView(float offsetX, float offsetY);
    }
}
