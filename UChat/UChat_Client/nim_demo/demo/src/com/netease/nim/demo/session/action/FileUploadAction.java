package com.netease.nim.demo.session.action;

import android.content.Intent;

import com.netease.nim.demo.R;
import com.netease.nim.demo.file.browser.FileBrowserActivity;
import com.netease.nim.uikit.session.constant.RequestCode;
import com.netease.nim.uikit.session.actions.BaseAction;
import com.netease.nim.uikit.uinfo.UserInfoHelper;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.model.IMMessage;

import java.io.File;

/**
 * Created by AllStar on 2017/3/19.
 */
public class FileUploadAction extends BaseAction {

    public FileUploadAction() {
        super(R.drawable.message_plus_file_selector, R.string.up_panel_file);

    }

    /**
     * **********************文件************************
     */
    private void chooseFile() {
        FileBrowserActivity.startActivityForResult(getActivity(), makeRequestCode(RequestCode.GET_LOCAL_FILE));
    }

    @Override
    public void onClick() {
        chooseFile();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RequestCode.GET_LOCAL_FILE) {
            String path = data.getStringExtra(FileBrowserActivity.EXTRA_DATA_PATH);
            File file = new File(path);
            IMMessage message = MessageBuilder.createFileMessage(getAccount(), getSessionType(), file, file.getName());
            sendMessage(message);
        }
    }
}
