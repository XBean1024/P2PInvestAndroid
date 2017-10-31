package com.atguigu.p2pinvest0828.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atguigu.p2pinvest0828.R;
import com.atguigu.p2pinvest0828.bean.Product;
import com.atguigu.p2pinvest0828.ui.RoundProgress;
import com.atguigu.p2pinvest0828.util.UIUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shkstart on 2016/12/5 0005.
 */
public class ProductAdapter extends BaseAdapter {

    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList == null ? 0 : productList.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //将具体的集合数据装配到具体的一个item layout中。
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e("TAG", "parent = " + parent.getClass().toString());
        Log.e("TAG", "parent.getContext() = " + parent.getContext());

        int itemViewType = getItemViewType(position);
        if(itemViewType == 0){
            TextView tv = new TextView(parent.getContext());
            tv.setText("与子同行，奈何覆舟");
            tv.setTextColor(UIUtils.getColor(R.color.text_progress));
            tv.setTextSize(UIUtils.dp2px(20));
            return tv;
        }

        if(position > 3){
            position--;
        }

        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_product_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //装配数据
        Product product = productList.get(position);
        holder.pMinnum.setText(product.memberNum);
        holder.pMinzouzi.setText(product.minTouMoney);
        holder.pMoney.setText(product.money);
        holder.pName.setText(product.name);
        holder.pProgresss.setProgress(Integer.parseInt(product.progress));
        holder.pSuodingdays.setText(product.suodingDays);
        holder.pYearlv.setText(product.yearRate);

        return convertView;
    }

    //不同的position位置上，显示的具体的item的type值
    @Override
    public int getItemViewType(int position) {
        if(position == 3){
            return 0;
        }else{
            return 1;
        }
    }
    //返回不同类型的item的个数
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    static class ViewHolder {
        @Bind(R.id.p_name)
        TextView pName;
        @Bind(R.id.p_money)
        TextView pMoney;
        @Bind(R.id.p_yearlv)
        TextView pYearlv;
        @Bind(R.id.p_suodingdays)
        TextView pSuodingdays;
        @Bind(R.id.p_minzouzi)
        TextView pMinzouzi;
        @Bind(R.id.p_minnum)
        TextView pMinnum;
        @Bind(R.id.p_progresss)
        RoundProgress pProgresss;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
