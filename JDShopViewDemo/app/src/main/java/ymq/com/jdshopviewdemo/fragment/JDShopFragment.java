package ymq.com.jdshopviewdemo.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ymq.com.jdshopviewdemo.R;
import ymq.com.jdshopviewdemo.adapter.OneListViewAdapter;
import ymq.com.jdshopviewdemo.widget.JDShopListView;
import ymq.com.jdshopviewdemo.widget.JDWebViewClient;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link JDShopFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link JDShopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JDShopFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.listview)
    JDShopListView listview;

    private ArrayList<String> list;
    private OneListViewAdapter adapter;
    private Context mContext;
    private View contentView;

    private Context context;

    private View headerView;
    private WebView headerWebView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private float xDown,yDown, xUp;

    private OnFragmentInteractionListener mListener;

    public JDShopFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Test2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JDShopFragment newInstance(String param1, String param2) {
        JDShopFragment fragment = new JDShopFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (contentView == null){
            contentView = inflater.inflate(R.layout.fragment_jdshop, container, false);
            ButterKnife.bind(this, contentView);
            initLsitview();
        }

        return contentView;
    }

    private void initLsitview() {
        list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list.add("item" + String.valueOf(i));
        }

        adapter = new OneListViewAdapter(mContext, list);
        listview.setAdapter(adapter);
        listview.setJDShopListViewTouchLinstener((JDShopListView.JDShopListViewTouchLinstener) mContext);

        headerView = this.getLayoutInflater().inflate(R.layout.header_placeholder_view, listview, false);
        headerWebView = headerView.findViewById(R.id.webview);
        listview.addHeaderView(headerView);

        headerWebView.setWebViewClient(new JDWebViewClient());
        WebSettings webSettings = headerWebView.getSettings();
        if (Build.VERSION.SDK_INT >= 21) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        // 设置支持javascript脚本
        webSettings.setJavaScriptEnabled(true);

        // 设置此属性，可任意比例缩放
        webSettings.setUseWideViewPort(true);
        // 设置不出现缩放工具
        webSettings.setBuiltInZoomControls(false);
        // 设置不可以缩放
        webSettings.setSupportZoom(false);
        webSettings.setDisplayZoomControls(false);

        //自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        // 自适应 屏幕大小界面
        webSettings.setLoadWithOverviewMode(true);
        headerWebView.loadUrl("file:///android_asset/万乡食尚馆.htm");
//        String html="<html><head><meta charset=\"UTF-8\"><style type=\"text/css\">html,body{padding:0px;margin:0px;} p{margin:0px;}</style></head><body>"+html+"</body></html>";
//        headerWebView.loadDataWithBaseURL(null,html, "text/html",  "utf-8", null);



//        headerWebView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                listview.mDownY = event.getRawY();
//                listview.lastYa = (int) event.getRawY();
//                switch (event.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                        xDown= event.getX();
//                        yDown = event.getY();
//
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        break;
//                    case MotionEvent.ACTION_UP:
//                    case MotionEvent.ACTION_CANCEL:
//                        break;
//                }
//
//                return false;
//            }
//        });
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            mContext = context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
