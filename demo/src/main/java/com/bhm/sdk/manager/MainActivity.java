package com.bhm.sdk.manager;

import android.view.View;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class MainActivity extends BaseActivity {

    private final static int no_data = 0;
    private final static int no_net = 1;

    @Override
    protected int setRootViewId() {
        return R.layout.activity_main;//根布局
    }

    @Override
    protected int setContentViewId() {
        return R.id.ll_item_content;//内容布局
    }

    @Override
    protected int setContainerViewId() {
        return R.id.ll_content;//内容布局的父布局
    }

    @Override
    protected void initView() {
        super.initView();
//        layoutManager.hideAllLayout();
//        layoutManager.showViewByPosition(no_data);deprecated
//        layoutManager.showViewByPosition(no_net);deprecated
//        layoutManager.showViewByTag("no_net");
        layoutManager.showViewByTag("no_net", R.id.tv_no_data_tips_center, "噢哦，没网",
                R.id.iv_no_data_center, R.mipmap.ic_launcher);
        layoutManager.getViewByTag("no_net").setBackgroundColor(ContextCompat.getColor(this, R.color.color_6));
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.layout_no_net :
                Toast.makeText(MainActivity.this, "点击了屏幕", Toast.LENGTH_SHORT).show();
                layoutManager.showContent();
                break;
            case R.id.tv_no_data :
                Toast.makeText(MainActivity.this, "点击屏幕重试", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_no_data :
                Toast.makeText(MainActivity.this, "点击刷新", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
