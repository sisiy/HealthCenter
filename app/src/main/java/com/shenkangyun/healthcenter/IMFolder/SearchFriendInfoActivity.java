package com.shenkangyun.healthcenter.IMFolder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.shenkangyun.healthcenter.R;
import com.shenkangyun.healthcenter.UtilFolder.InfoModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchFriendInfoActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    private ImageView mIv_photo;
    private TextView mTv_nickName;
    private TextView mTv_userName;
    private TextView mTv_sign;
    private TextView mTv_gender;
    private TextView mTv_birthday;
    private TextView mTv_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friend_info);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.home_red));
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("搜索结果");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initData();
    }

    private void initView() {
        mIv_photo = (ImageView) findViewById(R.id.iv_photo);
        mTv_nickName = (TextView) findViewById(R.id.tv_nickName);
        mTv_userName = (TextView) findViewById(R.id.tv_userName);
        mTv_sign = (TextView) findViewById(R.id.tv_sign);
        mTv_gender = (TextView) findViewById(R.id.tv_gender);
        mTv_birthday = (TextView) findViewById(R.id.tv_birthday);
        mTv_city = (TextView) findViewById(R.id.tv_city);

        findViewById(R.id.btn_addFriend).setOnClickListener(this);
    }

    private void initData() {
        InfoModel instance = InfoModel.getInstance();
        if (instance.getAvatar() == null) {
            mIv_photo.setImageResource(R.drawable.rc_default_portrait);
        } else {
            mIv_photo.setImageBitmap(instance.getAvatar());
        }
        if (TextUtils.isEmpty(instance.getNickName())) {
            mTv_nickName.setText("用户名: " + instance.getUserName());
        } else {
            mTv_nickName.setText("昵称:" + instance.getNickName());
        }
        mTv_userName.setText(instance.getUserName());
        mTv_sign.setText(instance.getSign());
        mTv_gender.setText(instance.getGender());
        mTv_birthday.setText(instance.getBirthday());
        mTv_city.setText(instance.getCity());
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_addFriend:
                //添加好友界面
                intent = new Intent(SearchFriendInfoActivity.this, VerificationActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
