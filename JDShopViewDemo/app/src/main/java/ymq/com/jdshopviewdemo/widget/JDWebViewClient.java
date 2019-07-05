package ymq.com.jdshopviewdemo.widget;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by iyunshu on 2019/7/4.
 */

public class JDWebViewClient extends WebViewClient {
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        // 重新测量
        view.measure(w, h);
    }
}
