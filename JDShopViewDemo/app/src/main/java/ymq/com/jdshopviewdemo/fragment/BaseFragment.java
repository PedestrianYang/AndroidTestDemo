package ymq.com.jdshopviewdemo.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ymq.com.jdshopviewdemo.R;

public class BaseFragment extends Fragment {
    public int getFragmentId() {
        return fragmentId;
    }

    public void setFragmentId(int fragmentId) {
        this.fragmentId = fragmentId;
    }

    private int fragmentId;

    public void adjustScroll(int scrollHeight){

    }
}
