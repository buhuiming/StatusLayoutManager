package com.bhm.sdk.manager;

import android.view.View;
import android.widget.Toast;

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
//        layoutManager.showViewByPosition(no_data);
        layoutManager.showViewByPosition(no_net);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.layout_no_net :
                Toast.makeText(this, "点击了屏幕", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_no_data :
                Toast.makeText(this, "点击屏幕重试", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_no_data :
                Toast.makeText(this, "点击刷新", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
