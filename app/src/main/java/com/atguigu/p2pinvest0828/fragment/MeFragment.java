package com.atguigu.p2pinvest0828.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.p2pinvest0828.R;
import com.atguigu.p2pinvest0828.activity.BarChartActivity;
import com.atguigu.p2pinvest0828.activity.ChongZhiActivity;
import com.atguigu.p2pinvest0828.activity.GestureVerifyActivity;
import com.atguigu.p2pinvest0828.activity.LineChartActivity;
import com.atguigu.p2pinvest0828.activity.LoginActivity;
import com.atguigu.p2pinvest0828.activity.PieChartActivity;
import com.atguigu.p2pinvest0828.activity.TiXianActivity;
import com.atguigu.p2pinvest0828.activity.UserInfoActivity;
import com.atguigu.p2pinvest0828.bean.User;
import com.atguigu.p2pinvest0828.common.BaseActivity;
import com.atguigu.p2pinvest0828.common.BaseFragment;
import com.atguigu.p2pinvest0828.util.BitmapUtils;
import com.atguigu.p2pinvest0828.util.UIUtils;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by shkstart on 2016/11/30 0030.
 */
public class MeFragment extends BaseFragment {

    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @Bind(R.id.iv_me_icon)
    ImageView ivMeIcon;
    @Bind(R.id.rl_me_icon)
    RelativeLayout rlMeIcon;
    @Bind(R.id.tv_me_name)
    TextView tvMeName;
    @Bind(R.id.rl_me)
    RelativeLayout rlMe;
    @Bind(R.id.recharge)
    ImageView recharge;
    @Bind(R.id.withdraw)
    ImageView withdraw;
    @Bind(R.id.ll_touzi)
    TextView llTouzi;
    @Bind(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @Bind(R.id.ll_zichan)
    TextView llZichan;


    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected void initData(String content) {
        //判断用户是否已经登录
        isLogin();
    }

    private void isLogin() {
        //查看本地是否有用户的登录信息
        SharedPreferences sp = this.getActivity().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String name = sp.getString("name", "");
        if(TextUtils.isEmpty(name)){
            //本地没有保存过用户信息，给出提示：登录
            doLogin();

        }else{
            //已经登录过，则直接加载用户的信息并显示
            doUser();
        }

    }
    //加载用户信息并显示
    private void doUser() {

        //1.读取本地保存的用户信息
        User user = ((BaseActivity) this.getActivity()).readUser();
        //2.获取对象信息，并设置给相应的视图显示。
        tvMeName.setText(user.getName());

        //判断本地是否已经保存头像的图片，如果有，则不再执行联网操作
        boolean isExist = readImage();
        if(isExist){
            return;
        }

        //使用Picasso联网获取图片
        Picasso.with(this.getActivity()).load(user.getImageurl()).transform(new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {//下载以后的内存中的bitmap对象
                //压缩处理
                Bitmap bitmap = BitmapUtils.zoom(source, UIUtils.dp2px(62),UIUtils.dp2px(62));
                //圆形处理
                bitmap = BitmapUtils.circleBitmap(bitmap);
                //回收bitmap资源
                source.recycle();
                return bitmap;
            }

            @Override
            public String key() {
                return "";//需要保证返回值不能为null。否则报错
            }
        }).into(ivMeIcon);


        //判断一下，是否开启了手势密码。如果开启：先输入手势密码
        SharedPreferences sp = this.getActivity().getSharedPreferences("secret_protect", Context.MODE_PRIVATE);
        boolean isOpen = sp.getBoolean("isOpen", false);
        if(isOpen){
            ((BaseActivity)this.getActivity()).goToActivity(GestureVerifyActivity.class,null);
            return;
        }
    }

    //给出提示：登录
    private void doLogin() {
        new AlertDialog.Builder(this.getActivity())
                    .setTitle("提示")
                    .setMessage("您还没有登录哦！么么~")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            UIUtils.toast("进入登录页面",false);
                            ((BaseActivity) MeFragment.this.getActivity()).goToActivity(LoginActivity.class, null);
                        }
                    })
                    .setCancelable(false)
                    .show();
    }

    public void initTitle() {
        ivTitleBack.setVisibility(View.INVISIBLE);
        tvTitle.setText("我的资产");
        ivTitleSetting.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.iv_title_setting)
    public void setting(View view){
        //启动用户信息界面的Activity
        ((BaseActivity)this.getActivity()).goToActivity(UserInfoActivity.class, null);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("TAG", "onResume()");

        //读取本地保存的图片
        readImage();
    }

    private boolean readImage() {
        File filesDir;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){//判断sd卡是否挂载
            //路径1：storage/sdcard/Android/data/包名/files
            filesDir = this.getActivity().getExternalFilesDir("");

        }else{//手机内部存储
            //路径：data/data/包名/files
            filesDir = this.getActivity().getFilesDir();

        }
        File file = new File(filesDir,"icon.png");
        if(file.exists()){
            //存储--->内存
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            ivMeIcon.setImageBitmap(bitmap);
            return true;
        }
        return false;

    }

    //设置“充值”操作
    @OnClick(R.id.recharge)
    public void reCharge(View view){
        ((BaseActivity)this.getActivity()).goToActivity(ChongZhiActivity.class,null);
    }
    //设置“提现”操作
    @OnClick(R.id.withdraw)
    public void withdraw(View view){
        ((BaseActivity)this.getActivity()).goToActivity(TiXianActivity.class,null);
    }

    //启动折线图
    @OnClick(R.id.ll_touzi)
    public void startLineChart(View view){
        ((BaseActivity)this.getActivity()).goToActivity(LineChartActivity.class,null);
    }
    //启动折线图
    @OnClick(R.id.ll_touzi_zhiguan)
    public void startBarChart(View view){
        ((BaseActivity)this.getActivity()).goToActivity(BarChartActivity.class,null);
    }
    //启动折线图
    @OnClick(R.id.ll_zichan)
    public void startPieChart(View view){
        ((BaseActivity)this.getActivity()).goToActivity(PieChartActivity.class,null);
    }
}
