package com.shenkangyun.healthcenter.LoginPage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.shenkangyun.healthcenter.BaseFolder.Base;
import com.shenkangyun.healthcenter.BeanFolder.RegisterBean;
import com.shenkangyun.healthcenter.R;
import com.shenkangyun.healthcenter.UtilFolder.GsonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.options.RegisterOptionalUserInfo;
import cn.jpush.im.api.BasicCallback;

import static com.shenkangyun.healthcenter.LoginPage.TagAliasOperatorHelper.ACTION_SET;
import static com.shenkangyun.healthcenter.LoginPage.TagAliasOperatorHelper.sequence;


public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.ed_userName)
    EditText edUserName;
    @BindView(R.id.ed_passWord)
    EditText edPassWord;
    @BindView(R.id.ed_confirm)
    EditText edConfirm;
    @BindView(R.id.ed_tel)
    EditText edTel;
    @BindView(R.id.ed_idCard)
    EditText edIdCard;
    @BindView(R.id.img_check)
    ImageView imgCheck;
    @BindView(R.id.btn_register)
    Button btnRegister;

    private String telTv;
    private String userNameTv;
    private String idCardTv;
    private String passWordTv;
    private String confirmTv;

//    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.home_red));
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("用户注册");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
//        sp = getSharedPreferences("Agreement", MODE_PRIVATE);
//
//        String accept = sp.getString("accept", "");
//
//        if (!TextUtils.isEmpty(accept) && "1".equals(accept)) {
//            imgCheck.setImageResource(R.drawable.election_true);
//            btnRegister.setEnabled(true);
//        } else {
            imgCheck.setImageResource(R.drawable.election_false);
            btnRegister.setEnabled(false);
//        }

    }


    @OnClick({R.id.btn_register, R.id.btn_cancel, R.id.tv_agreement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                if (btnRegister.isEnabled()) {
                    getData();
                }
                break;
            case R.id.btn_cancel:
                Intent intent = new Intent();
                intent.putExtra("UserName", "");
                intent.putExtra("PassWord", "");
                setResult(1, intent);
                finish();
                break;
            case R.id.tv_agreement:
                Intent intentAgree = new Intent(this, AgreementActivity.class);
                startActivityForResult(intentAgree,300);
                Intent intent1 = new Intent();
                intent1.putExtra("UserName", "");
                intent1.putExtra("PassWord", "");
                setResult(1, intent1);
//                finish();
                break;
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        String accept = data.getStringExtra("accept");
        if (!TextUtils.isEmpty(accept) && "1".equals(accept)) {
            imgCheck.setImageResource(R.drawable.election_true);
            btnRegister.setEnabled(true);
        } else {
            imgCheck.setImageResource(R.drawable.election_false);
            btnRegister.setEnabled(false);
        }
    }

    private void getData() {
        telTv = edTel.getText().toString();
        userNameTv = edUserName.getText().toString();
        idCardTv = edIdCard.getText().toString();
        passWordTv = edPassWord.getText().toString();
        confirmTv = edConfirm.getText().toString();
        if (TextUtils.isEmpty(userNameTv)) {
            Toast.makeText(this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(passWordTv)) {
            Toast.makeText(this, "密码不能为空！", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(confirmTv)) {
            Toast.makeText(this, "密码不一致！", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(telTv)) {
            Toast.makeText(this, "手机号不能为空！", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(idCardTv)) {
            Toast.makeText(this, "身份证号不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            initRegister();
        }
    }

    private void initRegister() {
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "registerPatient")
                .addParams("data", new registerPatient(Base.getMD5Str(), Base.getTimeSpan(), "1", userNameTv,
                        telTv, idCardTv, passWordTv, confirmTv).toJson())
                .build()
                .execute(new GsonCallBack<RegisterBean>() {
                    @Override
                    public void onSuccess(RegisterBean response) throws JSONException {
                        String status = response.getStatus();
                        if ("0".equals(status)) {
                            initJMessageRegister();
                            Intent intent = new Intent();
                            intent.putExtra("UserName", telTv);
                            intent.putExtra("PassWord", passWordTv);
                            setResult(1, intent);
                            onTagAliasAction(telTv);
                            finish();
                        } else {
                            Toast.makeText(RegisterActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });


    }

    private void initJMessageRegister() {
        RegisterOptionalUserInfo UserInfo = new RegisterOptionalUserInfo();
        UserInfo.setNickname(userNameTv);
        JMessageClient.register(telTv, passWordTv, UserInfo, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent();
                intent.putExtra("UserName", "");
                intent.putExtra("PassWord", "");
                setResult(1, intent);
                finish();
                break;
        }
        return true;
    }

    static class registerPatient {

        private String appKey;
        private String timeSpan;
        private String mobileType;
        private String loginName;
        private String mobile;
        private String idCard;
        private String password;
        private String passwordCopy;

        public registerPatient(String appKey, String timeSpan, String mobileType, String loginName,
                               String mobile, String idCard, String password, String passwordCopy) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.mobileType = mobileType;
            this.loginName = loginName;
            this.mobile = mobile;
            this.idCard = idCard;
            this.password = password;
            this.passwordCopy = passwordCopy;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("UserName", "");
        intent.putExtra("PassWord", "");
        setResult(1, intent);
        finish();
        super.onBackPressed();
    }
    public void onTagAliasAction(String alias) {
        int action = -1;
        boolean isAliasAction = true;

        if(TextUtils.isEmpty(alias)){
            return;
            }
        action = ACTION_SET;

        TagAliasOperatorHelper.TagAliasBean tagAliasBean = new TagAliasOperatorHelper.TagAliasBean();
        tagAliasBean.action = action;
        sequence++;
        tagAliasBean.alias = alias;

        tagAliasBean.isAliasAction = isAliasAction;
        TagAliasOperatorHelper.getInstance().handleAction(getApplicationContext(),sequence,tagAliasBean);
    }

}
