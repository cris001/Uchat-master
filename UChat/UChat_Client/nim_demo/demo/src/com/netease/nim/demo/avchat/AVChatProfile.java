package com.netease.nim.demo.avchat;

/**
 * Created by AllStar on 2017/3/1.
 */
public class AVChatProfile {

    private final String TAG = "AVChatProfile";

    private boolean isAVChatting = false; // 是否正在音视频通话

    public static AVChatProfile getInstance() {
        return InstanceHolder.instance;
    }

    public boolean isAVChatting() {
        return isAVChatting;
    }

    public void setAVChatting(boolean chating) {
        isAVChatting = chating;
    }

    private static class InstanceHolder {
        public final static AVChatProfile instance = new AVChatProfile();
    }
}
