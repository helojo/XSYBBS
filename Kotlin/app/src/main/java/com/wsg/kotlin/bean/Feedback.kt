package com.wsg.kotlin.bean

import cn.bmob.v3.BmobObject


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.bean
 *  文件名:   Feedback
 *  创建者:   wsg
 *  创建时间: 2019/5/25 21:11
 *  描述:     反馈实体
 */

data class Feedback(var Content : String ,var deviceType : String ,var userid : String) : BmobObject()