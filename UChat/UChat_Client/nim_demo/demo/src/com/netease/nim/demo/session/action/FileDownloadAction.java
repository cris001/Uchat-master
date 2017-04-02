package com.netease.nim.demo.session.action;

import android.content.Intent;

import com.netease.nim.demo.R;
import com.netease.nim.demo.file.browser.FileBrowserActivity;
import com.netease.nim.demo.session.activity.MessageHistoryActivity;
import com.netease.nim.uikit.NimUIKit;
import com.netease.nim.uikit.session.constant.RequestCode;
import com.netease.nim.uikit.session.actions.BaseAction;
import com.netease.nim.uikit.session.module.input.InputPanel;
import com.netease.nim.uikit.uinfo.UserInfoHelper;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;

import java.io.File;

/**
 * Created by AllStar on 2017/3/19.
 */
public class FileDownloadAction extends BaseAction {

    public FileDownloadAction() {
        super(R.drawable.message_plus_file_selector, R.string.down_panel_file);

    }

    @Override
    public void onClick() {
        listFile();
    }

    private void listFile() {
        MessageHistoryActivity.start(getActivity(), NimUIKit.getAccount(), SessionTypeEnum.P2P); // 漫游消息查询
    }

}
