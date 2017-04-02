package com.netease.nim.uikit.session.module.input;

import android.text.Editable;
import android.widget.EditText;

/**
 * Created by AllStar on 2017/3/11.
 */

public interface MessageEditWatcher {

    void afterTextChanged(Editable editable, int start, int count);

}
