package com.atguigu.p2pinvest0828.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atguigu.p2pinvest0828.ui.LoadingPage;
import com.loopj.android.http.RequestParams;

import butterknife.ButterKnife;

/**
 * Created by shkstart on 2016/12/3 0003.
 */
public abstract class BaseFragment extends Fragment {

    private LoadingPage loadingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*View view = UIUtils.getView(getLayoutId());
        ButterKnife.bind(this, view);

        //初始化title
        initTitle();

        //初始化数据
        initData();*/

        loadingPage = new LoadingPage(container.getContext()) {

            @Override
            public int layoutId() {
                return getLayoutId();
            }

            @Override
            protected void onSuccss(ResultState resultState, View view_success) {
                ButterKnife.bind(BaseFragment.this, view_success);
                initTitle();
                initData(resultState.getContent());
            }

            @Override
            protected RequestParams params() {
                return getParams();
            }

            @Override
            public String url() {
                return getUrl();
            }
        };


        return loadingPage;
    }
    //为了保证loadingPage不为null
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //模拟联网操作的延迟
//        UIUtils.getHandler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                show();
//            }
//        },2000);
        show();
    }

    protected abstract RequestParams getParams();

    protected abstract String getUrl();

    //初始化界面的数据
    protected abstract void initData(String content);

    //初始化title
    protected abstract void initTitle();

    //提供布局
    public abstract int getLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void show(){
        loadingPage.show();
    }
}
