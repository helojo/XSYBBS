package com.wsg.kotlin.bean

import cn.bmob.v3.BmobObject


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.bean
 *  文件名:   Comment
 *  创建者:   wsg
 *  创建时间: 2019/5/28 20:24
 *  描述:     评论实体
 */
 
data class Comment(var noteid : String,
                   var userid : String,
                   var username : String,
                   var content : String) : BmobObject()