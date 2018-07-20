/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.aip.face.turnstile.model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class HttpParams implements RequestParams {

    private Map<String, String> params = new HashMap<>();
    private Map<String, File> fileMap = new HashMap<>();


    @Override
    public Map<String, File> getFileParams() {
        return fileMap;
    }

    @Override
    public Map<String, String> getStringParams() {
        return params;
    }

    public void setUid(String uid) {
        putParam("uid", uid);
    }


    public void setGroupId(String groupId) {

        putParam("group_id", groupId);
    }

    public void setBase64Img(String base64Img) {
        putParam("image", base64Img);
    }

    public void setUserInfo(String userInfo) {
        putParam("user_info", userInfo);
    }

    //设置返回活体阈值选项 qb_20180721
    public void setExtFileds(String extFileds) {
        putParam("ext_fields", extFileds);
    }

    public void setToken(String token) {
        putParam("access_token", token);
    }

    public void setImageFile(File imageFile) {
        fileMap.put(imageFile.getName(), imageFile);
    }

    public void putParam(String key, String value) {
        if (value != null) {
            params.put(key, value);
        } else {
            params.remove(key);
        }
    }

    private void putParam(String key, boolean value) {
        if (value) {
            putParam(key, "true");
        } else {
            putParam(key, "false");
        }
    }
}