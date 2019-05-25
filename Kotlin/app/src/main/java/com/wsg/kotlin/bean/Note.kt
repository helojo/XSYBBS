package com.wsg.kotlin.bean

import cn.bmob.v3.BmobObject


/*
 *  项目名:  Kotlin
 *  包名:    com.wsg.kotlin.bean
 *  文件名:   Note
 *  创建者:   wsg
 *  创建时间: 2019/5/25 15:02
 *  描述:     Note 实体类
 */

data class Note(var userid : String ,
                var image : String ,
                var typeid : String ,
                var top : Int,
                var title : String,
                var content : String,
                var zancount : Int,
                var replaycount : Int
                ) : BmobObject(){

}