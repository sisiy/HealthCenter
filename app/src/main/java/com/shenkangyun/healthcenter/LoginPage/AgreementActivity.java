package com.shenkangyun.healthcenter.LoginPage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.shenkangyun.healthcenter.BaseFolder.AppConst;
import com.shenkangyun.healthcenter.MainPage.MainActivity;
import com.shenkangyun.healthcenter.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgreementActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.black));
        ButterKnife.bind(this);
        sp = getSharedPreferences("Agreement", MODE_PRIVATE);
        editor = sp.edit();
    }

    @OnClick({R.id.btn_accept, R.id.btn_decline})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_accept:
                editor.putString("accept", "1");
                editor.commit();
                Intent data = new Intent();
                data.putExtra("accept","1");
//                Intent intentA = new Intent(this, RegisterActivity.class);
//                startActivity(intentA);
                setResult(300,data);
                finish();
                break;
            case R.id.btn_decline:
                editor.putString("accept", "0");
                editor.commit();
                Intent data1 = new Intent();
                data1.putExtra("accept","0");
                setResult(300,data1);
//                Intent intentD = new Intent(this, RegisterActivity.class);
//                startActivity(intentD);
                finish();
                break;
        }
    }
}
