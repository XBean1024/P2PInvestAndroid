package com.atguigu.p2pinvest0828.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by shkstart on 2016/11/30 0030.
 */
public class HomeFragment1 extends Fragment {

//    @Bind(R.id.iv_title_back)
//    ImageView ivTitleBack;
//    @Bind(R.id.tv_title)
//    TextView tvTitle;
//    @Bind(R.id.iv_title_setting)
//    ImageView ivTitleSetting;
//    @Bind(R.id.vp_home)
//    ViewPager vpHome;
//    @Bind(R.id.tv_home_product)
//    TextView tvHomeProduct;
//    @Bind(R.id.tv_home_yearrate)
//    TextView tvHomeYearrate;
//    @Bind(R.id.cp_home_indicator)
//    CirclePageIndicator cpHomeIndicator;
//    private Index index;
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = UIUtils.getView(R.layout.fragment_home);//context实例：application
////        View view = View.inflate(getActivity(), R.layout.fragment_home, null);//context实例：activity
//        ButterKnife.bind(this, view);
//
//        //初始化title
//        initTitle();
//
//        //初始化数据
//        initData();
//
//        return view;
//    }
//
//    private void initData() {
//        index = new Index();
//        AsyncHttpClient client = new AsyncHttpClient();
//        //访问的url
//        String url = AppNetConfig.INDEX;
//        client.post(url, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(String content) {//200：响应成功
//                //解析json数据：GSON / FASTJSON
//                JSONObject jsonObject = JSON.parseObject(content);
//                //解析json对象数据
//                String proInfo = jsonObject.getString("proInfo");
//                Product product = JSON.parseObject(proInfo, Product.class);
//                //解析json数组数据
//                String imageArr = jsonObject.getString("imageArr");
//                List<Image> images = jsonObject.parseArray(imageArr, Image.class);
//                index.product = product;
//                index.images = images;
//
//                //更新页面数据
//                tvHomeProduct.setText(product.name);
//                tvHomeYearrate.setText(product.yearRate + "%");
//
//                //设置ViewPager
//                vpHome.setAdapter(new MyAdapter());
//                //设置小圆圈的显示
//                cpHomeIndicator.setViewPager(vpHome);
//            }
//
//            @Override
//            public void onFailure(Throwable error, String content) {//响应失败
//                Toast.makeText(UIUtils.getContext(), "联网获取数据失败", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void initTitle() {
//        ivTitleBack.setVisibility(View.GONE);
//        tvTitle.setText("首页");
//        ivTitleSetting.setVisibility(View.GONE);
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        ButterKnife.unbind(this);
//    }
//
//    class MyAdapter extends PagerAdapter {
//
//        @Override
//        public int getCount() {
//            List<Image> images = index.images;
//            return images == null ? 0 : images.size();
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            ImageView imageView = new ImageView(getActivity());
//            //1.imageView显示图片
//            String imaurl = index.images.get(position).IMAURL;
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            Picasso.with(getActivity()).load(imaurl).into(imageView);//使用Picasso联网下载图片
//
//            //2.imageView添加到容器中
//            container.addView(imageView);
//            return imageView;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);//移除操作
//
//        }
//    }
}
