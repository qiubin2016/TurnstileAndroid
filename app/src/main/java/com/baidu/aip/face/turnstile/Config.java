/*
* Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
*/
package com.baidu.aip.face.turnstile;


public class Config {


//    // 为了apiKey,secretKey为您调用百度人脸在线接口的，如注册，比对等。
//    // 为了的安全，建议放在您的服务端，端把人脸传给服务器，在服务端端
//    // license为调用sdk的人脸检测功能使用，人脸识别 = 人脸检测（sdk功能）  + 人脸比对（服务端api）
    public static String apiKey = "kuPFAOe2wWVNOAtSDjwKx2Q7";//"ABtPbk4SD2rhti5XVy8h37CG";  //替换为你的apiKey(ak);
    public static String secretKey = "CpSIWfXfF9te0NpOwk3syfoT7lsmWRQ4";//"0HkgT5iffoX8DAminQZPnR81SowIjS6z";  //替换为你的secretKey(sk);
    public static String licenseID = "itlong-face-android";
    public static String licenseFileName = "idl-license.face-android";

    /*
     * <p>
     * 每个开发者账号只能创建一个人脸库；groupID用于标识人脸库
     * <p>
     * 人脸识别 接口 https://aip.baidubce.com/rest/2.0/face/v2/identify
     * 人脸注册 接口 https://aip.baidubce.com/rest/2.0/face/v2/faceset/user/add
     */
    public static String groupID = "group1";  //"954A883D8E85402A9C1577BA4E4F501E";  //替换为groupID;

}
