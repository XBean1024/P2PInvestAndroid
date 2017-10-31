package com.atguigu.contexttest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * 1.Application对象的创建要早于所有的Activity对象的创建
 * 2.Context :getApplicationContext() 和 Activity:getApplication():返回的都是Application对象
 * 3.Application的实例，随着应用加载到内存中，此对象创建，方法调用。只要当前的进程存在，此对象就存在。（单例）
 *  如果进程销毁，此对象销毁。。
 * 4.Application实例的生命周期要早于所有的其他组件。
 * 5.application可以看成：应用全局数据内存级共享容器
 */
public class MainActivity extends Activity {

    public MainActivity(){
        Log.e("TAG", "MainActivity MainActivity()");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("TAG", "MainActivity onCreate()");

        //获取Application实例
        Context context = this.getApplicationContext();
        MyApplication application = (MyApplication) this.getApplication();
        Log.e("TAG", "context == application:" + (context == application));

        //设置其内部的数据
        application.setData("友谊的小船说翻就翻");


    }
    
    public void startMyService(View v) {
        //启动服务
        startService(new Intent(this,MyService.class));
        
        new AlertDialog.Builder(this)
                    .setTitle("测试")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
    }
}
