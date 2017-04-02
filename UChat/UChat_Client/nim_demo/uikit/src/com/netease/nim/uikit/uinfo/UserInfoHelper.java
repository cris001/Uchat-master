package com.netease.nim.uikit.uinfo;

import com.netease.nim.uikit.NimUIKit;
import com.netease.nim.uikit.cache.NimUserInfoCache;
import com.netease.nim.uikit.cache.TeamDataCache;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;

import java.util.List;

public class UserInfoHelper {

    private static UserInfoObservable userInfoObservable;

    private static String titleName;

    public static String getTitleName() {
        return titleName;
    }

    // 获取用户显示在标题栏和最近联系人中的名字
    public static String getUserTitleName(String id, SessionTypeEnum sessionType) {
        if (sessionType == SessionTypeEnum.P2P) {
            if (NimUIKit.getAccount().equals(id)) {
                NIMClient.getService(MsgService.class).clearChattingHistory(NimUIKit.getAccount(), SessionTypeEnum.P2P);
                titleName="yunpan";
                return "我的云盘";
            } else {
                titleName="other";
                return NimUserInfoCache.getInstance().getUserDisplayName(id);
            }
        }  else if (sessionType == SessionTypeEnum.Team) {
            return TeamDataCache.getInstance().getTeamName(id);
        }
        return id;
    }

    /**
     * 注册用户资料变化观察者。<br>
     *     注意：不再观察时(如Activity destroy后)，要unregister，否则会造成资源泄露
     * @param observer 观察者
     */
    public static void registerObserver(UserInfoObservable.UserInfoObserver observer) {
        if (userInfoObservable == null) {
            userInfoObservable = new UserInfoObservable(NimUIKit.getContext());
        }
        userInfoObservable.registerObserver(observer);
    }

    /**
     * 注销用户资料变化观察者。
     * @param observer 观察者
     */
    public static void unregisterObserver(UserInfoObservable.UserInfoObserver observer) {
        if (userInfoObservable != null) {
            userInfoObservable.unregisterObserver(observer);
        }
    }

    /**
     * 当用户资料发生改动时，请调用此接口，通知更新UI
     * @param accounts 有用户信息改动的帐号列表
     */
    public static void notifyChanged(List<String> accounts) {
        if (userInfoObservable != null) {
            userInfoObservable.notifyObservers(accounts);
        }
    }
}
