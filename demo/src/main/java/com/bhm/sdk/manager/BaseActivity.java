package com.bhm.sdk.manager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.bhm.sdk.manager.library.OnViewClickListener;
import com.bhm.sdk.manager.library.StatusLayoutManager;

import java.util.ArrayList;

/**
 * Created by bhm on 2018/5/2.
 */

public abstract class BaseActivity extends AppCompatActivity implements OnViewClickListener {

    protected StatusLayoutManager layoutManager;
    protected View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    protected void initView(){
        if(setRootViewId() != 0) {
            rootView = LayoutInflater.from(this).inflate(setRootViewId(), null, false);
            setContentView(rootView);
        }
        if(setContainerViewId() > 0 && setContentViewId() > 0) {
            layoutManager = StatusLayoutManager.newBuilder(this)
                    .rootView(rootView)//根布局
                    .containerViewId(setContainerViewId())//父布局
                    .contentViewId(setContentViewId())//内容布局
                    .itemViewsId(getItemViewsId())//布局集合
                    .OnViewClickListener(this)
                    .build();
        }
    }

    private ArrayList<Integer>  getItemViewsId(){
        ArrayList<Integer> res = new ArrayList<>();
        res.add(R.layout.layout_no_data);
        res.add(R.layout.layout_no_net);
        return res;
    }

    protected abstract int setRootViewId();

    protected abstract int setContentViewId();

    protected abstract int setContainerViewId();

    @Override
    public void onClick(View view) {
        
    }
}
