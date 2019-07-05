package ymq.com.jdshopviewdemo;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ymq.com.jdshopviewdemo.fragment.JDShopFragment;
import ymq.com.jdshopviewdemo.widget.JDShopListView;
import ymq.com.jdshopviewdemo.widget.NoScrollViewPager;
import ymq.com.jdshopviewdemo.widget.SlidingestedNView;

public class JDShopActivity extends AppCompatActivity implements SlidingestedNView.MyViewTouchLinstener,
        ViewPager.OnPageChangeListener,  JDShopFragment.OnFragmentInteractionListener,
        JDShopListView.JDShopListViewTouchLinstener {

    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_info)
    TextView titleInfo;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.shop_icon)
    ImageView shopIcon;
    @BindView(R.id.shop_arrow)
    ImageView shopArrow;
    @BindView(R.id.shop_info_container)
    RelativeLayout shopInfoContainer;
    @BindView(R.id.my_order_list_titles)
    MagicIndicator magicIndicator;
    @BindView(R.id.my_order_list_content)
    NoScrollViewPager myOrderListContent;
    @BindView(R.id.scorll_container)
    SlidingestedNView scorllContainer;

    private float orHeaderTitlesY;
    private float endHeaderTitlesY;

    private float orMigiHeaderY;
    private float endMigiHeaderY;

    private float shopInfoContainerHeight;
    ArrayList<JDShopFragment> fragmentArrayList;
    private String[] titles = {"全部", "待付款", "待发货", "待收货", "已完成", "已取消"};
    private int headerHeight;

    public int getState() {
        if (magicIndicator.getY() == orHeaderTitlesY){
            state = 0;
        }else if (magicIndicator.getY() == endHeaderTitlesY){
            state = 2;
        }else {
            state = 1;
        }

        return state;
    }

    public int state = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jdshop);
        ButterKnife.bind(this);

        initViews();
        scorllContainer.setMyViewTouchLinstener(this);
    }

    private void initViews() {
        magicIndicator.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
                shopInfoContainer.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                shopInfoContainerHeight = shopInfoContainer.getHeight();
                orHeaderTitlesY = shopInfoContainer.getY() + shopInfoContainer.getHeight();
                endHeaderTitlesY = shopInfoContainer.getY();

                orMigiHeaderY = orHeaderTitlesY + magicIndicator.getHeight();
                endMigiHeaderY = endHeaderTitlesY + magicIndicator.getHeight();
            }
        });

        fragmentArrayList = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];
            JDShopFragment tempFragemet = JDShopFragment.newInstance("aa", "bb");
            tempFragemet.setFragmentId(i);

            fragmentArrayList.add(tempFragemet);
        }

        GoodsDetailViewPagerAdapter adapter = new GoodsDetailViewPagerAdapter(getSupportFragmentManager(), fragmentArrayList, titles);
        myOrderListContent.setAdapter(adapter);
        myOrderListContent.setScrollable(true);

        initTabLayout();
    }


    private void initTabLayout() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return titles == null ? 0 : titles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setTextSize(14);
                colorTransitionPagerTitleView.setNormalColor(getResources().getColor(R.color.my_order_titles_color));
                colorTransitionPagerTitleView.setSelectedColor(getResources().getColor(R.color.my_order_btn_red));
                colorTransitionPagerTitleView.setText(titles[index]);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myOrderListContent.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setColors(Color.RED);
                //设置indicator的宽度
                return indicator;
            }
        });


        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, myOrderListContent);


        myOrderListContent.addOnPageChangeListener(this);


        headerHeight = getResources().getDimensionPixelSize(R.dimen.dimen_100dp);


        myOrderListContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
                myOrderListContent.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                ViewGroup.LayoutParams myOrderListContentp = myOrderListContent.getLayoutParams();
                myOrderListContentp.height = myOrderListContent.getMeasuredHeight() + headerHeight;
                myOrderListContent.setLayoutParams(myOrderListContentp);

                Log.e("height", String.valueOf(myOrderListContent.getMeasuredHeight()));
            }
        });


    }

    @OnClick({R.id.title_back, R.id.title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                this.finish();
                break;
            case R.id.title_right:
                break;
        }
    }

    @Override
    public void moveView(float offsetX, float offsetY) {
        float y = magicIndicator.getY();
        y += offsetY;


        Log.e("moveView", String.valueOf(y));

        if (y <= endHeaderTitlesY) {
            y = endHeaderTitlesY;
        }

        if (y >= orHeaderTitlesY) {
            y = orHeaderTitlesY;
        }


        ViewHelper.setY(magicIndicator, y);


        float myOrderListContenty = myOrderListContent.getY();

        myOrderListContenty += offsetY;
        if (myOrderListContenty <= endMigiHeaderY) {
            myOrderListContenty = endMigiHeaderY;

        }
        if (myOrderListContenty >= orMigiHeaderY) {
            myOrderListContenty = orMigiHeaderY;
        }

        ViewHelper.setY(myOrderListContent, myOrderListContenty);

        float ratio = y / shopInfoContainerHeight;
        shopInfoContainer.setAlpha(ratio);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public class GoodsDetailViewPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<JDShopFragment> fragmentArrayList;
        private String[] mTitles;

        public GoodsDetailViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public GoodsDetailViewPagerAdapter(FragmentManager fm, ArrayList<JDShopFragment> arrayList, String[] titles) {
            super(fm);
            fragmentArrayList = arrayList;
            mTitles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }
}
