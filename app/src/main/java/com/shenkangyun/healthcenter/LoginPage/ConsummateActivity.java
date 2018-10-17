package com.shenkangyun.healthcenter.LoginPage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.shenkangyun.healthcenter.BaseFolder.Base;
import com.shenkangyun.healthcenter.BeanFolder.JsonBean;
import com.shenkangyun.healthcenter.BeanFolder.UpDateBean;
import com.shenkangyun.healthcenter.DBFolder.User;
import com.shenkangyun.healthcenter.MainPage.MainActivity;
import com.shenkangyun.healthcenter.R;
import com.shenkangyun.healthcenter.UtilFolder.GetJsonDataUtil;
import com.shenkangyun.healthcenter.UtilFolder.GsonCallBack;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConsummateActivity extends AppCompatActivity {


    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.tv_birth)
    TextView tvBirth;
    @BindView(R.id.ed_age)
    EditText edAge;
    @BindView(R.id.ed_week)
    EditText edWeek;
    @BindView(R.id.ed_day)
    EditText edDay;
    @BindView(R.id.tv_complication)
    TextView tvComplication;
    @BindView(R.id.tv_province)
    TextView tvProvince;
    @BindView(R.id.tv_profession)
    TextView tvProfession;
    @BindView(R.id.tv_degree)
    TextView tvDegree;
    @BindView(R.id.ed_husbandAge)
    EditText edHusbandAge;
    @BindView(R.id.tv_husbandProfession)
    TextView tvHusbandProfession;
    @BindView(R.id.ed_weight)
    EditText edWeight;
    @BindView(R.id.ed_height)
    EditText edHeight;

    private String age;
    private String name;
    private String husbandAge;
    private String week;
    private String day;
    private String birth;
    private String complication;
    private String degree;
    private String husbandProfession;
    private String profession;
    private String province;
    private ListPopupWindow mListVocations;

    private ListPopupWindow mListEducations;
    private ListPopupWindow mListComplications;
    private List<String> vocations = new ArrayList<>();
    private List<String> educations = new ArrayList<>();
    private List<String> complications = new ArrayList<>();
    private ArrayList<JsonBean> options1Items = new ArrayList<>();

    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private boolean isLoaded = false;

    private String birthday;
    private String childWeeks;
    private String provinceID;
    private String cityID;
    private String regionID;
    private int HusbandProfession;
    private int Weeks;
    private int Degree;
    private int Complication;
    private int Profession;
    private String heightTv;
    private String weightTv;

    private int ageR;
    private int id;
    private int degreeR;
    private String nameR;
    private String idCard;
    private String mobile;
    private String loginName;
    private String height;
    private String weight;
    private int childWeeksR;
    private int husbandAgeR;
    private int professionR;
    private int complicationR;
    private int husbandProfessionR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consummate);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.home_red));
        initView();
        initPopupWindow();
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
    }

    private void initView() {
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

    private void initPopupWindow() {
        mListEducations = new ListPopupWindow(this);
        mListEducations.setAdapter(new ArrayAdapter<String>(this, R.layout.item_popup, educations));
        mListEducations.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        mListEducations.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mListEducations.setAnchorView(tvDegree);//设置ListPopupWindow的锚点，即关联PopupWindow的显示位置和这个锚点
        mListEducations.setModal(true);//设置是否是模式
        mListEducations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                tvDegree.setText(educations.get(position));
                Degree = position;
                mListEducations.dismiss();
            }
        });

        mListComplications = new ListPopupWindow(this);
        mListComplications.setAdapter(new ArrayAdapter<String>(this, R.layout.item_popup, complications));
        mListComplications.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        mListComplications.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mListComplications.setAnchorView(tvComplication);//设置ListPopupWindow的锚点，即关联PopupWindow的显示位置和这个锚点
        mListComplications.setModal(true);//设置是否是模式
        mListComplications.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                tvComplication.setText(complications.get(position));
                Complication = position;
                mListComplications.dismiss();
            }
        });
    }

    @OnClick({R.id.tv_birth, R.id.tv_degree, R.id.tv_profession, R.id.tv_husbandProfession, R.id.tv_complication,
            R.id.tv_province, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_birth:
                DatePickDialog datePickDialog = new DatePickDialog(this);
                TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
                //设置上下年分限制
                datePickDialog.setYearLimt(80);
                //设置标题
                datePickDialog.setTitle("选择时间");
                //设置类型
                datePickDialog.setType(DateType.TYPE_YMD);
                //设置点击确定按钮回调
                datePickDialog.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        birthday = simpleDateFormat.format(date);
                        tvBirth.setText(birthday);
                    }
                });
                datePickDialog.show();
                break;
            case R.id.tv_profession:
                mListVocations = new ListPopupWindow(this);
                mListVocations.setAdapter(new ArrayAdapter<String>(this, R.layout.item_popup, vocations));
                mListVocations.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
                mListVocations.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
                mListVocations.setAnchorView(tvProfession);//设置ListPopupWindow的锚点，即关联PopupWindow的显示位置和这个锚点
                mListVocations.setModal(true);//设置是否是模式
                mListVocations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        tvProfession.setText(vocations.get(position));
                        Profession = position;
                        mListVocations.dismiss();
                    }
                });
                mListVocations.show();
                break;
            case R.id.tv_degree:
                mListEducations.show();
                break;
            case R.id.tv_husbandProfession:
                mListVocations = new ListPopupWindow(this);
                mListVocations.setAdapter(new ArrayAdapter<String>(this, R.layout.item_popup, vocations));
                mListVocations.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
                mListVocations.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
                mListVocations.setAnchorView(tvHusbandProfession);//设置ListPopupWindow的锚点，即关联PopupWindow的显示位置和这个锚点
                mListVocations.setModal(true);//设置是否是模式
                mListVocations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        tvHusbandProfession.setText(vocations.get(position));
                        HusbandProfession = position;
                        mListVocations.dismiss();
                    }
                });
                mListVocations.show();
                break;
            case R.id.tv_complication:
                mListComplications.show();
                break;
            case R.id.tv_province:
                if (isLoaded) {
                    ShowPickerView();
                }
                break;
            case R.id.btn_save:
                age = edAge.getText().toString();
                name = edName.getText().toString();
                husbandAge = edHusbandAge.getText().toString();
                week = edWeek.getText().toString();
                day = edDay.getText().toString();
                birth = tvBirth.getText().toString();
                complication = tvComplication.getText().toString();
                degree = tvDegree.getText().toString();
                husbandProfession = tvHusbandProfession.getText().toString();
                profession = tvProfession.getText().toString();
                province = tvProvince.getText().toString();
                heightTv = edHeight.getText().toString();
                weightTv = edWeight.getText().toString();
                if (TextUtils.isEmpty(age)) {
                    Toast.makeText(this, "年龄不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(this, "姓名不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(husbandAge)) {
                    Toast.makeText(this, "配偶年龄不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(week)) {
                    Toast.makeText(this, "请填写具体周数！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(day)) {
                    Toast.makeText(this, "请填写具体天数！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(birth)) {
                    Toast.makeText(this, "生日不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(complication)) {
                    Toast.makeText(this, "请选择并发症类型！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(degree)) {
                    Toast.makeText(this, "请选择受教育程度！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(husbandProfession)) {
                    Toast.makeText(this, "请选择配偶职业类型！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(profession)) {
                    Toast.makeText(this, "请选择职业类型！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(province)) {
                    Toast.makeText(this, "请选择居住地！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(heightTv)) {
                    Toast.makeText(this, "体重不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(weightTv)) {
                    Toast.makeText(this, "身高不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                initSubmit();
                break;
        }
    }

    private void initSubmit() {
        Weeks = Integer.parseInt(week) * 7 + Integer.parseInt(day);
        childWeeks = String.valueOf(Weeks);
        degree = String.valueOf(Degree);
        complication = String.valueOf(Complication);
        husbandProfession = String.valueOf(HusbandProfession);
        profession = String.valueOf(Profession);
        OkHttpUtils.post()
                .url(Base.URL)
                .addParams("act", "UpdatePatient")
                .addParams("data", new UpdatePatient(Base.getMD5Str(), Base.getTimeSpan(), "1", "1", String.valueOf(Base.getUserID()),
                        Base.getIDCardP(), Base.getMobileP(), Base.getLoginNameP(), name, age, birth, degree, provinceID, cityID, regionID, profession, husbandAge,
                        husbandProfession, childWeeks, complication, heightTv, weightTv).toJson())
                .build()
                .execute(new GsonCallBack<UpDateBean>() {
                    @Override
                    public void onSuccess(UpDateBean response) throws JSONException {
                        String status = response.getStatus();
                        if ("0".equals(status)) {
                            initPatientData(response);
                            String data = response.getData().getData();
                            Toast.makeText(ConsummateActivity.this, data, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ConsummateActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {

                        }
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
    }

    private void initPatientData(UpDateBean response) {
        id = response.getPatient().get(0).getId();
        ageR = response.getPatient().get(0).getAge();
        nameR = response.getPatient().get(0).getName();
        idCard = response.getPatient().get(0).getIdCard();
        mobile = response.getPatient().get(0).getMobile();
        degreeR = response.getPatient().get(0).getDegree();
        loginName = response.getPatient().get(0).getLoginName();
        height = response.getPatient().get(0).getHeight();
        weight = response.getPatient().get(0).getWeight();
        childWeeksR = response.getPatient().get(0).getChildWeeks();
        husbandAgeR = response.getPatient().get(0).getHusbandAge();
        professionR = response.getPatient().get(0).getProfession();
        complicationR = response.getPatient().get(0).getComplication();
        husbandProfessionR = response.getPatient().get(0).getHusbandProfession();

        User user = new User();
        user.setUserID(id);
        user.setName(nameR);
        user.setMobile(mobile);
        user.setIdCard(idCard);
        user.setDegree(degreeR);
        user.setLoginName(loginName);
        user.setAge(ageR);
        user.setHeight(height);
        user.setWeight(weight);
        user.setChildWeeks(childWeeksR);
        user.setHusbandAge(husbandAgeR);
        user.setHusbandProfession(husbandProfessionR);
        user.setProfession(professionR);
        user.setComplication(complicationR);
        user.save();
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 写子线程中的操作,解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;
            }
        }
    };

    private void ShowPickerView() {// 弹出选择器
        final OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getName() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                tvProvince.setText(tx);
                Toast.makeText(ConsummateActivity.this, tx, Toast.LENGTH_SHORT).show();

                provinceID = options1Items.get(options1).getId();
                cityID = options1Items.get(options1).getCitylist().get(options2).getId();
                if (!(options1Items.get(options1).getCitylist().get(options2).getRegionlist().size() == 0)) {
                    regionID = options1Items.get(options1).getCitylist().get(options2).getRegionlist().get(options3).getId();
                }
                Log.i("info", "onOptionsSelect: " + provinceID + "\t" + cityID + "\t" + regionID);
            }
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText("城市选择")//标题
                .setSubCalSize(18)//确定和取消文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                .setTitleBgColor(Color.WHITE)
                .setContentTextSize(18)//滚轮文字大小
                .setOutSideCancelable(false)//点击外部dismiss default true
                .build();

        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void initJsonData() {//解析数据
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据
        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体
        /**
         * 添加省份数据
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;
        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            for (int c = 0; c < jsonBean.get(i).getCitylist().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCitylist().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表
                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCitylist().get(c).getRegionlist() == null
                        || jsonBean.get(i).getCitylist().get(c).getRegionlist().size() == 0) {
                    City_AreaList.add("");
                } else {
                    for (int d = 0; d < jsonBean.get(i).getCitylist().get(c).getRegionlist().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCitylist().get(c).getRegionlist().get(d).getName();
                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }
            options2Items.add(CityList);//添加城市数据
            options3Items.add(Province_AreaList);//添加地区数据
        }
        isLoaded = true;
    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

    static class UpdatePatient {
        private String appKey;
        private String timeSpan;
        private String mobileType;
        private String appType;
        private String id;
        private String idCard;
        private String mobile;
        private String loginName;
        private String name;
        private String age;
        private String brithday;
        private String degree;
        private String provinceID;
        private String cityID;
        private String regionID;
        private String profession;
        private String husbandAge;
        private String husbandProfession;
        private String childWeeks;
        private String complication;
        private String height;
        private String weight;


        public UpdatePatient(String appKey, String timeSpan, String mobileType, String appType, String id,
                             String idCard, String mobile, String loginName, String name, String age,
                             String brithday, String degree, String provinceID, String cityID, String regionID,
                             String profession, String husbandAge, String husbandProfession,
                             String childWeeks, String complication, String height, String weight) {
            this.appKey = appKey;
            this.timeSpan = timeSpan;
            this.mobileType = mobileType;
            this.appType = appType;
            this.id = id;
            this.idCard = idCard;
            this.mobile = mobile;
            this.loginName = loginName;
            this.name = name;
            this.age = age;
            this.brithday = brithday;
            this.degree = degree;
            this.provinceID = provinceID;
            this.cityID = cityID;
            this.regionID = regionID;
            this.profession = profession;
            this.husbandAge = husbandAge;
            this.husbandProfession = husbandProfession;
            this.childWeeks = childWeeks;
            this.complication = complication;
            this.weight = weight;
            this.height = height;
        }

        public String toJson() {
            return new Gson().toJson(this);
        }
    }
}
