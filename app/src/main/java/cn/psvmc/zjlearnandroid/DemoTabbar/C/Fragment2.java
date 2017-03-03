package cn.psvmc.zjlearnandroid.DemoTabbar.C;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import cn.psvmc.zjlearnandroid.R;

public class Fragment2 extends Fragment {
    private String TAG = "Fragment2";
    RelativeLayout back_layout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        View parentView = inflater.inflate(R.layout.fragment2, container, false);
        back_layout = (RelativeLayout)parentView.findViewById(R.id.back_layout);
        back_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment2.this.getActivity().onBackPressed();
            }
        });
        return parentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}
