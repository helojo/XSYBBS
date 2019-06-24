package com.wsg.kotlin.util;

/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.Util
 *  文件名:   Test
 *  创建者:   wsg
 *  创建时间: 2019/5/21 11:31
 *  描述:     java ->kotlin 学习kotlin
 */


import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import com.wsg.kotlin.bean.User;

public class Test {
    public void getUser(String id){
        BmobQuery q = new BmobQuery<User>();
        q.getObject(id, new QueryListener() {
            @Override
            public void done(Object o, BmobException e) {

            }
        });
    }
}
