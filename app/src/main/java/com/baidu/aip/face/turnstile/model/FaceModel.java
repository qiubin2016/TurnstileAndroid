/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.aip.face.turnstile.model;

import android.util.Log;

public class FaceModel {
    private static final double FACELIVENESS_THRESHOLD = 0.393241;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    //获取活体值 qb_20180721
    public String getFaceliveness() {
        return faceliveness;
    }

    //设置活体值 qb_20180721
    public void setFaceliveness(String faceliveness) {
        this.faceliveness = faceliveness;
    }

    public void print() {
        Log.i(getClass().getName(), "line:" + Thread.currentThread().getStackTrace()[2].getLineNumber()
        + ",uid:" + uid
        + ",score:" + score
        + ",groupID:" + groupID
        + ",uderInfo:" + userInfo
        + ",faceliveness:" + faceliveness);
    }

    private String uid;
    private double score;
    private String groupID;
    private String userInfo;

    /*活体检测分数，单帧活体检测参考阈值0.393241，超过此分值以上则可认为是活体。注意：活体检测接口主要用于判断是否为二次翻拍，
    需要限制用户为当场拍照获取图片；推荐配合客户端SDK有动作校验活体使用*/
    private String faceliveness;     //活体值 qb_20180721
}
