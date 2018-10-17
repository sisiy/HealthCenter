package com.shenkangyun.healthcenter.IMFolder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.shenkangyun.healthcenter.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FriendInfoActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
    @BindView(R.id.tv_nickName)
    TextView tvNickName;
    @BindView(R.id.tv_sign)
    TextView tvSign;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;

    private String nickname;
    private String userName;
    private long birthday;
    private String birth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_info);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.home_red));
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("好友信息");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initData();
    }

    private void initView() {
        Intent intent = getIntent();
        nickname = intent.getStringExtra("nickname");
        userName = intent.getStringExtra("userName");
        birthday = intent.getLongExtra("birthday", 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(birthday);
        birth = sdf.format(date);

    }

    private void initData() {
        ivPhoto.setImageResource(R.drawable.rc_default_portrait);

        if (TextUtils.isEmpty(nickname)) {
            tvNickName.setText("用户名: " + "资料未填写");
        } else {
            tvNickName.setText("昵称:" + nickname);
        }
        tvUserName.setText(userName);
        tvBirthday.setText(birth);
    }

    @OnClick(R.id.btn_addFriend)
    public void onViewClicked() {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("phone",userName);
        intent.putExtra("message","");
        startActivity(intent);
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
