package com.shenkangyun.healthcenter.IMFolder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jaeger.library.StatusBarUtil;
import com.shenkangyun.healthcenter.BeanFolder.FriendBean;
import com.shenkangyun.healthcenter.IMFolder.Adapter.FriendsListAdapter;
import com.shenkangyun.healthcenter.R;
import com.shenkangyun.healthcenter.UtilFolder.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.android.api.callback.GetUserInfoListCallback;
import cn.jpush.im.android.api.model.UserInfo;

public class FriendsListActivity extends AppCompatActivity {

    @BindView(R.id.toolBar_title)
    TextView toolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.friendRecycler)
    RecyclerView friendRecycler;

    private LinearLayoutManager manager;
    private FriendsListAdapter friendsListAdapter;
    private List<FriendBean> totalList = new ArrayList<>();

    private String nickname;
    private String userName;
    private long birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.home_red));
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolBarTitle.setText("好友列表");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        initView();
        initData();
    }

    private void initView() {
        totalList.clear();
        friendsListAdapter = new FriendsListAdapter();
        manager = new LinearLayoutManager(FriendsListActivity.this);
        friendRecycler.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL,
                10, getResources().getColor(R.color.white)));
        friendRecycler.setLayoutManager(manager);
        friendRecycler.setAdapter(friendsListAdapter);
    }

    private void initData() {
        final List<FriendBean> friendBeans = new ArrayList<>();
        ContactManager.getFriendList(new GetUserInfoListCallback() {
            @Override
            public void gotResult(int i, String s, List<UserInfo> list) {
                if (0 == i) {
                    for (int j = 0; j < list.size(); j++) {
                        FriendBean friendBean = new FriendBean();
                        nickname = list.get(j).getNickname();
                        userName = list.get(j).getUserName();
                        birthday = list.get(j).getBirthday();
                        friendBean.setNickName(nickname);
                        friendBean.setUserName(userName);
                        friendBean.setBirthday(birthday);
                        friendBeans.add(friendBean);
                        totalList.add(friendBean);
                    }
                    friendsListAdapter.setNewData(friendBeans);
                }
            }
        });
        initClick();
    }

    private void initClick() {
        friendsListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(FriendsListActivity.this, FriendInfoActivity.class);
                intent.putExtra("nickname", totalList.get(position).getNickName());
                intent.putExtra("userName", totalList.get(position).getUserName());
                intent.putExtra("birthday", totalList.get(position).getBirthday());
                startActivity(intent);
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

    @OnClick(R.id.tv_add)
    public void onViewClicked() {
        Intent intent = new Intent(this, AcceptFriendActivity.class);
        startActivity(intent);
    }
}
