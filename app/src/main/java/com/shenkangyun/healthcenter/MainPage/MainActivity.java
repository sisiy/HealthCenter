package com.shenkangyun.healthcenter.MainPage;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.shenkangyun.healthcenter.IMFolder.FriendRecommendActivity;
import com.shenkangyun.healthcenter.IMFolder.FriendsListActivity;
import com.shenkangyun.healthcenter.MainPage.Fragment.ChatRecordFragment;
import com.shenkangyun.healthcenter.MainPage.Fragment.HomePageFragment;
import com.shenkangyun.healthcenter.R;
import com.shenkangyun.healthcenter.UtilFolder.NetworkChangeReceiver;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.event.ContactNotifyEvent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.NotificationClickEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_user)
    ImageView imgUser;
    @BindView(R.id.img_chat)
    ImageView imgChat;
    @BindView(R.id.tv_count)
    TextView tvCount;

    private String nickname;
    private String targetId;
    private String latestText;
    public static ArrayList<String> forAddFriend = new ArrayList<>();
    private FragmentManager fragmentManager;
    private Fragment HomeFragment, ChatFragment, replaceFragment;

    private IntentFilter intentFilter;
    private NetworkChangeReceiver changeReceiver;

    private String name;
    public static boolean isForeground = false;

    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.home_red));
        intentFilter = new IntentFilter();
        changeReceiver = new NetworkChangeReceiver();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(changeReceiver, intentFilter);
        JMessageClient.registerEventReceiver(this);
        initView();
        initFragment();
    }

    public void onEventMainThread(ContactNotifyEvent event) {
        Log.i("1234567", "onEventMainThread: " + event);
        final String username = event.getFromUsername();
        final String appKey = event.getfromUserAppKey();
        if (event.getType() == ContactNotifyEvent.Type.invite_received) {
            //如果同一个人申请多次,则只会出现一次;当点击进验证消息界面后,同一个人再次申请则可以收到
            if (forAddFriend.size() > 0) {
                for (String forAdd : forAddFriend) {
                    if (forAdd.equals(username)) {
                        return;
                    } else {
                        forAddFriend.add(username);
                    }
                }
            } else {
                forAddFriend.add(username);
            }
            tvCount.setText(String.valueOf(forAddFriend.size()));
            JMessageClient.getUserInfo(username, appKey, new GetUserInfoCallback() {
                @Override
                public void gotResult(int status, String desc, UserInfo userInfo) {
                    if (status == 0) {
                        name = userInfo.getNickname();
                        if (TextUtils.isEmpty(name)) {
                            name = userInfo.getUserName();
                        }

                    }
                }
            });
        } else if (event.getType() == ContactNotifyEvent.Type.invite_declined) {
            Toast.makeText(this, "对方拒绝了您的好友请求", Toast.LENGTH_SHORT).show();
        }
    }

    public void onEventMainThread(MessageEvent event) {
        Message msg = event.getMessage();
        final UserInfo userInfo = (UserInfo) msg.getTargetInfo();
        targetId = userInfo.getUserName();
        nickname = userInfo.getNickname();
        Conversation conv = JMessageClient.getSingleConversation(targetId, userInfo.getAppKey());
        latestText = conv.getLatestText();
    }

    public void onEventMainThread(NotificationClickEvent event) {
        UserInfo fromUser = event.getMessage().getFromUser();
        Intent intent = new Intent(this, ChatRecordFragment.class);
        intent.putExtra("phone", fromUser.getUserName());
        intent.putExtra("message", latestText);
        startActivity(intent);
    }

    private void initView() {
        HomeFragment = new HomePageFragment();
        ChatFragment = new ChatRecordFragment();
        tvTitle.setText("首页");
        fragmentManager = getSupportFragmentManager();

    }

    private void initFragment() {
        FragmentTransaction Transaction = fragmentManager.beginTransaction();
        Transaction.add(R.id.layout_public, ChatFragment).commit();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.layout_public, HomeFragment).commit();
        replaceFragment = HomeFragment;
    }

    @OnClick({R.id.btn_home, R.id.btn_community, R.id.img_chat, R.id.img_user})
    public void onViewClicked(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.btn_home:
                tvTitle.setText("首页");
                imgUser.setVisibility(View.VISIBLE);
                replaceFragment(HomeFragment, fragmentTransaction);
                break;
            case R.id.btn_community:
                tvTitle.setText("消息");
                imgUser.setVisibility(View.GONE);
                replaceFragment(ChatFragment, fragmentTransaction);
                break;
            case R.id.img_chat:
                Intent intent = new Intent(this, FriendRecommendActivity.class);
                intent.putExtra("names", forAddFriend);
                startActivity(intent);
                break;
            case R.id.img_user:
                Intent intentUser = new Intent(this, FriendsListActivity.class);
                startActivity(intentUser);
                break;
        }
    }

    public void replaceFragment(Fragment showFragment, FragmentTransaction fragmentTransaction) {
        if (showFragment.isAdded()) {
            fragmentTransaction.hide(replaceFragment).show(showFragment).commit();
        } else {
            fragmentTransaction.hide(replaceFragment).add(R.id.layout_public, showFragment).commit();
        }
        replaceFragment = showFragment;
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(changeReceiver);
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 1) {
            finish();
        }
        if (requestCode == 0 && resultCode == 2) {
            finish();
        }
    }
}
