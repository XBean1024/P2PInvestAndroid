package com.atguigu.p2pinvest0828.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.atguigu.p2pinvest0828.R;
import com.atguigu.p2pinvest0828.bean.Product;

import java.util.List;

/**
 * Created by shkstart on 2016/12/5 0005.
 */
public class ProductAdapter2 extends MyBaseAdapter2<Product> {

    public ProductAdapter2(List<Product> list) {
        super(list);
    }

    @Override
    protected void setData(View convertView, Product product) {
        ((TextView)convertView.findViewById(R.id.p_name)).setText(product.name);
        //....

        Log.e("TAG", "setData");

    }

    @Override
    protected View initView(Context context) {
        return View.inflate(context,R.layout.item_product_list,null);
    }
}
