package com.netease.nim.demo.session.viewholder;

import com.netease.nim.demo.session.extension.GuessAttachment;
import com.netease.nim.uikit.common.ui.recyclerview.adapter.BaseMultiItemFetchLoadAdapter;
import com.netease.nim.uikit.session.viewholder.MsgViewHolderText;

/**
 * Created by AllStar on 2017/3/22.
 */
public class MsgViewHolderGuess extends MsgViewHolderText {

    public MsgViewHolderGuess(BaseMultiItemFetchLoadAdapter adapter) {
        super(adapter);
    }

    @Override
    protected String getDisplayText() {
        GuessAttachment attachment = (GuessAttachment) message.getAttachment();

        return attachment.getValue().getDesc() + "!";
    }
}
