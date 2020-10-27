package com.bhm.sdk.manager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.bhm.sdk.manager.library.OnViewClickListener;
import com.bhm.sdk.manager.library.StatusLayoutManager;

import java.util.LinkedHashMap;

import androidx.appcompat.app.AppCompatActivity;

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

    private LinkedHashMap<Object, Integer> getItemViewsId(){
        LinkedHashMap<Object, Integer> res = new LinkedHashMap<>();
        res.put("no_data", R.layout.layout_no_data);//参数1：layout的id；参数2：Tag，标识
        res.put("no_net", R.layout.layout_no_net);
        return res;
    }

    protected abstract int setRootViewId();

    protected abstract int setContentViewId();

    protected abstract int setContainerViewId();

    @Override
    public void onClick(View view) {
        
    }
}
