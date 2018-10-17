package com.shenkangyun.healthcenter.IMFolder;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.shenkangyun.healthcenter.DBFolder.FriendInvitation;
import com.shenkangyun.healthcenter.DBFolder.FriendRecommendEntry;
import com.shenkangyun.healthcenter.DBFolder.UserEntry;
import com.shenkangyun.healthcenter.R;
import com.shenkangyun.healthcenter.UtilFolder.InfoModel;
import com.shenkangyun.healthcenter.UtilFolder.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

public class VerificationActivity extends AppCompatActivity {

    @BindView(R.id.jmui_commit_btn)
    Button jmuiCommitBtn;
    @BindView(R.id.return_btn)
    ImageButton returnBtn;

    private EditText mEt_reason;
    private UserInfo mMyInfo;
    private String mTargetAppKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.home_red));
        initView();
        initData();
    }

    private void initData() {
        mEt_reason = findViewById(R.id.et_reason);
        mEt_reason.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    sendAddReason();
                }
                return false;
            }
        });

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        jmuiCommitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAddReason();
            }
        });
    }

    @SuppressLint("WrongConstant")
    private void sendAddReason() {
        final String userName;
        String displayName;
        displayName = InfoModel.getInstance().getNickName();
        if (TextUtils.isEmpty(displayName)) {
            displayName = InfoModel.getInstance().getUserName();
        }
        userName = InfoModel.getInstance().getUserName();
        final String reason = mEt_reason.getText().toString();
        ContactManager.sendInvitationRequest(userName, null, reason, new BasicCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage) {
                if (responseCode == 0) {
                    ToastUtil.shortToast(VerificationActivity.this, "申请成功");
                    finish();
                } else if (responseCode == 871317) {
                    ToastUtil.shortToast(VerificationActivity.this, "不能添加自己为好友");
                } else {
                    ToastUtil.shortToast(VerificationActivity.this, "申请失败");
                }
            }
        });
    }

    @SuppressLint("WrongConstant")
    private void initView() {
        mEt_reason = (EditText) findViewById(R.id.et_reason);
        mMyInfo = JMessageClient.getMyInfo();
        mTargetAppKey = mMyInfo.getAppKey();
        String name;
        //群组详细信息点击非好友头像,跳转到此添加界面
        if (getIntent().getFlags() == 1) {
            name = getIntent().getStringExtra("detail_add_friend_my_nickname");
            if (TextUtils.isEmpty(name)) {
                mEt_reason.setText("我是");
            } else {
                mEt_reason.setText("我是" + name);
            }
            //搜索用户发送添加申请
        } else {
            name = mMyInfo.getNickname();
            if (TextUtils.isEmpty(name)) {
                mEt_reason.setText("我是");
            } else {
                mEt_reason.setText("我是" + name);
            }
        }

    }
}
