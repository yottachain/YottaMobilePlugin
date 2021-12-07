package com.cn.testplugin;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import YottaMobile.YottaMobile;


public class TestModule extends WXModule {

    String NAME="name";
    String AGE ="age";

    @JSMethod(uiThread = true)
    public void testText(JSONObject options, JSCallback callBack){
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


//    public void sum(JSONObject options,JSCallback callback) {
//        SimpleExample.add()
//    }


}
