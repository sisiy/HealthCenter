package com.shenkangyun.healthcenter.IMFolder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jaeger.library.StatusBarUtil;
import com.shenkangyun.healthcenter.BeanFolder.AcceptBean;
import com.shenkangyun.healthcenter.IMFolder.Adapter.RequestAdapter;
import com.shenkangyun.healthcenter.R;
import com.shenkangyun.healthcenter.UtilFolder.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;

public class FriendRecommendActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.friend_recommend_list_view)
    RecyclerView friendRecommendListView;

    private ArrayList<String> names;
    private List<AcceptBean> acceptBeans;
    private RequestAdapter requestAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_recommend);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.home_red));
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("好友申请");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initData();
    }

    private void initView() {
        names = new ArrayList<>();
        acceptBeans = new ArrayList<>();
        requestAdapter = new RequestAdapter();
        layoutManager = new LinearLayoutManager(this);
        friendRecommendListView.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL,
                20, getResources().getColor(R.color.white)));
        friendRecommendListView.setLayoutManager(layoutManager);
        friendRecommendListView.setAdapter(requestAdapter);
    }

    private void initData() {
        names.clear();
        acceptBeans.clear();
        Intent intent = getIntent();
        names = intent.getStringArrayListExtra("names");
        if (names.size() != 0) {
            for (int i = 0; i < names.size(); i++) {
                AcceptBean acceptBean = new AcceptBean(1);
                acceptBean.setName(names.get(i));
                acceptBeans.add(acceptBean);
            }
            requestAdapter.setNewData(acceptBeans);
        }

        requestAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()) {
                    case R.id.tv_accept:
                        ContactManager.acceptInvitation(acceptBeans.get(position).getName(), null, new BasicCallback() {
                            @Override
                            public void gotResult(int responseCode, String responseMessage) {
                                if (responseCode == 0) {
                                    requestAdapter.remove(position);
                                    requestAdapter.notifyDataSetChanged();
                                }
                            }
                        });
                        break;
                    case R.id.tv_refuse:
                        ContactManager.declineInvitation(acceptBeans.get(position).getName(), null, null, new BasicCallback() {
                            @Override
                            public void gotResult(int responseCode, String responseMessage) {
                                if (responseCode == 0) {
                                    requestAdapter.remove(position);
                                    requestAdapter.notifyDataSetChanged();
                                }
                            }
                        });
                        break;
                    case R.id.right:
                        requestAdapter.remove(position);
                        requestAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
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
