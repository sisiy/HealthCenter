package com.shenkangyun.healthcenter.MainPage.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shenkangyun.healthcenter.DBFolder.User;
import com.shenkangyun.healthcenter.IMFolder.FriendsListActivity;
import com.shenkangyun.healthcenter.MainPage.KnowledgeActivity;
import com.shenkangyun.healthcenter.MainPage.MedicalRecordsActivity;
import com.shenkangyun.healthcenter.MainPage.PatientMessageActivity;
import com.shenkangyun.healthcenter.MainPage.PersonalActivity;
import com.shenkangyun.healthcenter.MainPage.QuestionnaireActivity;
import com.shenkangyun.healthcenter.R;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/30.
 */

public class HomePageFragment extends Fragment {

    @BindView(R.id.tv_word)
    TextView tvWord;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_age)
    TextView tvAge;

    private int age;
    private String name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        List<User> all = LitePal.findAll(User.class);
        for (int i = 0; i < all.size(); i++) {
            name = all.get(i).getName();
            age = all.get(i).getAge();
        }

        if (!TextUtils.isEmpty(name)) {
            String firstWord = name.substring(0, 1);
            StringBuilder builder = new StringBuilder();
            builder.append(String.valueOf(age)).append("岁");
            tvName.setText(name);
            tvWord.setText(firstWord);
            tvAge.setText(builder.toString());
        }
    }

    @OnClick({R.id.cv_interactive, R.id.cv_knowledge, R.id.cv_survey, R.id.cv_axis, R.id.cv_record, R.id.cv_user})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cv_interactive:
                Intent intentMine = new Intent(getContext(), FriendsListActivity.class);
                startActivity(intentMine);
                break;
            case R.id.cv_knowledge:
                Intent intentKnowledge = new Intent(getContext(), KnowledgeActivity.class);
                startActivity(intentKnowledge);
                break;
            case R.id.cv_survey:
                Intent intentQuestion = new Intent(getContext(), QuestionnaireActivity.class);
                startActivity(intentQuestion);
                break;
            case R.id.cv_axis:
                Intent intentPatient = new Intent(getContext(), PatientMessageActivity.class);
                startActivity(intentPatient);
                break;
            case R.id.cv_record:
                Intent intentMedical = new Intent(getContext(), MedicalRecordsActivity.class);
                startActivity(intentMedical);
                break;
            case R.id.cv_user:
                Intent intentPersonal = new Intent(getContext(), PersonalActivity.class);
                startActivityForResult(intentPersonal, 0);
                break;
        }
    }
}
