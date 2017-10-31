package com.atguigu.p2pinvest0828.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.p2pinvest0828.R;
import com.atguigu.p2pinvest0828.common.BaseActivity;
import com.atguigu.p2pinvest0828.fragment.HomeFragment;
import com.atguigu.p2pinvest0828.fragment.InvestFragment;
import com.atguigu.p2pinvest0828.fragment.MeFragment;
import com.atguigu.p2pinvest0828.fragment.MoreFragment;
import com.atguigu.p2pinvest0828.util.UIUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @Bind(R.id.fl_main)
    FrameLayout flMain;
    @Bind(R.id.iv_main_home)
    ImageView ivMainHome;
    @Bind(R.id.tv_main_home)
    TextView tvMainHome;
    @Bind(R.id.ll_main_home)
    LinearLayout llMainHome;
    @Bind(R.id.iv_main_invest)
    ImageView ivMainInvest;
    @Bind(R.id.tv_main_invest)
    TextView tvMainInvest;
    @Bind(R.id.ll_main_invest)
    LinearLayout llMainInvest;
    @Bind(R.id.iv_main_me)
    ImageView ivMainMe;
    @Bind(R.id.tv_main_me)
    TextView tvMainMe;
    @Bind(R.id.ll_main_me)
    LinearLayout llMainMe;
    @Bind(R.id.iv_main_more)
    ImageView ivMainMore;
    @Bind(R.id.tv_main_more)
    TextView tvMainMore;
    @Bind(R.id.ll_main_more)
    LinearLayout llMainMore;
    private FragmentTransaction transaction;


    @Override
    protected void initData() {
        //默认显示首页
        setSelect(0);

        //模拟异常
//        String str = null;
        //try {
//            if(str.equals("abc")){
//                Log.e("TAG", "abc");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.ll_main_home,R.id.ll_main_invest,R.id.ll_main_me,R.id.ll_main_more})
    public void showTab(View view){
//        Toast.makeText(MainActivity.this, "选择了具体的Tab", Toast.LENGTH_SHORT).show();
        switch (view.getId()) {
            case R.id.ll_main_home ://首页
                setSelect(0);
                break;
            case R.id.ll_main_invest ://投资
                setSelect(1);
                break;
            case R.id.ll_main_me ://我的资产
                setSelect(2);
                break;
            case R.id.ll_main_more ://更多
                setSelect(3);
                break;
        }
    }
    private HomeFragment homeFragment;
    private InvestFragment investFragment;
    private MeFragment meFragment;
    private MoreFragment moreFragment;
    //提供相应的fragment的显示
    private void setSelect(int i) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        //隐藏所有Fragment的显示
        hideFragments();
        //重置ImageView和TextView的显示状态
        resetTab();

        switch (i) {
            case 0 :
                if(homeFragment == null){
                    homeFragment = new HomeFragment();//创建对象以后，并不会马上调用生命周期方法。而是在commit()之后，方才调用
                    transaction.add(R.id.fl_main, homeFragment);
                }
                //显示当前的fragment
                transaction.show(homeFragment);
                //错误的调用位置
//                homeFragment.show();

                //改变选中项的图片和文本颜色的变化
                ivMainHome.setImageResource(R.drawable.bottom02);
//                tvMainHome.setTextColor(R.color.text_progress);//错误的写法
                tvMainHome.setTextColor(UIUtils.getColor(R.color.home_back_selected));

                break;
            case 1 :
                if(investFragment == null){
                    investFragment = new InvestFragment();
                    transaction.add(R.id.fl_main, investFragment);
                }
                transaction.show(investFragment);

                //改变选中项的图片和文本颜色的变化
                ivMainInvest.setImageResource(R.drawable.bottom04);
                tvMainInvest.setTextColor(UIUtils.getColor(R.color.home_back_selected));

                break;
            case 2 :
                if(meFragment == null){
                    meFragment = new MeFragment();
                    transaction.add(R.id.fl_main, meFragment);
                }
                transaction.show(meFragment);

                //改变选中项的图片和文本颜色的变化
                ivMainMe.setImageResource(R.drawable.bottom06);
                tvMainMe.setTextColor(UIUtils.getColor(R.color.home_back_selected01));

                break;
            case 3 :
                if(moreFragment == null){
                    moreFragment = new MoreFragment();
                    transaction.add(R.id.fl_main, moreFragment);
                }
                transaction.show(moreFragment);

                //改变选中项的图片和文本颜色的变化
                ivMainMore.setImageResource(R.drawable.bottom08);
                tvMainMore.setTextColor(UIUtils.getColor(R.color.home_back_selected));

                break;
        }
        transaction.commit();//提交事务

    }

    private void resetTab() {
        ivMainHome.setImageResource(R.drawable.bottom01);
        ivMainInvest.setImageResource(R.drawable.bottom03);
        ivMainMe.setImageResource(R.drawable.bottom05);
        ivMainMore.setImageResource(R.drawable.bottom07);

        tvMainHome.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvMainInvest.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvMainMe.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvMainMore.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        //这种方式也可以
//        tvMainMore.setTextColor(ContextCompat.getColor(this, R.color.home_back_unselected));
    }

    private void hideFragments() {

        if(homeFragment != null){
            transaction.hide(homeFragment);
        }
        if(investFragment != null){
            transaction.hide(investFragment);
        }
        if(meFragment != null){
            transaction.hide(meFragment);
        }
        if(moreFragment != null){
            transaction.hide(moreFragment);
        }

    }

    //重写onKeyUp()，实现连续两次点击方可退出当前应用

    private boolean flag = true;
    private static final int WHAT_RESET_BACK = 1;
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            Log.e("TAG", "handleMessage");
            switch (msg.what) {
                case WHAT_RESET_BACK :
                    flag = true;//复原
                    break;
            }
        }
    };
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK && flag){
            Toast.makeText(MainActivity.this, "再点击一次，退出当前应用", Toast.LENGTH_SHORT).show();
            flag = false;
            //发送延迟消息
            handler.sendEmptyMessageDelayed(WHAT_RESET_BACK,2000);
            return true;
        }

        return super.onKeyUp(keyCode, event);
    }

    //为了避免出现内存的泄漏，需要在onDestroy()中，移除所有未被执行的消息
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //方式一：
//        handler.removeMessages(WHAT_RESET_BACK);//移除指定id的所有的消息
        //方式二：移除所有的未被执行的消息
        handler.removeCallbacksAndMessages(null);
    }
}
