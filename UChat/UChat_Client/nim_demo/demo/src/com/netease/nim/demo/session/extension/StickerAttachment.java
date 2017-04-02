package com.netease.nim.demo.session.extension;

import com.alibaba.fastjson.JSONObject;
import com.netease.nim.uikit.common.util.file.FileUtil;

/**
 * Created by AllStar on 2017/3/23.
 */
public class StickerAttachment extends CustomAttachment {

    private final String KEY_CATALOG = "catalog";
    private final String KEY_CHARTLET = "chartlet";

    private String catalog;
    private String chartlet;

    public StickerAttachment() {
        super(CustomAttachmentType.Sticker);
    }

    public StickerAttachment(String catalog, String emotion) {
        this();
        this.catalog = catalog;
        this.chartlet = FileUtil.getFileNameNoEx(emotion);
    }

    @Override
    protected void parseData(JSONObject data) {
        this.catalog = data.getString(KEY_CATALOG);
        this.chartlet = data.getString(KEY_CHARTLET);
    }

    @Override
    protected JSONObject packData() {
        JSONObject data = new JSONObject();
        data.put(KEY_CATALOG, catalog);
        data.put(KEY_CHARTLET, chartlet);
        return data;
    }

    public String getCatalog() {
        return catalog;
    }

    public String getChartlet() {
        return chartlet;
    }
}
