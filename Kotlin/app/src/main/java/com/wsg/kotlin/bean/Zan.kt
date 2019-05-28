package com.wsg.kotlin.bean

import cn.bmob.v3.BmobObject


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.bean
 *  文件名:   Zan
 *  创建者:   wsg
 *  创建时间: 2019/5/28 20:27
 *  描述:     点赞实体
 */

data class Zan(var noteid: String,
               var userid: String,
               var username: String,
               var status: Boolean,
               var notename: String) : BmobObject() {


}