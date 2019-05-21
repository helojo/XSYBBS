package com.wsg.kotlin.bean

import cn.bmob.v3.BmobUser


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.bean
 *  文件名:   User
 *  创建者:   wsg
 *  创建时间: 2019/5/13 11:40
 *  描述:     data 用户实体类
 */
 
data class User(var age : Int = 20) :BmobUser()