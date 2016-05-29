package com.mx.coorindatorlayoutsample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.one)
    LinearLayout mOne;
    @Bind(R.id.two)
    LinearLayout mTwo;
    @Bind(R.id.head_layout)
    LinearLayout mHeadLayout;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Bind(R.id.toolbar_tab)
    TabLayout mToolbarTab;
    @Bind(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    @Bind(R.id.main_vp_container)
    ViewPager mMainVpContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.bg);
        mHeadLayout.setBackgroundDrawable(new BitmapDrawable(BlurUtil.fastblur(this,bitmap,180)));

        //使用 CollapsingToolbarLayout 必须title设置到CollapsingToobarLayout上，设置到TooBar上则不会显示。
        mCollapsingToolbarLayout.setContentScrim(new BitmapDrawable(BlurUtil.fastblur(this,bitmap,180)));
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(verticalOffset<=-mHeadLayout.getHeight()/2){
                    mCollapsingToolbarLayout.setTitle("游天龙");
                }else{
                    mCollapsingToolbarLayout.setTitle(" ");
                }


            }
        });

        Toolbar.OnMenuItemClickListener onMenuItemClickListener=new Toolbar.OnMenuItemClickListener(){

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String msg="";
                switch (item.getItemId()){
                    case R.id.webview:
                        msg+="博客跳转";
                        break;
                    case R.id.weibo:
                        msg+="微博跳转";
                        break;
                    case R.id.action_settings:
                        msg+="设置";
                        break;
                }
                if(!msg.equals("")){
                    Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        };

        mToolbar.setOnMenuItemClickListener(onMenuItemClickListener);


        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),this);
        mMainVpContainer.setAdapter(viewPagerAdapter);
        mMainVpContainer.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mToolbarTab));

        mToolbarTab.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mMainVpContainer));
        //tablayout和viewpager建立联系为什么不用下面这个方法呢？自己去研究一下，可能收获更多
        //toolbar_tab.setupWithViewPager(main_vp_container);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
}
