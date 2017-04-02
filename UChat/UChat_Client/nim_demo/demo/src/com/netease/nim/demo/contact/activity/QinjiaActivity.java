package com.netease.nim.demo.contact.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.nim.demo.DemoCache;
import com.netease.nim.demo.R;
import com.netease.nim.demo.team.activity.AdvancedTeamSearchActivity;
import com.netease.nim.uikit.cache.NimUserInfoCache;
import com.netease.nim.uikit.cache.TeamDataCache;
import com.netease.nim.uikit.common.activity.UI;
import com.netease.nim.uikit.common.ui.dialog.DialogMaker;
import com.netease.nim.uikit.common.ui.dialog.EasyAlertDialogHelper;
import com.netease.nim.uikit.common.ui.dialog.EasyEditDialog;
import com.netease.nim.uikit.common.ui.widget.ClearableEditTextWithIcon;
import com.netease.nim.uikit.common.util.sys.NetworkUtil;
import com.netease.nim.uikit.model.ToolBarOptions;
import com.netease.nim.uikit.team.activity.AdvancedTeamInfoActivity;
import com.netease.nim.uikit.team.activity.NormalTeamInfoActivity;
import com.netease.nim.uikit.team.model.TeamExtras;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.ResponseCode;
import com.netease.nimlib.sdk.friend.FriendService;
import com.netease.nimlib.sdk.friend.constant.VerifyType;
import com.netease.nimlib.sdk.friend.model.AddFriendData;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.team.TeamService;
import com.netease.nimlib.sdk.team.constant.TeamTypeEnum;
import com.netease.nimlib.sdk.team.model.Team;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加好友页面
 * Created by AllStar on 2017/3/9.
 */
public class QinjiaActivity extends UI {

    private ClearableEditTextWithIcon searchEdit;
    private static final String TAG = "qingjiaInfoActivity";

    private String tid;

    private ArrayList<String> list=new ArrayList();

    public static final void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, QinjiaActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qinjia_activity);

        ToolBarOptions options = new ToolBarOptions();
        options.titleId = R.string.qin;
        setToolBar(R.id.toolbar, options);

        findViews();
        initActionbar();
    }

    private void findViews() {
        searchEdit = findView(R.id.search_friend_edit);
        searchEdit.setDeleteImage(R.drawable.nim_grey_delete_icon);
    }

    private void initActionbar() {
        TextView toolbarView = findView(R.id.action_bar_right_clickable_textview);
        toolbarView.setText(R.string.ok);
        toolbarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (DemoCache.getCountTime()==0){
                    list.add(DemoCache.getAccount());
//                  inviteMembers(list);
                    List<Team> team=new ArrayList<>();
                    team = getAdvancedTeams();
                    tid=team.get(0).getId();
                    DemoCache.setClassId(tid);
                }

                DemoCache.setCountTime(1);
                queryTeamById();
            }
        });
    }


    //请假请求      searchTeam("23315847")为该学生所属的群号
    private void queryTeamById() {
//        NIMClient.getService(TeamService.class).searchTeam("24482956").setCallback(new RequestCallback<Team>() {


//        退群
        NIMClient.getService(TeamService.class).quitTeam(DemoCache.getClassId()).setCallback(new RequestCallback<Void>() {
            @Override
            public void onSuccess(Void param) {
                DialogMaker.dismissProgressDialog();
//                Toast.makeText(NormalTeamInfoActivity.this, com.netease.nim.uikit.R.string.quit_normal_team_success, Toast.LENGTH_SHORT).show();
                setResult(Activity.RESULT_OK, new Intent().putExtra(TeamExtras.RESULT_EXTRA_REASON, TeamExtras.RESULT_EXTRA_REASON_QUIT));

                NIMClient.getService(MsgService.class).deleteRecentContact2(DemoCache.getClassId(), SessionTypeEnum.Team);
                finish();
            }

            @Override
            public void onFailed(int code) {
                DialogMaker.dismissProgressDialog();
//                Toast.makeText(NormalTeamInfoActivity.this, com.netease.nim.uikit.R.string.quit_normal_team_failed, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onException(Throwable exception) {
                DialogMaker.dismissProgressDialog();
            }
        });



//        申请入群
        NIMClient.getService(TeamService.class).applyJoinTeam(DemoCache.getClassId(), null).setCallback(new RequestCallback<Team>() {
            @Override
            public void onSuccess(Team team) {

                Toast.makeText(QinjiaActivity.this, "请假审批发送成功", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailed(int code) {
                Toast.makeText(QinjiaActivity.this, "请假审批发送成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onException(Throwable exception) {
//                Toast.makeText(AdvancedTeamSearchActivity.this, "search team exception：" + exception.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

////        进群
//        NIMClient.getService(TeamService.class).addMembers(DemoCache.getClassId(), list).setCallback(new RequestCallback<Void>() {
//            @Override
//            public void onSuccess(Void param) {
//                System.out.println("qqqqqqqqqqq   "+tid);
//            }
//
//            @Override
//            public void onFailed(int code) {
//                System.out.println("wwwwwwwwwwwwwww  "+tid);
//            }
//
//            @Override
//            public void onException(Throwable exception) {
//                System.out.println("eeeeeeeeeee  "+tid);
//            }
//        });


    }


    private List<Team> getAllTeamsByType(TeamTypeEnum type) {
        List<Team> teams = new ArrayList<>();
        for (Team t : TeamDataCache.getInstance().getId2TeamMap().values()) {
            if (t.isMyTeam() && t.getType() == type) {
                teams.add(t);
            }
        }

        return teams;
    }

    private List<Team> getAdvancedTeams() {
        return getAllTeamsByType(TeamTypeEnum.Advanced);
    }






    private void query() {
        DialogMaker.showProgressDialog(this, null, false);
        final String account = searchEdit.getText().toString().toLowerCase();
        NimUserInfoCache.getInstance().getUserInfoFromRemote(account, new RequestCallback<NimUserInfo>() {
            @Override
            public void onSuccess(NimUserInfo user) {
                DialogMaker.dismissProgressDialog();
                if (user == null) {
                    EasyAlertDialogHelper.showOneButtonDiolag(QinjiaActivity.this, R.string.user_not_exsit,
                            R.string.user_tips, R.string.ok, false, null);
                } else {
                    UserProfileActivity.start(QinjiaActivity.this, account);
                }
            }

            @Override
            public void onFailed(int code) {
                DialogMaker.dismissProgressDialog();
                if (code == 408) {
                    Toast.makeText(QinjiaActivity.this, R.string.network_is_not_available, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QinjiaActivity.this, "on failed:" + code, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onException(Throwable exception) {
                DialogMaker.dismissProgressDialog();
                Toast.makeText(QinjiaActivity.this, "on exception:" + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 请假审批发送
     *
     * @param accounts 发送帐号
     */
    private void inviteMembers(ArrayList<String> accounts) {
        NIMClient.getService(TeamService.class).addMembers("23315847", accounts).setCallback(new RequestCallback<Void>() {
            @Override
            public void onSuccess(Void param) {
                Toast.makeText(QinjiaActivity.this, "请假审批发送成功", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailed(int code) {
                if (code == ResponseCode.RES_TEAM_INVITE_SUCCESS) {
                    Toast.makeText(QinjiaActivity.this, "请假审批发送成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QinjiaActivity.this, " failed, code=" + code, Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "failed, code=" + code);
                }
            }

            @Override
            public void onException(Throwable exception) {

            }
        });
    }




}
