package com.netease.nim.demo.session.extension;

import com.alibaba.fastjson.JSONObject;
import com.netease.nimlib.sdk.msg.attachment.MsgAttachment;

/**
 * Created by AllStar on 2017/3/22.
 */
public abstract class CustomAttachment implements MsgAttachment {

    protected int type;

    CustomAttachment(int type) {
        this.type = type;
    }

    public void fromJson(JSONObject data) {
        if (data != null) {
            parseData(data);
        }
    }

    @Override
    public String toJson(boolean send) {
        return CustomAttachParser.packData(type, packData());
    }

    public int getType() {
        return type;
    }

    protected abstract void parseData(JSONObject data);
    protected abstract JSONObject packData();
}
