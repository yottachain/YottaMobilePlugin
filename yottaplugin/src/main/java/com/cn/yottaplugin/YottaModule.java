package com.cn.yottaplugin;


import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;

import YottaMobile.YottaMobile;
import io.dcloud.feature.uniapp.annotation.UniJSMethod;
import io.dcloud.feature.uniapp.bridge.UniJSCallback;
import io.dcloud.feature.uniapp.common.UniModule;

public class YottaModule extends UniModule {

    @UniJSMethod(uiThread = true)
    public void register(JSONObject options,UniJSCallback callback){

        String userName = options.getString("userName");
        String privatekey = options.getString("privateKey");
        String snlistDirectory = options.getString("snlistDirectory");

        JSONObject data = new JSONObject();

        if(userName != null && !userName.isEmpty() && privatekey != null && !privatekey.isEmpty()) {

            String result = YottaMobile.register(userName,privatekey,snlistDirectory);
            data.put("data",result);

        }else {
            data.put("code","params errors");
        }

        callback.invoke(data);

    }

    @UniJSMethod(uiThread = true)
    public void listBucket(JSONObject options, UniJSCallback callback){

        String bucketName = options.getString("bucketName");
        String publicKey = options.getString("publicKey");

        JSONObject data = new JSONObject();

        if(bucketName != null && !bucketName.isEmpty() && publicKey != null && !publicKey.isEmpty()) {

            String result = YottaMobile.listObjects(bucketName,publicKey);

            data.put("data",result);
        } else {
            data.put("code","params errors");
        }

        callback.invoke(data);
    }

    @UniJSMethod(uiThread = true)
    public void upload(JSONObject options,UniJSCallback callback) {

        //url为s3服务器中的普通端口+ip，如http://localhost:8080
        String url = options.getString("url");

        //filePath为文件的本地路径
        String filePath = options.getString("filePath");

        String bucketName = options.getString("bucketName");

        String userName = options.getString("userName");

        String privateKey = options.getString("privateKey");

        String snlistDirectory = options.getString("snlistDirectory");

        YottaMobile.uploadObject(url,filePath,bucketName,userName,privateKey,snlistDirectory);

        if(callback!=null) {
            JSONObject data = new JSONObject();
            data.put("code", "success");
            callback.invoke(data);
        }
    }

    @UniJSMethod(uiThread = true)
    public void download(JSONObject options,UniJSCallback callback){

        //url为s3服务器中的普通端口+ip，如http://localhost:8080
        String url = options.getString("url");

        //directory 下载文件保存的目录
        String directory = options.getString("directory");

        String bucketName = options.getString("bucketName");

        String fileName = options.getString("fileName");


        JSONObject data = new JSONObject();
        if (url != null && !url.isEmpty() && directory != null && !directory.isEmpty() && bucketName != null
         && !bucketName.isEmpty() && fileName != null && !fileName.isEmpty()) {
            String result = YottaMobile.downloadObject(url,directory,fileName,bucketName);

            data.put("data",result);
        }

        callback.invoke(data);
    }


//test
    String NAME="name";
    String AGE ="age";

    @JSMethod(uiThread = true)
    public void add(JSONObject options, JSCallback callBack){
        Log.e("TestModule", "成功调用!" );
        String name =options.getString(NAME);
//        long sum = YottaModule.add(11,13);
        long sum = YottaMobile.add(22,3);
        String age = String.valueOf(sum);
//        String age =options.getString(AGE);
        JSONObject data =new JSONObject();
        if (name !=null && !name.isEmpty() && age !=null && !age.isEmpty()){
            int _age =Integer.parseInt(age);
            if (_age<0 || _age>30){
                data.put("code","不合格!");
            }else {
                age=(_age>0 && _age<10) ? "0"+age:age;
                data.put("code","合格:"+"姓名_"+name+",年龄_"+age);
            }
        }else {
            data.put("code","输入无效!");
        }
        callBack.invoke(data);
    }


}
