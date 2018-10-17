package com.shenkangyun.healthcenter.MainPage;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.shenkangyun.healthcenter.BaseFolder.AppConst;
import com.shenkangyun.healthcenter.BaseFolder.Base;
import com.shenkangyun.healthcenter.BeanFolder.ApkUpBean;
import com.shenkangyun.healthcenter.DBFolder.User;
import com.shenkangyun.healthcenter.LoginPage.LoginActivity;
import com.shenkangyun.healthcenter.R;
import com.shenkangyun.healthcenter.UtilFolder.APKVersionCodeUtils;
import com.shenkangyun.healthcenter.UtilFolder.GsonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.litepal.LitePal;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.doc_name)
    TextView docName;
    @BindView(R.id.doc_age)
    TextView docAge;
    @BindView(R.id.doc_idCard)
    TextView docIdCard;
    @BindView(R.id.doc_birthday)
    TextView docBirthday;
    @BindView(R.id.doc_mobile)
    TextView docMobile;
    @BindView(R.id.tv_profession)
    TextView tvProfession;
    @BindView(R.id.tv_degree)
    TextView tvDegree;
    @BindView(R.id.tv_height)
    TextView tvHeight;
    @BindView(R.id.tv_weight)
    TextView tvWeight;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.tv_day)
    TextView tvDay;
    @BindView(R.id.tv_husbandAge)
    TextView tvHusbandAge;
    @BindView(R.id.tv_husbandProfession)
    TextView tvHusbandProfession;
    @BindView(R.id.tv_complication)
    TextView tvComplication;

    private List<String> vocations = new ArrayList<>();
    private List<String> educations = new ArrayList<>();
    private List<String> complications = new ArrayList<>();

    private int age;
    private int degree;
    private String idCard;
    private String mobile;
    private String national;
    private String loginName;
    private String height;
    private String weight;
    private int childWeeks;
    private int husbandAge;
    private int profession;
    private int complication;
    private int husbandProfession;

    private SharedPreferences sp;
    private ProgressDialog pd;

    private String versionName;
    private String appVersion;
    private String appUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.home_red));
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("个人中心");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initList();
        initData();
    }

    private void initView() {
        sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        age = Base.getAgeP();
        idCard = Base.getIDCardP();
        mobile = Base.getMobileP();
        national = Base.getNationalP();
        loginName = Base.getLoginNameP();
        List<User> all = LitePal.findAll(User.class);
        for (int i = 0; i < all.size(); i++) {
            height = all.get(i).getHeight();
            weight = all.get(i).getWeight();
            degree = all.get(i).getDegree();
            profession = all.get(i).getProfession();
            childWeeks = all.get(i).getChildWeeks();
            husbandAge = all.get(i).getHusbandAge();
            complication = all.get(i).getComplication();
            husbandProfession = all.get(i).getHusbandProfession();
        }
    }

    private void initList() {
        vocations.add("政府机关及事业单位");
        vocations.add("个体");
        vocations.add("职员");
        vocations.add("工人");
        vocations.add("农民");
        vocations.add("自由职业");
        vocations.add("其他");

        educations.add("初中及以下");
        educations.add("高中");
        educations.add("中专");
        educations.add("大专");
        educations.add("本科及以上");

        complications.add("妊娠期糖尿病");
        complications.add("妊娠期高血压");
        complications.add("羊水过多");
        complications.add("羊水过少");
        complications.add("子痫前期重度");
        complications.add("其他");
    }


    private void initData() {
        if (!TextUtils.isEmpty(String.valueOf(age))) {
            docAge.setText(String.valueOf(age));
        }
        if (!TextUtils.isEmpty(String.valueOf(degree))) {
            for (int i = 0; i < educations.size(); i++) {
                if (degree == i) {
                    tvDegree.setText(educations.get(i));
                }
            }
        }
        if (!TextUtils.isEmpty(String.valueOf(profession))) {
            for (int i = 0; i < vocations.size(); i++) {
                if (profession == i) {
                    tvProfession.setText(vocations.get(i));
                }
            }
        }
        if (!TextUtils.isEmpty(String.valueOf(husbandProfession))) {
            for (int i = 0; i < educations.size(); i++) {
                if (husbandProfession == i) {
                    tvHusbandProfession.setText(vocations.get(i));
                }
            }
        }
        if (!TextUtils.isEmpty(String.valueOf(husbandAge))) {
            tvHusbandAge.setText(String.valueOf(husbandAge));
        }
        if (!TextUtils.isEmpty(String.valueOf(complication))) {
            for (int i = 0; i < complications.size(); i++) {
                if (complication == i) {
                    tvComplication.setText(complications.get(i));
                }
            }
        }
        if (!TextUtils.isEmpty(String.valueOf(childWeeks))) {
            int weekNum = childWeeks / 7;
            int dayNum = childWeeks % 7;
            tvWeek.setText(String.valueOf(weekNum));
            tvDay.setText(String.valueOf(dayNum));
        }
        if (!TextUtils.isEmpty(idCard)) {
            docIdCard.setText(idCard);
        }
        if (!TextUtils.isEmpty(mobile)) {
            docMobile.setText(mobile);
        }
        if (!TextUtils.isEmpty(national)) {
            docBirthday.setText(national);
        }
        if (!TextUtils.isEmpty(loginName)) {
            docName.setText(loginName);
        }
        if (!TextUtils.isEmpty(height)) {
            tvHeight.setText(height);
        }
        if (!TextUtils.isEmpty(weight)) {
            tvWeight.setText(weight);
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

    @OnClick({R.id.tv_change, R.id.tv_logout, R.id.tv_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_change:
                Intent intent = new Intent(this, ChangeWordActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.tv_logout:
                SharedPreferences.Editor edit = sp.edit();
                edit.putString(AppConst.LOGING_PASSWORD, "");
                edit.commit();
                Intent intentLog = new Intent();
                setResult(2, intentLog);
                Intent intentExit = new Intent(PersonalActivity.this, LoginActivity.class);
                startActivity(intentExit);
                finish();
                break;
            case R.id.tv_update:
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // 没有获得授权，申请授权
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        Toast.makeText(this, "请授权！", Toast.LENGTH_LONG).show();

                        // 帮跳转到该应用的设置界面，让用户手动授权
                        Intent intentSet = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", this.getPackageName(), null);
                        intentSet.setData(uri);
                        intentSet.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intentSet);
                    } else {
                        // 不需要解释为何需要该权限，直接请求授权
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 10000);
                    }
                } else {
                    // 已经获得授权，可以打电话
                    initUpdate();
                }
                break;
        }
    }

    private void initUpdate() {
        versionName = APKVersionCodeUtils.getVerName(this);

        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "appVesionCompare")
                .addParams("data", new appVesionCompare(Base.getMD5Str(), Base.getTimeSpan(), 1, "1", versionName).toJson())
                .build()
                .execute(new GsonCallBack<ApkUpBean>() {
            @Override
            public void onSuccess(ApkUpBean response) throws JSONException {
                appVersion = response.getData().getData().getApp_parent_version();
                appUrl = response.getData().getData().getApp_parent_version_url();
                if (appVersion.equals(versionName)) {
                    Toast.makeText(PersonalActivity.this, "已是最新版本", Toast.LENGTH_SHORT).show();
                } else {
                    showDialogUpdate();//弹出提示版本更新的对话框
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // TODO Auto-generated method stub
        switch (requestCode) {
            case 10000: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 授权成功，继续打电话
                    initUpdate();
                } else {
                    // 授权失败！
                    Toast.makeText(this, "授权失败！", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void showDialogUpdate() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 设置提示框的标题
        builder.setTitle("版本升级").
                // 设置要显示的信息
                        setMessage("发现新版本！请及时更新").
                // 设置确定按钮
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loadNewVersionProgress();//下载最新的版本程序
                    }
                }).setNegativeButton("取消", null);

        // 生产对话框
        AlertDialog alertDialog = builder.create();
        // 显示对话框
        alertDialog.show();

    }

    private void loadNewVersionProgress() {
        //进度条对话框
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.show();
        //启动子线程下载任务
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(appUrl, pd);
                    sleep(3000);
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    //下载apk失败
                    Log.i("1234567", "run: "+e.toString());
                    Message message = new Message();
                    message.arg1 = 1;
                    handler.sendMessage(message);
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == 1) {
                pd.dismiss();
                Toast.makeText(PersonalActivity.this, "下载新版本失败", Toast.LENGTH_LONG).show();
            }
        }
    };

    private void installApk(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data;
        // 判断版本大于等于7.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            data = FileProvider.getUriForFile(this, "com.shenkangyun.healthcenter.fileprovider", file);
            // 给目标应用一个临时授权
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            data = Uri.fromFile(file);
        }
        intent.setDataAndType(data, "application/vnd.android.package-archive");
        startActivity(intent);
    }

    private File getFileFromServer(String appUrl, ProgressDialog pd) throws IOException {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(appUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            pd.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();
            File file = new File(Environment.getExternalStorageDirectory(), "updata.apk");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            int total = 0;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                //获取当前下载量
                pd.setProgress(total);
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 2) {
            finish();
        }
    }

    static class appVesionCompare {
        private String appKey;
        private String timeSpan;
        private int appType;
        private String mobileType;
        private String version;

        public appVesionCompare(String appKey, String timeSpan, int appType, String mobileType, String version) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.appType = appType;
            this.mobileType = mobileType;
            this.version = version;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
