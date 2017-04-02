package com.netease.nim.demo.main.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.netease.nim.demo.R;
import com.netease.nim.demo.chatroom.fragment.ChatRoomsFragment;
import com.netease.nim.demo.main.model.MainTab;
import com.netease.nim.uikit.common.adapter.TAdapter;
import com.netease.nim.uikit.common.adapter.TAdapterDelegate;
import com.netease.nim.uikit.common.adapter.TViewHolder;
import com.netease.nim.uikit.team.helper.AnnouncementHelper;
import com.netease.nim.uikit.team.model.Announcement;
import com.netease.nim.uikit.team.viewholder.TeamAnnounceHolder;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.team.TeamService;
import com.netease.nimlib.sdk.team.constant.TeamTypeEnum;
import com.netease.nimlib.sdk.team.model.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * 聊天室主TAB页
 * Created by AllStar on 2017/3/9.
 */
public class ChatRoomListFragment extends MainTabFragment implements TAdapterDelegate {
    private Handler uiHandler;
    // data
    private String teamId;
    private String announceId;
    private String announce;
    // view
    private TextView announceTips;
    private ListView announceListView;
    private SwipeRefreshLayout mSwipeLayout;
    private TAdapter mAdapter;
    private List<Announcement> items;

    private boolean isMember = false;
    private Bundle savedInstanceState;
    private static final int REFRESH_UI = 0X110;
    private ChatRoomsFragment fragment;
    public ChatRoomListFragment() {
        setContainerId(MainTab.CHAT_ROOM.fragmentId);
    }

    @Override
    protected void onInit() {
        announceListView = (ListView) getView().findViewById(R.id.lv_notices);
        mSwipeLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipe_ly);
        //设置监听
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessageDelayed(REFRESH_UI, 2000);
            }
        });
        initAdapter();
        show();
    }

    private void show() {
        items.clear();
        NIMClient.getService(TeamService.class).queryTeamListByType(TeamTypeEnum.Advanced)
                .setCallback(new RequestCallback<List<Team>>() {

                    @Override
                    public void onSuccess(List<Team> teams) {
                        for(Team team:teams){
                            teamId = team.getId();
                            announce = team.getAnnouncement();
                            setAnnounceItem();
                        }
                    }

                    @Override
                    public void onFailed(int i) {

                    }

                    @Override
                    public void onException(Throwable throwable) {

                    }
                });
    }

    private Handler mHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            switch (msg.what)
            {
                case REFRESH_UI:
                    show();
                    //刷新adapter
                    mAdapter.notifyDataSetChanged();
                    //关闭刷新动画
                    mSwipeLayout.setRefreshing(false);
                    break;

            }
        };
    };

    private void initAdapter() {
        items = new ArrayList<>();
        mAdapter = new TAdapter(getContext(), items, this);
        announceListView.setAdapter(mAdapter);
        announceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        announceListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    /**
     * 设置公告
     */
    private void setAnnounceItem() {
        List<Announcement> list = AnnouncementHelper.getAnnouncements(teamId, announce, isMember ? 5 : Integer.MAX_VALUE);
        Log.e("---------",teamId+"");
        if (list == null || list.isEmpty()) {
            return;
        }


        items.addAll(list);
        mAdapter.notifyDataSetChanged();

    }

    /**
     * ************************ TAdapterDelegate **************************
     */
    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public Class<? extends TViewHolder> viewHolderAtPosition(int position) {
        return TeamAnnounceHolder.class;
    }

    @Override
    public boolean enabled(int position) {
        return false;
    }
}
