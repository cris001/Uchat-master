package com.netease.nim.demo.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.nim.demo.DemoCache;
import com.netease.nim.demo.R;
import com.netease.nim.demo.common.ui.viewpager.FadeInOutPageTransformer;
import com.netease.nim.demo.common.ui.viewpager.PagerSlidingTabStrip;
import com.netease.nim.demo.contact.activity.QinjiaActivity;
import com.netease.nim.demo.main.activity.AskForHoliday;
import com.netease.nim.demo.main.activity.AttendanceActivity;
import com.netease.nim.demo.main.activity.CloudDiskActivity;
import com.netease.nim.demo.main.activity.LimitSettingActivity;
import com.netease.nim.demo.main.activity.MainActivity;
import com.netease.nim.demo.main.activity.SettingsActivity;
import com.netease.nim.demo.main.activity.TaskActivity;
import com.netease.nim.demo.main.adapter.MainTabPagerAdapter;
import com.netease.nim.demo.main.helper.SystemMessageUnreadManager;
import com.netease.nim.demo.main.model.MainTab;
import com.netease.nim.demo.main.reminder.ReminderItem;
import com.netease.nim.demo.main.reminder.ReminderManager;
import com.netease.nim.demo.session.SessionHelper;
import com.netease.nim.uikit.cache.NimUserInfoCache;
import com.netease.nim.uikit.common.fragment.TFragment;
import com.netease.nim.uikit.common.ui.drop.DropCover;
import com.netease.nim.uikit.common.ui.drop.DropManager;
import com.netease.nim.uikit.common.ui.imageview.HeadImageView;
import com.netease.nim.uikit.common.util.log.LogUtil;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.friend.FriendService;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.SystemMessageObserver;
import com.netease.nimlib.sdk.msg.SystemMessageService;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.netease.nimlib.sdk.team.TeamService;

import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * 主界面（导航页）
 */
public class HomeFragment extends TFragment implements OnPageChangeListener, ReminderManager.UnreadNumChangedCallback {

    private PagerSlidingTabStrip tabs;

    private ViewPager pager;

    private int scrollState;

    private MainTabPagerAdapter adapter;

    private View rootView;

    private NavigationView mNavigationView;

    private DrawerLayout drawerLayout;

    private Class mClass;

    public HomeFragment() {
        setContainerId(R.id.welcome_container);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.main, container, false);
        return rootView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setToolBar(R.id.toolbar, R.string.app_name, R.drawable.actionbar_dark_logo);

        setTitle(R.string.app_name);

        findViews();
        setupPager();
        setupTabs();
        registerMsgUnreadInfoObserver(true);
        registerSystemMessageObservers(true);
        requestSystemMessageUnreadCount();
        initUnreadCover();


        initNavigationView();

