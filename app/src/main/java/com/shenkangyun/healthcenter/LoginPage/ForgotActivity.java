package com.shenkangyun.healthcenter.LoginPage;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.shenkangyun.healthcenter.R;
import com.shenkangyun.healthcenter.UtilFolder.ClearWriteEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.ed_userName)
    ClearWriteEditText edUserName;
    @BindView(R.id.ed_idCard)
    ClearWriteEditText edIdCard;
    @BindView(R.id.ed_newWord)
    ClearWriteEditText edNewWord;
    @BindView(R.id.ed_confirm)
    ClearWriteEditText edConfirm;

    private String userNameTv;
    private String idCardTv;
    private String newWordTv;
    private String confirmTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.home_red));
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("忘记密码");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
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

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        getData();

    }

    private void getData() {
        userNameTv = edUserName.getText().toString();
        idCardTv = edIdCard.getText().toString();
        newWordTv = edNewWord.getText().toString();
        confirmTv = edConfirm.getText().toString();
        if (TextUtils.isEmpty(userNameTv)) {
            Toast.makeText(this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(idCardTv)) {
            Toast.makeText(this, "身份证号码不能为空！", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(newWordTv)) {
            Toast.makeText(this, "新密码不能为空！", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(confirmTv)) {
            Toast.makeText(this, "确认密码不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            initSubmit();
        }
    }

    private void initSubmit() {


    }
}
