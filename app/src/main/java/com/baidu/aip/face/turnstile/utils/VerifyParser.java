/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.aip.face.turnstile.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.baidu.aip.face.turnstile.exception.FaceError;
import com.baidu.aip.face.turnstile.model.FaceModel;

public class VerifyParser implements Parser<FaceModel> {
    @Override
    public FaceModel parse(String json) throws FaceError {
        FaceModel faceModel = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray resultArray = jsonObject.optJSONArray("result");
            if (resultArray != null) {
                faceModel = new FaceModel();
                JSONObject faceObject = resultArray.getJSONObject(0);
                faceModel.setUid(faceObject.getString("uid"));
                JSONArray scroeArray = faceObject.optJSONArray("scores");
                if (scroeArray != null) {
                    faceModel.setScore(scroeArray.getDouble(0));
                }
                faceModel.setGroupID(faceObject.getString("group_id"));
                faceModel.setUserInfo(faceObject.getString("user_info"));
            }
            //添加活体值获取 qb_20180721
            JSONArray extInfoArray = jsonObject.optJSONArray("ext_info");
            if (extInfoArray != null) {
                Log.i(getClass().getName(), "line:" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                JSONObject facelivenessObject = extInfoArray.getJSONObject(0);
                if (faceModel != null) {
                    Log.i(getClass().getName(), "line:" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    faceModel.setFaceliveness(facelivenessObject.getString("faceliveness"));
                    faceModel.print();    //打印
                }
            }
            //添加活体值获取 qb_20180721
            JSONObject extInfoObject = jsonObject.optJSONObject("ext_info");
            if (extInfoObject != null) {
                Log.i(getClass().getName(), "line:" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                if (faceModel != null) {
                    Log.i(getClass().getName(), "line:" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    faceModel.setFaceliveness(extInfoObject.getString("faceliveness"));
                    faceModel.print();    //打印
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return faceModel;
    }
}
