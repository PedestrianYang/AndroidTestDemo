package ymq.com.jdshopviewdemo.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by iyunshu on 2019/7/4.
 */

public class SlidingestedNView extends LinearLayout {
    private int lastXa, lastYa;

    private MyViewTouchLinstener myViewTouchLinstener;

    public SlidingestedNView(Context context) {
        super(context);
    }

    public SlidingestedNView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SlidingestedNView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setMyViewTouchLinstener(MyViewTouchLinstener myViewTouchLinstener) {
        this.myViewTouchLinstener = myViewTouchLinstener;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                lastXa = (int) event.getRawX();// 获取触摸事件触摸位置的原始X坐标
                lastYa = (int) event.getRawY();

                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) event.getRawX() - lastXa;
                int dy = (int) event.getRawY() - lastYa;
                Log.e("dy", String.valueOf(dy));

                lastXa = (int) event.getRawX();
                lastYa = (int) event.getRawY();

                myViewTouchLinstener.moveView(dx, dy);

                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }

    public interface MyViewTouchLinstener{
        void moveView(float offsetX, float offsetY);
    }
}
