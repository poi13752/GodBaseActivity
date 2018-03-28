package com.godframe;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.godframe.listener.GodOnClickListener;
import com.godframe.ui.GodLeftHandBaseActivity;


public class MainActivity extends GodLeftHandBaseActivity implements GodOnClickListener {

    @Override
    protected int setContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        //设置标题
        setTitle("我是标题");
        //设置值
        setText(R.id.login_bt, "我不是登录了哈哈");
        //图片
        setImageResource(R.id.head_img, R.mipmap.ic_launcher_round);
        //自定义View
        MyView myView = getView(R.id.myview);
        //RecyclerView
        MyAdapter adapter = new MyAdapter();
        setRecyclerViewLayoutManager(R.id.recyclerview, new LinearLayoutManager(this)).setAdapter(adapter);
        //获取View的值
        String str = getTextView(R.id.login_bt).getText().toString();
        //隐藏头布局
        hideTopView();
        //隐藏头布局的返回键
        hideBackView();
        //点击事件监听
        setOnClickListener(this,R.id.register_bt,R.id.login_bt);
    }

    @Override
    public void OnClickListener(View view) {
        switch (view.getId()) {
            case R.id.register_bt:
                //显示加载框
                showProgress();
                break;
            case R.id.login_bt:
                //页面跳转的方法
                skipActivity(LoginActivity.class);
                //带值跳转的方法
                mIntent.putExtra("data", "zhu");
                skipActivity(LoginActivity.class);
                //跳转完关闭的方法
                skipActivityFinish(LoginActivity.class);
                //ResultCode的跳转
                skipActivity(LoginActivity.class, 10086);

                break;
        }

    }
}