        NIMClient.getService(FriendService.class).deleteFriend("p_38771489924125630");
        NIMClient.getService(TeamService.class).quitTeam("22433805");
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // TO TABS
        tabs.onPageScrolled(position, positionOffset, positionOffsetPixels);
        // TO ADAPTER
        adapter.onPageScrolled(position);
    }

    @Override
    public void onPageSelected(int position) {
        // TO TABS
        tabs.onPageSelected(position);

        selectPage(position);

        enableMsgNotification(false);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // TO TABS
        tabs.onPageScrollStateChanged(state);

        scrollState = state;

        selectPage(pager.getCurrentItem());
    }

    private void selectPage(int page) {
        // TO PAGE
        if (scrollState == ViewPager.SCROLL_STATE_IDLE) {
            adapter.onPageSelected(pager.getCurrentItem());
        }
    }

    public void switchTab(int tabIndex, String params) {
        pager.setCurrentItem(tabIndex);
    }

    /**
     * 查找页面控件
     */
    private void findViews() {
        tabs = findView(R.id.tabs);
        pager = findView(R.id.main_tab_pager);
    }

    @Override
    public void onResume() {
        super.onResume();
        enableMsgNotification(false);
        //quitOtherActivities();
    }

    @Override
    public void onPause() {
        super.onPause();
        enableMsgNotification(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        registerMsgUnreadInfoObserver(false);
        registerSystemMessageObservers(false);
    }

    public boolean onBackPressed() {
        return false;
    }

    public boolean onClick(View v) {
        return true;
    }

    /**
     * 设置viewPager
     */
    private void setupPager() {
        // CACHE COUNT
        adapter = new MainTabPagerAdapter(getFragmentManager(), getActivity(), pager);
        pager.setOffscreenPageLimit(adapter.getCacheCount());
        // page swtich animation
        pager.setPageTransformer(true, new FadeInOutPageTransformer());
        // ADAPTER
        pager.setAdapter(adapter);
        // TAKE OVER CHANGE
        pager.setOnPageChangeListener(this);
    }

    /**
     * 设置tab条目
     */
    private void setupTabs() {
        tabs.setOnCustomTabListener(new PagerSlidingTabStrip.OnCustomTabListener() {
            @Override
            public int getTabLayoutResId(int position) {
                return R.layout.tab_layout_main;
            }

            @Override
            public boolean screenAdaptation() {
                return true;
            }
        });
        tabs.setViewPager(pager);
        tabs.setOnTabClickListener(adapter);
        tabs.setOnTabDoubleTapListener(adapter);
    }

    private void enableMsgNotification(boolean enable) {
        boolean msg = (pager.getCurrentItem() != MainTab.RECENT_CONTACTS.tabIndex);
        if (enable | msg) {
            /**
             * 设置最近联系人的消息为已读
             *
             * @param account,    聊天对象帐号，或者以下两个值：
             *                    {@link #MSG_CHATTING_ACCOUNT_ALL} 目前没有与任何人对话，但能看到消息提醒（比如在消息列表界面），不需要在状态栏做消息通知
             *                    {@link #MSG_CHATTING_ACCOUNT_NONE} 目前没有与任何人对话，需要状态栏消息通知
             */
            NIMClient.getService(MsgService.class).setChattingAccount(MsgService.MSG_CHATTING_ACCOUNT_NONE, SessionTypeEnum.None);
        } else {
            NIMClient.getService(MsgService.class).setChattingAccount(MsgService.MSG_CHATTING_ACCOUNT_ALL, SessionTypeEnum.None);
        }
    }

    /**
     * 注册未读消息数量观察者
     */
    private void registerMsgUnreadInfoObserver(boolean register) {
        if (register) {
            ReminderManager.getInstance().registerUnreadNumChangedCallback(this);
        } else {
            ReminderManager.getInstance().unregisterUnreadNumChangedCallback(this);
        }
    }

    /**
     * 未读消息数量观察者实现
     */
    @Override
    public void onUnreadNumChanged(ReminderItem item) {
        MainTab tab = MainTab.fromReminderId(item.getId());
        if (tab != null) {
            tabs.updateTab(tab.tabIndex, item);
        }
    }

    /**
     * 注册/注销系统消息未读数变化
     *
     * @param register
     */
    private void registerSystemMessageObservers(boolean register) {
        NIMClient.getService(SystemMessageObserver.class).observeUnreadCountChange(sysMsgUnreadCountChangedObserver,
                register);
    }

    private Observer<Integer> sysMsgUnreadCountChangedObserver = new Observer<Integer>() {
        @Override
        public void onEvent(Integer unreadCount) {
            SystemMessageUnreadManager.getInstance().setSysMsgUnreadCount(unreadCount);
            ReminderManager.getInstance().updateContactUnreadNum(unreadCount);
        }
    };

    /**
     * 查询系统消息未读数
     */
    private void requestSystemMessageUnreadCount() {
        int unread = NIMClient.getService(SystemMessageService.class).querySystemMessageUnreadCountBlock();
        SystemMessageUnreadManager.getInstance().setSysMsgUnreadCount(unread);
        ReminderManager.getInstance().updateContactUnreadNum(unread);
    }

    /**
     * 初始化未读红点动画
     */
    private void initUnreadCover() {
        DropManager.getInstance().init(getContext(), (DropCover) findView(R.id.unread_cover),
                new DropCover.IDropCompletedListener() {
            @Override
            public void onCompleted(Object id, boolean explosive) {
                if (id == null || !explosive) {
                    return;
                }

                if (id instanceof RecentContact) {
                    RecentContact r = (RecentContact) id;
                    NIMClient.getService(MsgService.class).clearUnreadCount(r.getContactId(), r.getSessionType());
                    LogUtil.i("HomeFragment", "clearUnreadCount, sessionId=" + r.getContactId());
                } else if (id instanceof String) {
                    if (((String) id).contentEquals("0")) {
                        List<RecentContact> recentContacts = NIMClient.getService(MsgService.class).queryRecentContactsBlock();
                        for (RecentContact r : recentContacts) {
                            if (r.getUnreadCount() > 0) {
                                NIMClient.getService(MsgService.class).clearUnreadCount(r.getContactId(), r.getSessionType());
                            }
                        }
                        LogUtil.i("HomeFragment", "clearAllUnreadCount");
                    } else if (((String) id).contentEquals("1")) {
                        NIMClient.getService(SystemMessageService.class).resetSystemMessageUnreadCount();
                        LogUtil.i("HomeFragment", "clearAllSystemUnreadCount");
                    }
                }
            }
        });
    }

    /**
     * 初始化导航栏
     */
    private void initNavigationView() {


//对于不是本地布局文件中的view进行初始化时需要初始化view之后再采用view.findviewById()的方式进行初始化
//LayoutInflater inflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
//View view=LayoutInflater.from(getActivity()).inflate(R.layout.include_navigation_view,null);

        //fragment中的按钮初始化通过getActivity()来获取，否则无法响应点击事件
        drawerLayout=(DrawerLayout)(getActivity().findViewById(R.id.drawer_layout)) ;
        mNavigationView=(NavigationView)(getActivity().findViewById(R.id.navigation_view));

        //根据权限设置navigation是否显示请假功能
        MenuItem item = mNavigationView.getMenu().getItem(4);
        Log.i("--------",item.getItemId()+"");
        String permission=DemoCache.getPermission();
        if(!"student".equals(permission)){
            item.setVisible(false);
        }

        //加载头像及设置其监听事件
        View headview = mNavigationView.getHeaderView(0);
        HeadImageView imageView=(HeadImageView)headview.findViewById(R.id.avatar);
        imageView.loadBuddyAvatar(DemoCache.getAccount());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("-------","imageView is clicked!");
                startActivity(new Intent(getActivity(),SettingsActivity.class));
            }
        });

        //加载nickname
        TextView textView=(TextView)headview.findViewById(R.id.navigation_nickname);
        textView.setText(NimUserInfoCache.getInstance().getUserDisplayName(DemoCache.getAccount()));

        //获取身份
        TextView identity_text=(TextView)headview.findViewById(R.id.identity_text);
        Log.i("-----------",DemoCache.getPermission()+"123");
//        identity_text.setText(DemoCache.getPermission());




        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);

                switch(item.getItemId()){
                    case R.id.action_chat:{
                        mClass=MainActivity.class;
                        Log.i("-------","chat");
                        break;

                    }
                    case R.id.action_attendance:{
                        mClass=AttendanceActivity.class;
                        Log.i("-------","attendance");
                        break;
                    }
                    case R.id.action_cloud:{
//                        mClass=CloudDiskActivity.class;
//                        Log.i("-------","cloud disk");
//                        break;
                        SessionHelper.startP2PSession(getActivity(), DemoCache.getAccount());
                        return true;
                    }
                    case R.id.action_task:{
                        mClass= TaskActivity.class;
                        Log.i("-------","task");
                        break;
                    }

                    case R.id.action_holiday:{

//                        mClass= AskForHoliday.class;
                        mClass= QinjiaActivity.class;
                        Log.i("-------","holiday");
                        break;
                    }
                }
                drawerLayout.closeDrawers();
                startActivity(new Intent(getActivity(),mClass));

                return true;
            }
        });
    }
}