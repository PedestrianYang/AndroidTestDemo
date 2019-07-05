package ymq.com.jdshopviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_nextpage)
    TextView btnNextpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_nextpage)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_nextpage:
                Intent second = new Intent(this, JDShopActivity.class);
                startActivity(second);
                break;
        }
    }
}
