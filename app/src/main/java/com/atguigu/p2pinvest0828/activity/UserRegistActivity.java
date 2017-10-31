package com.atguigu.p2pinvest0828.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.p2pinvest0828.R;
import com.atguigu.p2pinvest0828.common.AppNetConfig;
import com.atguigu.p2pinvest0828.common.BaseActivity;
import com.atguigu.p2pinvest0828.util.MD5Utils;
import com.atguigu.p2pinvest0828.util.UIUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import butterknife.Bind;
import butterknife.OnClick;

public class UserRegistActivity extends BaseActivity {

    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @Bind(R.id.et_register_number)
    EditText etRegisterNumber;
    @Bind(R.id.et_register_name)
    EditText etRegisterName;
    @Bind(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @Bind(R.id.et_register_pwdagain)
    EditText etRegisterPwdagain;
    @Bind(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void initData() {


        //设置“注册”button的点击事件
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.获取用户注册的信息
                String name = etRegisterName.getText().toString().trim();
                String number = etRegisterNumber.getText().toString().trim();
                String pwd = etRegisterPwd.getText().toString().trim();
                String pwdAgain = etRegisterPwdagain.getText().toString().trim();


                //2.所填写的信息不能为空
                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(number) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwdAgain)){
                    UIUtils.toast("填写的信息不能为空",false);
                }else if(!pwd.equals(pwdAgain)){//2.两次密码必须一致
                    UIUtils.toast("两次填写的密码不一致",false);
                    etRegisterPwd.setText("");
                    etRegisterPwdagain.setText("");

                }else{
                    //3.联网发送用户注册信息
                    String url = AppNetConfig.USERREGISTER;
                    RequestParams params = new RequestParams();
//                    name = new String(name.getBytes(),"UTF-8");
                    params.put("name",name);
                    params.put("password", MD5Utils.MD5(pwd));
                    params.put("phone",number);
                    client.post(url,params,new AsyncHttpResponseHandler(){
                        @Override
                        public void onSuccess(String content) {
                            JSONObject jsonObject = JSON.parseObject(content);
                            boolean isExist = jsonObject.getBoolean("isExist");
                            if(isExist){//已经注册过
                                UIUtils.toast("此用户已注册",false);
                            }else{
                                UIUtils.toast("注册成功",false);
                            }

                        }

                        @Override
                        public void onFailure(Throwable error, String content) {
                            UIUtils.toast("联网请求失败",false);
                        }
                    });
                }

            }
        });
    }

    @Override
    protected void initTitle() {
        ivTitleBack.setVisibility(View.VISIBLE);
        tvTitle.setText("用户注册");
        ivTitleSetting.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.iv_title_back)
    public void back(View view){
        removeCurrentActivity();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_regist;
    }

}
